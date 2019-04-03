import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;

public class World {

    //Atributos
    public LinkedList<Barra> listaObstaculos = new LinkedList<>();
    private double velocidadBajada = 3;
    private int obstaculosPasados = 0;
    public Enemigo enemy;

    // CONSTRUCTOR
    World() {
        createObstacles(4);
    }

    //Metodos de World

    public void update() {
        int y;
        int i = 0;
        Obstacle x;

        for (int j = 0; j < listaObstaculos.size(); j++) {
            x = listaObstaculos.get(j);
            if (x.getPosicion() >= 730) {
                obstaculosPasados++;
                listaObstaculos.remove(i);
                añadirObs();

            }
            x.aumentarPosicion(velocidadBajada);
        }

        if (enemy != null) {
            enemy.aumentarY(velocidadBajada + 1.5);
            if (enemy.getPosY() >= 700) {
                enemy = null;
            }
        }


    }

    public void render(Graphics graphics, GameContainer gc) {
        Obstacle o;
        for (int i = 0; i < this.listaObstaculos.size(); i++) {
            o = listaObstaculos.get(i);
            o.render(gc, graphics);
        }


        if (enemy != null) {
            enemy.render(gc, graphics);
        }
    }


    private void createObstacles(int x) {
        Barra o;
        int pos = 0;
        for (int i = 0; i < x; i++) {


            o = new Barra(pos);
            pos -= 300 + velocidadBajada;
            listaObstaculos.add(o);
        }


    }

    private void añadirObs() {
        Barra o;
        float pos = listaObstaculos.getLast().getPosicion();
        o = new Barra((int) ((pos - 300) - velocidadBajada));
        listaObstaculos.add(o);
    }

    public void lanzarPersonaje() {

        enemy = new Enemigo(-100);

    }

    public int getObstaculosPasados() {
        return this.obstaculosPasados;
    }

    public void incrementVelocidadBajada(double s) {
        this.velocidadBajada += s;
    }

    public double getSpeed() {
        return this.velocidadBajada;
    }
}