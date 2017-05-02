public abstract class AbstractCmd{
	// 对接受者的引用
	protected IReceiver zip = new ZipReceiver();
	protected IReceiver gzip = new GzipReceiver();
	// 抽象方法命令的具体单元
	public abstract boolean execute(String source,String to);
}

