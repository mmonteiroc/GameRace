import org.newdawn.slick.*;

import java.lang.management.OperatingSystemMXBean;

/**
 * Esta es nuestra clase principal de nuestro proyecto,
 * dicha clase hereda de BASIC GAME
 * que es una clase que nos proporciona la libreria slick2d
 * para crear juegos basicos.
 */
public class RaceGame extends BasicGame {


    /**
     * @param args
     * @throws SlickException Posible excepcion que podria lanzar nuestro juego
     *                        <p>
     *                        Este metodo lo que hace es inicializar nuestro juego,
     *                        definir cual seran los maximos frames por segundo
     *                        y crear la nueva ventana de juego
     */
    public static void main(String[] args) throws SlickException {
        // Maximos fps por segundo
        int maxFPS = 60;

        // Creamos la ventana de juego
        RaceGame rg = new RaceGame("Super race !");
        aplicacion = new AppGameContainer(rg, 1300, 750, false);

        //Definimos el maximo de FPS de la ventana
        aplicacion.setShowFPS(false);
        aplicacion.setTargetFrameRate(maxFPS);
        aplicacion.start();
    }


    // Atributos de la clase
    Player jugador;
    World mundo;
    private int HighScore = 0;
    private static AppGameContainer aplicacion;
    private static boolean debug = false;
    private boolean controls = false;
    private Runtime runtime = Runtime.getRuntime();
    private Image bg;
    private boolean mostrarInicio;


    /**
     * @param title Titulo de la ventana
     *              Constructor del juego el cual
     *              llamamos al super de esta clase
     */
    public RaceGame(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        jugador = new Player();
        mundo = new World();
        mostrarInicio = true;
        try {
            bg = new Image("/assets/grass.jpeg");
        } catch (SlickException e) {
            //e.printStackTrace();
            System.out.println("Ha habido una excepcion a la hora de cargar la imagen del fondo de cesped.\n" +
                    "Ir al codigo y descomentar la linea para mas info ");
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input tecla = gameContainer.getInput();


        if (!gameContainer.isPaused() && !mostrarInicio) {
            jugador.update(gameContainer, mundo);
            mundo.update(gameContainer);
            if (jugador.score > this.HighScore) this.HighScore = jugador.score;
        }


        // CREAMOS UN CONTROL DE PAUSA PARA NUESTRO JUEGO
        /*
         *
         * ESC --> PAUSA
         * SPACE --> RESUME
         *
         * */

        if (mostrarInicio) {

            if (tecla.isKeyPressed(Input.KEY_SPACE)) {
                mostrarInicio = false;
            }
            // SALIMOS DEL JUEGO
            if (tecla.isKeyPressed(Input.KEY_ESCAPE)) {
                gameContainer.exit();
            }
        }
        if (gameContainer.isPaused()) {
            // RESUMIMOS PARTIDA
            if (tecla.isKeyPressed(Input.KEY_SPACE)) {
                gameContainer.resume();
            }
            // SALIMOS DEL JUEGO
            if (tecla.isKeyPressed(Input.KEY_ESCAPE)) {
                gameContainer.exit();
            }
            // REINICIAMOS
            if (tecla.isKeyPressed(Input.KEY_ENTER)) {
                gameContainer.reinit();
                gameContainer.setPaused(false);
            }
        }

        if (tecla.isKeyPressed(Input.KEY_F3)) {
            if (debug) {
                debug = false;

            } else {
                debug = true;
            }
        }

        if (tecla.isKeyPressed(Input.KEY_F2)) {
            if (controls) {
                controls = false;

            } else {
                controls = true;
            }
        }

        if (tecla.isKeyPressed(Input.KEY_ESCAPE) && !gameContainer.isPaused()) {
            gameContainer.pause();
        }


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        // Opciones generales
        graphics.drawImage(bg, 0, 0);
        graphics.setLineWidth((float) 2.5);


        if (gameContainer.isPaused()) {

            //Si esta pausado lo que hacemos es
            // mostrar una pequeña pantalla de pausa
            graphics.drawString("El juego esta pausado,\n" +
                            "pulsa \'space\' para resumir\n" +
                            "Pulsa ESC si quieres cerrar el juego\n" +
                            "Pulsa enter para reiniciar partida"
                    , 520, 350);

        } else if (mostrarInicio) {
            graphics.drawString("NEW GAME\nPULSAR SPACE PARA EMPEZAR\nPULSAR ESC PARA SALIR"
                    , 520, 350);
        } else {
            // Si el juego no esta pausado, renderizamos nuestro
            // personaje y a el mundo con los obstaculos
            mundo.render(graphics, gameContainer);
            jugador.render(graphics, gameContainer, this.debug);
        }


        // DEBUG MENU

        if (debug) {
            graphics.drawString("DEBUG MENU", 10, 10);
            graphics.drawString("Fps: " + aplicacion.getFPS(), 10, 30);
            graphics.drawString("Window width: " + aplicacion.getWidth(), 10, 50);
            graphics.drawString("Window height: " + aplicacion.getHeight(), 10, 70);

            int x;

            if (mundo.p != null) {
                x = mundo.listaObstaculos.size() + 1;
            } else {
                x = mundo.listaObstaculos.size();
            }
            graphics.drawString("Obstacles charged on memory: " + x, 10, 90);
            int dataSize = 1024 * 1024;
            long memoriaTotal = runtime.maxMemory();
            long memoriaFree = runtime.freeMemory();
            long memoriaUsada = memoriaTotal - memoriaFree;
            graphics.drawString("Used memory " + memoriaUsada / dataSize + "MB/" + memoriaTotal / dataSize + "MB", 10, 110);
            String jug = "Player position (x,y): (";
            jug += jugador.getX();
            jug += ",";
            jug += jugador.getY();
            jug += ")";
            graphics.drawString(jug, 10, 130);

            int distancia = (int) (jugador.getY() - mundo.listaObstaculos.getFirst().getPosicion()) + 30;
            if (distancia < 0) {
                distancia = (int) (jugador.getY() - mundo.listaObstaculos.get(1).getPosicion()) + 30;
            }
            graphics.drawString("Next wall distance: " + distancia, 10, 150);
            graphics.drawString("Collisions enabled - F5 : " + jugador.colisiones, 10, 170);

        } else {
            graphics.drawString("Press F3 to enter\non debug menu", 10, 10);

        }


        // CONTROl
        // MENU HELP
        if (controls) {

            graphics.drawString("Controls menu", 350, 10);
            graphics.drawString("Move left - Left arrow", 350, 30);
            graphics.drawString("Move Right - Right arrow", 350, 50);
            graphics.drawString("Speed boost - press Ctrl", 350, 70);
            graphics.drawString("Menu - ESC", 350, 90);

        } else {
            graphics.drawString("Press F2 to see\ncontrol help", 350, 10);
        }


        graphics.drawString("Score:     " + jugador.score * 10, 1100, 10);
        graphics.drawString("HighScore: " + this.HighScore * 10, 1100, 30);
        graphics.drawString("Velocidad scroll: " + mundo.getSpeed(), 1100, 50);


    }

    public static boolean isDebug() {
        return debug;
    }

}
