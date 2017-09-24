package com.chub.Graph;

import com.chub.Graph.StorageMethod.Pair;
import com.chub.Graph.StorageMethod.StorageList;
import com.chub.Graph.StorageMethod.StorageMatrix;

import java.util.ArrayList;
import java.util.Scanner;

public class Graph{
	private int numVertex = 0;
	private Storage storage = Storage.unknown;
	private Orientation orientation = Orientation.unknown;
	private StorageMatrix matrix = new StorageMatrix ();
	private StorageList list = new StorageList ();
	
	
	private void selectStorageMethod (){
		boolean userSelect = false;
		String inputMethod;
		
		while (!userSelect){
			Scanner scanner = new Scanner (System.in);
			System.out.println ("Select the storage method.");
			System.out.print ("Enter 'm' if you want store in matrix or 'l' in list: ");
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
		
		numVertex = matrix.getNumVertex ();
		orientation = matrix.getOrientation ();
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
	
	public ArrayList <ArrayList<Pair>> getList (){
		return list.getList ();
	}
	
	public int getNumVertex (){
		return numVertex;
	}
	
	public Orientation getOrientation (){
		return orientation;
	}
}
