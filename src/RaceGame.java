import org.newdawn.slick.*;

public class RaceGame extends BasicGame {
    public static void main(String[] args) throws SlickException{
        // Creamos la ventana de juego
        RaceGame rg = new RaceGame("Super juego");
        AppGameContainer aplicacion = new AppGameContainer(rg,1850,950,false);
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

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }
}
