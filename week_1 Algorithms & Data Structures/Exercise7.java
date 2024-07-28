package weekone;

public class Exercise7 {


    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
      
        if (periods == 0) {
            return presentValue;
        }
       
        return calculateFutureValue(presentValue, growthRate, periods - 1) * (1 + growthRate);
    }

    public static void main(String[] args) {
        double presentValue = 1000; 
        double growthRate = 0.05; 
        int periods = 10; 

        double futureValue = calculateFutureValue(presentValue, growthRate, periods);
        System.out.println("Future Value after " + periods + " periods: " + futureValue);
    }
}
