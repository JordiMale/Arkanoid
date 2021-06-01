import java.awt.*;

public class MapGenerator3 {
    public int map3[][];
    public int brickWidth3;
    public int brickHeight3;

    public MapGenerator3( int row, int col){

        map3 = new int [row][col];
        for(int i = 0; i < map3.length; i++){
            for(int j = 0; j <map3[0].length; j++){
                map3[i][j] = 1;
            }
        }
        brickWidth3 = 540/col;
        brickHeight3 = 75/row;
    }

    public void draw3(Graphics2D g){

        for(int i = 0; i < map3.length; i++) {
            for (int j = 0; j < map3[0].length; j++) {
                if(map3[i][j] > 0){
                    g.setColor(Color.RED);
                    g.fillRect(j * brickWidth3 + 80, i * brickHeight3 + 210, brickWidth3, brickHeight3);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth3 + 80, i * brickHeight3 + 210, brickWidth3, brickHeight3);
                }
            }
        }

    }
    public void setBrickValue3(int value, int row, int col){
            map3[row][col] = value;
    }

}
