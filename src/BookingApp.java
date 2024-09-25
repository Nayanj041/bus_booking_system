import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingApp extends JFrame {
    private DatabaseManager dbManager;
    private UIComponents uiComponents;

    public BookingApp() {
        initComponents();
        Date date = new Date();
        uiComponents.getCalendar().getDayChooser().setMinSelectableDate(date);
        dbManager = new DatabaseManager();
        dbManager.connect();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bus Booking System");
        uiComponents = new UIComponents(this);
        add(uiComponents.getMainPanel());

        uiComponents.getSubmitButton().addActionListener(this::submitButtonActionPerformed);
        uiComponents.getResetButton().addActionListener(this::resetButtonActionPerformed);
        uiComponents.getBookButton().addActionListener(this::bookButtonActionPerformed);
        uiComponents.getViewButton().addActionListener(this::viewButtonActionPerformed);
        uiComponents.getDeleteButton().addActionListener(this::deleteButtonActionPerformed);

        pack();
        setLocationRelativeTo(null);
    }

    private void submitButtonActionPerformed(ActionEvent evt) {
        String userName = uiComponents.getUserNameField().getText();
        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter User Name");
            return;
        }

        int passengers, pricePerPassenger, totalPrice;
        try {
            passengers = Integer.parseInt(uiComponents.getPassengersField().getText());
            pricePerPassenger = 200;
            totalPrice = passengers * pricePerPassenger;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of passengers");
            return;
        }

        String source = (String) uiComponents.getSourceComboBox().getSelectedItem();
        String destination = (String) uiComponents.getDestinationComboBox().getSelectedItem();
        String time = (String) uiComponents.getTimeComboBox().getSelectedItem();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String journeyDate = dateFormat.format(uiComponents.getCalendar().getDate());

        if (source.equals(destination)) {
            JOptionPane.showMessageDialog(this, "Source and destination cannot be the same");
            resetFields();
            return;
        }

        uiComponents.getAmountField().setText(Integer.toString(totalPrice));
        String summary = String.format("""
            *************************************
            Name of User: %s
            From: %s
            To: %s
            No of Passengers: %d
            Time: %s
            Date of Journey: %s
            Payable Amount: %d
            *************************************""",
                userName, source, destination, passengers, time, journeyDate, totalPrice);
        uiComponents.getSummaryArea().setText(summary);
    }

    private void resetButtonActionPerformed(ActionEvent evt) {
        resetFields();
    }

    private void bookButtonActionPerformed(ActionEvent evt) {
        try {
            String userName = uiComponents.getUserNameField().getText();
            String source = (String) uiComponents.getSourceComboBox().getSelectedItem();
            String destination = (String) uiComponents.getDestinationComboBox().getSelectedItem();
            String passengers = uiComponents.getPassengersField().getText();
            String time = (String) uiComponents.getTimeComboBox().getSelectedItem();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String journeyDate = dateFormat.format(uiComponents.getCalendar().getDate());
            String amount = uiComponents.getAmountField().getText();

            String sql = "INSERT INTO book (User_Name, S_Source, destination, No_of_passenger, Time, DOJ, Paid_amt) VALUES (?, ?, ?, ?, ?, ?, ?)";
            int rowsAffected = dbManager.executeUpdate(sql, userName, source, destination, passengers, time, journeyDate, amount);

            if (rowsAffected == 1) {
                try (FileWriter writer = new FileWriter("bbsystem.txt", true)) {
                    writer.append(uiComponents.getSummaryArea().getText());
                    writer.write(System.getProperty("line.separator"));
                }
                JOptionPane.showMessageDialog(this, "Booking Successful");
                resetFields();
            } else {
                JOptionPane.showMessageDialog(this, "Booking Failed");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(BookingApp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void viewButtonActionPerformed(ActionEvent evt) {
        try {
            ResultSet resultSet = dbManager.executeQuery("SELECT * FROM book");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            DefaultTableModel model = (DefaultTableModel) uiComponents.getRecordsTable().getModel();
            model.setRowCount(0);

            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }
            model.setColumnIdentifiers(columnNames);

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingApp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error viewing records: " + ex.getMessage());
        }
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
        int selectedRow = uiComponents.getRecordsTable().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = (int) uiComponents.getRecordsTable().getValueAt(selectedRow, 0);
                String sql = "DELETE FROM book WHERE id = ?";
                int rowsAffected = dbManager.executeUpdate(sql, id);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Record deleted successfully");
                    viewButtonActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete record");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingApp.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error deleting record: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record to delete");
        }
    }

    private void resetFields() {
        uiComponents.getUserNameField().setText("");
        uiComponents.getPassengersField().setText("");
        uiComponents.getSummaryArea().setText("");
        uiComponents.getAmountField().setText("");
        uiComponents.getSourceComboBox().setSelectedIndex(0);
        uiComponents.getDestinationComboBox().setSelectedIndex(0);
        uiComponents.getTimeComboBox().setSelectedIndex(0);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new BookingApp().setVisible(true));
    }
}
