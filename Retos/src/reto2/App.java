package reto2;

public class App {
    public static void main(String[] args){
        new App().simple();
    }
    
    public void simple(){
        int vote;
        
        do{ 
            Vote v =
            new Vote(new CategoryChooser(CategoryChooser.CATEGORIES_FILE).prompt());
            
            v.showInfo(vote = v.prompt());
        }while(vote != -1);
    }
}
