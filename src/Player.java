import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;

public class Player {

    // Atributos de player
    int score;



    // Metodos
    //Atributos circulo
    int x = 630;
    int y = 670;
    public void render(Graphics graphics, GameContainer gc){
        Circle c = new Circle(0,0,30);

        graphics.drawString("Puntuación: "+score,1100,10);

        c.setX(x);
        c.setY(y);
        graphics.draw(c);

    }

    public void update(GameContainer gameContainer){

        Input i = gameContainer.getInput();
        if (i.isKeyDown(Input.KEY_LEFT )&& x>=10){
            x-=10;
        }

        if (i.isKeyDown(Input.KEY_RIGHT)&&x<=1300-70){
            x+=10;
        }





    }

}
