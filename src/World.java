import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;
import java.util.List;

public class World {


    //Atributos
    List<Obstacle> listaObstaculos = new LinkedList<>();

    Obstacle o = new Obstacle();





    //Metodos de World


    int velocidadBajada=2;

    int y=0;
    int x=0;
    public void update(GameContainer gameContainer){
        y+=velocidadBajada;
    }

    public void render(Graphics graphics, GameContainer gc){
        graphics.drawLine(0,y,1150,y);

        o.render(gc,graphics,x,y);




    }


}
