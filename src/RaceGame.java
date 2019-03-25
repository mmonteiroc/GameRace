import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;

public class RaceGame extends BasicGame {
    public static void main(String[] args) throws SlickException{
        // Creamos la ventana de juego
        RaceGame rg = new RaceGame("Super juego");
        AppGameContainer aplicacion = new AppGameContainer(rg,1300,750,false);

        //Definimos el maximo de FPS de la ventana a 60
        aplicacion.setTargetFrameRate(60);
        aplicacion.start();


    }




    // Atributos de la clase
    Player jugador;
    World mundo;




    public RaceGame(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        jugador = new Player();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        jugador.update(gameContainer);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        jugador.render(graphics,gameContainer);
    }
}
