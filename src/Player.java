import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;

public class Player {

    // Atributos de player
    int score;



    // Metodos
    //Atributos circulo
    private int x = 630;
    private int y = 650;

    public void render(Graphics graphics, GameContainer gc){
        // Creamos el circulo
        Circle c = new Circle(0,0,30);

        // Definimos posiciones X y Y del circulo
        c.setX(x);
        c.setY(y);
        // Dibujamos dicho circulo
        graphics.draw(c);


    }

    public void update(GameContainer gameContainer){

        Input i = gameContainer.getInput();

        // Movimientos del jugador
        if (i.isKeyDown(Input.KEY_LEFT )&& x>=10)x-=10;
        if (i.isKeyDown(Input.KEY_RIGHT)&&x<=1300-70)x+=10;

        /*
        ACTIVAMOS ESTO SI QUEREMOS PODER
        MOVER HACIA ARRIBA Y HACIA ABAJO

        if (i.isKeyDown(Input.KEY_UP)&&y>=10)y-=10;
        if (i.isKeyDown(Input.KEY_DOWN)&&y<=750-70)y+=10;
        */



    }

}
