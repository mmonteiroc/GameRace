import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

public class Personaje extends Obstacle{
    // Atributos

    Image personaje;
    Rectangle cuadrado;
    int x;

    // Constructor
    Personaje(float posicion){
        super(posicion);
        int max = 4;
        String url ="";
        this.x = (int)Math.ceil(Math.random()*1000+200);
        try{
            switch ((int)Math.ceil(Math.random()*max)){
                //case 1:
                  //  url="/assets/ronaldo-back.png";
                    //break;
                case 2:
                    url="/assets/messi.png";
                    break;
                default:
                    url="/assets/mariano.png";

            }

            personaje = new Image(url);
        }catch (SlickException e){
            e.printStackTrace();
        }

    }




    public void render(GameContainer gc, Graphics g){

        cuadrado = new Rectangle(this.x,this.posicion,130,160);
        g.texture(this.cuadrado,this.personaje,true);

    }




    // Getter
    public float getPosY(){
        return this.posicion;
    }

    // Setter
    public void setX(int x){
        this.x = x;
    }
}
