import org.newdawn.slick.*;
import org.newdawn.slick.opengl.ImageData;

public class Fantasma extends Image {



    public void render(GameContainer gameContainer, Graphics graphics)throws SlickException {
        Image fantasmilla = new Image("/assets/fantasma.png");
        graphics.drawImage(fantasmilla,200,200);
    }

    public void update(GameContainer gameContainer, Player jugador)throws SlickException{




    }

}
