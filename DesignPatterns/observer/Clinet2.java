import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lg on 17-4-25.
 */
public class Clinet2 {
    public static void main(String [] args) throws IOException {
        DnsServer sh = new SHDnsServer();
        DnsServer china = new ChinaTopDnsServer();
        DnsServer top = new TopDnsServer();

        china.setUpperServer(top);
        sh.setUpperServer(china);

        while(true){
            System.out.println(">>>");
            String domain = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            if(domain.equalsIgnoreCase("n")){
                return;
            }
            Recorder recorder = new Recorder();
            recorder.setDomain(domain);
            sh.update(null,recorder);
            System.out.println("dns end");
            System.out.println(recorder);

        }
    }
}
