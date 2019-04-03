import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public class Enemigo {
    // Atributos

    Image enemy;
    Rectangle cuadrado;
    float y;
    int x;

    // Constructor
    Enemigo(float y) {
        this.y = y;
        int max = 9;
        String url = "";
        this.x = (int) Math.ceil(Math.random() * 1000 + 200);
        try {
            switch ((int) Math.ceil(Math.random() * max)) {
                //case 1:
                //  url="/assets/ronaldo-back.png";
                //break;
                case 2:
                    url = "/assets/messi.png";
                    break;
                case 3:
                    url = "/assets/roger.png";
                    break;
                case 4:
                    url = "/assets/pique.png";
                    break;
                case 5:
                    url = "/assets/mariano.png";
                    break;
                default:
                    url = "/assets/xavi.png";
            }

            enemy = new Image(url);
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }


    public void render(GameContainer gc, Graphics g) {

        cuadrado = new Rectangle(this.x, this.y, 130, 160);
        cuadrado.setCenterX(this.x);
        cuadrado.setCenterY(this.y);
        g.texture(this.cuadrado, this.enemy, true);
        if (RaceGame.isDebug()) {
            Line l = new Line(this.x + (this.cuadrado.getWidth() / 2), 0, this.x + (this.cuadrado.getWidth() / 2), 750);
            g.draw(l);
            //Horizontal
            l = new Line(0, this.y + (this.cuadrado.getHeight() / 2), 1300, this.y + (this.cuadrado.getHeight() / 2));
            g.draw(l);
        }

    }


    public void aumentarY(double aumento) {
        this.y += aumento;
    }


    // Getter
    public float getPosY() {
        return this.y;
    }

    // Setter
    public void setX(int x) {
        this.x = x;
    }
}
