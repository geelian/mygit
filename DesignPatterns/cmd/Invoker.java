/**
 * Created by lg on 17-4-24.
 */
public class Invoker {
    private AbstractCmd cmd;
    public Invoker(AbstractCmd _cmd){
        this.cmd = _cmd;
    }

    public boolean execute(String source,String to){
        return cmd.execute(source,to);
    }
}
