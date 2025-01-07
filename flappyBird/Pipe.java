import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Pipe extends Rectangle{

    int id;
    int xVelocity = 5;
    //int x;
    
    Pipe(int x, int y, int PIPE_WIDTH, int PIPE_HEIGHT, int id){
        super(x, y, PIPE_WIDTH, PIPE_HEIGHT);
        this.id = id;
        //this.x = x;  
    }

    public void setXDirection(int xDirection){
        xVelocity = xDirection; 
    }

    public void move(){
        x -= xVelocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }
}
