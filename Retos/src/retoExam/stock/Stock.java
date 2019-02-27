package retoExam.stock;

import java.util.HashMap;
import retoExam.Screen.Screen;
import retoExam.files.FileManager;

public class Stock {
    
    //Variables
    public static int PRODUCT_FIELD = 0,
            QUANTITY_FIELD = 1,
            PRICE_FIELD = 2;
    
    //Wich implements Map
    private HashMap<String, Integer> stock;
    
    public Stock(){
        stock = new HashMap();
        retrieveStock();
    }
    
    public void prompt(){
        Screen.stock(getStock());
    }
    
    // Setter and Getters ======================================================
    public String[] getMenu(){
        String[] bar = new String[stock.size()];
        
        //Returns a Set which is converted to an Array
        stock.keySet().toArray(bar);
        return bar;
    }
    
    private HashMap getStock(){
        return stock;
    }
    
    private void retrieveStock(){
        String[] buffer = FileManager.getLines(FileManager.STOCK);
        
        for(String line: buffer){
            String[] foo = line.split(FileManager.SEPARATOR);
            
            stock.put(foo[PRODUCT_FIELD], Integer.valueOf(foo[QUANTITY_FIELD]));
        }
    }
}
