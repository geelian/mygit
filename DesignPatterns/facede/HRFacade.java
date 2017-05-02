import java.util.Date;

/**
 * Created by lg on 17-4-26.
 */
public class HRFacade {
    private  SalaryProvider salaryProvider = new SalaryProvider();
    private  Attendance attendance = new Attendance();

    public int querySalary(String name,Date date){
        return salaryProvider.totalSalary();
    }

    public int queryWorkDays(String name){
        return  attendance.getWorkDays();
    }
}
