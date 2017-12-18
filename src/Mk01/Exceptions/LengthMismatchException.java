package Mk01.Exceptions;

public class LengthMismatchException extends IllegalArgumentException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6618766178154183105L;
	
	public LengthMismatchException()
	{
		super();
	}
	
	public LengthMismatchException(String message)
	{
		super(message);
	}
}
