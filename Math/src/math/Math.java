package math;

import java.util.function.Function;

public class Math {
    
    public static double integrate(Function<Double, Double> f, int a, int b, int steps){
        /**
         * Calculates area under function f between intervals [a,b]
         * dividing the interval in 'steps' areas
         * Using trapeze method
         */
        
        double h        = Double.valueOf(b -a)/steps,
               result   = 0;
        
        for(double k = a; k < steps; k++){
            result += 2*f.apply(a + k*h);
        }
        
        result += (f.apply(Double.valueOf(a)) + f.apply(Double.valueOf(b)));
        result *= h/2;
        
        return result;
    }
    
//    public static void main(String[] args) {
//        Function<Double, Double> f = x -> 3*x;
//        
//        System.out.println(integrate(f, 0, 2, 6));
//    }
}