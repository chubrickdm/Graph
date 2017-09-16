package com.chubrickdm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

class InputGraph{
	private Graph graph;
	private Scanner scanner = new Scanner (System.in);
	private String fileName;
	
	private void keyInput (){
		try{
			selectStorageMethod ();
			
			System.out.print ("Enter the number of vertices: ");
			graph.numVertex = scanner.nextInt ();
			graph.info ();
			
			if (!graph.graphOnList){
				keyInputGraphOnMatrix ();
			}
			else{
				keyInputGraphOnList ();
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println ("ERROR! Can not create a graph with " + graph.numVertex + " vertices!");
		}
		catch (InputMismatchException e){
			System.out.println ("ERROR! Can not input symbols!");
		}
	}
	
	private void keyInputGraphOnMatrix (){
		graph.matrix = new int[graph.numVertex][graph.numVertex];
		System.out.println ("Enter the vertex incidence matrix:");
		for (int i = 0; i < graph.numVertex; i++){
			for (int j = 0; j < graph.numVertex; j++){
				graph.matrix[i][j] = scanner.nextInt ();
			}
		}
	}
	
	private void keyInputGraphOnList (){
		for (int i = 0; i <= graph.numVertex; i++){
			graph.list.add (new ArrayList <Graph.Edge> ());
		}
		
		try{
			System.out.println ("Enter the list incidence matrix: ");
			Graph.Edge tmpE;
			InputStreamReader isr = new InputStreamReader (System.in);
			BufferedReader br = new BufferedReader (isr);
			String line;
			boolean finishInput = false;
			int firstV, secondV;
			while (!finishInput){
				line = br.readLine ();
				if (line.length () == 0){
					finishInput = true;
				}
				else{
					StringTokenizer tokens = new StringTokenizer (line);
					firstV = Integer.parseInt (tokens.nextToken ());
					secondV = Integer.parseInt (tokens.nextToken ());
					
					if (graph.isWeightedGraph && !graph.isOrgraph){
						keyInputWeightedNoOrgraphOnList (firstV, secondV, tokens);
					}
					else if (graph.isWeightedGraph && graph.isOrgraph){
						keyInputWeightedOrgraphOnList (firstV, secondV, tokens);
					}
					else if (!graph.isWeightedGraph && graph.isOrgraph){
						keyInputNoWeightedOrgraphOnList (firstV, secondV);
					}
					else if (!graph.isWeightedGraph && !graph.isOrgraph){
						keyInputNoWeightedNoOrgraphOnList (firstV, secondV);
					}
					
					graph.numEdge++;
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR reading from the keyboard!");
		}
	}
	
	private void keyInputWeightedOrgraphOnList (int firstV, int secondV, StringTokenizer tokens){
		Graph.Edge tmpE;
		int weight = Integer.parseInt (tokens.nextToken ());
		
		tmpE = new Graph.Edge ();
		tmpE.index = secondV;
		tmpE.weight = weight;
		graph.list.get (firstV).add (tmpE);
	}
	
	private void keyInputWeightedNoOrgraphOnList (int firstV, int secondV, StringTokenizer tokens){
		Graph.Edge tmpE;
		int weight = Integer.parseInt (tokens.nextToken ());
		
		tmpE = new Graph.Edge ();
		tmpE.index = secondV;
		tmpE.weight = weight;
		graph.list.get (firstV).add (tmpE);
		
		tmpE = new Graph.Edge ();
		tmpE.index = firstV;
		tmpE.weight = weight;
		graph.list.get (secondV).add (tmpE);
	}
	
	private void keyInputNoWeightedOrgraphOnList (int firstV, int secondV){
		Graph.Edge tmpE;
		
		tmpE = new Graph.Edge ();
		tmpE.index = secondV;
		graph.list.get (firstV).add (tmpE);
	}
	
	private void keyInputNoWeightedNoOrgraphOnList (int firstV, int secondV){
		Graph.Edge tmpE;
		
		tmpE = new Graph.Edge ();
		tmpE.index = secondV;
		graph.list.get (firstV).add (tmpE);
		
		tmpE = new Graph.Edge ();
		tmpE.index = firstV;
		graph.list.get (secondV).add (tmpE);
	}
	
	//////////////////////////////////////////////////////
	
	private void fileInput (){
		selectStorageMethod ();
		
		System.out.print ("Enter the file name: ");
		InputStreamReader isr = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader (isr);
		try{
			fileName = br.readLine ();
			if (!graph.graphOnList){
				fileInputGraphOnMatrix ();
			}
			else{
				fileInputGraphOnList ();
			}
			
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	private void fileInputGraphOnMatrix (){
		try{
			BufferedReader br = new BufferedReader (new FileReader (fileName));
			String line = br.readLine ();
			StringTokenizer tokens = new StringTokenizer (line);
			graph.numVertex = Integer.parseInt (tokens.nextToken ());
			graph.matrix = new int[graph.numVertex][graph.numVertex];
			for (int i = 0; i < graph.numVertex; i++){
				line = br.readLine ();
				tokens = new StringTokenizer (line);
				for (int j = 0; j < graph.numVertex; j++){
					graph.matrix[i][j] = Integer.parseInt (tokens.nextToken ());
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	private void fileInputGraphOnList (){
		String line;
		boolean finishInput = false;
		int firstV, secondV;
		try{
			BufferedReader br = new BufferedReader (new FileReader (fileName));
			line = br.readLine ();
			StringTokenizer tokens = new StringTokenizer (line);
			graph.numVertex = Integer.parseInt (tokens.nextToken ());
			for (int i = 0; i <= graph.numVertex; i++){
				graph.list.add (new ArrayList <Graph.Edge> ());
			}
			while (!finishInput){
				line = br.readLine ();
				if (line == null || line.length () == 0){
					finishInput = true;
				}
				else{
					tokens = new StringTokenizer (line);
					firstV = Integer.parseInt (tokens.nextToken ());
					secondV = Integer.parseInt (tokens.nextToken ());
					
					if (graph.isWeightedGraph && graph.isOrgraph){
						fileInputWeightedOrgraphOnList (firstV, secondV, tokens);
					}
					else if (graph.isWeightedGraph && !graph.isOrgraph){
						fileInputWeightedNoOrgraphOnList (firstV, secondV, tokens);
					}
					else if (!graph.isWeightedGraph && graph.isOrgraph){
						fileInputNoWeightedOrgraphOnList (firstV, secondV);
					}
					else if (!graph.isWeightedGraph && !graph.isOrgraph){
						fileInputNoWeightedNoOrgraphOnList (firstV, secondV);
					}
					
					graph.numEdge++;
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	private void fileInputWeightedOrgraphOnList (int firstV, int secondV, StringTokenizer tokens){
		Graph.Edge tmpE;
		int weight = Integer.parseInt (tokens.nextToken ());
		
		tmpE = new Graph.Edge ();
		tmpE.index = secondV;
		tmpE.weight = weight;
		graph.list.get (firstV).add (tmpE);
	}
	
	private void fileInputWeightedNoOrgraphOnList (int firstV, int secondV, StringTokenizer tokens){
		Graph.Edge tmpE;
		int weight;
		weight = Integer.parseInt (tokens.nextToken ());
		
		tmpE = new Graph.Edge ();
		tmpE.index = secondV;
		tmpE.weight = weight;
		graph.list.get (firstV).add (tmpE);
		
		tmpE = new Graph.Edge ();
		tmpE.index = firstV;
		tmpE.weight = weight;
		graph.list.get (secondV).add (tmpE);
	}
	
	private void fileInputNoWeightedOrgraphOnList (int firstV, int secondV){
		Graph.Edge tmpE;
		
		tmpE = new Graph.Edge ();
		tmpE.index = secondV;
		graph.list.get (firstV).add (tmpE);
	}
	
	private void fileInputNoWeightedNoOrgraphOnList (int firstV, int secondV){
		Graph.Edge tmpE;
		
		tmpE = new Graph.Edge ();
		tmpE.index = secondV;
		graph.list.get (firstV).add (tmpE);
		
		tmpE = new Graph.Edge ();
		tmpE.index = firstV;
		graph.list.get (secondV).add (tmpE);
	}
	
	///////////////////////////////////////////////////////
	
	private void selectStorageMethod (){
		String inputMethod;
		boolean userSelect = false;
		
		while (userSelect != true){
			System.out.print ("Select the storage method. Enter 'm' if you want store in matrix or 'l' in list: ");
			inputMethod = scanner.next ();
			
			if (inputMethod.equals ("m")){
				graph.graphOnList = false;
				userSelect = true;
			}
			else if (inputMethod.equals ("l")){
				graph.graphOnList = true;
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'm' or 'l'.");
			}
		}
	}
	
	private void selectTypeGraph (){
		String inputMethod;
		boolean userSelect = false;
		
		while (userSelect != true){
			System.out.print ("Select the type of graph. Enter 'w' if you want weighted graph or 'n' - no weighted graph: ");
			
			inputMethod = scanner.next ();
			
			if (inputMethod.equals ("w")){
				graph.isWeightedGraph = true;
				userSelect = true;
			}
			else if (inputMethod.equals ("n")){
				graph.isWeightedGraph = false;
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'w' or 'n'.");
			}
		}
		
		userSelect = false;
		while (userSelect != true){
			System.out.print ("Select the type of graph. Enter 'o' if you want orgraph or 'n' - no orgraph: ");
			
			inputMethod = scanner.next ();
			
			if (inputMethod.equals ("o")){
				graph.isOrgraph = true;
				userSelect = true;
			}
			else if (inputMethod.equals ("n")){
				graph.isOrgraph = false;
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter '0' or 'n'.");
			}
		}
		
	}
	
	InputGraph (Graph tmpGraph){
		graph = tmpGraph;
		
		selectTypeGraph ();
		
		String inputMethod;
		boolean userSelect = false;
		
		while (userSelect != true){
			System.out.print ("Select the input method. Enter 'f' if you want input from file or 'k' from keyboard: ");
			inputMethod = scanner.next ();
			
			if (inputMethod.equals ("f")){
				fileInput ();
				userSelect = true;
			}
			else if (inputMethod.equals ("k")){
				keyInput ();
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'f' or 'k'.");
			}
		}
	}
}