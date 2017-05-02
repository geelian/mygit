import com.sun.org.apache.regexp.internal.RE;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Created by lg on 17-4-25.
 */
public abstract class DnsServer extends Observable implements Observer{
    @Override
    public void update(Observable observable, Object o) {
        Recorder recorder = (Recorder) o;
        if(isLocal(recorder)){
            recorder.setIp(genIpAddress());
        }else{
            responsFromUpperServer(recorder);
        }
        sign(recorder);
    }

    protected abstract void sign(Recorder recorder);

    public void setUpperServer(DnsServer dnsServer){
        super.deleteObservers();
        super.addObserver(dnsServer);
    }
    private void responsFromUpperServer(Recorder recorder) {
        super.setChanged();
        super.notifyObservers(recorder);
    }

    private String genIpAddress(){
        Random rand = new Random();
        String address = rand.nextInt(255) + "." + rand.nextInt(255) + "." +
                rand.nextInt(255) + "." + rand.nextInt(255);
        return address;
    }

    protected abstract boolean isLocal(Recorder recorder);

}
