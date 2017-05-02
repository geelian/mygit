

/**
 * Created by lg on 17-4-26.
 */
public class Agent implements IStar {
    private IStar star;

    public Agent(IStar star){
        this.star = star;
    }
    @Override
    public void sign() {
        star.sign();
    }
}
