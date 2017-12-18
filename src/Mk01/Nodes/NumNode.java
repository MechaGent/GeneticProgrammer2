package Mk01.Nodes;

import Mk01.Enums.OpTypes;
import Mk01.Enums.TypedTypes;

public abstract class NumNode extends BaseNode
{
	private boolean isConstant;
	private boolean constantCheckIsAccurate;
	
	public NumNode(OpTypes inNodeType)
	{
		super(TypedTypes.Num, inNodeType);
		this.isConstant = false;
		this.constantCheckIsAccurate = false;
	}
	
	public abstract double Returns();
	
	/**
	 * 
	 * @return {@code true} if this represents a constant value, {@code false} otherwise.
	 */
	protected abstract boolean evalSelfForConstance();
	
	/**
	 * Checks whether the subtree, with this node as its root, evaluates to a constant
	 * @return
	 */
	public boolean representsConstant()
	{
		if(!this.constantCheckIsAccurate)
		{
			this.isConstant = this.evalSelfForConstance();
			this.constantCheckIsAccurate = true;
		}
		
		return this.isConstant;
	}
	/*
	/**
	 * conditions caught:<br>
	 * <ul>
	 * <li>self is {@link OpTypes#ConstNum}</li>
	 * <li></li>
	 * </ul>
	 * @return
	 
	private boolean evalSelfForConstance()
	{
		return null;
	}
	*/
}
