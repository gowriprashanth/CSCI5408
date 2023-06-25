import java.sql.*;

public class Main {

public static void main(String[] args) {
try {

long startTime = System.currentTimeMillis();

Connection remoteConnection = DriverManager.getConnection("jdbc:mysql://34.69.90.33/lab-4",
"root", "password123");

long endTime = System.currentTimeMillis();
System.out.println("time: " + (endTime - startTime) + "ms");

startTime = System.currentTimeMillis();

Connection localConnection = DriverManager.getConnection("jdbc:mysql://localhost/local_ecomm",
"root", "password123");

endTime = System.currentTimeMillis();

System.out.println("Execution time: " + (endTime - startTime) + "ms");

// Create an order in the Order_info table
startTime = System.currentTimeMillis();

// Perform the query to create an order
endTime = System.currentTimeMillis();
System.out.println("Created an order in local database. Execution time: " + (endTime - startTime) + "ms");

// Update the available quantity in the remote Inventory table
startTime = System.currentTimeMillis();

// Perform the query to update quantity
endTime = System.currentTimeMillis();
System.out.println("Updated quantity in remote database. Execution time: " + (endTime - startTime) + "ms");

remoteConnection.close();
localConnection.close();
} catch (SQLException e) {
e.printStackTrace();
}
}
}