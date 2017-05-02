import java.util.Random;

/**
 * Created by lg on 17-4-26.
 */
public class Attendance {
    public int getWorkDays(){
        return (new Random()).nextInt(30);
    }
}
