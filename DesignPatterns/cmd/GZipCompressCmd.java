public class GZipCompressCmd extends AbstractCmd{
	public boolean execute(String source,String to){
		return super.gzip.compress(source,to);
	}
}
