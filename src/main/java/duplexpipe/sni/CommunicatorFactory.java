package duplexpipe.sni;

import duplexpipe.utils.NetInfo;

/**
 * <p>
 * <B>CommunicatorFactory: </B>通讯器工厂，根据NetInfo的信息创建通讯器。
 * </p>
 * <p>
 * 该类主要是为了屏蔽创建网络连接时使用TCP或UDP等协议细节，简化主程序的编码。
 * </p>
 * 
 * @author redraiment
 * @version 0.3.0
 * @category Factory
 */
public class CommunicatorFactory
{
	public static NetCommunicator createNetCommunicator ( NetInfo ni )
	{
		switch ( ( ni.getModel () << 1 ) + ni.getType () )
		{
		case 0:
			return new TCPCommunicator ( ni.getHost (), ni.getPort () );
		case 1:
			return new TCPCommunicator ( ni.getPort () );
		case 2:
			return new UDPCommunicator ( ni.getHost (), ni.getPort () );
		case 3:
			return new UDPCommunicator ( ni.getPort () );
		default :
			return null;
		}
	}
}
