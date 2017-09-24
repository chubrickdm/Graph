package com.mycompany;

import com.chub.Graph.Algorithm.AlgorithmsManager;
import com.chub.Graph.Draw.DrawGraph;
import com.chub.Graph.Graph;

import java.util.Scanner;

public class Main{
	public static void main (String[] args){
		String comand = "0";
		Scanner in = new Scanner (System.in);
		
		Graph g = new Graph ();
		AlgorithmsManager algorithms = new AlgorithmsManager (g);
		while (!comand.equals ("6")){
			System.out.println ("\n------------Menu------------");
			System.out.println ("1-input graph\n2-output graph\n3-search all conected components");
			System.out.println ("4-draw graph in image\n5-search Euler cycle\n6-exit");
			System.out.print ("Select an action: ");
			comand = in.next ();
			
			if (comand.equals ("1")){
				g = new Graph ();
				algorithms = new AlgorithmsManager (g);
				g.input ();
			}
			else if (comand.equals ("2")){
				g.output ();
			}
			else if (comand.equals ("3")){
				algorithms.searchAllConectComponents ();
			}
			else if (comand.equals ("4")){
				DrawGraph dg = new DrawGraph (g);
				dg.saveInImage ();
			}
			else if (comand.equals ("5")){
				algorithms.searchEulerCycle ();
			}
			else if (comand.equals ("6")){
				System.out.println ("\nGood bye.");
			}
			else{
				System.out.println ("Enter '1' - '6'.");
			}
		}
	}
}
