import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Obstacle {
    protected float posicion;
    protected int longitud;

    public Obstacle(float posicion) {
        this.posicion = posicion;
    }


    public void render(GameContainer gc, Graphics g) {

    }


    public float getPosicion() {
        return this.posicion;
    }

    public void aumentarPosicion(double aumento) {
        this.posicion += aumento;
    }
}
