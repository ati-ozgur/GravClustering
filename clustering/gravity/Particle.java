package clustering.gravity;

import geom.VectorND;

public class Particle
{
	public VectorND position;
	public double mass;
	
	public Particle(VectorND v)
	{
		position = v;
		mass = 1.0;
	}
	
	public Particle(VectorND v, double m)
	{
		position = v;
		mass = m;
	}
	
	public double distanceSquare(Particle b) { return position.distSq(b.position); }
	
	public static double[] MoveVector(Particle a, Particle b, double G)
	{
		int dimension = a.position.length();
		
		double magnitude = 0.0;
		double[] diff = new double[dimension];
		
		for (int i = 0; i < dimension; i++)	
		{
			diff[i] = b.position.get(i) - a.position.get(i);
			magnitude += (diff[i]*diff[i]);
		}
		magnitude = Math.sqrt(magnitude);
		
		double scale = 0.0;
		if (magnitude > 0) scale = (G*a.mass*b.mass)/(2.0*(Math.pow(magnitude, 3)));
		
		double[] move = new double[dimension];
		for (int i = 0; i < dimension; i++)	 move[i] = diff[i]*scale;
		return move;
	}
}
