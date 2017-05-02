import java.util.Date;

/**
 * Created by lg on 17-4-26.
 */
class Client4 {
    public static void main(String [] args){
        HRFacade hrFacade = new HRFacade();
        int salary = hrFacade.querySalary("张三",new Date(System.currentTimeMillis()));

        System.out.println(salary);

        System.out.println(hrFacade.queryWorkDays("李四"));
    }
}
