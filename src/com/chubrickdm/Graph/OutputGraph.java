package com.chubrickdm.Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class OutputGraph{
	private Graph graph;
	private Scanner scanner = new Scanner (System.in);
	
	
	private void consoleMatrixOutput (){
		for (int i = 1; i < graph.numVertex + 1; i++){
			for (int j = 1; j < graph.numVertex + 1; j++){
				System.out.print ("\t" + graph.matrix[i][j] + " ");
			}
			System.out.println ();
		}
	}
	
	private void consoleListOutput (){
		if (graph.isWeightedGraph){
			int i = 0;
			for (ArrayList <Graph.Edge> tmpList : graph.list){
				for (Graph.Edge tmpEdge : tmpList){
					System.out.println ("\t" + (i) + " \t" + tmpEdge.index + " \t" + tmpEdge.weight);
				}
				i++;
			}
		}
		else{
			int i = 0;
			for (ArrayList <Graph.Edge> tmpList : graph.list){
				for (Graph.Edge tmpEdge : tmpList){
					System.out.println ("\t" + (i) + " \t" + tmpEdge.index);
				}
				i++;
			}
		}
	}
	
	private void consoleOutput (){
		String outputMethod;
		boolean userSelect = false;
		
		while (userSelect != true){
			System.out.println ("Select the graph output method.");
			System.out.print ("Enter 'm' if you want output from matrix or 'l' from list: ");
			outputMethod = scanner.next ();
			
			if (outputMethod.equals ("m")){
				consoleMatrixOutput ();
				userSelect = true;
			}
			else if (outputMethod.equals ("l")){
				consoleListOutput ();
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'm' or 'l'.");
			}
		}
	}
	
	//////////////////////////////////////////////////////////
	
	private void windowOutput (){
	
	}
	
	//////////////////////////////////////////////////////////
	
	private void fileOutput (){
		String outputMethod;
		boolean userSelect = false;
		
		while (userSelect != true){
			System.out.println ("Select the graph output method.");
			System.out.print ("Enter 'm' if you want output from matrix or 'l' from list: ");
			outputMethod = scanner.next ();
			
			if (outputMethod.equals ("m")){
				fileMatrixOutput ();
				userSelect = true;
			}
			else if (outputMethod.equals ("l")){
				fileListOutput ();
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'm' or 'l'.");
			}
		}
	}
	
	private void fileMatrixOutput (){
		String fileName;
		System.out.print ("Enter the file name: ");
		InputStreamReader isr = new InputStreamReader (System.in);
		BufferedReader brIn = new BufferedReader (isr);
		BufferedWriter brOut;
		try{
			fileName = brIn.readLine ();
			brOut = new BufferedWriter (new FileWriter (fileName));
			
			for (int i = 1; i < graph.numVertex + 1; i++){
				for (int j = 1; j < graph.numVertex + 1; j++){
					brOut.write ("\t" + graph.matrix[i][j] + " ");
				}
				brOut.newLine ();
			}
			
			brOut.flush ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	private void fileListOutput (){
		String fileName;
		System.out.print ("Enter the file name: ");
		InputStreamReader isr = new InputStreamReader (System.in);
		BufferedReader brIn = new BufferedReader (isr);
		BufferedWriter brOut;
		try{
			fileName = brIn.readLine ();
			brOut = new BufferedWriter (new FileWriter (fileName));
			
			if (graph.isWeightedGraph){
				int i = 0;
				for (ArrayList <Graph.Edge> tmpList : graph.list){
					for (Graph.Edge tmpEdge : tmpList){
						brOut.write ("\t" + (i) + " \t" + tmpEdge.index + " \t" + tmpEdge.weight);
						brOut.newLine ();
					}
					i++;
				}
			}
			else{
				int i = 0;
				for (ArrayList <Graph.Edge> tmpList : graph.list){
					for (Graph.Edge tmpEdge : tmpList){
						brOut.write ("\t" + (i) + " \t" + tmpEdge.index);
						brOut.newLine ();
					}
					i++;
				}
			}
			
			brOut.flush ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	/////////////////////////////////////////////////////////
	
	OutputGraph (Graph tmpGraph){
		graph = tmpGraph;
		String outputMethod;
		boolean userSelect = false;
		
		while (userSelect != true){
			System.out.println ("Select the output method.");
			System.out.print ("Enter 'c' if you want output from console, 'w' from window or 'f' from file: ");
			outputMethod = scanner.next ();
			
			if (outputMethod.equals ("c")){
				consoleOutput ();
				userSelect = true;
			}
			else if (outputMethod.equals ("w")){
				windowOutput ();
				userSelect = true;
			}
			else if (outputMethod.equals ("f")){
				fileOutput ();
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'c', 'w' or 'f'.");
			}
		}
	}
}
