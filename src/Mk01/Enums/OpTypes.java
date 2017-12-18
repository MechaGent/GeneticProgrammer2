package Mk01.Enums;

import java.util.EnumMap;

public enum OpTypes
{
	/**
	 * Children:
	 * Boolean LogicStatement <br>
	 * Void CodeBlock_ifChild0isTrue <br>
	 * Void CodeBlock_ifChild0isFalse <br>
	 * Void CodeBlock_executeAfterEvalIsDone< br>
	 */
	IfElse,

	/**
	 * Children:
	 * Boolean LogicStatement
	 * Void CodeBlock_ifChild0isTrue
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	While,

	/**
	 * Number indexOfPertinentNumber (like a pseudo-pointer)
	 */
	NumGetter,

	/**
	 * Number indexOfPertinentNumber (like a pseudo-pointer)
	 * Number valueToWhichPertinentNumberShallBeSet
	 * Void CodeBlock_executeAfterEvalIsDone
	 */
	NumSetter,

	/**
	 * Leaf node - returns internally-rolled var
	 */
	VarGetter,

	/**
	 * Leaf node
	 */
	ConstNum,

	/**
	 * Leaf node
	 */
	ConstBool,

	/**
	 * Leaf node - acts as plug
	 */
	ConstVoid,

	/**
	 * combines two Nums in such a way as to produce a Num
	 */
	BinNumNum,

	/**
	 * combines two Nums in such a way as to produce a Bool
	 */
	BinNumBool,

	/**
	 * combines two Bools in such a way as to produce a Bool
	 */
	BinBoolBool,

	/**
	 * Operates on a single Num in such a way as to produce a Num
	 */
	UnNumNum,

	/**
	 * Operates on a single Bool in such a way as to produce a Bool
	 */
	UnBoolBool,

	/**
	 * indicates the root node for a program
	 */
	Root;

	private static final OpTypes[] All = OpTypes.values();
	private static final EnumMap<OpTypes, OpTypesData> Data = initAcceptables(All);

	private static EnumMap<OpTypes, OpTypesData> initAcceptables(OpTypes[] in)
	{
		final EnumMap<OpTypes, OpTypesData> result = new EnumMap<OpTypes, OpTypesData>(OpTypes.class);

		for (int i = 0; i < in.length; i++)
		{
			result.put(in[i], initArgTables(in[i]));
		}

		return result;
	}

	private static OpTypesData initArgTables(OpTypes in)
	{
		final boolean[][] parameters;
		final TypedTypes returns;
		final boolean isTerminal;

		switch (in)
		{
			case BinBoolBool:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Bool), // arg1
												getPseudoMask(TypedTypes.Bool) // arg2
				};
				returns = TypedTypes.Bool;
				isTerminal = false;
				break;
			}
			case BinNumBool:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Num), // arg1
												getPseudoMask(TypedTypes.Num) // arg2
				};
				returns = TypedTypes.Bool;
				isTerminal = false;
				break;
			}
			case BinNumNum:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Num), // arg1
												getPseudoMask(TypedTypes.Num) // arg2
				};
				returns = TypedTypes.Num;
				isTerminal = false;
				break;
			}
			case ConstBool:
			{
				parameters = new boolean[0][0];
				returns = TypedTypes.Bool;
				isTerminal = true;
				break;
			}
			case ConstNum:
			{
				parameters = new boolean[0][0];
				returns = TypedTypes.Num;
				isTerminal = true;
				break;
			}
			case ConstVoid:
			{
				parameters = new boolean[0][0];
				returns = TypedTypes.Void;
				isTerminal = true;
				break;
			}
			case VarGetter:
			{
				parameters = new boolean[0][0];
				returns = TypedTypes.Num;
				isTerminal = true;
				break;
			}
			case IfElse:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Bool), // arg1
												getPseudoMask(TypedTypes.Void), // arg2
												getPseudoMask(TypedTypes.Void), // arg3
												getPseudoMask(TypedTypes.Void) // arg4
				};
				returns = TypedTypes.Void;
				isTerminal = false;
				break;
			}
			case NumGetter:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Num) // arg1
				};
				returns = TypedTypes.Num;
				isTerminal = false;
				break;
			}
			case NumSetter:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Num), // arg1
												getPseudoMask(TypedTypes.Num), // arg2
												getPseudoMask(TypedTypes.Void) // arg3
				};
				returns = TypedTypes.Void;
				isTerminal = false;
				break;
			}
			case UnBoolBool:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Bool) // arg1
				};
				returns = TypedTypes.Bool;
				isTerminal = false;
				break;
			}
			case UnNumNum:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Num) // arg1
				};
				returns = TypedTypes.Num;
				isTerminal = false;
				break;
			}
			case While:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Bool), // arg1
												getPseudoMask(TypedTypes.Void), // arg2
												getPseudoMask(TypedTypes.Void) // arg3
				};
				returns = TypedTypes.Void;
				isTerminal = false;
				break;
			}
			case Root:
			{
				parameters = new boolean[][] {
												getPseudoMask(TypedTypes.Void) // arg1
				};
				returns = TypedTypes.Void;
				isTerminal = false;
				break;
			}
			default:
			{
				throw new IllegalArgumentException();
			}

		}

		return new OpTypesData(parameters, returns, isTerminal);
	}

	private static boolean[] getPseudoMask(TypedTypes... inTypes)
	{
		final boolean[] result = new boolean[TypedTypes.NumTypes()];

		for (int i = 0; i < inTypes.length; i++)
		{
			result[inTypes[i].ordinal()] = true;
		}

		return result;
	}

	public boolean argIsAcceptable(int argIndex, TypedTypes in)
	{
		return Data.get(this).isAcceptableArgForParameter(argIndex, in);
	}

	public TypedTypes getReturnType()
	{
		return Data.get(this).returns;
	}

	public boolean isTerminalOp()
	{
		return Data.get(this).isTerminal;
	}

	/**
	 * be sure not to confuse this method and {@link getNumberOfTotalChildren()}.
	 * @return number of arguments expected
	 */
	public int getNumberOfExpectedArguments()
	{
		return Data.get(this).argTables.length;
	}

	/**
	 * be sure not to confuse this method and {@link getNumberOfExpectedArguments}.
	 * @return
	 */
	public int getNumberOfTotalChildren()
	{
		return getNumberOfTotalChildren(this);
	}

	public static int getNumberOfTotalChildren(OpTypes in)
	{
		final int result;

		switch (in)
		{
			case BinBoolBool:
			{
				result = 2;
				break;
			}
			case BinNumBool:
			{
				result = 2;
				break;
			}
			case BinNumNum:
			{
				result = 2;
				break;
			}
			case ConstBool:
			{
				result = 0;
				break;
			}
			case ConstNum:
			{
				result = 0;
				break;
			}
			case ConstVoid:
			{
				result = 0;
				break;
			}
			case IfElse:
			{
				result = 4;
				break;
			}
			case NumGetter:
			{
				result = 1;
				break;
			}
			case NumSetter:
			{
				result = 3;
				break;
			}
			case Root:
			{
				result = 1;
				break;
			}
			case UnBoolBool:
			{
				result = 1;
				break;
			}
			case UnNumNum:
			{
				result = 1;
				break;
			}
			case VarGetter:
			{
				result = 0;
				break;
			}
			case While:
			{
				result = 3;
				break;
			}
			default:
			{
				throw new IllegalArgumentException();
			}
		}

		return result;
	}

	private static class OpTypesData
	{
		private final boolean[][] argTables;
		private final TypedTypes returns;
		private final boolean isTerminal;

		public OpTypesData(boolean[][] inArgTables, TypedTypes inReturns, boolean inIsTerminal)
		{
			this.argTables = inArgTables;
			this.returns = inReturns;
			this.isTerminal = inIsTerminal;
		}

		private boolean isAcceptableArgForParameter(int paramIndex, TypedTypes in)
		{
			return this.argTables[paramIndex][in.ordinal()];
		}
	}
}
