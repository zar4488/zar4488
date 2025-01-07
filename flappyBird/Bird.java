import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Bird extends Rectangle{

    int id;
    int speed = 12;
    int yVelocity = -6;
    int gravity = 1;
    int x;
    
    Bird(int x, int y, int BIRD_WIDTH, int BIRD_HEIGHT){
        super(x, y, BIRD_WIDTH, BIRD_HEIGHT);
        this.x = x;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE){  
            setYDirection(-speed);  
            move();
        }
    }

    public void keyReleased(KeyEvent e){
        setYDirection(0);
        move();
    }

    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    public void move(){
        yVelocity += gravity;
        y += yVelocity;
    }    

    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x, y, width, height);
    }
}
