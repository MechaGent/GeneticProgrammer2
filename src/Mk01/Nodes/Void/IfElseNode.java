package Mk01.Nodes.Void;

import Mk01.Enums.OpTypes;
import Mk01.Enums.TypedTypes;
import Mk01.Nodes.BaseNode;
import Mk01.Nodes.BoolNode;
import Mk01.Nodes.VoidNode;

/**
 * Represents a classic {@code If/Else} control statement.
 * <br> Args:
 * <ul>
 * <li>{@link TypedTypes#Bool} BoolStatement</li>
 * <li>{@link TypedTypes#Void} IfBlock</li>
 * <li>{@code TypedTypes.Void} ElseBlock</li>
 * <li>{@code TypedTypes.Void} NextCodeBlock</li>
 * </ul>
 * 
 * @author MechaGent
 *
 */
public class IfElseNode extends VoidNode
{
	public IfElseNode()
	{
		super(OpTypes.IfElse);
	}

	@Override
	public VoidNode runAndReturnNextBlock()
	{
		final BoolNode child0 = this.getChildAsBoolAt(0);
		final boolean BoolStatement = child0.isTrue();
		
		final VoidNode runner;
		
		if(BoolStatement)
		{
			runner = this.getChildAsVoidAt(1);
		}
		else
		{
			runner = this.getChildAsVoidAt(2);
		}
		
		VoidNode.runChain(runner);
		
		return this.getChildAsVoidAt(3);
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
