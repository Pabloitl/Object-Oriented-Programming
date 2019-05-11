package montecarlo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class Point{
    int x, y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public static Point getRandomPoint(int radius){
        return new Point(new Random().nextInt(radius*2 + 1),
                         new Random().nextInt(radius*2 + 1));
    }
    
    public boolean isInside(int radius){
        int opx = x - radius;
        int opy = y - radius;
        
        return Math.pow(opx, 2) + Math.pow(opy, 2) <= Math.pow(radius, 2);
    }
    
    public void draw(Color color, JPanel panel){
        int stroke = 2;
        
        Graphics canva = panel.getGraphics();
        canva.setColor(color);
        canva.fillOval(x, y, stroke, stroke);
    }
    
    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
}
