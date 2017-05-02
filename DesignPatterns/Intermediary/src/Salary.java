package Intermediary.src;

/**
 * Created by lg on 17-4-26.
 */
public class Salary extends AbsColleague implements ISalary {
    public Salary(AbsMediator absMediator) {
        super(absMediator);
    }

    @Override
    public void increaseSalary() {
        super.mediator.down(this);
    }

    @Override
    public void decreaseSalary() {
        super.mediator.up(this);
    }
}
