package decorate;

/**
 * Created by lg on 17-4-26.
 */
public class FreakStar implements IStar {
    @Override
    public void act() {
        System.out.println("表演中：演技很拙劣");
    }
}
