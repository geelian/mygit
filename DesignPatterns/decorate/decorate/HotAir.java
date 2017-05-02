package decorate;

/**
 * Created by lg on 17-4-26.
 */
public class HotAir extends Decorator {
    public HotAir(IStar iStar) {
        super(iStar);
    }

    public void act(){
        System.out.println("表演前:ok");
        super.act();
    }
}
