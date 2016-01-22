package duplexpipe.doc;

import duplexpipe.DuplexPipe;

/**
 * 中文帮助文档
 * 
 * @author redraiment
 * @version 0.3.0
 * @category Help
 */
public class ChHelp extends Help
{
	@Override
	public void manual ()
	{
		v.setVerbose ( true );
		v.holler ( "[v" + DuplexPipe.version + " in Java]" );
		v.holler ( "作者: " + DuplexPipe.author );
		v.holler ( "有任何建议，欢迎联系" + DuplexPipe.email );
		v.holler ( "项目主页：" + DuplexPipe.homepage + "\n" );
		v.holler ( "用法: java pipe.DuplexPipe [-vh] model model" );
		v.holler ( "选项:" );
		v.holler ( "\t-v\t\t输出一些提示信息" );
		v.holler ( "\t-h\t\t显示本帮助文档" );
		v.holler ( "模式:" );
		v.holler ( "\t-u\t\tUDP 模式" );
		v.holler ( "\t-l port\t\t监听本地端口" );
		v.holler ( "\t-c host port\t连接远程主机的端口" );
		v.holler ( "示例:" );
		v.holler ( "\tjava pipe.DuplexPipe -c 192.168.1.100 3389 -l 1234" );
		v.bail ( "\t将本地 1234 号 UDP 端口上的信息转发给 192.168.1.100 的 3389 TCP 端口" );
	}
}
