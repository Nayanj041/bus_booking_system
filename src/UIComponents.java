import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JCalendar;
import javax.swing.table.DefaultTableModel;

public class UIComponents {
    private JFrame parentFrame;
    private JPanel mainPanel;
    private JTextField userNameField;
    private JComboBox<String> sourceComboBox;
    private JComboBox<String> destinationComboBox;
    private JTextField passengersField;
    private JComboBox<String> timeComboBox;
    private JCalendar calendar;
    private JTextArea summaryArea;
    private JTextField amountField;
    private JButton submitButton;
    private JButton resetButton;
    private JButton bookButton;
    private JButton viewButton;
    private JButton deleteButton;
    private JTable recordsTable;
    private JScrollPane tableScrollPane;

    public UIComponents(JFrame parent) {
        this.parentFrame = parent;
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // User Name
        addLabel("Name of User:", gbc, 0, 0);
        userNameField = new JTextField(20);
        addComponent(userNameField, gbc, 1, 0, 2);

        // Source
        addLabel("Source:", gbc, 0, 1);
        sourceComboBox = new JComboBox<>(new String[]{"Pune", "Mumbai", "Nashik", "Delhi", "Nagpur", "Ahmednagar"});
        addComponent(sourceComboBox, gbc, 2, 1, 2);

        // Destination
        addLabel("Destination:", gbc, 0, 2);
        destinationComboBox = new JComboBox<>(new String[]{"Mumbai", "Pune", "Nashik", "Delhi", "Nagpur", "Ahmednagar"});
        addComponent(destinationComboBox, gbc, 2, 2, 2);

        // Number of Passengers
        addLabel("No. of Passengers:", gbc, 0, 3);
        passengersField = new JTextField(5);
        addComponent(passengersField, gbc, 2, 3, 2);

        // Time
        addLabel("Time:", gbc, 0, 4);
        timeComboBox = new JComboBox<>(new String[]{"6:00 am", "8:00 am", "12:00 pm", "2:00 pm", "5:00 pm", "7:00 pm"});
        addComponent(timeComboBox, gbc, 2, 4, 2);

        // Departure Date
        addLabel("Departure Date:", gbc, 0, 5);
        calendar = new JCalendar();
        addComponent(calendar, gbc, 1, 6, 2);

        // Summary Area
        addLabel("Ticket Summary:", gbc, 3, 0);
        summaryArea = new JTextArea(10, 30);
        summaryArea.setEditable(false);
        JScrollPane summaryScrollPane = new JScrollPane(summaryArea);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(summaryScrollPane, gbc);

        // Payable Amount
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        addLabel("Payable Amount:", gbc, 0, 7);
        amountField = new JTextField(10);
        amountField.setEditable(false);
        addComponent(amountField, gbc, 1, 7, 2);

        // Buttons
        submitButton = new JButton("SUBMIT");
        addComponent(submitButton, gbc, 0, 8, 1);

        resetButton = new JButton("RESET");
        addComponent(resetButton, gbc, 1, 8, 1);

        bookButton = new JButton("BOOK");
        addComponent(bookButton, gbc, 2, 8, 1);

        viewButton = new JButton("VIEW RECORDS");
        addComponent(viewButton, gbc, 3, 8, 1);

        deleteButton = new JButton("DELETE RECORD");
        addComponent(deleteButton, gbc, 4, 8, 1);

        // Records Table
        recordsTable = new JTable(new DefaultTableModel());
        recordsTable.setPreferredScrollableViewportSize(new Dimension(600, 200)); // Set table size
        tableScrollPane = new JScrollPane(recordsTable);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        mainPanel.add(tableScrollPane, gbc);
    }

    private void addLabel(String text, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel(text), gbc);
    }

    private void addComponent(JComponent component, GridBagConstraints gbc, int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(component, gbc);
    }

    // Getter methods for all UI components
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public JComboBox<String> getSourceComboBox() {
        return sourceComboBox;
    }

    public JComboBox<String> getDestinationComboBox() {
        return destinationComboBox;
    }

    public JTextField getPassengersField() {
        return passengersField;
    }

    public JComboBox<String> getTimeComboBox() {
        return timeComboBox;
    }

    public JCalendar getCalendar() {
        return calendar;
    }

    public JTextArea getSummaryArea() {
        return summaryArea;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getBookButton() {
        return bookButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JTable getRecordsTable() {
        return recordsTable;
    }
}

