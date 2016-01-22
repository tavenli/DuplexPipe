package duplexpipe.doc;

import duplexpipe.utils.Verboser;

/**
 * <p>
 * <B>Help: </B>提供所有帮助信息！
 * </p>
 * <p>
 * 为以后扩展语言提供统一接口。
 * </p>
 * 
 * @author redraiment
 * @version 0.3.0
 * @category Help
 */
public abstract class Help
{
	protected Verboser v = Verboser.getVerboser ();

	public abstract void manual ();
}
