package Intermediary.src;
/**
 * Created by lg on 17-4-26.
 */
public abstract class AbsMediator {
    protected  final ISalary salary;
    protected final IPosition position;
    protected final ITax tax;

    protected AbsMediator() {
        this.salary = new Salary(this);
        this.position = new Position(this);
        this.tax = new Tax(this);
    }

    public abstract void up(Position position);

    public abstract void down(Position position);

    public abstract void down(Salary salary);

    public abstract void up(Salary salary);

    public abstract void down(Tax tax);

    public abstract void up(Tax tax);
}
