package com.chub.Graph.Draw;

import com.chub.Graph.Graph;
import com.chub.Graph.Orientation;
import com.chub.Graph.StorageMethod.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DrawGraph{
	private int width = 300;
	private int height = 300;
	private int sizePoint = 4;
	private int numVertex = 0;
	private String typeImage;
	private String imageName;
	private Graph graph;
	private ArrayList <Coordinate> coordinates;
	private BufferedImage image;
	private Graphics graphics;
	
	
	private void save (){
		File file = new File (imageName + "." + typeImage);
		try{
			ImageIO.write (image, typeImage, file);  // ignore returned boolean
		}
		catch (IOException e){
			System.out.println ("Write error for " + file.getPath () + ": " + e.getMessage ());
		}
	}
	
	private void calulateCoord (){
		int radius;
		int x;
		int y;
		int centerX = width / 2;
		int centerY = height / 2;
		Coordinate coord;
		double interval = Math.PI * 2 / ((double) numVertex);
		if (width < height){
			radius = (width / 2) - width / 6;
		}
		else{
			radius = (height / 2) - height / 6;
		}
		
		coordinates.add (new Coordinate (0,0));
		for (int i = 1; i < numVertex + 1; i++){
			x = (int) (centerX - (Math.cos (i * interval) * radius));
			y = (int) (centerY - (Math.sin (i * interval) * radius));
			coord = new Coordinate (x, y);
			coordinates.add (coord);
		}
	}
	
	private void enterName (){
		InputStreamReader isr = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader (isr);
		try{
			System.out.print ("Enter the image name !WITHOUT TYPE!: ");
			imageName = br.readLine ();
			System.out.print ("Enter the type of image: ");
			typeImage = br.readLine ();
		}
		catch (IOException e){
			System.out.println ("ERROR! Input error.");
		}
	}
	
	private void drawVertex (){
		int x1;
		int y1;
		graphics.setColor (Color.RED);
		for (int i = 1; i < numVertex + 1; i++){
			x1 = coordinates.get (i).x;
			y1 = coordinates.get (i).y;
			graphics.drawRect (x1 - sizePoint / 2, y1 - sizePoint / 2, sizePoint, sizePoint);
			if (x1 > width / 2 && y1 <= height / 2){
				graphics.drawString (String.valueOf (i), x1 + 10, y1 - 10);
			}
			else if (x1 <= width / 2 && y1 < height / 2){
				graphics.drawString (String.valueOf (i), x1 - 10, y1 - 10);
			}
			else if (x1 < width / 2 && y1 >= height / 2){
				graphics.drawString (String.valueOf (i), x1 - 10, y1 + 10);
			}
			else{
				graphics.drawString (String.valueOf (i), x1 + 10, y1 + 10);
			}
		}
	}
	
	private void draw (){
		image = new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
		graphics = image.getGraphics ();
		graphics.setColor (Color.LIGHT_GRAY);
		graphics.fillRect (0, 0, width, height);
		
		calulateCoord ();
		drawVertex ();
		
		int x1;
		int y1;
		int x2;
		int y2;
		
		for (int i = 1; i < numVertex + 1; i++){
			x1 = coordinates.get (i).x;
			y1 = coordinates.get (i).y;
			for (Pair tmpP : graph.getList ().get (i)){
				graphics.setColor (Color.black);
				x2 = coordinates.get (tmpP.index).x;
				y2 = coordinates.get (tmpP.index).y;
				graphics.drawLine (x1, y1, x2, y2);
				
				graphics.setColor (Color.GREEN);
				graphics.drawRect ((x2 + (x1 - x2) / 10) - 1, (y2 + (y1 - y2) / 10) - 1, 3, 3);
			}
		}
		
		graphics.setColor (Color.black);
		if (graph.getOrientation () == Orientation.oriented){
			graphics.drawString ("Graph is oriented", 5, 10);
		}
		else if (graph.getOrientation () == Orientation.unOriented){
			graphics.drawString ("Graph is unOriented", 5, 10);
		}
	}
	
	
	public DrawGraph (Graph graph){
		this.graph = graph;
		numVertex = graph.getNumVertex ();
		coordinates = new ArrayList <> (numVertex + 1);
	}
	
	public void saveInImage (){
		enterName ();
		draw ();
		save ();
	}
}
