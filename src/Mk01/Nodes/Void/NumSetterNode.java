package Mk01.Nodes.Void;

import Mk01.PseudoMem;
import Mk01.Enums.OpTypes;
import Mk01.Nodes.BaseNode;
import Mk01.Nodes.NumNode;
import Mk01.Nodes.VoidNode;

public class NumSetterNode extends VoidNode
{
	private final PseudoMem LocalPseudoMem;
	
	public NumSetterNode()
	{
		super(OpTypes.NumSetter);
		this.LocalPseudoMem = super.getLocalPseudoMem();
	}
	
	private NumSetterNode(PseudoMem in)
	{
		super(OpTypes.NumSetter);
		this.LocalPseudoMem = in;
	}
	
	protected PseudoMem getLocalPseudoMem()
	{
		return this.LocalPseudoMem;
	}

	@Override
	public VoidNode runAndReturnNextBlock()
	{
		final NumNode rawTargetIndex = this.getChildAsNumAt(0);
		final int targetIndex = (int) rawTargetIndex.Returns();
		final NumNode rawPayload = this.getChildAsNumAt(1);
		final double payload = rawPayload.Returns();
		
		this.LocalPseudoMem.setDubAt(payload, targetIndex);
		
		return this.getChildAsVoidAt(2);
	}

	@Override
	public BaseNode cascadeClone(ParentRef stepParent, PseudoMem newPseudoMem)
	{
		final NumSetterNode result = new NumSetterNode(newPseudoMem);
		//final BaseNode children
	}
}
