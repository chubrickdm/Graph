package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;
import com.chub.Graph.StorageMethod.Pair;

import java.util.ArrayList;

public class BreadthSearch{
	private int numVertex;
	private int markValue = 1;
	private int[] markVert;
	private ArrayList <Integer> list = new ArrayList <> ();
	private ArrayList <ArrayList <Pair>> graph = new ArrayList <> ();
	
	
	private void breadthSearch (int vertex){
		if (!list.isEmpty ()){
			list.remove (0);
			int nextV;
			for (int i = 0; i < graph.get (vertex).size (); i++){
				nextV = graph.get (vertex).get (i).index;
				if (markVert[nextV] != markValue){
					list.add (i);
					markVert[nextV] = markValue;
					breadthSearch (nextV);
				}
			}
		}
	}
	
	
	public void startSearch (int indexStart){
		list.add (indexStart);
		markVert[indexStart] = markValue;
		breadthSearch (indexStart);
	}
	
	public BreadthSearch (Graph g){
		graph = g.getList ().getList ();
		numVertex = g.getNumVertex ();
		markVert = new int[numVertex + 1];
		for (int i = 0; i < numVertex + 1; i++){
			markVert[i] = 0;
		}
	}
	
	public int[] getConectedComponents (){
		return markVert;
	}
	
	public void setMarkValue (int markValue){
		this.markValue = markValue;
	}
}
