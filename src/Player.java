import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * Esta clase nos permite crear un objeto Player
 * el cual lo que hace es crear un circulo que sera nuestro jugado
 * y ir actualizando su posicicon en la pantalla dependiendo
 * de que controles estemos pulsando nosotros
 */
public class Player {

    // Atributos de player
    int score;
    private int scoreHidden=0;
    Circle c;

    // Metodos
    //Atributos circulo
    private int x = 630;
    private int y = 650;
    private int radio = 30;

    /**
     * @param graphics Graphics es donde tendremos que dibujar a dicho jugador
     * @param gc
     *
     * Este metodo lo que hace es renderizar nuestro personaje
     * visualmente en sus coordenadas (x,y) que le tocan
     */
    public void render(Graphics graphics, GameContainer gc){
        c = new Circle(x,y,radio);
        graphics.draw(c);
    }


    /**
     * @param gameContainer
     * @param mundo Este objeto lo recibimos ya que necesitaremos
     *              saber los obstaculos para saber si hemos colisionado
     * @throws SlickException Posible excepcion que podria lanzar nuestro programa
     *
     * Este metodo lo que hace es ir actualizando la posicion del nuestro
     * jugador dependiendo si pulsamos o no algunas teclas
     * Si pulsamos el control ganamos un boost de 5px por frame
     * Si pulsamos arrow left/right nos movemos respectivamente a la IZQ o DCHA
     *
     * En este metodo tambien vamos mirando si dicho player colisiona o no con
     * nuestros obstaculos, ya que si colisiona eso daria la partida por finalizada
     *
     * En este caso solo comparamos con que colisione con el primero obstaculo de la lista ya que
     * nuestro personaje no se puede mover hacia arriba/abajo, por lo tanto solo podra colisionar
     * siempre con el primero obstaculo
     *
     * Tambien vamos mirando y aumentando la puntuacion interna del jugador dependiendo de cuantos
     * obstaculos vaya evitando durante la partida
     */
    public void update(GameContainer gameContainer, World mundo)throws SlickException {

        Input i = gameContainer.getInput();

        // Movimientos del jugador
        double speed;
        // Usar speed up de jugador
        if (i.isKeyDown(Input.KEY_LCONTROL)){
            speed=20;
        }else{
            speed=15;
        }
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

    /**
     * @param x puntuacion
     *
     * Este pequeño metodo lo que hace es
     * simplemente ir continuamente asignando
     * el score al jugador
     */
    public void añadirPuntuacion(int x){

        int oldScore = this.score;
        this.score=x;
        if (this.score!=oldScore){
            scoreHidden++;
        }
    }

    // GETTERS
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}
