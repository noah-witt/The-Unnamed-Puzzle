package com.noah_witt.tableGen;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.math.BigInteger;


public class Table {
	
	final static int size = 10;
	final static int squelch =99;
	final static boolean save=true;
	private static int ans=0;
	private static long startTime;
	final private static int benchmarkN =5;
	final private static boolean benchmark = false;
	
	/*
	final static int x=0;
	final static int y=10;
	*/

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		String start="";
		System.out.println("Table Project");
		Scanner s = new Scanner(System.in);
		System.out.println("Do you wish to enter a starting value? y or n");
		if(s.next()=="y")
		{
			//STARTING VALUE HERE
			start = "d1afbf";
		}
		else
		{
			start ="0";
		}
		s.close();
		
		if(benchmark)
		{
			startTime = System.currentTimeMillis();
		}
		//run(start);
		//run("0");
		runV2();
	}
	
	
	
	@SuppressWarnings("unused")
	private static void run(String start)
	{
		TableObject t= new TableObject(size);
		Integer n = Integer.valueOf(start,16);
		System.out.println("Start at:"+n.toString());
		
		while(n.toString().length()<Math.pow(size, 2))
		{
			t.resetTable();
			tryN(new Coordinate(0,0),Integer.toOctalString(n),t);
			n++;
		}
		
	}
	
	private static void tryN(Coordinate c,String n, TableObject t)
	{
		int numberOfMoves = (size*size)-1;
		int i=0;
		boolean Ndone=true;
		while(i<=numberOfMoves&&Ndone)
		{
			String move ="0";
			if(n.length()>i)
			{
				move = ""+n.charAt(i);
				
			}
			else
			{
				move = "0";
			}
			
			
			if(Integer.valueOf(move)==0)
			{
				c.setY(c.y()-3);
			}
			else if(Integer.valueOf(move)==1)
			{
				c.setY(c.y()-2);
				c.setX(c.x()+2);
				
			}
			else if(Integer.valueOf(move)==2)
			{
				c.setX(c.x()+3);
			}
			else if(Integer.valueOf(move)==3)
			{
				c.setY(c.y()+2);
				c.setX(c.x()+2);
			}
			else if(Integer.valueOf(move)==4)
			{
				c.setY(c.y()+3);
			}
			else if(Integer.valueOf(move)==5)
			{
				c.setY(c.y()+2);
				c.setX(c.x()-2);
				
			}
			else if(Integer.valueOf(move)==6)
			{
				c.setX(c.x()-3);
			}
			else if(Integer.valueOf(move)==7)
			{
				c.setY(c.y()-2);
				c.setX(c.x()-2);
			}
			else
			{
				System.out.println("ERROR");
			}
			
			boolean r = t.use(c);
			if(r)
			{
				//worked
			}
			else
			{
				Ndone = false;
			}
			
			i++;
		}
		if(Ndone)
		{
			System.out.println("BOY, "+n);
			/*
			try {
				//PrintWriter out = new PrintWriter("data.txt");
				//out.println(""+n);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
		}
		else
		{
			if(i>=squelch)
			{
				
				System.out.println("Places before error: "+i);
				System.out.println("done with moves:"+n);
				if(benchmark)
				{
					ans++;
					if(ans==benchmarkN)
					{
						long endTime = System.currentTimeMillis();
						System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 
					}
				}
			}
		}
	}
	
	private static void runV2()
	{
		v2primary();
	}
	
	private static void v2primary()
	{
		for(int i=0;i<size;i++)
		{
			for(int e=0;e<size;e++)
			{
				System.out.println("PRIMARY LOOP:"+i+","+e);
				TableObject t = new TableObject(size);
				Coordinate c=new Coordinate(i,e);
				t.use(c);
				v2recursion(1,c,"",t,new Coordinate(i,e));
			}
		}
		
	}
	private static void v2recursion(int d,Coordinate c,String n, TableObject t,Coordinate start)
	{
		if(d>=squelch)
		{
			/*
			try {
				PrintWriter writer;
				writer = new PrintWriter("results.txt");
				writer.println("depth: "+(d)+" "+start.toString()+" "+n);
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			//BigInteger BigInt = new BigInteger(n,8);
			
			if(save){
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("results2.txt", true));
					bw.write(d+" "+start.toString()+" "+n);
					bw.newLine();
					bw.flush();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally { // always close the file
					if (bw != null) {
						try {
							bw.close();
						} catch (IOException ioe2) {
							// just ignore it
						}
					}
				}
			}
			if(benchmark)
			{
				ans++;
				if(ans==benchmarkN)
				{
					long endTime = System.currentTimeMillis();
					System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 
				}
			}
			
			System.out.println("PATH FOUND Starting at:"+start.toString()+" using path:"+n+"\n depth:"+d);
		}
		
		String newN="";
		String cStore = c.toStringStore();
		int[][] oldT = t.getArray();
		//Move 0
		//c.setY(c.y()-3);
		c.changeY(-3);
		if(t.use(c))
		{
			newN = n+"0";
			v2recursion(d+1,c,newN,t,start);
		}
		t.importer(oldT);
		c.fromStringStore(cStore);
		
		//Move 1
		//c.setY(c.y()-2);
		//c.setX(c.x()+2);
		c.changeY(-2);
		c.changeX(+2);
		if(t.use(c))
		{
			newN = n+"1";
			v2recursion(d+1,c,newN,t,start);
		}
		
		t.importer(oldT);
		c.fromStringStore(cStore);
		
		//Move 2
		//c.setX(c.x()+3);
		c.changeX(3);
		if(t.use(c))
		{
			newN = n+"2";
			v2recursion(d+1,c,newN,t,start);
		}
		
		t.importer(oldT);
		c.fromStringStore(cStore);
		
		//Move 3
		//c.setY(c.y()+2);
		//c.setX(c.x()+2);
		c.changeY(2);
		c.changeX(2);
		if(t.use(c))
		{
			newN = n+"3";
			v2recursion(d+1,c,newN,t,start);
		}
		
		t.importer(oldT);
		c.fromStringStore(cStore);
		
		//Move 4
		//c.setY(c.y()+3);
		c.changeY(3);
		if(t.use(c))
		{
			newN = n+"4";
			v2recursion(d+1,c,newN,t,start);
		}
		
		t.importer(oldT);
		c.fromStringStore(cStore);
		
		//Move 5
		//c.setY(c.y()+2);
		//c.setX(c.x()-2);
		c.changeY(2);
		c.changeX(-2);
		if(t.use(c))
		{
			newN = n+"5";
			v2recursion(d+1,c,newN,t,start);
		}
		
		t.importer(oldT);
		c.fromStringStore(cStore);
		
		//Move 6
		//c.setX(c.x()-3);
		c.changeX(-3);
		if(t.use(c))
		{
			newN = n+"6";
			v2recursion(d+1,c,newN,t,start);
		}
		
		t.importer(oldT);
		c.fromStringStore(cStore);
		
		//move 7
		//c.setY(c.y()-2);
		//c.setX(c.x()-2);
		c.changeY(-2);
		c.changeX(-2);
		if(t.use(c))
		{
			newN = n+"7";
			v2recursion(d+1,c,newN,t,start);
		}
		t.importer(oldT);
		c.fromStringStore(cStore);
	}

}
