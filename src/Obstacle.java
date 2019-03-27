import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.util.Random;

public class Obstacle {
    private float posicion;
    private int longitud=(int)(Math.random()*1150) +1;

    Obstacle(float posicion){
        this.posicion=posicion;
    }


    Rectangle rec1;
    Rectangle rec2;

    public void render(GameContainer gc, Graphics g){
        rec1= new Rectangle(0,this.posicion,this.longitud,1);
        rec2= new Rectangle(this.longitud+150,this.posicion,1300-this.longitud,1);

        g.draw(rec1);
        g.draw(rec2);
    }


    public float getPosicion(){
        return this.posicion;
    }

    public void aumentarPosicion(double x){
        this.posicion+=x;
    }
}
