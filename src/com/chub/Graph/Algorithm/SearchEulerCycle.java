package com.chub.Graph.Algorithm;

import com.chub.Graph.Graph;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SearchEulerCycle extends Algorithm{
	private boolean requirementMet = true;
	private int startV = 0;
	private int endV = 0;
	private int numVertex;
	private int lengthResult = 0;
	//private int numMarkEdge = 0;
	//private int sumDegreeVertices;
	private int[] result;
	private Edge currEdge;
	private int[][] markEdge;
	private ArrayList<Edge> list = new ArrayList <>  ();
	
	
	private void finishAlgorithm (){
		int tmpI = 0;
		for (int i = 0; i < list.size (); i++){
			currEdge = list.get (i);
			result[tmpI++] = currEdge.firstV;
		}
		result[tmpI] = currEdge.secondV;
	}
	
	private boolean evenDegreeVertices (){
		SearchDegreeAllVertices sdav = new SearchDegreeAllVertices (graph);
		int[] arrayI = sdav.perform ();
		//sumDegreeVertices = sdav.sumDegreeAllVertices ();
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
		arrayI[0] = 1;
		for (int tmpI : arrayI){
			if (tmpI != 1){
				return false;
			}
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
	
	/*private void previousVertex (int vertex){
		int previousV;
		for (int i = 0; i < graph.getList ().get (vertex).size (); i++){
			previousV = graph.getList ().get (vertex).get (i).index;
			if ((markEdge[vertex][previousV] == 0) && (previousV != currEdge.secondV)){
				numMarkEdge++;
				markEdge[vertex][previousV] = 1;
				currEdge = new Edge (vertex, previousV);
				list.push (currEdge);
				nextVertex (previousV);
				break;
			}
		}
	}*/
	
	private void nextVertex (int vertex){
		int nextV;
		for (int i = 0; i < graph.getList ().get (vertex).size (); i++){
			nextV = graph.getList ().get (vertex).get (i).index;
			if (markEdge[nextV][vertex] != 1){
				//numMarkEdge++;
				lengthResult++;
				markEdge[vertex][nextV] = 1;
				markEdge[nextV][vertex] = 1;
				currEdge = new Edge (vertex, nextV);
				list.add (currEdge);
				nextVertex (nextV);
				break;
			}
		}
		
		/*if (numMarkEdge == sumDegreeVertices * 2){
			currEdge = list.pop ();
			numMarkEdge--;
			markEdge[currEdge.firstV][currEdge.secondV] = 0;
			previousVertex (currEdge.firstV);
		}*/
	}
	
	
	@Override
	protected void output (BufferedWriter bw){
		if (requirementMet){
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
	}
	
	
	SearchEulerCycle (Graph graph){
		this.graph = graph;
		numVertex = graph.getNumVertex ();
		markEdge = new int[numVertex + 1][numVertex + 1];
	}
	
	int[] perform (){
		requirement ();
		if (!requirementMet){
			error = true;
			System.out.println ("ERROR! This graph doesn't meet the requirements!");
			return new int[0];
		}
		if (startV == 0){
			startV = 1;
		}
		
		currEdge = new Edge (startV, graph.getList ().get (startV).get (0).index);
		list.add (currEdge);
		//numMarkEdge++;
		lengthResult++;
		markEdge[currEdge.firstV][currEdge.secondV] = 1;
		markEdge[currEdge.secondV][currEdge.firstV] = 1;
		nextVertex (currEdge.secondV);
		
		result = new int[lengthResult + 1];
		finishAlgorithm ();
		
		return result;
	}
}
