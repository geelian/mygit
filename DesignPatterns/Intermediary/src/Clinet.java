package Intermediary.src;
/**
 * Created by lg on 17-4-26.
 */
public class Clinet {
    public static void main(String [] args){
        Mediator mediator = new Mediator();

        IPosition position = new Position(mediator);
        ISalary salary = new Salary(mediator);
        ITax tax = new Tax(mediator);
        System.out.println("==职位提升==");
        position.promote();
    }
}
