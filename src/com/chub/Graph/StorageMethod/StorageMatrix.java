package com.chub.Graph.StorageMethod;

import com.chub.Graph.Orientation;
import java.io.*;
import java.util.StringTokenizer;

public class StorageMatrix extends IStorageMethod{
	private int [][] matrix;
	
	
	@Override
	protected void outputGraph (BufferedWriter bw){
		try{
			bw.write (String.valueOf(numVertex));
			bw.newLine ();
			for (int i = 1; i < numVertex + 1; i++){
				for (int j = 1; j < numVertex + 1; j++){
					bw.write ("\t" + matrix[i][j] + " ");
				}
				bw.newLine ();
			}
			bw.flush ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	@Override
	protected void inputGraph (BufferedReader br){
		try{
			String line = br.readLine ();
			StringTokenizer tokens = new StringTokenizer (line);
			numVertex = Integer.parseInt (tokens.nextToken ());
			matrix = new int[numVertex + 1][numVertex + 1];
			int tmpInt;
			for (int i = 1; i < numVertex + 1; i++){
				line = br.readLine ();
				tokens = new StringTokenizer (line);
				for (int j = 1; j < numVertex + 1; j++){
					tmpInt = Integer.parseInt (tokens.nextToken ());
					matrix[i][j] = tmpInt;
					numEdge++;
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	
	public void createMatrixBasedOnList (StorageList tmpList){
		numVertex = tmpList.numVertex;
		matrix = new int[numVertex + 1][numVertex + 1];
		
		for (int i = 1; i < numVertex + 1; i++){
			for (Pair tmpPair : tmpList.getList ().get (i)){
				matrix[i][tmpPair.index] = tmpPair.weight;
			}
		}
	}
	
	public int[][] getMatrix (){
		return matrix;
	}
	
	@Override
	public Orientation getOrientation (){
		for (int i = 1; i < numVertex + 1; i++){
			for (int j = i; j < numVertex + 1; j++){
				if (matrix[i][j] != matrix[j][i]){
					orientation = Orientation.oriented;
					return orientation;
				}
			}
		}
		orientation = Orientation.unOriented;
		return orientation;
	}
}