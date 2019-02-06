package reto2.Oscars;

public class Hash {
    
    final static public int DEFAULT_STEP = 3;
    
    public static String hash(String password, int step){
        if(password == null) return "";
        
        //Ceaser cipher
        String hash = shiftLetters(password, step);
        
        return hash;
    }
    
    private static String shiftLetters(String password, int step){
        String hash = "";
        
        for(int i = 0; i < password.length(); i++){
            hash += Character.toString((char) (password.charAt(i) + step));
        }
        
        return hash;
    }
}
