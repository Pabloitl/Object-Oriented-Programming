package Oscars;

public class App {
    public static void main(String[] args){
        new App().run();
    }
    
    public void run(){
        Vote v;
        int vote;
        
        //Prompt login dialog
        String session = new Login("src/Oscars/files/shadow.txt").prompt();
        do{
            //Select category
            String category = new CategoryChooser().prompt();
            
            //If no category chosen, exit program
            if(category == null) System.exit(0);
            
            //Let user vote in the category chosen
            v = new Vote(category);
            vote = v.prompt();
        }while(vote != -1 && !v.process(vote, session));
    }
}
