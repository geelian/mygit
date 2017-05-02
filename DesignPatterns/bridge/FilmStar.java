package bridge;

/**
 * Created by lg on 17-4-26.
 */
public class FilmStar extends AbsStar {
    protected FilmStar() {
        super(new ActFilm());
    }

    public FilmStar(AbsAction absAction) {
        super(absAction);
    }

    public void doJob(){
        System.out.println("\n 影星工作");
        super.doJob();
    }
}
