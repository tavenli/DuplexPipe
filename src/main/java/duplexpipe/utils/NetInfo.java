package duplexpipe.utils;

/**
 * 记录连接的信息，包括：<br>
 * <ol>
 * <li>目标IP地址；</li>
 * <li>连接端口；</li>
 * <li>连接模式。TCP或UDP。</li>
 * </ol>
 * 由 Getopt 通过处理命令行参数而生成。
 * 
 * @author redraiment
 * @version 0.3.0
 * @category InnerData
 */
public class NetInfo
{
	/** Type */
	public final static int CONNECT = 0;
	public final static int LISTEN = 1;

	/** Model */
	public final static int TCP = 0;
	public final static int UDP = 1;

	private String host;
	private int model;
	private int port;
	private int type;

	public String getHost ()
	{
		return host;
	}

	public int getModel ()
	{
		return model;
	}

	public int getPort ()
	{
		return port;
	}

	public int getType ()
	{
		return type;
	}

	public void setHost ( String host )
	{
		this.host = host;
	}

	public void setModel ( int model )
	{
		if ( model != TCP && model != UDP )
			throw new IllegalArgumentException ( "unknow model: " + model );
		this.model = model;
	}

	public void setPort ( int port )
	{
		if ( 65535 < port || port < 1 )
			throw new IllegalArgumentException ( "unknow port: " + port );
		this.port = port;
	}

	public void setType ( int type )
	{
		if ( type != LISTEN && type != CONNECT )
			throw new IllegalArgumentException ( "unknow type: " + type );
		this.type = type;
	}
}
