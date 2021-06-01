import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    int cont = 3;

    int speed = 20;


    private  int totalBricks = 42;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX = 350;
    private int ballposY = 510;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;
    private MapGenerator2 map2;
    private MapGenerator3 map3;


    public GamePlay(){
        map = new MapGenerator(2,7);
        map2 = new MapGenerator2(2,7);
        map3 = new MapGenerator3(2,7);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        //Background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692, 592);

        //drawing map
        ///BLUE
        map.draw((Graphics2D) g);

        //GREEN
        map2.draw2((Graphics2D) g);

        ///RED
        map3.draw3((Graphics2D) g);



        //Borders
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0,3,592);
        g.fillRect(0, 0,692,3);
        g.fillRect(691, 0,3,592);

        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Vidas: "+cont, 90, 30);

        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        //the paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8);

        //the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballposX, ballposY, 20, 20);

        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won: ", 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        if(ballposY > 570 && cont != 1){
            cont--;
            ballposX = 350;
            ballposY = 510;
            ballXdir = -2;
            ballYdir = -1;

        }else{
            if(ballposY > 570 && cont == 1){
                play = false;
                ballXdir = 0;
                ballYdir = 0;
                g.setColor(Color.RED);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Game Over ", 190, 300);

                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Press Enter to Restart", 230, 350);
            }
        }

        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballposX, ballposY,20,20).intersects(new Rectangle(playerX, 550, 100,8))){
                ballYdir = -ballYdir;
            }

            A: for(int i = 0; i <map.map.length; i++){
                for(int j = 0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            speed = speed +2;
                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir =-ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }

            B: for(int i = 0; i <map2.map2.length; i++){
                for(int j = 0; j<map2.map2[0].length; j++){
                    if(map2.map2[i][j] > 0){
                        int brickX = j * map2.brickWidth2 + 80;
                        int brickY = i * map2.brickHeight2 + 130;
                        int brickWidth = map2.brickWidth2;
                        int brickHeight = map2.brickHeight2;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map2.setBrickValue2(0, i, j);
                            totalBricks--;
                            score += 5;

                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir =-ballYdir;
                            }
                            break B;
                        }
                    }
                }
            }


            C: for(int i = 0; i <map3.map3.length; i++){
                for(int j = 0; j<map3.map3[0].length; j++){
                    if(map3.map3[i][j] > 0){
                        int brickX = j * map3.brickWidth3 + 80;
                        int brickY = i * map3.brickHeight3 + 210;
                        int brickWidth = map3.brickWidth3;
                        int brickHeight = map3.brickHeight3;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map3.setBrickValue3(0, i, j);
                            totalBricks--;
                            score += 5;

                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir =-ballYdir;
                            }
                            break C;
                        }
                    }
                }
            }




            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0){
               ballXdir = -ballXdir;
            }
            if(ballposY < 0){
                ballYdir = -ballYdir;
            }
            if(ballposX > 670){
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else{
                moveRight();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }else{
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                cont = 3;
                totalBricks = 42;
                map = new MapGenerator(2,7);
                map2 = new MapGenerator2(2,7);
                map3 = new MapGenerator3(2,7);


                repaint();
            }
        }
    }

    public void moveRight(){
        play = true;
        playerX+=speed;
    }
    public void moveLeft(){
        play = true;
        playerX-=speed;
    }



}
