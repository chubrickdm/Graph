package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;

import java.io.BufferedWriter;
import java.io.IOException;

class SearchDegreeAllVertices extends Algorithm{
	private int[] degreeVert;
	private int numVertex;
	
	@Override
	protected void output (BufferedWriter bw){
		try{
			for (int i = 1; i < numVertex + 1; i++){
				bw.write (degreeVert[i] + " ");
			}
			bw.newLine ();
			bw.flush ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	
	SearchDegreeAllVertices (Graph graph){
		this.graph = graph;
		numVertex = graph.getNumVertex ();
	}
	
	int[] perform (){
		degreeVert = new int [numVertex + 1];
		
		for (int i = 1; i < numVertex + 1; i++){
			degreeVert[i] = graph.getList ().get (i).size ();
		}
		
		return degreeVert;
	}
}
