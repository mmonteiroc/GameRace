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
        mundo = new World();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        jugador.update(gameContainer);
        mundo.update(gameContainer);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        // Llamamos a renderizar a el jugador () el circulo
        jugador.render(graphics,gameContainer);

        // Llamamos a renderizar a el mundo
        mundo.render(graphics,gameContainer);

    }
}
