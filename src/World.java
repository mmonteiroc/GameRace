import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class World {

    //Atributos
    public LinkedList<Obstacle> listaObstaculos = new LinkedList<>();
    private double velocidadBajada=2;
    private int obstaculosPasados =0;


    // CONSTRUCTOR
    World(){createObstacles(10);}

    //Metodos de World

    public void update(GameContainer gameContainer){
        int y;
        int i=0;
        Obstacle x;

        for (int j = 0; j < listaObstaculos.size(); j++) {
            x=listaObstaculos.get(j);
            if (x.getPosicion()>=730){
                obstaculosPasados++;
                listaObstaculos.remove(i);
                añadirObs();
            }
            x.aumentarPosicion(velocidadBajada);
        }


    }

    public void render(Graphics graphics, GameContainer gc){
        Obstacle o;
        for (int i = 0; i < this.listaObstaculos.size(); i++) {
            o = listaObstaculos.get(i);
            o.render(gc,graphics);
        }


    }


    private void createObstacles(int x){
        Obstacle o;
        int pos=0;
        for (int i = 0; i < x; i++) {

            o=new Obstacle(pos);
            pos -=290+velocidadBajada;
            listaObstaculos.add(o);
        }


    }

    private void añadirObs(){
        Obstacle o;
        float pos=listaObstaculos.getLast().getPosicion();
        o=new Obstacle((int) ((pos-290)-velocidadBajada));
        listaObstaculos.add(o);

    }



    public int getObstaculosPasados(){
        return this.obstaculosPasados;
    }

    public void incrementVelocidadBajada(double s){
        this.velocidadBajada+=s;
    }
    public double getSpeed(){
        return this.velocidadBajada;
    }
}