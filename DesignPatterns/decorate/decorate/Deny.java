package decorate;

/**
 * Created by lg on 17-4-26.
 */
public class Deny extends Decorator {
    public Deny(IStar iStar) {
        super(iStar);
    }

    public void act(){
        super.act();
        System.out.println("表演后:end");
    }

}
