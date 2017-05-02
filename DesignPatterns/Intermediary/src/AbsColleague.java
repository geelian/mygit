package Intermediary.src;
/**
 * Created by lg on 17-4-26.
 */
public abstract class AbsColleague {
    protected AbsMediator mediator;
    public AbsColleague(AbsMediator absMediator){
        this.mediator = absMediator;
    }
}
