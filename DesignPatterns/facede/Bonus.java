/**
 * Created by lg on 17-4-26.
 */
public class Bonus {
    private Attendance attendance = new Attendance();

    public int getBonus(){
        int workDays = attendance.getWorkDays();

        int bonus = workDays * 1800 /30;
        return bonus;
    }
}
