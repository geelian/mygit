/**
 * Created by lg on 17-4-24.
 */
public class ZipReceiver implements IReceiver {
    @Override
    public boolean compress(String source, String to) {
        System.out.println(source + "--->" + to + "zip compress");
        return true;
    }

    @Override
    public boolean uncompress(String soure, String to) {
        System.out.println(soure + "--->" + to + "zip uncompress");
        return true;
    }
}
