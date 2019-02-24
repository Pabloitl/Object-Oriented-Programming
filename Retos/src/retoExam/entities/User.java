package retoExam.entities;

public class User {
    
    private float getBalance(){
        return 0;
    }
    
    public static String format(String...args){
        String buffer = "";

        for (int i = 0; i < args.length; i++) {
            buffer += args[i];
            if(i < args.length - 1) buffer += ":";
        }
        
        return buffer;
    }
}
