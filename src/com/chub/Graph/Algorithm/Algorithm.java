package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;

import java.io.*;
import java.util.Scanner;

abstract class Algorithm{
	protected Graph graph;
	protected boolean error = false;
	
	
	private String enterFileName (){
		System.out.print ("Enter the file name: ");
		InputStreamReader isr = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader (isr);
		try{
			return br.readLine ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
		return "";
	}
	
	
	abstract protected void output (BufferedWriter bw);
	
	
	void selectOuputMethod (){
		if (!error){
			String outputMethod;
			boolean userSelect = false;
			
			while (userSelect != true){
				Scanner scanner = new Scanner (System.in);
				System.out.println ("Select the output method.");
				System.out.print ("Enter 'c' if you want output from console or 'f' from file: ");
				outputMethod = scanner.next ();
				
				if (outputMethod.equals ("f")){
					
					try{
						output (new BufferedWriter (new FileWriter (enterFileName ())));
					}
					catch (IOException e){
						System.out.println ("ERROR! File is locked.");
					}
					userSelect = true;
				}
				else if (outputMethod.equals ("c")){
					output (new BufferedWriter (new OutputStreamWriter (System.out)));
					userSelect = true;
				}
				else{
					System.out.println ("ERROR! Enter 'c' or 'f'.");
				}
			}
		}
	}
}
