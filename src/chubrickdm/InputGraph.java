package chubrickdm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

class InputGraph{
	private Graph graph;
	private Scanner scanner = new Scanner (System.in);
	
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
		
		if (graph.isWeightedGraph && !graph.isOrgraph){
			keyInputWeightedNoOrgraphOnList ();
		}
		else if (graph.isWeightedGraph && graph.isOrgraph){
			keyInputWeightedOrgraphOnList ();
		}
		else if (!graph.isWeightedGraph && graph.isOrgraph){
			keyInputNoWeightedOrgraphOnList ();
		}
		else if (!graph.isWeightedGraph && !graph.isOrgraph){
			keyInputNoWeightedNoOrgraphOnList ();
		}
	}
	
	private void keyInputWeightedOrgraphOnList (){
		try{
			System.out.println ("Enter the list incidence matrix: ");
			Graph.Edge tmpE;
			InputStreamReader isr = new InputStreamReader (System.in);
			BufferedReader br = new BufferedReader (isr);
			String line;
			boolean finishInput = false;
			int firstV, secondV, weight;
			while (!finishInput){
				line = br.readLine ();
				if (line.length () == 0){
					finishInput = true;
				}
				else{
					StringTokenizer tokens = new StringTokenizer (line);
					firstV = Integer.parseInt (tokens.nextToken ());
					secondV = Integer.parseInt (tokens.nextToken ());
					weight = Integer.parseInt (tokens.nextToken ());
					
					tmpE = new Graph.Edge ();
					tmpE.index = secondV;
					tmpE.weight = weight;
					graph.list.get (firstV).add (tmpE);
					
					graph.numEdge++;
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR reading from the keyboard!");
		}
	}
	
	private void keyInputWeightedNoOrgraphOnList (){
		try{
			System.out.println ("Enter the list incidence matrix: ");
			Graph.Edge tmpE;
			InputStreamReader isr = new InputStreamReader (System.in);
			BufferedReader br = new BufferedReader (isr);
			String line;
			boolean finishInput = false;
			int firstV, secondV, weight;
			while (!finishInput){
				line = br.readLine ();
				if (line.length () == 0){
					finishInput = true;
				}
				else{
					StringTokenizer tokens = new StringTokenizer (line);
					firstV = Integer.parseInt (tokens.nextToken ());
					secondV = Integer.parseInt (tokens.nextToken ());
					weight = Integer.parseInt (tokens.nextToken ());
					
					tmpE = new Graph.Edge ();
					tmpE.index = secondV;
					tmpE.weight = weight;
					graph.list.get (firstV).add (tmpE);
					
					tmpE = new Graph.Edge ();
					tmpE.index = firstV;
					tmpE.weight = weight;
					graph.list.get (secondV).add (tmpE);
					
					graph.numEdge++;
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR reading from the keyboard!");
		}
	}
	
	private void keyInputNoWeightedOrgraphOnList (){
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
					
					tmpE = new Graph.Edge ();
					tmpE.index = secondV;
					graph.list.get (firstV).add (tmpE);
					
					graph.numEdge++;
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR reading from the keyboard!");
		}
	}
	
	private void keyInputNoWeightedNoOrgraphOnList (){
		try{
			System.out.println ("Enter the list incidence matrix: ");
			Graph.Edge tmpE = new Graph.Edge ();
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
					
					tmpE = new Graph.Edge ();
					tmpE.index = secondV;
					graph.list.get (firstV).add (tmpE);
					
					tmpE = new Graph.Edge ();
					tmpE.index = firstV;
					graph.list.get (secondV).add (tmpE);
					
					graph.numEdge++;
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR reading from the keyboard!");
		}
	}
	
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
	
	private void fileInput (){
	/*	String fileName;
		System.out.print ("Enter the file name: ");
		InputStreamReader isr = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader (isr);
		try{
			fileName = br.readLine ();
			br = new BufferedReader (new FileReader (fileName));
			String line = br.readLine ();
			StringTokenizer tokens = new StringTokenizer (line);
			row = Integer.parseInt (tokens.nextToken ());
			column = Integer.parseInt (tokens.nextToken ());
			matrix = new int[row][column];
			for (int i = 0; i < row; i++){
				line = br.readLine ();
				tokens = new StringTokenizer (line);
				for (int j = 0; j < column; j++){
					matrix[i][j] = Integer.parseInt (tokens.nextToken ());
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}*/
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
