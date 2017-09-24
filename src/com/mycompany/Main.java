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
		while (!comand.equals ("7")){
			System.out.println ("\n------------Menu------------");
			System.out.println ("1-new graph\n2-input graph\n3-output graph\n4-search all conected components");
			System.out.println ("5-draw graph in image\n6-search Euler cycle\n7-exit");
			System.out.print ("Select an action: ");
			comand = in.next ();
			if (comand.equals ("1")){
				g = new Graph ();
			}
			else if (comand.equals ("2")){
				g.input ();
			}
			else if (comand.equals ("3")){
				g.output ();
			}
			else if (comand.equals ("4")){
				algorithms.searchAllConectComponents ();
			}
			else if (comand.equals ("5")){
				DrawGraph dg = new DrawGraph (g);
				dg.saveInImage ();
			}
			else if (comand.equals ("6")){
				//algorithms.s
			}
			else if (comand.equals ("7")){
				System.out.println ("\nGood bye.");
			}
			else{
				System.out.println ("Enter '1' - '7'.");
			}
		}
	}
}
