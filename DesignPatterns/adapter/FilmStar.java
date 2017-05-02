package adapter;

/**
 * Created by lg on 17-4-26.
 */
public class FilmStar implements IStar {
    @Override
    public void act(String context) {
        System.out.println("明星表演:"+ context);
    }
}
