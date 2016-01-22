package duplexpipe.utils;

/**
 * <p>
 * <B>Verboser: </B>当程序开启 -v 选项时，将向 stderr 输出详细的提示。
 * </p>
 * <p>
 * 该类为单件类，通过 getVerboser() 获得类实例。
 * </p>
 * 
 * @author redraiment
 * @version 0.1.0
 * @category Logger
 */
public class Verboser
{
	private static Verboser v = new Verboser ();
	private boolean verbose = false;

	/** 单件类的构造方法为私有。 */
	private Verboser ()
	{
	}

	/** 获得类实例。 */
	public static Verboser getVerboser ()
	{
		return v;
	}

	/** 是否输出提示信息。 */
	public boolean isVerbose ()
	{
		return verbose;
	}

	/** 设置是否输出提示信息。 */
	public void setVerbose ( boolean verbose )
	{
		this.verbose = verbose;
	}

	/** 大声喊出提示信息！ */
	public void holler ( String msg )
	{
		if ( verbose )
		{
			System.err.println ( msg );
		}
	}

	/** 致命错误！提示后退出。 */
	public void bail ( String msg )
	{
		holler ( msg );
		System.exit ( 1 );
	}
}
