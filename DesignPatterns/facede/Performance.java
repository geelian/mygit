import java.util.Random;

/**
 * Created by lg on 17-4-26.
 */
public class Performance {
    private BasicSalary salary = new BasicSalary();


    public int getPerformanceValue(){
        int perf = (new Random()).nextInt(100);
        return  salary.getBasicSalary() * perf /100;
    }
}
