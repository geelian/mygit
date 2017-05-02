package bridge;

/**
 * Created by lg on 17-4-26.
 */
public abstract class AbsStar {
    protected final AbsAction absAction;

    protected AbsStar(AbsAction absAction) {
        this.absAction = absAction;
    }

    public void doJob(){
        absAction.desc();
    }
}
