package com.chub.Graph.StorageMethod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class StorageList extends IStorageMethod{
	int numVertex;
	int numEdge;
	int noEdgeValue = 0;
	int existEdgeValue = 1;
	ArrayList <ArrayList <Pair>> list;
	
	
	@Override
	protected void outputGraph (BufferedWriter bw){
	
	}
	
	@Override
	protected void inputGraph (BufferedReader br){
		try{
			
			
			String line = br.readLine ();
			StringTokenizer tokens = new StringTokenizer (line);
			numVertex = Integer.parseInt (tokens.nextToken ());
			//matrix = new int[numVertex + 1][numVertex + 1];
			int tmpInt;
			for (int i = 1; i < numVertex + 1; i++){
				line = br.readLine ();
				tokens = new StringTokenizer (line);
				for (int j = 1; j < numVertex + 1; j++){
					tmpInt = Integer.parseInt (tokens.nextToken ());
					//if (matrix[i][j] == 0){
					//	matrix[i][j] = tmpInt;
					//	if (!isOrgraph && tmpInt != noEdgeValue){
					//		matrix[j][i] = matrix[i][j];
					//	}
					//}
					
				}
			}
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
}
