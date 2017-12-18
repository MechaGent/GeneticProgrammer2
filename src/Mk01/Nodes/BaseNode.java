package Mk01.Nodes;

import Mk01.PseudoMem;
import Mk01.Enums.OpTypes;
import Mk01.Enums.TypedTypes;
import Mk01.Exceptions.LengthMismatchException;

public abstract class BaseNode
{
	private final ParentRef Parent;
	private final TypedTypes TypedType;
	private final OpTypes OpType;
	private final BaseNode[] children;
	private final int[] OffspringSubSums;

	private int OffspringSum; // if negative, is inaccurate

	public BaseNode(TypedTypes inTypedType, OpTypes inNodeType)
	{
		this.Parent = new ParentRef();
		this.TypedType = inTypedType;
		this.OpType = inNodeType;
		this.children = new BaseNode[inNodeType.getNumberOfTotalChildren()];
		this.OffspringSubSums = new int[TypedTypes.NumTypes()];
		this.OffspringSum = -1;
	}

	public TypedTypes getTypedType()
	{
		return this.TypedType;
	}

	public int getSubSum()
	{
		final int[] temp = this.getSubSums();

		int result = 0;

		for (int subSum : temp)
		{
			result += subSum;
		}

		return result;
	}

	private boolean OffspringSumIsInaccurate()
	{
		return this.OffspringSum < 0;
	}

	private int[] getSubSums()
	{
		if (this.OffspringSumIsInaccurate())
		{
			final int[] temp = new int[this.OffspringSubSums.length];
			int newSum = 0;

			for (BaseNode child : children)
			{
				final int[] superTemp = child.getSubSums();

				for (int i = temp.length; i > 0; i--)
				{
					temp[i] += superTemp[i];
				}
			}

			for (int i = temp.length; i > 0; i--)
			{
				this.OffspringSubSums[i] = temp[i];
				newSum += this.OffspringSubSums[i];
			}

			this.OffspringSum = newSum;
		}

		return this.OffspringSubSums;
	}

	public OpTypes getOpType()
	{
		return this.OpType;
	}
	
	protected boolean canAcceptChildAt(TypedTypes childType, int childIndex)
	{
		return this.OpType.argIsAcceptable(childIndex, childType);
	}
	
	protected BaseNode getChildAt(int index)
	{
		return this.children[index];
	}
	
	protected BoolNode getChildAsBoolAt(int index)
	{
		return (BoolNode) this.children[index];
	}
	
	protected NumNode getChildAsNumAt(int index)
	{
		return (NumNode) this.children[index];
	}
	
	protected VoidNode getChildAsVoidAt(int index)
	{
		return (VoidNode) this.children[index];
	}

	protected static void link(BaseNode parent, BaseNode child, int childIndex)
	{
		final TypedTypes childTypedType = child.getTypedType();
		
		if (parent.canAcceptChildAt(childTypedType, childIndex))
		{
			parent.children[childIndex] = child;
			child.Parent.setParent(parent);
			child.Parent.setIndex(childIndex);
		}
		else
		{
			throw new IllegalArgumentException("Bad homeCast! Node of OpType " + parent.getOpType() + " cannot accept Node of TypedType " + childTypedType + " as child[" + childIndex + "].");
		}
	}
	
	/*
	 * Abstracted these out so accuracy flags could be triggered with minimal acrobatics
	 * -Later: can't remember why I did this, so undoing but leaving note
	 */
	public void linkAsChild(BaseNode parent, int childIndex)
	{
		link(parent, this, childIndex);
		this.OffspringSum = -1;
	}
	
	public void linkAsParent(BaseNode child, int childIndex)
	{
		link(this, child, childIndex);
		this.OffspringSum = -1;
	}
	
	/**
	 * links all children in the order by which they are received, then returns self
	 * @param inChildren
	 */
	public BaseNode linkAllChildren(BaseNode ... inChildren)
	{
		return linkAllChildren(this, inChildren);
	}
	
	/**
	 * links all children in the order by which they are received, then returns self
	 * @param parent
	 * @param inChildren
	 * @return
	 */
	protected static BaseNode linkAllChildren(BaseNode parent, BaseNode ... inChildren)
	{
		final int numKidsExpected = parent.getOpType().getNumberOfTotalChildren();
		
		if(numKidsExpected != inChildren.length)
		{
			throw new LengthMismatchException();
		}
		else
		{
			for(int i = 0; i < numKidsExpected; i++)
			{
				link(parent, inChildren[i], i);
			}
		}
		
		return parent;
	}
	
	protected static BaseNode[] cascadeCloneChildren(BaseNode parent)
	{
		
	}
	
	/**
	 * Make sure to override this in the rootNode class, otherwise NullPointerExceptions will happen
	 * @return
	 */
	protected PseudoMem getLocalPseudoMem()
	{
		return this.Parent.getParent().getLocalPseudoMem();
	}
	
	public abstract BaseNode cascadeClone(ParentRef stepParent, PseudoMem newPseudoMem);

	protected static class ParentRef
	{
		private BaseNode Parent;
		private int Index;

		public ParentRef()
		{
			this.Parent = null;
			this.Index = -1;
		}

		public ParentRef(BaseNode inParent, int inIndex)
		{
			this.Parent = inParent;
			this.Index = inIndex;
		}

		public BaseNode getParent()
		{
			return this.Parent;
		}

		public int getIndex()
		{
			return this.Index;
		}

		public void setParent(BaseNode inParent)
		{
			this.Parent = inParent;
		}

		public void setIndex(int inIndex)
		{
			this.Index = inIndex;
		}
	}
}
