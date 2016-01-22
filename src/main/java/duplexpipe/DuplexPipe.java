package duplexpipe;

import duplexpipe.doc.EnHelp;
import duplexpipe.doc.Help;
import duplexpipe.sni.CommunicatorFactory;
import duplexpipe.sni.NetCommunicator;
import duplexpipe.sni.PipeIO;
import duplexpipe.utils.Getopt;
import duplexpipe.utils.NetInfo;
import duplexpipe.utils.Verboser;

/**
 * <p>
 * <B>DuplexPipe: </B>双向管道！
 * </p>
 * <p>
 * 本程序是一个双向管道。传统的管道只能从一端输入、一端输出。双向管道不仅可以<br>
 * 让进程 A 的输出作为进程 B 的输入，也会让进程 B 的输出作为进程 A 的输入。<br>
 * 这样就可以让两个进程实现交流。
 * </p>
 * <p>
 * 本程序主要是 TCP 转发工具。允许监听本地端口，也可以主动连接远程端口。<br>
 * 如果和瑞士军刀“nc -e”配合使用， 就能实现本地进程和网络进程任意沟通。
 * </p>
 * <p>
 * 程序的具体使用方法可以参见 help()。
 * </p>
 * 
 * @author redraiment
 * @version 0.3.0
 * @category Main
 */
public class DuplexPipe
{
	public final static String version = "0.3.0";
	public final static String author = "redraiment";
	public final static String email = "redraiment@gmail.com";
	public final static String homepage = "http://code.google.com/p/duplexpipe";

	private static Help help = new EnHelp ();

	public static void main ( String[] args )
	{
		Verboser v = Verboser.getVerboser ();
		NetCommunicator[] nc = new NetCommunicator[2];
		PipeIO[] p = new PipeIO[2];
		NetInfo[] ni = null;

		try
		{
			ni = Getopt.parse ( args );
		}
		catch ( Exception e )
		{
			help.manual ();
		}

		while ( true )
		{
			// connect net, create two sockets.
			nc[0] = CommunicatorFactory.createNetCommunicator ( ni[0] );
			nc[1] = CommunicatorFactory.createNetCommunicator ( ni[1] );

			p[0] = new PipeIO ( nc[0], nc[1] );
			p[1] = new PipeIO ( nc[1], nc[0] );

			p[0].start ();
			p[1].start ();
			try
			{
				p[0].join ();
				p[1].join ();
			}
			catch ( InterruptedException e )
			{
				v.holler ( "Don't ask me!" );
				v.holler ( "I nerver interrupted it!" );
			}
		}
	}
}
