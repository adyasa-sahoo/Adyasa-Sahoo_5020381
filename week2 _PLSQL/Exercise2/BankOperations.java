package weektwo;
import java.sql.*;

public class BankOperations {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Change as per your DB details
        String user = "username"; // Change as per your DB username
        String password = "password"; // Change as per your DB password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Call the stored procedures here
            callSafeTransferFunds(conn, 1, 2, 500);
            callUpdateSalary(conn, 1, 10);
            callAddNewCustomer(conn, 3, "Charlie Brown", Date.valueOf("1980-08-15"), 2000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void callSafeTransferFunds(Connection conn, int fromAccountId, int toAccountId, double amount) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{CALL SafeTransferFunds(?, ?, ?)}")) {
            cstmt.setInt(1, fromAccountId);
            cstmt.setInt(2, toAccountId);
            cstmt.setDouble(3, amount);
            cstmt.execute();
        }
    }

    private static void callUpdateSalary(Connection conn, int employeeId, double percentage) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{CALL UpdateSalary(?, ?)}")) {
            cstmt.setInt(1, employeeId);
            cstmt.setDouble(2, percentage);
            cstmt.execute();
        }
    }

    private static void callAddNewCustomer(Connection conn, int customerId, String name, Date dob, double balance) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{CALL AddNewCustomer(?, ?, ?, ?)}")) {
            cstmt.setInt(1, customerId);
            cstmt.setString(2, name);
            cstmt.setDate(3, dob);
            cstmt.setDouble(4, balance);
            cstmt.execute();
        }
    }
}
