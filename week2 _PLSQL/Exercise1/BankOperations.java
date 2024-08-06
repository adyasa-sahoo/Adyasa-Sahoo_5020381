package weektwo;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class BankOperations {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Change as per your DB details
        String user = "system"; // Change as per your DB username
        String password = "ady23"; // Change as per your DB password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            applyInterestRateDiscount(conn);
            promoteVIPCustomers(conn);
            sendLoanReminders(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void applyInterestRateDiscount(Connection conn) throws SQLException {
        String selectCustomers = "SELECT CustomerID, DOB FROM Customers";
        String selectInterestRate = "SELECT InterestRate FROM Loans WHERE CustomerID = ?";
        String updateInterestRate = "UPDATE Loans SET InterestRate = ? WHERE CustomerID = ?";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectCustomers);
             PreparedStatement psSelectInterest = conn.prepareStatement(selectInterestRate);
             PreparedStatement psUpdateInterest = conn.prepareStatement(updateInterestRate)) {

            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                LocalDate dob = rs.getDate("DOB").toLocalDate();
                int age = Period.between(dob, LocalDate.now()).getYears();

                if (age > 60) {
                    psSelectInterest.setInt(1, customerId);
                    try (ResultSet rsInterest = psSelectInterest.executeQuery()) {
                        if (rsInterest.next()) {
                            double interestRate = rsInterest.getDouble("InterestRate");
                            double newInterestRate = interestRate - 1;

                            psUpdateInterest.setDouble(1, newInterestRate);
                            psUpdateInterest.setInt(2, customerId);
                            psUpdateInterest.executeUpdate();

                            System.out.println("Updated interest rate for CustomerID " + customerId + " to " + newInterestRate + "%");
                        }
                    }
                }
            }
        }
    }

    private static void promoteVIPCustomers(Connection conn) throws SQLException {
        String selectCustomers = "SELECT CustomerID, Balance FROM Customers";
        String updateVIPStatus = "UPDATE Customers SET IsVIP = TRUE WHERE CustomerID = ?";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectCustomers);
             PreparedStatement psUpdateVIP = conn.prepareStatement(updateVIPStatus)) {

            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                double balance = rs.getDouble("Balance");

                if (balance > 10000) {
                    psUpdateVIP.setInt(1, customerId);
                    psUpdateVIP.executeUpdate();

                    System.out.println("CustomerID " + customerId + " is now a VIP.");
                }
            }
        }
    }

    private static void sendLoanReminders(Connection conn) throws SQLException {
        String selectLoans = "SELECT LoanID, CustomerID, EndDate FROM Loans WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30";
        String selectCustomerName = "SELECT Name FROM Customers WHERE CustomerID = ?";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectLoans);
             PreparedStatement psSelectName = conn.prepareStatement(selectCustomerName)) {

            while (rs.next()) {
                int loanId = rs.getInt("LoanID");
                int customerId = rs.getInt("CustomerID");
                Date dueDate = rs.getDate("EndDate");

                psSelectName.setInt(1, customerId);
                try (ResultSet rsName = psSelectName.executeQuery()) {
                    if (rsName.next()) {
                        String customerName = rsName.getString("Name");

                        System.out.println("Reminder: Loan " + loanId + " for customer " + customerName + " is due on " + dueDate.toString());
                    }
                }
            }
        }
    }
}
