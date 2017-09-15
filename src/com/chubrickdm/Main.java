package com.chubrickdm;
import chubrickdm.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;


public class Main extends Application{
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
}
