package duplexpipe.sni;

import java.io.IOException;

import duplexpipe.utils.Verboser;

/**
 * <p>
 * <B>PipeIO: </B>管道的输入输出操作。
 * </p>
 * <p>
 * 通过制定管道的输入输出，来实现进程通信。<br>
 * 因为请求和相应是异步的，因此采用多线程。<br>
 * 为提高性能，内部使用带缓冲区的输入输出流。
 * </p>
 * 
 * @author redraiment
 * @version 0.1.0
 * @category Thread
 */
public class PipeIO extends Thread
{
	private Verboser v = Verboser.getVerboser ();
	private NetCommunicator in = null;
	private NetCommunicator out = null;

	/** 需要指定管道的输入流和输出流。 */
	public PipeIO ( NetCommunicator in, NetCommunicator out )
	{
		this.in = in;
		this.out = out;
	}

	/** 从输入流获取数据，完整地写到输出流。 */
	public void run ()
	{
		if ( in == null || out == null )
			return;

		try
		{
			byte[] buffer = new byte[1024];
			int len;

			// 核心：读写数据
			while ( ( len = in.read ( buffer, 0, buffer.length ) ) != -1 )
			{
				out.write ( buffer, 0, len );
			}
		}
		catch ( IOException e )
		{
			v.holler ( "Maybe connect is closed." );
		}

		try
		{
			// 无论如何都关闭IO stream。
			if ( in != null )
			{
				in.close ();
			}
			if ( out != null )
			{
				out.close ();
			}
		}
		catch ( IOException e )
		{
			v.holler ( "IO stream can not close?" );
			v.holler ( "Maybe connect is closed!" );
		}
	}
}
