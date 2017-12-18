package Mk01.Nodes.Void;

import Mk01.PseudoMem;
import Mk01.Enums.OpTypes;
import Mk01.Nodes.VoidNode;

public class RootNode extends VoidNode
{
	private static final ConstVoidNode NullVoidNode = new ConstVoidNode();
	
	private final PseudoMem CentralMem;
	
	public RootNode(int numMemSlots)
	{
		super(OpTypes.Root);
		this.CentralMem = new PseudoMem(numMemSlots);
	}

	@Override
	public VoidNode runAndReturnNextBlock()
	{
		final VoidNode child0 = this.getChildAsVoidAt(0);
		VoidNode.runChain(child0);
		
		return NullVoidNode;
	}
	
	@Override
	protected PseudoMem getLocalPseudoMem()
	{
		return this.CentralMem;
	}
}
