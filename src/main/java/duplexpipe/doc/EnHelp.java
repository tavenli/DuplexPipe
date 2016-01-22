package duplexpipe.doc;

import duplexpipe.DuplexPipe;

/**
 * The manual in english.
 * 
 * @author redraiment
 * @version 0.1.0
 * @category Help
 */
public class EnHelp extends Help
{
	@Override
	public void manual ()
	{
		v.setVerbose ( true );
		v.holler ( "[v" + DuplexPipe.version + " in Java]" );
		v.holler ( "Author: " + DuplexPipe.author );
		v.holler ( "Email: " + DuplexPipe.email );
		v.holler ( "HomePage：" + DuplexPipe.homepage + "\n" );
		v.holler ( "Usage: java pipe.DuplexPipe [-vh] model model" );
		v.holler ( "Option:" );
		v.holler ( "\t-v\t\tverbose" );
		v.holler ( "\t-h\t\tshow this manual" );
		v.holler ( "Model:" );
		v.holler ( "\t-u\t\tUDP model" );
		v.holler ( "\t-l port\t\tlisten mode, for inbound connects" );
		v.holler ( "\t-c host port\tconnect remote host" );
		v.holler ( "Example:" );
		v.holler ( "\tjava pipe.DuplexPipe -c 192.168.1.100 3389 -u -l 1234\n" );
		v.bail ( "\tThis would set the program to listen for connections on\n"
				+ "\tport 1234 with UDP model and when a local connection is\n"
				+ "\tdetected a further connection will be made to port 3389\n"
				+ "\tof the remote machine at 192.168.1.100." );
	}
}
