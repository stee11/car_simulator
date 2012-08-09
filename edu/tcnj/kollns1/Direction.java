/**Steven Kolln
Project 3
CSC 230 02
4/13/11
Direction*/
package edu.tcnj.kollns1;
public enum Direction
{
	N("North"),
	
	S("South"),
	
	E("East"),
	
	W("West");
	
	private String direction;
	private Direction(String direction)
	{
		this.direction=direction;
	}
	public String getDirection()
	{
		return direction;
	}
	public String toString()
	{
		return this.getDirection();
	}
}
	
	