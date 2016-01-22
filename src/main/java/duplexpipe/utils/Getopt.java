package duplexpipe.utils;


public class Getopt
{
	public static NetInfo[] parse ( String[] args )
	{
		boolean tcpModel = true;
		NetInfo[] ni = new NetInfo[2];
		int ac = 0;

		for ( int i = 0; i < args.length; i++ )
		{
			if ( args[i].charAt ( 0 ) != '-' )
			{
				throw new IllegalArgumentException ( "Unknow argument!" );
			}

			switch ( args[i].charAt ( 1 ) )
			{
			case 'c':
			case 'l':
				// 如果参数多于两个，数组就会越界
				// 就会自动抛出异常，因此不用检查 ac 是否大于2
				ni[ac] = new NetInfo ();

				if ( args[i].charAt ( 1 ) == 'c' )
				{
					ni[ac].setHost ( args[++i] );
					ni[ac].setType ( NetInfo.CONNECT );
				}
				else
				{
					ni[ac].setType ( NetInfo.LISTEN );
				}
				ni[ac].setPort ( Integer.parseInt ( args[++i] ) );
				ni[ac].setModel ( tcpModel ? NetInfo.TCP : NetInfo.UDP );

				tcpModel = true;
				ac++;
				break;
			case 'u':
				tcpModel = false;
				break;
			case 'v':
				Verboser.getVerboser ().setVerbose ( true );
				break;
			default :
				throw new IllegalArgumentException ( "Show help!" );
			}
		}
		if ( ni[0] == null || ni[1] == null )
		{
			throw new IllegalArgumentException ();
		}

		return ni;
	}
}
