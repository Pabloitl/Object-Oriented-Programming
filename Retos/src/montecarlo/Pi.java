package montecarlo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Pi {
    int Acirc, Asqr, radius;
    double pi;
    
    public void aproximate(JPanel panel, int iterations){
        radius = panel.getWidth()/2;
       
        for(int i = 0; i < iterations; i++){
            Point temp = Point.getRandomPoint(radius);
            
            if(temp.isInside(radius)){
                Acirc++;
                Asqr++;
                temp.draw(Color.WHITE, panel);
            }else{
                Asqr++;
                temp.draw(Color.YELLOW, panel);
            }
            
            pi = 4 * ((double) Acirc/Asqr);
            drawPi(panel);
        }
    }
    
    private void drawPi(JPanel panel){
        int padding = 20;
        
        Graphics pixel = panel.getGraphics();
        pixel.setColor(Color.WHITE);
        pixel.setFont(new Font("Arial", Font.BOLD, 20));
        pixel.fillRect(padding, radius*2 - 2*padding, 250, 20);
        pixel.setColor(Color.BLACK);
        pixel.drawString(String.valueOf(pi), padding, radius*2 - padding);
    }
}