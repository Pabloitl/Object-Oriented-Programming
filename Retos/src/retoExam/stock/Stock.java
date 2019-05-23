package retoExam.stock;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

import java.util.ArrayList;
import static retoExam.Screen.Screen.*;
import retoExam.entities.User;
import static retoExam.files.FileManager.*;

public class Stock {

    //Variables
    public static int PRODUCT_FIELD = 0,
            QUANTITY_FIELD = 1,
            PRICE_FIELD = 2,

            MIN_QUANTITY = 10;

    //Wich implements Map
    private final ArrayList<Product> stock;

    public Stock(){
        stock = new ArrayList();
        retrieveStock();
    }

    public void prompt(){
        switch(showOptions("New", "Add", "Low")){
            case 0:
                Product product = addProduct();
                append(STOCK, User.format(product.getName(),
                    String.valueOf(product.getQuantity()),
                    String.valueOf(product.getPrice())));
                break;
            case 1:
                Product p = stock(this);
                updateStock(p, p.getQuantity() + order(p));
                break;
            case 2:
                showLowProducts(this, MIN_QUANTITY);
        }
    }

    // Setter and Getters ======================================================
    public String[] getMenu(){
        String[] bar = new String[stock.size()];

        //Returns a Set which is converted to an Array
        for(int i = 0; i < stock.size(); i++){
            bar[i] = stock.get(i).getName();
        }
        return bar;
    }

    public ArrayList<Product> getStock(){
        return stock;
    }

    public Product getProduct(String name){
        for(Product p:stock){
            if(p.getName().equals(name))
                return p;
        }
        return null;
    }

    private void retrieveStock(){
        String[] buffer = getLines(STOCK);

        for(String line: buffer){
            String[] foo = line.split(SEPARATOR);

            stock.add(new Product(foo[PRODUCT_FIELD],
                    Integer.valueOf(foo[QUANTITY_FIELD])));
        }
    }

    public void updateStock(Product product, int quantity){
        StringBuilder buffer = new StringBuilder();
        for(Product p:stock){
            if(p.getName().equals(product.getName())){
                buffer.append(p.getName()).append(":").append(quantity)
                        .append(":").append(p.getPrice()).append("\n");
                continue;
            }
            buffer.append(p.getName()).append(":").append(p.getQuantity())
                    .append(":").append(p.getPrice()).append("\n");
        }

        overWrite(STOCK, buffer.substring(0, buffer.length() - 1));
    }
}
