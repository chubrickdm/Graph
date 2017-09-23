package com.chub.Graph.StorageMethod;

import com.chub.Graph.Orientation;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class StorageList extends IStorageMethod{
	private ArrayList <ArrayList <Pair>> list = new ArrayList <> ();
	
	
	private void initializeList (){
		for (int i = 0; i < numVertex + 1; i++){
			list.add (new ArrayList <Pair> ());
		}
	}
	
	
	@Override
	protected void outputGraph (BufferedWriter bw){
		try{
			bw.write (String.valueOf (numVertex));
			bw.newLine ();
			
			int i = 0;
			for (ArrayList <Pair> tmpList : list){
				for (Pair tmpPair : tmpList){
					bw.write ("\t" + (i) + " \t" + tmpPair.index + " \t" + tmpPair.weight);
					bw.newLine ();
				}
				i++;
			}
			
			bw.flush ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	@Override
	protected void inputGraph (BufferedReader br){
		boolean finishInput = false;
		int firstV, secondV, weight;
		String line;
		StringTokenizer tokens;
		try{
			line = br.readLine ();
			tokens = new StringTokenizer (line);
			numVertex = Integer.parseInt (tokens.nextToken ());
			initializeList ();
			while (!finishInput){
				line = br.readLine ();
				if (line == null || line.length () == 0){
					finishInput = true;
				}
				else{
					tokens = new StringTokenizer (line);
					firstV = Integer.parseInt (tokens.nextToken ());
					secondV = Integer.parseInt (tokens.nextToken ());
					if (tokens.hasMoreTokens ()){
						weight = Integer.parseInt (tokens.nextToken ());
					}
					else{
						weight = 1;
					}
					list.get (firstV).add (new Pair (secondV, weight));
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	
	public void createListBasedOnMatrix (StorageMatrix tmpMatrix){
		numVertex = tmpMatrix.numVertex;
		initializeList ();
		
		Pair tmpE;
		for (int i = 1; i < numVertex + 1; i++){
			for (int j = 1; j < numVertex + 1; j++){
				if (tmpMatrix.getMatrix ()[i][j] != noPairValue){
					tmpE = new Pair (j, tmpMatrix.getMatrix ()[i][j]);
					list.get (i).add (tmpE);
				}
			}
		}
	}
	
	public ArrayList <ArrayList <Pair>> getList (){
		return list;
	}
	
	@Override
	public Orientation getOrientation (){
		Pair tmpP;
		boolean isArc = true;
		for (int i = 1; i < list.size (); i++){
			for (int j = 0; j < list.get (i).size (); j++){
				tmpP = list.get (i).get (j);
				for (Pair tmpP2 : list.get (tmpP.index)){
					if (tmpP2.index == i){
						isArc = false;
					}
				}
				
				if (isArc){
					orientation = Orientation.oriented;
					return orientation;
				}
				isArc = true;
			}
		}
		orientation = Orientation.unOriented;
		return orientation;
	}
}
