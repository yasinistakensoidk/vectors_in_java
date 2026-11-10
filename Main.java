package Function.*;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;

public class Main{
    public static void main(String[] args){
        //===============================================
        // initialise function class with basic operators
        //===============================================

        System.out.println("running");
        new C_Function.builder()
        .name("+")
        .function("x+y")
        .precedence((byte) 1)
        .variables({"x", "y"})
        .build();


        new C_Function.builder()
        .name("test")
        .function("3.1+4 + 7").build();

        System.out.println("done building" + C_Function.Funs);
        //===============================================
        // initialise GUI
        //===============================================
        Frame frame1 = new Frame("hello there");
        frame1.setSize(1000, 1000);
        frame1.setVisible(true);

        frame1.addWindowListener(new WindowAdapter(){
            @Override 
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        draw(sin(0, Math.PI, 100));

    }

    public static C_PCM_samps sin(double start, double end, int samples){
        C_PCM_samps temp = new C_PCM_samps();
        double dt = (end-start)/samples;
        for (int i=0;i<end;i++){
            double t = (start+(i*dt))%(2*Math.PI);
            double amplitude = t-Math.pow(t, 3)/(3*2)+Math.pow(t, 5)/(5*4*3*2)-Math.pow(t, 7)/(7*6*5*4*3*2)+Math.pow(t, 9)/(9*8*7*6*5*4*3*2)-Math.pow(t, 11)/(11*10*9*8*7*6*5*4*3*2)+Math.pow(t, 13)/(13*12*11*10*9*8*7*6*5*4*3*2);
            temp.add(new C_PCM_Sample(t, amplitude));
        }

        return temp;

    }

    public static void draw(C_PCM_samps function){
        for (int i=0;i<function.size();i=i+2){
            Line2D.Double l = new Line2D.Double(function.get(i).getTime(), function.get(i).getAmplitude(), function.get(i+1).getTime(), function.get(i+1).getAmplitude());
        }
    }
}
