package Mk01;

public class PseudoMem
{
	private final double[] Mem;
	
	public PseudoMem(int NumMemSlots)
	{
		this.Mem = new double[NumMemSlots];
	}
	
	public double getDubFrom(int index)
	{
		return this.Mem[index];
	}
	
	public void setDubAt(double in, int index)
	{
		this.Mem[index] = in;
	}
}
