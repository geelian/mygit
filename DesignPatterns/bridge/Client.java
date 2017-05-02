package bridge;

/**
 * Created by lg on 17-4-26.
 */
public class Client {
    public static  void main(String [] args){
        AbsStar zhangSan = new FilmStar();

        AbsStar lisi = new Singer();

        zhangSan.doJob();

        lisi.doJob();

        lisi = new Singer(new ActFilm());
        lisi.doJob();
    }
}
