//Simple program that uses recursion to raise a number to a given exponent

public class Power {

    public static double power(double base, int exponent){

        if(exponent == 0){
            return 1;
        }
        else if(exponent < 0){
            return 1.0/power(base, exponent*-1);
        }
        else{
            return base * power(base, exponent-1);
        }
    }



    public static void main(String[] args) {
        System.out.println(power(2, -5));
    }
}
