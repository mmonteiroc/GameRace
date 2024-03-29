import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;

/**
 * Esta clase nos permite crear un objeto Player
 * el cual lo que hace es crear un circulo que sera nuestro jugado
 * y ir actualizando su posicicon en la pantalla dependiendo
 * de que controles estemos pulsando nosotros
 */
public class Player {

    // Atributos de player
    int score;
    private int scoreHidden = 0;
    Circle c;

    // Metodos
    //Atributos circulo
    private int x = 600;
    private int y = 650;
    private int radio = 30;
    boolean colisiones = true;
    private Image balon;

    Player() {
        try {
            balon = new Image("/assets/football.png");
        } catch (SlickException e) {
            //e.printStackTrace();
            System.out.println("Ha habido una excepcion a la hora de cargar la imagen del balon.\n" +
                    "Ir al codigo y descomentar la linea para mas info ");
        }

        //balon = new Image("Hola");
    }

    /**
     * @param graphics Graphics es donde tendremos que dibujar a dicho jugador
     * @param gc       Este metodo lo que hace es renderizar nuestro personaje
     *                 visualmente en sus coordenadas (x,y) que le tocan
     */
    public void render(Graphics graphics, GameContainer gc, boolean debug) {

        if (this.x < 30) {
            this.x = 30;
        }


        if (this.x > 1300 - this.radio) {
            this.x = 1300 - this.radio;
        }

        //balon.draw(this.x-radio,this.y-radio,2);
        balon.drawCentered(this.x, this.y);
        c = new Circle(x, y, 30);


        // Mostramos una cruz
        // encima del jugador
        if (debug) {
            Line l = new Line(this.x, 0, this.x, 750);
            graphics.draw(l);
            l = new Line(0, this.y, 1300, this.y);
            graphics.draw(l);
        }

    }


    /**
     * @param gameContainer
     * @param mundo         Este objeto lo recibimos ya que necesitaremos
     *                      saber los obstaculos para saber si hemos colisionado
     * @throws SlickException Posible excepcion que podria lanzar nuestro programa
     *                        <enemy>
     *                        Este metodo lo que hace es ir actualizando la posicion del nuestro
     *                        jugador dependiendo si pulsamos o no algunas teclas
     *                        Si pulsamos el control ganamos un boost de 5px por frame
     *                        Si pulsamos arrow left/right nos movemos respectivamente a la IZQ o DCHA
     *                        <enemy>
     *                        En este metodo tambien vamos mirando si dicho player colisiona o no con
     *                        nuestros obstaculos, ya que si colisiona eso daria la partida por finalizada
     *                        <enemy>
     *                        En este caso solo comparamos con que colisione con el primero obstaculo de la lista ya que
     *                        nuestro personaje no se puede mover hacia arriba/abajo, por lo tanto solo podra colisionar
     *                        siempre con el primero obstaculo
     *                        <enemy>
     *                        Tambien vamos mirando y aumentando la puntuacion interna del jugador dependiendo de cuantos
     *                        obstaculos vaya evitando durante la partida
     */
    public void update(GameContainer gameContainer, World mundo) throws SlickException {

        Input i = gameContainer.getInput();

        double speed;
        // Boost speed de jugador
        if (i.isKeyDown(Input.KEY_LCONTROL)) {
            speed = 20;
        } else {
            speed = 15;
        }
        balon.rotate(0);
        // Movimientos del jugador
        if (i.isKeyDown(Input.KEY_LEFT) && x >= this.radio) {
            x -= speed;
            balon.rotate(-15);
        }
        if (i.isKeyDown(Input.KEY_RIGHT) && x <= 1300 - this.radio) {
            x += speed;
            balon.rotate(15);
        }


        // Condiciones de colision
        if (RaceGame.isDebug()) {
            i = gameContainer.getInput();
            if (i.isKeyPressed(Input.KEY_F5)) {
                if (colisiones) {
                    colisiones = false;
                } else {
                    colisiones = true;
                }
            }
        }
        // condicion de game over colisionando con un obstaculo
        if (c.intersects(mundo.listaObstaculos.getFirst().rec1) || c.intersects(mundo.listaObstaculos.getFirst().rec2)) {
            if (colisiones) {
                gameContainer.reinit();
            }
        }

        if (mundo.enemy != null) {
            if (c.intersects(mundo.enemy.cuadrado)) {
                if (colisiones) {
                    gameContainer.reinit();
                }
            }
        }


        // Puntuacion del jugador
        añadirPuntuacion(mundo.getObstaculosPasados());
        if (this.scoreHidden >= 10) {
            this.scoreHidden = 0;
            mundo.incrementVelocidadBajada(0.5);
            mundo.lanzarPersonaje();
        }

    }

    /**
     * @param x puntuacion
     *          <enemy>
     *          Este pequeño metodo lo que hace es
     *          simplemente ir continuamente asignando
     *          el score al jugador
     */
    public void añadirPuntuacion(int x) {

        int oldScore = this.score;
        this.score = x;
        if (this.score != oldScore) {
            scoreHidden++;
        }
    }


    // GETTERS
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
