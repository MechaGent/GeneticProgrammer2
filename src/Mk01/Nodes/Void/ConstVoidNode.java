package Mk01.Nodes.Void;

import Mk01.Enums.OpTypes;
import Mk01.Nodes.BaseNode;
import Mk01.Nodes.VoidNode;

public class ConstVoidNode extends VoidNode
{
	public ConstVoidNode()
	{
		super(OpTypes.ConstVoid);
	}

	@Override
	public VoidNode runAndReturnNextBlock()
	{
		return null;
	}
	
	@Override
	public void linkAsChild(BaseNode parent, int childIndex)
	{
		BaseNode.link(parent, this, childIndex);
	}
	
	@Override
	public void linkAsParent(BaseNode child, int childIndex)
	{
		throw new UnsupportedOperationException();
	}
}
