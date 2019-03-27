import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Barra extends Obstacle {
    Rectangle rec1;
    Rectangle rec2;

    Barra(float posicion){
        super(posicion);
        this.longitud=(int)(Math.random()*1150) +1;
    }


    @Override
    public void render(GameContainer gc, Graphics g){
        rec1= new Rectangle(0,this.posicion,this.longitud,1);
        rec2= new Rectangle(this.longitud+150,this.posicion,1300-this.longitud,1);

        g.draw(rec1);
        g.draw(rec2);
    }

}
