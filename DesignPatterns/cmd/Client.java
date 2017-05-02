/**
 * Created by lg on 17-4-24.
 */
public class Client {
    public static void main(String [] args){
        AbstractCmd cmd = new ZipCompressCmd();

        Invoker invoker = new Invoker(cmd);
        invoker.execute("a","b");
    }
}
