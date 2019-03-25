import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Obstacle {



    /*
        CREAMOS UNA LINEA DE SEPARACION DE 150px Y DE LONGITUD
        1300


    * */


    // DOS LINEAS PRIMERA SERA LONG RANDOM COMO MUCHO SERA 1250

    public void render(GameContainer gameContainer, Graphics graphics,int x,int y){

        graphics.drawLine(100,200,300,400);

    }

}
