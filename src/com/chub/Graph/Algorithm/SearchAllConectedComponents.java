package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;
import com.chub.Graph.Orientation;

import java.io.BufferedWriter;
import java.io.IOException;

class SearchAllConectedComponents extends Algorithm{
	private int numVertex;
	private int numComponents = 0;
	private int [] arrayConectComp;
	
	
	@Override
	protected void output (BufferedWriter bw){
		try{
			for (int i = 1; i < numComponents; i++){
				for (int j = 1; j < numVertex + 1; j++){
					if (arrayConectComp[j] == i){
						bw.write (j + " ");
					}
				}
				bw.newLine ();
			}
			bw.flush ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	
	SearchAllConectedComponents (Graph g){
		this.graph = g;
		numVertex = g.getNumVertex ();
	}
	
	int [] perform (){
		if (graph.getOrientation () == Orientation.unOriented){
			numComponents = 1;
			arrayConectComp = new int[numVertex + 1];
			BreadthSearch bs = new BreadthSearch (graph);
			for (int i = 1; i < numVertex + 1; i++){
				if (arrayConectComp[i] == 0){
					bs.setMarkValue (numComponents);
					bs.start (i);
					arrayConectComp = bs.getConectedComponents ();
					numComponents++;
				}
			}
			return arrayConectComp;
		}
		else{
			error = true;
			System.out.println ("ERROR! Graph should be unOriented.");
		}
		return new int [0];
	}
}
