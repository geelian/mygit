/**
 * Created by lg on 17-4-24.
 */
public class GzipReceiver implements IReceiver {
    @Override
    public boolean compress(String source, String to) {
        System.out.println(source + "--->" + to + "gzip compress");
        return true;
    }

    @Override
    public boolean uncompress(String soure, String to) {
        System.out.println(soure + "--->" + to + "gzip uncompress");
        return true;
    }
}
