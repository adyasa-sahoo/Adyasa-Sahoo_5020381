package weektwo;
import java.sql.*;

public class BankOperations {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Change as per your DB details
        String user = "username"; // Change as per your DB username
        String password = "password"; // Change as per your DB password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Call the stored procedures here
            callProcessMonthlyInterest(conn);
            callUpdateEmployeeBonus(conn, "IT", 5);
            callTransferFunds(conn, 1, 2, 500);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void callProcessMonthlyInterest(Connection conn) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{CALL ProcessMonthlyInterest()}")) {
            cstmt.execute();
        }
    }

    private static void callUpdateEmployeeBonus(Connection conn, String department, double bonusPercentage) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{CALL UpdateEmployeeBonus(?, ?)}")) {
            cstmt.setString(1, department);
            cstmt.setDouble(2, bonusPercentage);
            cstmt.execute();
        }
    }

    private static void callTransferFunds(Connection conn, int fromAccountId, int toAccountId, double amount) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{CALL TransferFunds(?, ?, ?)}")) {
            cstmt.setInt(1, fromAccountId);
            cstmt.setInt(2, toAccountId);
            cstmt.setDouble(3, amount);
            cstmt.execute();
        }
    }
}
