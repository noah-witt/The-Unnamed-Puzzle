package com.noah_witt.tableGen;


public class TableObject {
	
	int size;
	private int[][] arr;
	
	public TableObject(int sizeL)
	{
		arr = new int[sizeL][sizeL];
		size = sizeL;
		resetTable();
	}
	
	public TableObject(int sizeL, int[][]a)
	{
		arr = new int[sizeL][sizeL];
		size = sizeL;
		importer(a);
	}
	
	public void resetTable()
	{
		for(int i=0; i<size; i++)
		{
			for(int e=0; e<size; e++)
			{
				arr[i][e] = 0;
			}
		}
	}
	
	
	public boolean use(Coordinate c)
	{
		if(!(c.x()>=0&&c.x()<size&&c.y()>=0&&c.y()<size))
		{
			//out of bounds
			return false;
		}
		else if(arr[c.x()][c.y()]==1)
		{
			//space is filled
			return false;
		}
		arr[c.x()][c.y()] =1;
		return true;
	}
	
	public void importer(int[][] a)
	{
		for(int i=0; i<size; i++)
		{
			for(int e=0; e<size; e++)
			{
				arr[i][e] = a[i][e];
			}
		}
	}
	public TableObject duplicate()
	{
		return new TableObject(size,arr);
	}
	public int[][] getArray()
	{
		int[][] a = new int[size][size];
		for(int i=0; i<size; i++)
		{
			for(int e=0; e<size; e++)
			{
				a[i][e] = arr[i][e];
			}
		}
		return a;
	}
}
