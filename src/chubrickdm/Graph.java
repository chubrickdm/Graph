package chubrickdm;

import java.util.ArrayList;

public class Graph{
	boolean isWeightedGraph = false;
	boolean isOrgraph = false;
	boolean graphOnList = false;
	int noEdgeValue = -1;
	int existEdgeValue = 0;
	int[][] matrix = new int[5][5];
	int numVertex = 0;
	int numEdge = 0;
	ArrayList <ArrayList <Edge>> list = new ArrayList <ArrayList <Edge>> ();
	static class Edge{
		public int index;
		public int weight;
	}
	
	////////////////////////////////////////////////////////
	public Graph (boolean isOrgraph, boolean isWeightedGraph){
		this.isOrgraph = isOrgraph;
		this.isWeightedGraph = isWeightedGraph;
	}
	
	public Graph (boolean isOrgraph, boolean isWeightedGraph, int noEdgeValue, int existEdgeValue){
		this.isOrgraph = isOrgraph;
		this.isWeightedGraph = isWeightedGraph;
		this.noEdgeValue = noEdgeValue;
		this.existEdgeValue = existEdgeValue;
	}
	
	public Graph (){ }
	////////////////////////////////////////////////////////
	public void input (){
		InputGraph input = new InputGraph (this);
	}
	
	public void output (){
		OutputGraph output = new OutputGraph (this);
	}
	
	public void info (){
		System.out.println ("---------Information about your graph---------");
		System.out.println ("Is orgraph - " + isOrgraph + ".");
		System.out.println ("Is weighted graph - " + isWeightedGraph + ".");
		if (!graphOnList){
			System.out.println ("Storage method - matrix.");
		}
		else{
			System.out.println ("Storage method - list.");
		}
		if (!isWeightedGraph){
			System.out.println ("Exist edge value - " + existEdgeValue + ".");
		}
		System.out.println ("No edge value - " + noEdgeValue + ".");
		System.out.println ("Number of vertices - " + numVertex + ".");
		System.out.println ("----------------------------------------------");
	}
}
