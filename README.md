<h1>Introduction:</h1>
<p>In this article, we’ll show how to build a Bus Ticket Booking System using the Java Swing library and JDBC. This bus ticket booking system allows you to book a bus ticket easily is a graphical user interface (GUI) application. This system is built with the Java Swing library and JDBC which offers a set of components that can be used to build a Graphical user interface(GUI). This system has an easy and simple design with buttons for ticket booking. So grab a seat, pour a cup of coffee, and start booking tickets with the bus Ticket Booking system!</p>

<h1>Explanation:</h1>
<p>This Bus Ticket Booking System is a Graphical User Interface that allows you to easily book bus tickets. Users may very quickly book a ticket using the system’s simple design. The Bus Ticket Booking System has a modern and simple design with buttons like submit, Reset, and book making it suitable for a wide range of users, from simply booking bus tickets quickly. The database stores the data when users book the tickets. The system stores the data in a MySQL database.

The user may book tickets using the Book button, reset details using the reset button and submit details and view the payable amount using submit button. The system will quickly and accurately calculate the payable amount based on the number of passengers.

The Java Swing library which provides a flexible set of components is used to build the Bus Ticket Booking System. The system makes use of Swing components such as Jbutton, JTextField, JLabel, JComboBox, JCalender, and JTextArea among many other Swing components. Button event is handled by the ActionListener interface and button events are defined using ActionListener’s actionPerformed() method. The data of booked tickets are inserted into the database using the preparedStatement() method. </p>

<h1>Methods used in the project:</h1>

<h3>connect():</h3>

<p>This method is used to make a connection between the Java source code and the MySQL database. The Driver manager class and connection class are used in this method to connect the MySQL database. The Database ‘booking’ is created in MySQL.</p>

<h3>actionPerformed():</h3>

<p>The action or events of the buttons are handled using this method. Buttons in the system like Submit, reset and the book is handled using this method. This method describes how the component should behave after the button is clicked.</p>

<h3>executeUpdate():</h3>

<p>The number of rows that the SQL statement execution has an impact on is returned by this function. The system made use of this method to check whether the data of booked tickets is stored or not.</p>

<h3>preparedStatement():</h3>

<p>preparedStatement() method belongs to the java.sql package. This method is used to store the SQL statement that can be executed multiple times when it is needed.</p>

<h1>Screensort</h1>


![img1](https://github.com/user-attachments/assets/991d633b-7356-4f3a-b31a-811c157c8459)
![img2](https://github.com/user-attachments/assets/9f221197-7201-400f-ac58-b940653fecee)
![img3](https://github.com/user-attachments/assets/88f4ed7c-3686-4d4c-bfca-f2a0b9b87085)
![img4](https://github.com/user-attachments/assets/b5d5bb8e-a048-420e-8499-2b09e3196c28)
![img5](https://github.com/user-attachments/assets/cd4eeaff-e306-4462-8854-e5e4e4d81dc8)
