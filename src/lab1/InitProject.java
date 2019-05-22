package lab1;

public class InitProject implements IArithmeticsDiff, IArithmeticsMult, IArithmeticsDiv, IArithmeticsAdd {

    public static void main(String[] args) {
        System.out.println("ZPI_2019_Dzienni_IO2_1 \n developer - pawo97");
        System.out.println("ZPI_2019_Dzienni_IO2_1 \n operations - JHinge");
        System.out.println("tester - Marax97");
        System.out.println("developer - dominik3131");
    }

    @Override
    public double Difference(double A, double B) {
        return A - B;
    }

    @Override
    public double multiplication(double a, double b) {
        return a * b;
    }

    @Override
    public double division(double A, double B) {
        try {
            return A / B;
        } catch (ArithmeticException e) {
            System.err.println("You can't divide by 0");
        }
        return 0;
    }

    //implementation of addition method
    @Override
    public double addition(double a, double b) {
        return a + b;
    }

}
