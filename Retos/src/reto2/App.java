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
    
//    public void run(){
//        Vote v;
//        int vote;
//        
//        //Prompt login dialog
//        String session = new Login("src/files/shadow.txt").prompt();
//        do{
//            //Select category
//            String category = 
//                  new CategoryChooser(CategoryChooser.DEFAULT_SOURCE).prompt();
//            
//            //Let user vote in the category chosen
//            v = new Vote(category);
//            vote = v.prompt();
//        }while(vote != -1 && !v.process(vote, session));
//    }
}
