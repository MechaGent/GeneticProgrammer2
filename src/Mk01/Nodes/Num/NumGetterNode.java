package Mk01.Nodes.Num;

import Mk01.PseudoMem;
import Mk01.Enums.OpTypes;
import Mk01.Enums.TypedTypes;
import Mk01.Nodes.BaseNode;
import Mk01.Nodes.NumNode;

/**
 * Represents fetching a number from "central memory".
 * <br> Args:
 * <ul>
 * <li>{@link TypedTypes#Num} MemIndex</li>
 * </ul>
 * 
 * @author MechaGent
 *
 */
public class NumGetterNode extends NumNode
{
	private final PseudoMem CentralMem;
	
	public NumGetterNode()
	{
		super(OpTypes.NumGetter);
		this.CentralMem = super.getLocalPseudoMem();
	}

	@Override
	public double Returns()
	{
		final NumNode child0 = this.getChildAsNumAt(0);
		final int index = (int) child0.Returns();
		return this.CentralMem.getDubFrom(index);
	}

	@Override
	protected boolean evalSelfForConstance()
	{
		return false;
	}

	@Override
	public void linkAsChild(BaseNode parent, int childIndex)
	{
		BaseNode.link(parent, this, childIndex);
	}
	
	@Override
	public void linkAsParent(BaseNode child, int childIndex)
	{
		BaseNode.link(this, child, childIndex);
	}
	
	@Override
	protected PseudoMem getLocalPseudoMem()
	{
		return this.CentralMem;
	}
}
