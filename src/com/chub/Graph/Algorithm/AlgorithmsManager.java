package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;

public class AlgorithmsManager{
	private Graph graph;
	
	
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
	
	public void searchDegreeAllVert (){
		SearchDegreeAllVertices sdav = new SearchDegreeAllVertices (graph);
		sdav.perform ();
		sdav.selectOuputMethod ();
	}
	
	public void searchEulerCycle (){
		SearchEulerCycle sec = new SearchEulerCycle (graph);
		sec.perform ();
		sec.selectOuputMethod ();
	}
}
