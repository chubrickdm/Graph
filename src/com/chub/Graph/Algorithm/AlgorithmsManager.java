package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;

public class AlgorithmsManager{
	Graph graph;
	
	public AlgorithmsManager (Graph graph){
		this.graph = graph;
	}
	
	public void searchAllConectComponents (){
		SearchAllConectedComponents sacc = new SearchAllConectedComponents (graph);
		sacc.perform ();
		sacc.selectOuputMethod ();
	}
	
	public void searchConectedComponent (int vertexStart){
		BreadthSearch bs = new BreadthSearch (graph);
		bs.start (vertexStart);
		bs.selectOuputMethod ();
	}
}
