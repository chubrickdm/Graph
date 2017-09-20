package com.chub.Graph.StorageMethod;

import java.io.*;
import java.util.Scanner;

public abstract class IStorageMethod{
	public int numVertex = 0;
	public int numEdge = 0;
	private String fileName;
	
	abstract protected void inputGraph (BufferedReader br);
	abstract protected void outputGraph (BufferedWriter bw);
	
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
				outputGraph (new BufferedWriter (new OutputStreamWriter (System.out)));
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'c' or 'f'.");
			}
		}
	}
}
