package montecarlo;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

public class Test{
    public static void main(String[] args){
        Board b = new Board(500);
        new Pi().aproximate(b.panel, 100000000);
    }
}
