package montecarlo;

public class Test{
    public static void main(String[] args){
        Board b = new Board(500);
        new Pi().aproximate(b.panel, 100000000);
    }    
}
