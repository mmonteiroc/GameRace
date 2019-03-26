import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Obstacle {
        private int x1;
        private int y1;
        private int x2;
        private int y2;


        private final int max=1150;

    /*
        CREAMOS UNA LINEA DE SEPARACION DE 150px Y DE LONGITUD
        1300


    * */

    Obstacle(){


        this.x1=0;
        this.x2= (int) (Math.random()*((max-0)+1))+0;
        System.out.println("Este es el random" + x2);
    }


    // DOS LINEAS PRIMERA SERA LONG RANDOM COMO MUCHO SERA 1250

    public void render(GameContainer gameContainer, Graphics graphics){
        graphics.drawLine(this.x1,this.y1,this.x2,this.y2);
    }


    public void setY(int y){
        this.y1=y;
        this.y2=y;

    }

    public void setX(int x1){
        this.x1=x1;
        this.x2=1300;
    }


    public int getX2(){
        return this.x2;
    }

    public int getY(){
        return this.y1;
    }
}
