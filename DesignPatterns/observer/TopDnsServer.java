/**
 * Created by lg on 17-4-25.
 */
public class TopDnsServer extends DnsServer {
    @Override
    protected void sign(Recorder recorder) {
        recorder.setOwner("全球顶级Dns");
    }

    @Override
    protected boolean isLocal(Recorder recorder) {
        return true;
    }
}
