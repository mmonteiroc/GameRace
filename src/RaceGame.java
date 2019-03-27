import org.newdawn.slick.*;

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
     *
     * Este metodo lo que hace es inicializar nuestro juego,
     * definir cual seran los maximos frames por segundo
     * y crear la nueva ventana de juego
     */
    public static void main(String[] args) throws SlickException{
        // Maximos fps por segundo
        int maxFPS = 60;

        // Creamos la ventana de juego
        RaceGame rg = new RaceGame("Super race !");
        AppGameContainer aplicacion = new AppGameContainer(rg,1300,750,false);

        //Definimos el maximo de FPS de la ventana
        aplicacion.setTargetFrameRate(maxFPS);
        aplicacion.start();

    }




    // Atributos de la clase
    Player jugador;
    World mundo;
    int HighScore=0;

    /**
     * @param title Titulo de la ventana
     * Constructor del juego el cual
     * llamamos al super de esta clase
     */
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
        Input tecla = gameContainer.getInput();


        if (!gameContainer.isPaused()){
            jugador.update(gameContainer,mundo);
            mundo.update(gameContainer);
            if (jugador.score>this.HighScore)this.HighScore=jugador.score;
        }


        // CREAMOS UN CONTROL DE PAUSA PARA NUESTRO JUEGO
        /*
        *
        * ESC --> PAUSA
        * SPACE --> RESUME
        *
        * */
        if (gameContainer.isPaused()){
            // RESUMIMOS PARTIDA
            if (tecla.isKeyPressed(Input.KEY_SPACE)){
                gameContainer.resume();
            }
            // SALIMOS DEL JUEGO
            if (tecla.isKeyPressed(Input.KEY_ESCAPE)){
                gameContainer.exit();
            }
            // REINICIAMOS
            if (tecla.isKeyPressed(Input.KEY_ENTER)){
                gameContainer.reinit();
                gameContainer.setPaused(false);
            }
        }
        if (tecla.isKeyPressed(Input.KEY_ESCAPE)&& !gameContainer.isPaused()){
            gameContainer.pause();
        }



    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        // Mostramos la puntuacion del jugador
        graphics.drawString("Score:     "+jugador.score*10,1100,10);
        graphics.drawString("HighScore: "+this.HighScore*10,1100,30);
        graphics.drawString("Velocidad scroll: "+ mundo.getSpeed(),1100,50);
        graphics.setLineWidth((float) 2.5);
        graphics.setBackground(Color.darkGray);




        if (gameContainer.isPaused()&&jugador.score>0){

            //Si esta pausado lo que hacemos es
            // mostrar una pequeña pantalla de pausa
            graphics.drawString("El juego esta pausado,\n" +
                                    "pulsa \'space\' para resumir\n" +
                                    "Pulsa ESC si quieres cerrar el juego\n" +
                                    "Pulsa enter para reiniciar partida"
                    ,520,350);

        }else if(gameContainer.isPaused()&&jugador.score==0){
            graphics.drawString("Has perdido\n" +
                            "Pulsa ESC si quieres cerrar el juego\n" +
                            "Pulsa enter para reiniciar partida"
                    ,520,350);

        }else{
            // Si el juego no esta pausado, renderizamos nuestro
            // personaje y a el mundo con los obstaculos
            jugador.render(graphics,gameContainer);
            mundo.render(graphics,gameContainer);
        }




     }
}
