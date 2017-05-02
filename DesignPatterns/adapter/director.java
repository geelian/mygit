package adapter;

/**
 * Created by lg on 17-4-26.
 */
public class director {
    public static  void main(String[] args){
        System.out.println("bgein");

        IStar star = new FilmStar();
        star.act("15 realy");
        IActor actor = new UnknowActor();
        IStar star1 = new Standin(actor);
        star1.act("中间");
        star.act("end 15");
    }

}
