package com.mycompany;

import com.chubrickdm.Graph;
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
/*public class Main extends Application{
	public static void main (String [] args){
		System.out.println ("Launch javaFX program.");
		launch (args);
	}
	
	public void init (){
		System.out.println ("Init program.");
		Graph g = new Graph ();
		
	}
	
	public void start (Stage myStage){
		System.out.println ("Start program.");
		myStage.setTitle ("Example program.");
		FlowPane rootNode = new FlowPane ();
		Scene myScene = new Scene (rootNode , 300 , 200);
		myStage.setScene (myScene);
	    myStage.show ();
	}
	
	public void stop (){
		System.out.println ("Stop program.");
	}
}*/
