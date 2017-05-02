package bridge;

/**
 * Created by lg on 17-4-26.
 */
public class Singer extends AbsStar {
    protected Singer(AbsAction absAction) {
        super(absAction);
    }

    protected Singer(){
        super(new Sing());
    }

    public void doJob(){
        System.out.println("歌星工作");
        super.doJob();
    }
}


