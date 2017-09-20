package com.chub.Graph;

import com.chub.Graph.StorageMethod.*;

import java.util.Scanner;

public class Graph{
	Storage storage = Storage.unknown;
	StorageMatrix matrix = new StorageMatrix ();
	StorageList list = new StorageList ();
	
	
	private void selectStorageMethod (){
		String inputMethod;
		boolean userSelect = false;
		
		while (!userSelect){
			Scanner scanner = new Scanner (System.in);
			System.out.print ("Select the storage method. Enter 'm' if you want store in matrix or 'l' in list: ");
			inputMethod = scanner.next ();
			
			if (inputMethod.equals ("m")){
				storage = Storage.matrix;
				userSelect = true;
			}
			else if (inputMethod.equals ("l")){
				storage = Storage.list;
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'm' or 'l'.");
			}
		}
	}
	
	public Graph (Storage storage){
		this.storage = storage;
	}
	
	public Graph (){}
	
	
	public void input (){
		if (storage == Storage.unknown){
			selectStorageMethod ();
		}
		
		switch (storage){
		case list:
			list.selectInputMethod ();
			matrix.createMatrixBasedOnList (list);
			break;
		case matrix:
			matrix.selectInputMethod ();
			list.createListBasedOnMatrix (matrix);
			break;
		}
	}
	
	public void output (){
		String inputMethod;
		boolean userSelect = false;
		
		while (!userSelect){
			Scanner scanner = new Scanner (System.in);
			System.out.print ("Select the input type. Enter 'm' if you want input matrix or 'l' list: ");
			inputMethod = scanner.next ();
			
			if (inputMethod.equals ("m")){
				matrix.selectOutputMethod ();
				userSelect = true;
			}
			else if (inputMethod.equals ("l")){
				list.selectOutputMethod ();
				userSelect = true;
			}
			else{
				System.out.println ("ERROR! Enter 'm' or 'l'.");
			}
		}
	}
	
	/*public void info (){
		System.out.println ("---------Information about your graph---------");
		System.out.println ("Is orgraph - " + isOrgraph + ".");
		System.out.println ("Is weighted graph - " + isWeightedGraph + ".");
		if (!graphOnList){
			System.out.println ("Storage method - matrix.");
		}
		else{
			System.out.println ("Storage method - list.");
		}
		if (!isWeightedGraph){
			System.out.println ("Exist edge value - " + existEdgeValue + ".");
		}
		System.out.println ("No edge value - " + noEdgeValue + ".");
		System.out.println ("Number of vertices - " + numVertex + ".");
		System.out.println ("----------------------------------------------");
	}*/
}
