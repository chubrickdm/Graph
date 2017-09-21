package com.chub.Graph.StorageMethod;

import com.chub.Graph.Orientation;
import java.io.*;
import java.util.Scanner;

public abstract class IStorageMethod{
	protected int numVertex = 0;
	protected int numEdge = 0;
	protected String fileName;
	Orientation orientation = Orientation.unknown;
	
	
	private void enterName (){
		System.out.print ("Enter the file name: ");
		InputStreamReader isr = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader (isr);
		try{
			fileName = br.readLine ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	abstract public Orientation getOrientation ();
	abstract protected void inputGraph (BufferedReader br);
	abstract protected void outputGraph (BufferedWriter bw);
	
	
	public void selectInputMethod (){
		String inputMethod;
		boolean userSelect = false;
		
		while (!userSelect){
			Scanner scanner = new Scanner (System.in);
			System.out.print ("Select the input method. Enter 'f' if you want input from file or 'k' from keyboard: ");
			inputMethod = scanner.next ();
			
			if (inputMethod.equals ("f")){
				enterName ();
				try{
					inputGraph (new BufferedReader (new FileReader (fileName)));
				}
				catch (FileNotFoundException e){
					System.out.println ("ERROR! File not found.");
				}
				userSelect = true;
			}
			else if (inputMethod.equals ("k")){
				System.out.println ("Enter the number of vertices and then the graph.");
				inputGraph (new BufferedReader (new InputStreamReader (System.in)));
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'f' or 'k'.");
			}
		}
	}
	
	public void selectOutputMethod (){
		String outputMethod;
		boolean userSelect = false;
		
		while (userSelect != true){
			Scanner scanner = new Scanner (System.in);
			System.out.println ("Select the output method.");
			System.out.print ("Enter 'c' if you want output from console or 'f' from file: ");
			outputMethod = scanner.next ();
			
			if (outputMethod.equals ("f")){
				enterName ();
				try{
					outputGraph (new BufferedWriter (new FileWriter (fileName)));
				}
				catch (IOException e){
					System.out.println ("ERROR! File is locked.");
				}
				userSelect = true;
			}
			else if (outputMethod.equals ("c")){
				System.out.println ("Your graph.");
				outputGraph (new BufferedWriter (new OutputStreamWriter (System.out)));
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'c' or 'f'.");
			}
		}
	}
	
	public int getNumVertex (){
		return numVertex;
	}
}
