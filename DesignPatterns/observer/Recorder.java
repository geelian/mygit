/**
 * Created by lg on 17-4-25.
 */
public class Recorder {
    private String domain;
    private String ip;
    private String owner;

    @Override
    public String toString() {
        return "Recorder{" +
                "domain='" + domain + '\'' +
                ", ip='" + ip + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
