package Intermediary.src;
/**
 * Created by lg on 17-4-26.
 */
public class Position extends AbsColleague implements  IPosition {
    public Position(AbsMediator absMediator) {
        super(absMediator);
    }

    @Override
    public void promote() {
        super.mediator.down(this);
    }

    @Override
    public void demote() {
        super.mediator.up(this);
    }
}
