package adapter;

/**
 * Created by lg on 17-4-26.
 */
public class UnknowActor implements IActor {
    @Override
    public void playact(String contet) {
        System.out.println("普通演员：" + contet);
    }
}
