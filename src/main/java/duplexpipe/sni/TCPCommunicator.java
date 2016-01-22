package duplexpipe.sni;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import duplexpipe.utils.Verboser;

public class TCPCommunicator implements NetCommunicator
{
	private Socket s;
	private InputStream in;
	private OutputStream out;

	public TCPCommunicator ( int port )
	{
		try
		{
			ServerSocket ss = new ServerSocket ( port );
			s = ss.accept ();
			ss.close ();

			in = new BufferedInputStream ( s.getInputStream () );
			out = new BufferedOutputStream ( s.getOutputStream () );
		}
		catch ( IOException e )
		{
			e.printStackTrace ();
		}
	}

	public TCPCommunicator ( String host, int port )
	{
		try
		{
			s = new Socket ( host, port );

			in = new BufferedInputStream ( s.getInputStream () );
			out = new BufferedOutputStream ( s.getOutputStream () );
		}
		catch ( UnknownHostException e )
		{
			Verboser.getVerboser ().bail ( e.getMessage () );
		}
		catch ( IOException e )
		{
			e.printStackTrace ();
		}
	}

	@Override
	public void close () throws IOException
	{
		if ( out != null )
			out.close ();
		if ( in != null )
			in.close ();
		if ( s != null )
			s.close ();
	}

	@Override
	public int read ( byte[] b, int off, int len ) throws IOException
	{
		return in.read ( b, off, len );
	}

	@Override
	public void write ( byte[] b, int off, int len ) throws IOException
	{
		out.write ( b, off, len );
		out.flush ();
	}
}
