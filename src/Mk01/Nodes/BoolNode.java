package Mk01.Nodes;

import Mk01.Enums.OpTypes;
import Mk01.Enums.TypedTypes;

public abstract class BoolNode extends BaseNode
{
	public BoolNode(OpTypes inNodeType)
	{
		super(TypedTypes.Bool, inNodeType);
	}

	public abstract boolean isTrue();
}
