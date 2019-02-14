package reto4;

public class Password {
    
    
    final static public int DEFAULT_STEP = 3;
    
    private String password, hash;
    private int step;
    
    //Constructor               =========================
    public Password(String password, int step){
        this.password = password;
        this.step = step;
    }
    
    
    //Setters and getters       =========================
    public void setPassword(String password){
        this.password = password;
    }
   
    public String getPassword(){
        return password;
    }
   
    public String getHash(){
        return hash(password, step);
    }
    
    public void setStep(int step){
        this.step = step;
    }
    
    public int getStep(){
        return step;
    }
   
    //Static methods to get Hash =========================
    public static String getHash(String password){
       return hash(password, DEFAULT_STEP);
    }
    
    public static String getHash(String password, int step){
        return hash(password, step);
    }
   
    //Proccessing methods        =========================
    private static String hash(String password, int step){
        if(password == null) return "";
        
        return shiftLetters(password, step);
    }
    
    private static String shiftLetters(String password, int step){
        String hash = "";
        
        for(int i = 0; i < password.length(); i++)
            hash += Character.toString((char) (password.charAt(i) + step));
        
        return hash;
    }
}