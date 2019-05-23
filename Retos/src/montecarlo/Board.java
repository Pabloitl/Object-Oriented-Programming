package montecarlo;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board {
    JFrame window;
    JPanel panel;

    int width, height;

    public Board(int dimension){
        window = new JFrame();
        panel = new JPanel();
        this.width = this.height = dimension;

        configure();
    }

    private void configure(){
        int pading = 50;

        configurePanel();
        configureFrame(50);
    }

    private void configureFrame(int padding){
        window.setTitle("Monte carlo aproximation of PI");
        window.setResizable(false);
        window.setSize(new Dimension(width + padding, height + padding));
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new FlowLayout());
        window.add(panel);
        window.setVisible(true);
    }

    private void configurePanel(){
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(Color.BLACK);
    }
}
