import java.io.*;
import java.util.*;

public class NBody {

	public static void main(String[] args)
	{
		System.out.println(new File(".").getAbsolutePath());

		Scanner keyboard = new Scanner(System.in);
		double T = keyboard.nextDouble();
		double dt = keyboard.nextDouble();
		String filename = "datainput.txt";
		System.out.println("T = " + T + "dt = " + dt);
		double radius = readRadius(filename);
		String imageToDraw = "starfield.jpg";
		Planet[] planetArray = readPlanets(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, imageToDraw);
		for(int x=0; x<planetArray.length; x++)
		{
			planetArray[x].draw();
		}
		StdDraw.show(2000);		
		double counter = 0;
		while(counter!= T)
		{
			double[] xForces = new double[1000];
			double[] yForces = new double[1000];
			for(int x =0; x<planetArray.length; x++)
			{
				xForces[x] = planetArray[x].calcNetForceExertedByX(planetArray);
				yForces[x] = planetArray[x].calcNetForceExertedByY(planetArray);
				planetArray[x].update(dt, xForces[x], yForces[x]);
			}
			StdDraw.picture(0, 0, imageToDraw);
			for(int x=0; x<planetArray.length; x++)
			{
				planetArray[x].draw();
			}
			StdDraw.show(10);	
			counter = counter + dt;			
		}

	}
	
	public static double readRadius(String filename)
	{
		In in = new In(filename);
		in.readLine();
		double radius = in.readDouble();
		return radius;	
	}
	
	public static Planet[] readPlanets(String filename)
	{
		In in = new In(filename);
		int numOfPlanets = in.readInt();
		in.readDouble();
		Planet[] newplanets = new Planet[numOfPlanets];
		
		for(int x =0; x<newplanets.length; x++)
		{
			newplanets[x] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),in.readDouble(), in.readString()); 

		}
		return newplanets;
	}
	


		
			
	
}
