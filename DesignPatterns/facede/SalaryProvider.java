/**
 * Created by lg on 17-4-26.
 */
public class SalaryProvider {
    private BasicSalary basicSalary = new BasicSalary();

    private  Bonus bonus = new Bonus();

    private Performance performance = new Performance();

    private Tax tax = new Tax();

    public int totalSalary(){
        return basicSalary.getBasicSalary() + bonus.getBonus() +
                performance.getPerformanceValue() - tax.getTax();
    }
}
