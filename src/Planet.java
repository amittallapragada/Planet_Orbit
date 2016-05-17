
public class Planet
{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p)
	{	
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	
	/*
	 * returns the distance between two bodies
	 */
	public double calcDistance(Planet pName)
	{
		//r^2 = dx^2 + dy^2
		double r;
		double dxsquare = Math.pow(pName.xxPos - xxPos, 2);
		double dysquare = Math.pow(pName.yyPos - yyPos, 2);
		r = Math.sqrt(dxsquare + dysquare);
		return r;
	}
	
	public double calcForceExertedBy(Planet pName)
	{
		//F = G * m1 * m2 / r2
		double mass1 = mass;
		double mass2 = pName.mass;
		double G = 6.67 * Math.pow(10, -11);
		double rsquared = Math.pow(calcDistance(pName), 2);
		double force = (G * mass1 * mass2) / rsquared;
		return force;
	}
	
	public double calcForceExertedByX(Planet pName)
	{
		//Fx = F * dx / r
		double dx = pName.xxPos - xxPos;
		double force = calcForceExertedBy(pName);
		double ForcebyX = (force * dx) / (calcDistance(pName));
		return ForcebyX;
	}
	
	public double calcForceExertedByY(Planet pName)
	{
		//Fy = F * dy / r
		double dy = pName.yyPos - yyPos;
		double force = calcForceExertedBy(pName);
		double ForcebyY = (force * dy) / (calcDistance(pName));
		return ForcebyY;
	}
	
	public double calcNetForceExertedByX(Planet[] planets)
	{
		double netForceX = 0;
		for(int x =0; x<planets.length; x++)
		{
			if(!(this.equals(planets[x])))
			{
				netForceX += calcForceExertedByX(planets[x]);
			}
		}
		return netForceX;
	}
	
	public double calcNetForceExertedByY(Planet[] planets)
	{
		double netForceY = 0;
		for(int x =0; x<planets.length; x++)
		{
			if(!(this.equals(planets[x])))
			{
				netForceY += calcForceExertedByY(planets[x]);
			}
		}
		return netForceY;
	}
	
	public void update(double dt, double Fx, double Fy)
	{
		//anet,x = Fnet,x / m 
		double accelX = Fx/mass;		
		double accelY = Fy/mass;
		//vnew,x = vold,x + dt * anet,x 
		double newVeloX = xxVel + (dt*(accelX));
		double newVeloY = yyVel + (dt*(accelY));
		//pnew,x = pold,x + dt * vnew,x
		double newPosX = xxPos + (dt*(newVeloX));
		double newPosY = yyPos + (dt*(newVeloY));
		xxVel = newVeloX;
		yyVel = newVeloY;
		xxPos = newPosX;
		yyPos = newPosY;
	}
	
	public void draw()
	{
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}
	
	
}//end class
