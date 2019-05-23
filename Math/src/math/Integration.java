package math;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

import java.util.function.Function;

public class Integration {

    public static double integrate(Function<Double, Double> f, double a, double b, int steps){
        /**
         * Calculates area under function f between intervals [a,b]
         * dividing the interval in 'steps' areas
         * Using trapeze method
         */

        double h        = (b - a) / steps,
               result   = 0;

        for(double k = a; k < steps; k++){
            result += 2 * f.apply(a + k * h);
        }

        result += (f.apply(a) + f.apply(b));
        result *= h / 2;

        return result;
    }

    public static void main(String[] args) {
        /**
         * Example of how it works
         */

        Function<Double, Double> f = x -> Math.sin(x);

        System.out.println(integrate(f, 0, Math.PI, 100));
    }
}
