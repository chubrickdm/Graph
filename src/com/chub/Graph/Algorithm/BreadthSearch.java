package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;
import com.chub.Graph.StorageMethod.Pair;

import java.util.ArrayList;

public class BreadthSearch{
	private int numVertex;
	private int markValue = 1;
	private int[] markVert;
	private ArrayList <Integer> list = new ArrayList <> ();
	private ArrayList <Integer> conectedComponents = new ArrayList <> ();
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
		numVertex = graph.size () - 1;
		markVert = new int[numVertex + 1];
		for (int i = 0; i < numVertex + 1; i++){
			markVert[i] = 0;
		}
	}
	
	public ArrayList <Integer> getConectedComponents (){
		for (int i = 1; i < numVertex + 1; i++){
			if (markVert [i] == markValue){
				conectedComponents.add (i);
			}
		}
		return conectedComponents;
	}
}
