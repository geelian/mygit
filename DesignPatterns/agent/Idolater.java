

/**
 * Created by lg on 17-4-26.
 */
public class Idolater {
    public static void main(String [] args){
        IStar star = new Singer();
        IStar agent = new Agent(star);

        agent.sign();
    }
}
