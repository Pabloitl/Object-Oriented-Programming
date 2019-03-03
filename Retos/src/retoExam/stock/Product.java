package retoExam.stock;

import java.util.ArrayList;
import static retoExam.files.FileManager.*;

public class Product {
    String name;
    int quantity;
    float price;
    
    public Product(String name){
        this.name = name;
        price = retrievePrice();
    }
    
    public Product(String name, float price){
        this.name = name;
        this.price = price;
    }
    
    public Product(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }
    
    public Product(String name, int quantity, float price){
        this(name, quantity);
        this.price = price;
    }
    
    private float retrievePrice(){
        String[] buffer = getLines(STOCK);
        
        for(String line: buffer){
            String[] bar = line.split(SEPARATOR);
            
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
        if(price != 0) return price;
        
        String[] buffer = getLines(STOCK);
        for(String line: buffer){
            String[] foo = line.split(SEPARATOR);
            if(foo[Stock.PRODUCT_FIELD].equals(name))
                return Float.parseFloat(foo[Stock.PRICE_FIELD]);
        }
        return 0;
    }
    
    public int getQuantity(){
        if(quantity != 0) return quantity;
        
        String[] buffer = getLines(STOCK);
        for(String line: buffer){
            String[] foo = line.split(SEPARATOR);
            if(foo[Stock.PRODUCT_FIELD].equals(name))
                return Integer.parseInt(foo[Stock.QUANTITY_FIELD]);
        }
        return 0;
    }
    
    public static String format(ArrayList<Product> products){
        ArrayList<String> temp = new ArrayList();
        for(Product p: products){
            temp.add(p.getName());
        }
        return format(temp.toArray(new String[temp.size()]));
    }
    
    public static String format(String...args){
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            buffer.append(args[i]);
            if(i < args.length - 1) buffer.append("-");
        }
        
        return buffer.toString();
    }
    
    @Override
    public String toString(){
        return name;
    }
}
