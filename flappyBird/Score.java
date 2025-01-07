import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int playerScore;
    
    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawString(String.valueOf(playerScore/10)+String.valueOf(playerScore%10), (GAME_WIDTH/2)-20, 50);
    }
}
