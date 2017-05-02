package Intermediary.src;
/**
 * Created by lg on 17-4-26.
 */
public class Mediator extends AbsMediator {


    @Override
    public void up(Position position) {
        upPosition();
        upSalary();
        upTax();
    }

    private void upTax() {
        System.out.println("上升税收");
    }

    private void upSalary() {
        System.out.println("上升工资");
    }

    private void upPosition() {
        System.out.println("上升职位");
    }

    @Override
    public void down(Position position) {

    }

    @Override
    public void down(Salary salary) {
        System.out.println("工资下降");
    }

    @Override
    public void up(Salary salary) {
        upSalary();
        upTax();
    }

    @Override
    public void down(Tax tax) {
        downTax();
        upSalary();
    }

    private void downTax() {
        System.out.println("减税");
    }

    @Override
    public void up(Tax tax) {
        upTax();
        downSalary();
    }

    private void downSalary() {
        System.out.println("工资增加");
    }
}
