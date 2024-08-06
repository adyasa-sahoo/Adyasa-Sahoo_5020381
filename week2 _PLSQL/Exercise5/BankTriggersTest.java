package weektwo;
import java.sql.*;

public class BankTriggersTest {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Change as per your DB details
        String user = "username"; // Change as per your DB username
        String password = "password"; // Change as per your DB password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Test the UpdateCustomerLastModified trigger
            testUpdateCustomerLastModified(conn);

            // Test the LogTransaction trigger
            testLogTransaction(conn);

            // Test the CheckTransactionRules trigger
            testCheckTransactionRules(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testUpdateCustomerLastModified(Connection conn) throws SQLException {
        String updateCustomerSQL = "UPDATE Customers SET Name = ? WHERE CustomerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateCustomerSQL)) {
            pstmt.setString(1, "Updated Name");
            pstmt.setInt(2, 1);
            pstmt.executeUpdate();
        }

        String checkCustomerSQL = "SELECT LastModified FROM Customers WHERE CustomerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(checkCustomerSQL)) {
            pstmt.setInt(1, 1);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Last Modified Date: " + rs.getTimestamp("LastModified"));
            }
        }
    }

    private static void testLogTransaction(Connection conn) throws SQLException {
        String insertTransactionSQL = "INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertTransactionSQL)) {
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 1);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setDouble(4, 100);
            pstmt.setString(5, "Deposit");
            pstmt.executeUpdate();
        }

        String checkAuditLogSQL = "SELECT * FROM AuditLog WHERE TransactionID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(checkAuditLogSQL)) {
            pstmt.setInt(1, 1);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Audit Log Entry: TransactionID = " + rs.getInt("TransactionID"));
            }
        }
    }

    private static void testCheckTransactionRules(Connection conn) throws SQLException {
        String insertTransactionSQL = "INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertTransactionSQL)) {
            pstmt.setInt(1, 2);
            pstmt.setInt(2, 1);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setDouble(4, -100); // This should trigger an error
            pstmt.setString(5, "Deposit");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Expected Error: " + e.getMessage());
        }

        try (PreparedStatement pstmt = conn.prepareStatement(insertTransactionSQL)) {
            pstmt.setInt(1, 3);
            pstmt.setInt(2, 1);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setDouble(4, 5000); // Assuming balance is less than 5000
            pstmt.setString(5, "Withdrawal");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Expected Error: " + e.getMessage());
        }
    }
}
