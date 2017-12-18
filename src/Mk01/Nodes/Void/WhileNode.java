package Mk01.Nodes.Void;

import Mk01.Enums.OpTypes;
import Mk01.Enums.TypedTypes;
import Mk01.Nodes.BaseNode;
import Mk01.Nodes.BoolNode;
import Mk01.Nodes.VoidNode;

/**
 * Represents a classic {@code While} control statement.
 * <br> Args:
 * <ul>
 * <li>{@link TypedTypes#Bool} BoolStatement</li>
 * <li>{@link TypedTypes#Void} WhileBlock</li>
 * <li>{@code TypedTypes.Void} NextCodeBlock</li>
 * </ul>
 * 
 * @author MechaGent
 *
 */
public class WhileNode extends VoidNode
{
	private static final int hardLoopLimit = 10;
	
	/**
	 * Children:
	 * Boolean LogicStatement
	 * Void CodeBlock_ifChild0isTrue
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	public WhileNode()
	{
		super(OpTypes.While);
	}

	@Override
	public VoidNode runAndReturnNextBlock()
	{
		final BoolNode child0 = this.getChildAsBoolAt(0);
		final VoidNode child1 = this.getChildAsVoidAt(1);
		int localLoopCount = 0;
		
		while((localLoopCount < hardLoopLimit) && child0.isTrue())
		{
			VoidNode.runChain(child1);
			localLoopCount++;
		}
		
		return this.getChildAsVoidAt(2);
	}
	
	/**
	 * this method checks whether the BoolStatement child is unreasonable, ie infinite or many-cycled.
	 * @return true, regardless of reality - TODO
	 */
	public boolean BoolStatementIsReasonable()
	{
		final boolean result;
		
		result = true;
		
		return result;
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
}
