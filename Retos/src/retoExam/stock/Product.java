package retoExam.stock;

import retoExam.files.FileManager;

public class Product {
    String name;
    String quantity;
    float price;
    
    public Product(String name){
        this.name = name;
        price = retrievePrice();
    }
    
    private float retrievePrice(){
        String[] buffer = FileManager.getLines(FileManager.STOCK);
        
        for(String line: buffer){
            String[] bar = line.split(FileManager.SEPARATOR);
            
            if(bar[Stock.PRODUCT_FIELD].equals(name))
                return Float.parseFloat(bar[Stock.PRICE_FIELD]);
        }
        
        return 0;
    }
    
    // Setters and getters =================================================
    public String getName(){
        return name;
    }
    
    public float getPrice(){
        return price;
    }
    
    
}
