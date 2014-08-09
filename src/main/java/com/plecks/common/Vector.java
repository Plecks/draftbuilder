package com.plecks.common;

public class Vector {
	private final double x,y,z;
	
	public Vector(double posX, double posY, double posZ)
	{
		x = posX;
		y = posY;
		z = posZ;
	}
	
	public Vector(int posX, int posY, int posZ)
	{
		x = posX;
		y = posY;
		z = posZ;
	}
	
	public double getX()
	{
		return x;
	}
	
	public int getBlockX()
	{
		return (int) Math.floor(x);
	}
	
	public double getY()
	{
		return y;
	}
	
	public int getBlockY()
	{
		return (int) Math.floor(y);
	}
	
	public double getZ()
	{
		return z;
	}
	
	public int getBlockZ()
	{
		return (int) Math.floor(z);
	}

	public double magnitude()
	{
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector normal()
	{
		double mag = this.magnitude();
		
		return new Vector(x / mag, y / mag, z / mag);
	}
	
	public Vector add(int dx, int dy, int dz)
	{
		return new Vector(x + dx, y + dy, z + dz);
	}
	
	public Vector add(Vector v)
	{
		return new Vector(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	
	public Vector subtract(int dx, int dy, int dz)
	{
		return new Vector(x - dx, y - dy, z - dz);
	}
	
	public Vector subtract(Vector v)
	{
		return new Vector(x - v.getX(), y - v.getY(), z - v.getZ());
	}
	
	public Vector scale(int mult)
	{
		return new Vector(x * mult, y * mult, z * mult);
	}
	
	public Vector copy()
	{
		return new Vector(x, y, z);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this){return true;}
		
		if(obj instanceof Vector)
		{
			Vector point = (Vector)obj;

			if( x == point.x && y == point.y && z == point.z)
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int) ((x*37 + y) * 8999 + z);
	}

	@Override
	public String toString()
	{
		return "(" + x + "," + y + "," + z + ")";
	}
}
