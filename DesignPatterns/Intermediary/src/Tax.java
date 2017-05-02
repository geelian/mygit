package Intermediary.src;

/**
 * Created by lg on 17-4-26.
 */
public class Tax extends AbsColleague implements ITax {
    public Tax(AbsMediator absMediator) {
        super(absMediator);
    }

    @Override
    public void raise() {
        super.mediator.down(this);
    }

    @Override
    public void drop() {
        super.mediator.up(this);
    }
}
