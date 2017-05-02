package decorate;

/**
 * Created by lg on 17-4-26.
 */
public class Decorator implements IStar {
    private IStar iStar;

    public Decorator(IStar iStar) {
        this.iStar = iStar;
    }

    @Override
    public void act() {
        this.iStar.act();
    }
}
