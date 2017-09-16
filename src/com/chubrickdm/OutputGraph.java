package com.chubrickdm;

import java.util.Scanner;
import java.util.ArrayList;

class OutputGraph{
	private Graph graph;
	private Scanner scanner = new Scanner (System.in);
	
	private void consoleMatrixOutput (){
		for (int [] tmpArray : graph.matrix){
			for (int tmpInt : tmpArray){
				System.out.print (tmpInt + " ");
			}
			System.out.println ();
		}
	}
	
	private void consoleListOutput (){
		if (graph.isWeightedGraph){
			int i = 0;
			for (ArrayList <Graph.Edge> tmpList : graph.list){
				for (Graph.Edge tmpEdge : tmpList){
					System.out.println ((i) + " " + tmpEdge.index + " " + tmpEdge.weight);
				}
				i++;
			}
		}
		else{
			int i = 0;
			for (ArrayList <Graph.Edge> tmpList : graph.list){
				for (Graph.Edge tmpEdge : tmpList){
					System.out.println ((i) + " " + tmpEdge.index);
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
	
	private void windowOutput (){
	
	}
	
	OutputGraph (Graph tmpGraph){
		graph = tmpGraph;
		String outputMethod;
		boolean userSelect = false;
		
		while (userSelect != true){
			System.out.println ("Select the output method.");
			System.out.print ("Enter 'c' if you want output from console or 'w' from window: ");
			outputMethod = scanner.next ();
			
			if (outputMethod.equals ("c")){
				consoleOutput ();
				userSelect = true;
			}
			else if (outputMethod.equals ("w")){
				windowOutput ();
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'c' or 'w'.");
			}
		}
	}
}
