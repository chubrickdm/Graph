package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;
import com.chub.Graph.StorageMethod.Pair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

class BreadthSearch extends Algorithm{
	private int markValue = 1;
	private int[] markVert;
	private ArrayList <Integer> list = new ArrayList <> ();
	private ArrayList <ArrayList <Pair>> graphList = new ArrayList <> ();
	
	
	private void breadthSearch (int vertex){
		if (!list.isEmpty ()){
			list.remove (0);
			int nextV;
			for (int i = 0; i < graphList.get (vertex).size (); i++){
				nextV = graphList.get (vertex).get (i).index;
				if (markVert[nextV] != markValue){
					list.add (i);
					markVert[nextV] = markValue;
					breadthSearch (nextV);
				}
			}
		}
	}
	
	
	@Override
	protected void output (BufferedWriter bw){
		try{
			for (int tmpI : markVert){
				if (tmpI == markValue){
					bw.write (tmpI + " ");
				}
			}
			bw.newLine ();
			bw.flush ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	
	BreadthSearch (Graph g){
		graphList = g.getList ();
		int numVertex = g.getNumVertex ();
		markVert = new int[numVertex + 1];
		for (int i = 0; i < numVertex + 1; i++){
			markVert[i] = 0;
		}
	}
	
	void start (int vertexStart){
		list.add (vertexStart);
		markVert[vertexStart] = markValue;
		breadthSearch (vertexStart);
	}
	
	int[] getConectedComponents (){
		return markVert;
	}
	
	void setMarkValue (int markValue){
		this.markValue = markValue;
	}
}
