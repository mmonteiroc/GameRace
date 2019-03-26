import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;
import java.util.List;

public class World {

    //Atributos
    public List<Obstacle> listaObstaculos = new LinkedList<>();
    private double velocidadBajada=40;
    private int obstaculosPasados =0;


    // CONSTRUCTOR
    World(){createObstacles();}

    //Metodos de World

    public void update(GameContainer gameContainer){
        int y;
        this.obstaculosPasados =0;
        int cantidadPasadas=0;
        int i=0;
        for (Obstacle x:listaObstaculos) {
            y = x.getY();

            if (y>=750){
                obstaculosPasados++;
            }
            x.setY(y+=velocidadBajada);
        }




    }

    public void render(Graphics graphics, GameContainer gc){
        for (Obstacle x:listaObstaculos) {

            if (x.getY()>=0 && x.getY()<=750){
                x.render(gc,graphics);
            }
        }
    }


    private void createObstacles(){

        Obstacle o ;
        Obstacle o1;
        int x2antiguo;
        int cont=0;
        for (long i = 0; i < 100000; i++) {
            o = new Obstacle();
            o.setY((-1)*cont);

            x2antiguo = o.getX2() + 150;

            o1 = new Obstacle();
            o1.setX(x2antiguo);
            o1.setY((-1)*cont);

            listaObstaculos.add(o);
            listaObstaculos.add(o1);
            cont+=240;
        }
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