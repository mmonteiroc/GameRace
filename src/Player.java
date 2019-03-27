import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Player {

    // Atributos de player
    int score;
    int scoreHidden=0;
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
        double speed = 15;
        if (i.isKeyDown(Input.KEY_LEFT )&& x>=10)x-= speed;
        if (i.isKeyDown(Input.KEY_RIGHT)&&x<=1300-70)x+= speed;



        // condicion de game over colisionando con un obstaculo
        if (c.intersects(mundo.listaObstaculos.getFirst().rec1) || c.intersects(mundo.listaObstaculos.getFirst().rec2)){
            gameContainer.reinit();
            gameContainer.pause();
        }



        // Puntuacion del jugador
        añadirPuntuacion(mundo.getObstaculosPasados());
        if (this.scoreHidden==20){
            this.scoreHidden=0;
            mundo.incrementVelocidadBajada(0.5);
        }

    }

    public void añadirPuntuacion(int x){

        int oldScore = this.score;
        this.score=x;
        if (this.score!=oldScore){
            scoreHidden++;
        }
    }


    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}
