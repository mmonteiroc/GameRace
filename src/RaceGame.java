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
        aplicacion = new AppGameContainer(rg,1300,750,false);

        //Definimos el maximo de FPS de la ventana
        aplicacion.setShowFPS(false);
        aplicacion.setTargetFrameRate(maxFPS);
        aplicacion.start();


    }


    // Atributos de la clase
    Player jugador;
    World mundo;
    private int HighScore=0;
    private static AppGameContainer aplicacion;
    private boolean debug = false;
    private boolean controls = false;
    private Runtime runtime = Runtime.getRuntime();

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

        if (tecla.isKeyPressed(Input.KEY_F3)){
            if (debug){
                debug=false;

            }else{
                debug=true;
            }
        }

        if (tecla.isKeyPressed(Input.KEY_F2)){
            if (controls){
                controls=false;

            }else{
                controls=true;
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




        // DEBUG MENU

        if (debug){
            graphics.drawString("DEBUG MENU",10,10);
            graphics.drawString("Fps: "+aplicacion.getFPS(),10,30);
            graphics.drawString("Window width: "+aplicacion.getWidth(),10,50);
            graphics.drawString("Window height: "+aplicacion.getHeight(),10,70);
            graphics.drawString("Obstacles charged on memory: "+mundo.listaObstaculos.size(),10,90);
            int dataSize = 1024*1024;
            long memoriaTotal = runtime.maxMemory();
            long memoriaFree = runtime.freeMemory();
            long memoriaUsada = memoriaTotal-memoriaFree;
            graphics.drawString("Memoria usada "+memoriaUsada/dataSize+"MB/"+memoriaTotal/dataSize+"MB",10,110);
            String jug = "Player position (x,y): (";
            jug += jugador.getX();
            jug += ",";
            jug += jugador.getY();
            jug += ")";
            graphics.drawString(jug,10,130);


        }else {
            graphics.drawString("Press F3 to enter\non debug menu",10,10);

        }



        // CONTROlS MENU HELP

        if (controls){

            graphics.drawString("Controls menu",350,10);
            graphics.drawString("Move left - Left arrow",350,30);
            graphics.drawString("Move Right - Right arrow",350,50);
            graphics.drawString("Menu - ESC",350,70);
            graphics.drawString("Move faster - press Ctrl",350,90);

        }else {
            graphics.drawString("Press F2 to see\ncontrol help",350,10);
        }



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
