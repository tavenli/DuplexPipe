package duplexpipe.sni;

import java.io.IOException;

/**
 * sni: sample net interface
 * 
 * @author redraiment
 * 
 */
public interface NetCommunicator
{
	public void close () throws IOException;

	public int read ( byte[] b, int off, int len ) throws IOException;

	public void write ( byte[] b, int off, int len ) throws IOException;
}
