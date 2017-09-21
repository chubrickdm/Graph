package com.chub.Graph;

import com.chub.Graph.Algorithm.BreadthSearch;
import com.chub.Graph.StorageMethod.StorageList;
import com.chub.Graph.StorageMethod.StorageMatrix;

import java.util.Scanner;

public class Graph{
	private Orientation orientation = Orientation.unknown;
	private int numComponents = 0;
	private int numVertex = 0;
	private int [] arrayConectComp;
	private Storage storage = Storage.unknown;
	private StorageMatrix matrix = new StorageMatrix ();
	private StorageList list = new StorageList ();
	
	
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
	
	private void inputConectedComponents (){
		System.out.println ("Your conected components:");
		for (int i = 1; i < numComponents; i++){
			System.out.print ("Conected components " + i + ": ");
			for (int j = 1; j < numVertex + 1; j++){
				if (arrayConectComp[j] == i){
					System.out.print (j + " ");
				}
			}
			System.out.println ();
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
		
		numVertex = matrix.getNumVertex ();
		orientation = list.getOrientation ();
		System.out.println (orientation);
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
	
	public void conectedComponents (){
		if (orientation == Orientation.unOriented){
			numComponents = 1;
			arrayConectComp = new int[numVertex + 1];
			BreadthSearch bs = new BreadthSearch (this);
			for (int i = 1; i < numVertex + 1; i++){
				if (arrayConectComp[i] == 0){
					bs.setMarkValue (numComponents);
					bs.startSearch (i);
					arrayConectComp = bs.getConectedComponents ();
					numComponents++;
				}
			}
			inputConectedComponents ();
		}
		else{
			System.out.println ("Graph should be unoriented.");
		}
	}
	
	public StorageList getList (){
		return list;
	}
	
	public int getNumVertex (){
		return numVertex;
	}
}
