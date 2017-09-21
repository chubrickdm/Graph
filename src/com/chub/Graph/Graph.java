package com.chub.Graph;

import com.chub.Graph.Algorithm.BreadthSearch;
import com.chub.Graph.StorageMethod.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Graph{
	private int numVertex;
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
		int numComponents = 1;
		ArrayList <ArrayList <Integer>> allComponents = new ArrayList <> ();
		BreadthSearch bs = new BreadthSearch (this);
		bs.startSearch (1);
		allComponents.add (1, bs.getConectedComponents ());
		
		for (int i = 1; i < numVertex + 1; i++){
			if (i == allComponents.get (numComponents).get (0) && !allComponents.get (numComponents).isEmpty ()){
				allComponents.get (numComponents).remove (0);
			}
			else{
			
			}
		}
		
		for (Integer tmpI : components){
			System.out.println (tmpI);
		}
	}
	
	public StorageList getList (){
		return list;
	}
}
