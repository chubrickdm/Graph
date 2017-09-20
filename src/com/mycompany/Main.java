package com.mycompany;

import com.chub.Graph.Graph;

import java.util.Scanner;

public class Main{
	public static void main (String[] args){
		String comand = "0";
		Scanner in = new Scanner (System.in);
		
		Graph g = new Graph ();
		while (!comand.equals ("4")){
			System.out.println ("\n------------Menu------------");
			System.out.println ("1-new graph\n2-input graph\n3-output graph\n4-exit");
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
				System.out.println ("\nGood bye.");
			}
			else{
				System.out.println ("Enter '1', '2', '3' or '4'.");
			}
		}
	}
}
