package com.noah_witt.tableGen;

//represents a coordinate pair

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int xL, int yL)
	{
		x=xL;
		y=yL;
	}
	
	public int x()
	{
		return x;
	}
	
	public int y()
	{
		return y;
	}
	
	public int setX(int xL)
	{
		x = xL;
		return x;
	}
	
	public int setY(int yL)
	{
		y = yL;
		return y;
	}
	public int changeX(int c)
	{
		return x=x+c;
	}
	public int changeY(int c)
	{
		return y=y+c;
	}
	public String toString()
	{
		return "("+x+","+y+")";
	}
	
	public String  toStringStore()
	{
		return x+","+y;
	}
	
	public void fromStringStore(String in)
	{
		//System.out.println(in);
		String[] parts = in.split(",");
		x = Integer.parseInt(parts[0]);
		y = Integer.parseInt(parts[1]);
		
		//System.out.println("("+x+","+y+")");
	}
	
	public Coordinate duplicate()
	{
		return new Coordinate(x,y);
	}
}
