package reto4;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Subset {
    private int max, rows;
    
    //Constructor                =========================
    public Subset(int max, int rows){
        this.max = max;
        this.rows = rows;
    }
    
    //Setters and getters        =========================
    public void setMax(int max){
        this.max = max;
    }
    
    public int getMax(){
        return max;
    }
    
    public void setRows(int rows){
        this.rows = rows;
    }
    
    public int getRows(){
        return rows;
    }
    
    //Dynamic methods            =========================
    public String getSubsets(){
        return getSubsets(max);
    }
    
    public JScrollPane toJScrollPane(){
        return toJScrollPane(getSubsets(max), rows);
    }
    
    //Static methods             =========================
    public static String getSubsets(int n){
        if(n < 0) return null;
        
        StringBuffer sb = new StringBuffer();
        
        //Build String Buffer
        for(int i = 0; i < (1<<n); i++){
            sb.append("{");
            for(int j = 0; j <= n; j++) if((i & (1 << j)) > 0) sb.append(" " + j + " ");
            sb.append("}\n");
        }
        
        return sb.toString();
    }
    
    public static JScrollPane toJScrollPane(String convert, int rows){
        //Create text area and scrollpane
        JTextArea ta = new JTextArea(convert);
        ta.setRows(rows);
        return new JScrollPane(ta);
    }
}
