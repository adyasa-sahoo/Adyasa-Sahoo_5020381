package weektwo;
import java.sql.*;

public class BankFunctions {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Change as per your DB details
        String user = "username"; // Change as per your DB username
        String password = "password"; // Change as per your DB password

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Call the functions here
            System.out.println("Age: " + callCalculateAge(conn, Date.valueOf("1985-05-15")));
            System.out.println("Monthly Installment: " + callCalculateMonthlyInstallment(conn, 5000, 5, 5));
            System.out.println("Has Sufficient Balance: " + callHasSufficientBalance(conn, 1, 500));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int callCalculateAge(Connection conn, Date dob) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{? = CALL CalculateAge(?)}")) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setDate(2, dob);
            cstmt.execute();
            return cstmt.getInt(1);
        }
    }

    private static double callCalculateMonthlyInstallment(Connection conn, double loanAmount, double interestRate, int durationYears) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{? = CALL CalculateMonthlyInstallment(?, ?, ?)}")) {
            cstmt.registerOutParameter(1, Types.DOUBLE);
            cstmt.setDouble(2, loanAmount);
            cstmt.setDouble(3, interestRate);
            cstmt.setInt(4, durationYears);
            cstmt.execute();
            return cstmt.getDouble(1);
        }
    }

    private static boolean callHasSufficientBalance(Connection conn, int accountId, double amount) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{? = CALL HasSufficientBalance(?, ?)}")) {
            cstmt.registerOutParameter(1, Types.BOOLEAN);
            cstmt.setInt(2, accountId);
            cstmt.setDouble(3, amount);
            cstmt.execute();
            return cstmt.getBoolean(1);
        }
    }
}
