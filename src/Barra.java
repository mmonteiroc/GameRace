import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Barra extends Obstacle {
    Rectangle rec1;
    Rectangle rec2;
    Image muro;

    Barra(float posicion) {
        super(posicion);
        this.longitud=(int)(Math.random()*1150) +1;
        try{
            this.muro =new Image("/assets/muro.jpeg");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void render(GameContainer gc, Graphics g){
        rec1= new Rectangle(0,this.posicion,this.longitud,20);

        rec2= new Rectangle(this.longitud+150,this.posicion,1300-this.longitud,20);
        g.texture(rec1,muro,true);
        g.texture(rec2,muro,true);



    }

}
