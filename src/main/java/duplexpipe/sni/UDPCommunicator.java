package duplexpipe.sni;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import duplexpipe.utils.Verboser;

public class UDPCommunicator implements NetCommunicator
{
	Verboser v = Verboser.getVerboser ();
	DatagramSocket dsocket;
	InetAddress addr = null;
	int port = -1;

	public UDPCommunicator ( int port )
	{
		try
		{
			dsocket = new DatagramSocket ( port );
		}
		catch ( IOException e )
		{
			v.bail ( "port " + port + " has been bounded." );
		}
	}

	public UDPCommunicator ( String host, int port )
	{
		try
		{
			dsocket = new DatagramSocket ();
			addr = InetAddress.getByName ( host );
			this.port = port;
		}
		catch ( SocketException e )
		{
			v.bail ( "remote port " + port + " may not open." );
		}
		catch ( UnknownHostException e )
		{
			v.bail ( "remote machine " + host + " may not open." );
		}
	}

	@Override
	public void close () throws IOException
	{
		if ( dsocket != null )
			dsocket.close ();
	}

	@Override
	public int read ( byte[] b, int off, int len ) throws IOException
	{
		DatagramPacket packet = new DatagramPacket ( b, len - off );
		dsocket.receive ( packet );

		if ( addr == null )
		{
			addr = packet.getAddress ();
		}
		if ( port < 1 || 65535 < port )
		{
			port = packet.getPort ();
		}

		return packet.getLength ();
	}

	@Override
	public void write ( byte[] b, int off, int len ) throws IOException
	{
		if ( addr == null || port < 1 || 65535 < port )
		{
			return;
		}

		DatagramPacket packet = new DatagramPacket ( b, len - off, addr, port );
		dsocket.send ( packet );
	}
}
