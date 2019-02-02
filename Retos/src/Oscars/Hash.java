package Oscars;

public class Hash {
    
    final static public int DEFAULT_STEP = 3;
    private int step;
    
    public Hash(int step){
        this.step = step;
    }
    
    public String hash(String msg){
        if(msg == null) return "";
        //Ceaser cipher
        String hash = "";
        
        for(int i = 0; i < msg.length(); i++){
            hash += Character.toString((char) (msg.charAt(i) + step));
        }
        
        return hash;
    }
}
