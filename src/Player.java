import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Player {

    // Atributos de player
    int score;
     int scoreHidden=0;
    private double speed=15;
    Circle c;

    // Metodos
    //Atributos circulo
    private int x = 630;
    private int y = 650;

    public void render(Graphics graphics, GameContainer gc){
        // Creamos el circulo
        c = new Circle(0,0,30);

        // Definimos posiciones X y Y del circulo
        c.setX(x);
        c.setY(y);
        // Dibujamos dicho circulo
        graphics.draw(c);


    }

    public void update(GameContainer gameContainer, World mundo)throws SlickException {

        Input i = gameContainer.getInput();

        // Movimientos del jugador
        if (i.isKeyDown(Input.KEY_LEFT )&& x>=10)x-=speed;
        if (i.isKeyDown(Input.KEY_RIGHT)&&x<=1300-70)x+=speed;

        /*
        ACTIVAMOS ESTO SI QUEREMOS PODER
        MOVER HACIA ARRIBA Y HACIA ABAJO

        if (i.isKeyDown(Input.KEY_UP)&&y>=10)y-=10;
        if (i.isKeyDown(Input.KEY_DOWN)&&y<=750-70)y+=10;
        */


        if (c.intersects(mundo.listaObstaculos.getFirst().rec1) || c.intersects(mundo.listaObstaculos.getFirst().rec2)){
            gameContainer.reinit();
            gameContainer.pause();
        }

    }

    public void añadirPuntuacion(int x){

        int oldScore = this.score;
        this.score=x;
        if (this.score!=oldScore){

            scoreHidden++;
        }
    }


}
