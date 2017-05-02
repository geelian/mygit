package decorate;

/**
 * Created by lg on 17-4-26.
 */
public class Client {
    public static void main(String [] args){
        IStar freakStar = new FreakStar();
        freakStar = new HotAir(freakStar);
        freakStar = new Deny(freakStar);
        System.out.println("begin");
        freakStar.act();
    }
}
