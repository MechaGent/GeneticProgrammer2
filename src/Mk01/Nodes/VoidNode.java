package Mk01.Nodes;

import Mk01.Enums.OpTypes;
import Mk01.Enums.TypedTypes;

public abstract class VoidNode extends BaseNode
{
	public VoidNode(OpTypes inNodeType)
	{
		super(TypedTypes.Void, inNodeType);
	}

	public abstract VoidNode runAndReturnNextBlock();
	
	protected static void runChain(VoidNode in)
	{
		VoidNode currNode = in;
		
		while(currNode.getOpType() != OpTypes.ConstVoid)
		{
			currNode = currNode.runAndReturnNextBlock();
		}
	}
}
