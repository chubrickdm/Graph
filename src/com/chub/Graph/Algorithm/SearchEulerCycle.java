package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Stack;

public class SearchEulerCycle extends Algorithm{
	private boolean requirementMet = true;
	private int startV = 0;
	private int endV = 0;
	private int numVertex;
	private int[] result;
	private Edge currEdge;
	private Stack <Edge> stack;
	
	
	private boolean evenDegreeVertices (){
		SearchDegreeAllVertices sdav = new SearchDegreeAllVertices (graph);
		int[] arrayI = sdav.perform ();
		for (int i = 1; i < numVertex + 1; i++){
			if (arrayI[i] % 2 != 0){
				if (startV == 0){
					startV = i;
				}
				else if (endV == 0){
					endV = i;
				}
				else{
					return false;
				}
			}
		}
		if (startV != 0 && endV == 0){
			return false;
		}
		return true;
	}
	
	private boolean conectedGraph (){
		BreadthSearch bs = new BreadthSearch (graph);
		bs.setMarkValue (1);
		bs.start (1);
		int[] arrayI = bs.getConectedComponents ();
		int tmpNumVert = 0;
		for (int tmpI : arrayI){
			if (tmpI == 1){
				tmpNumVert++;
			}
		}
		if (tmpNumVert != numVertex){
			return false;
		}
		return true;
	}
	
	private void requirement (){
		if (conectedGraph () && evenDegreeVertices ()){
			requirementMet = true;
		}
		else{
			requirementMet = false;
		}
	}
	
	private void nextVertex (int vertex){
		//int nextV = graph.getList ().get ()
	}
	
	
	@Override
	protected void output (BufferedWriter bw){
		try{
			for (int tmpI : result){
				bw.write (tmpI + " ");
			}
			bw.newLine ();
			bw.flush ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	
	SearchEulerCycle (Graph graph){
		this.graph = graph;
		numVertex = graph.getNumVertex ();
	}
	
	int[] perform (){
		requirement ();
		if (!requirementMet){
			System.out.println ("This graph doesn't meet the requirements!");
			return new int[0];
		}
		
		
		if (startV != 0){
			if (graph.getList ().get (startV).get (0).index == endV){
				currEdge = new Edge (startV, graph.getList ().get (startV).get (1).index);
				stack.push (currEdge);
				nextVertex (graph.getList ().get (startV).get (1).index);
			}
			else{
				currEdge = new Edge (startV, graph.getList ().get (startV).get (0).index);
				stack.push (currEdge);
				nextVertex (graph.getList ().get (startV).get (0).index);
			}
		}
		else{
			currEdge = new Edge (1, graph.getList ().get (1).get (0).index);
			stack.push (currEdge);
			nextVertex (graph.getList ().get (1).get (0).index);
		}
		
		
		return result;
	}
}
