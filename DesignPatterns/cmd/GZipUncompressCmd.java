public class GZipUncompressCmd extends AbstractCmd{
	public boolean execute(String source,String to){
		return super.gzip.uncompress(source,to);
	}
}
