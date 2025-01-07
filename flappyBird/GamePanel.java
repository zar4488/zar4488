import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



public class GamePanel extends JPanel implements Runnable{

    static final int WIDTH = 500;
    static final int HEIGHT = 1000;
    static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
    static final int BIRD_DIAMETER = 30;
    static final int PIPE_WIDTH = (int) (WIDTH / 10);
    static final int PIPE_HEIGHT = (int) (HEIGHT / 2.65); 
    static final int PIPE_SHORT_HEIGHT = (int) (HEIGHT / 7.94);
    static final int PIPE_LONG_HEIGHT = (int) (HEIGHT / 1.543);
    
    boolean GAME_OVER = false;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Bird bird;
    Pipe pipe1;
    Pipe pipe2;
    Score score;
    Random random;
    int choice;


    GamePanel(){
        newBird();
        newPipe();
        score = new Score(WIDTH, HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBird(){
        bird = new Bird((WIDTH/2)-(BIRD_DIAMETER/2), (HEIGHT/2)-(BIRD_DIAMETER/2), BIRD_DIAMETER, BIRD_DIAMETER);
    }

    public void newPipe(){
        random = new Random();
        choice = random.nextInt(3) + 1;
        switch(choice){
            case 1:
                pipe1 = new Pipe(WIDTH, 0, PIPE_WIDTH, PIPE_HEIGHT, 1);
                pipe2 = new Pipe(WIDTH, (HEIGHT - PIPE_HEIGHT), PIPE_WIDTH, PIPE_HEIGHT, 2);
                break;
            case 2: // low pipe
                pipe1 = new Pipe(WIDTH, 0, PIPE_WIDTH, PIPE_LONG_HEIGHT, 1);
                pipe2 = new Pipe(WIDTH, (HEIGHT - PIPE_SHORT_HEIGHT), PIPE_WIDTH, PIPE_SHORT_HEIGHT, 2);
                break;
            case 3: // high pipe
                pipe1 = new Pipe(WIDTH, 0, PIPE_WIDTH, PIPE_SHORT_HEIGHT, 1);
                pipe2 = new Pipe(WIDTH, (HEIGHT - PIPE_LONG_HEIGHT), PIPE_WIDTH, PIPE_LONG_HEIGHT, 2);
                break;
            default:
                System.err.println("ERROR: random int out of bounds!!!!!");
        }
        
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    public void draw(Graphics g){
        bird.draw(g);
        pipe1.draw(g);
        pipe2.draw(g);
        score.draw(g);
    }

    public void moveBird(){
        bird.move();

    }

    public void movePipe(){
        pipe1.move();
        pipe2.move();
    }

    public void checkEdge(){
        if(pipe1.x <= 0){
            newPipe();
        }
        
    }

    public void checkCollision(){
        if(bird.intersects(pipe1)){
            bird.yVelocity += 2;
            GAME_OVER = true;
        }
        if(bird.intersects(pipe2)){
            bird.yVelocity -= 6;
            bird.yVelocity += 2;
            GAME_OVER = true;
        }
    }

    public void checkPoint(){
        if(pipe1.x == bird.x && pipe2.x == bird.x && !(GAME_OVER))
            score.playerScore++;
            
    }

    public void run(){ 
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                moveBird();
                if (!(GAME_OVER)){
                    movePipe();
                    checkCollision();

                }             
                checkEdge();
                checkPoint();
                repaint();
                delta--;
            }

        }
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            if(!(GAME_OVER))
                bird.keyPressed(e);
        }

        public void keyReleased(KeyEvent e){

        }
    }


}
