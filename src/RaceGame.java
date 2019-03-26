import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;

public class RaceGame extends BasicGame {
    public static void main(String[] args) throws SlickException{
        // Maximos fps por segundo
        int maxFPS = 60;

        // Creamos la ventana de juego
        RaceGame rg = new RaceGame("Super race !");
        AppGameContainer aplicacion = new AppGameContainer(rg,1300,750,false);

        //Definimos el maximo de FPS de la ventana a 60
        aplicacion.setTargetFrameRate(maxFPS);
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
        Input tecla = gameContainer.getInput();


        if (!gameContainer.isPaused()){
            jugador.update(gameContainer);
            mundo.update(gameContainer);
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
        graphics.drawString("Puntuación: "+jugador.score,1100,10);



        // Llamamos a renderizar a el jugador () el circulo


        if (gameContainer.isPaused()){

            //Si esta pausado lo que hacemos es
            // mostrar una pequeña pantalla de pausa
            graphics.drawString("El juego esta pausado,\npulsa \'space\' para resumir\nPulsa ESC si quieres cerrar el juego\nPulsa enter para reiniciar partida",520,350);

        }else {
            // Si el juego no esta pausado, renderizamos nuestro
            // personaje y a el mundo con los obstaculos
            jugador.render(graphics,gameContainer);
            mundo.render(graphics,gameContainer);
        }




     }
}
