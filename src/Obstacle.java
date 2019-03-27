import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.util.Random;

public class Obstacle {
    protected float posicion;
    protected int longitud;

    Obstacle(float posicion){
        this.posicion=posicion;
    }




    public void render(GameContainer gc, Graphics g){

    }


    public float getPosicion(){
        return this.posicion;
    }

    public void aumentarPosicion(double aumento){
        this.posicion+=aumento;
    }
}
