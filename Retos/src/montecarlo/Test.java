package montecarlo;

public class Test{
    public static void main(String[] args){
        Board b = new Board(1000);
        new Pi().aproximate(b.panel, 100000000);
    }    
}
