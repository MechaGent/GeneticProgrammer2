package Mk01.Enums;

public enum TypedTypes
{
	Void, Num, Bool;
	
	private static final TypedTypes[] All = TypedTypes.values();
	
	public static int NumTypes()
	{
		return All.length;
	}
}
