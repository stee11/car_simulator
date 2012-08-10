/**Steven Kolln
Vehicle*/
package edu.tcnj.kollns1;
import java.text.DecimalFormat;
public class Vehicle
{
	/**All cars have a departure time of zero and are set to the time in the processor class*/
	private int vehicleNumber, arrivalTime, departureTime=0;
	private Street streetName;
	private Direction directionName;
	private boolean straight;
	public Vehicle(int num, int arrivalTime, Direction direction, Street street, boolean straight)
	{
		vehicleNumber=num;
		this.arrivalTime=arrivalTime;
		streetName=street;
		directionName=direction;
		this.straight=straight;
	}
	public int getVehicleNumber()
	{
		return vehicleNumber;
	}
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	public void setDepartureTime(int number)
	{
		departureTime=number;
	}
	public int getDepartureTime()
	{
		return departureTime;
	}
	public Street getStreet()
	{
		return streetName;
	}
	public Direction getDirection()
	{
		return directionName;
	}
	/**Applies the math for you by sbubtracting the arrival time from when the car left*/
	public int totalTime()
	{
		return departureTime-arrivalTime;
	}
	public boolean getStraight()
	{
		return straight;
	}
	public String toString()
	{
		DecimalFormat fmt=new DecimalFormat("00");
		String d="";
		String bound="";
		/**Finds the direction of the car*/
		switch (directionName)
		{
			case N:
				d="(Northbound)";
				bound="eastbound";
				break;
			case S:
				d="(Southbound)";
				bound="westbound";
				break;
			case E:
				d="(Eastbound)";
				bound="southbound";
				break;
			case W:
				d="(Westbound)";
				bound="northbound";
				break;
			default:
				System.out.println("There was an error with your direction. Please look into this.");
		}
		String result="";
		/**Two different string resultsd dependent on whether the car goes straight through the intersection or not*/
		if (straight)
			result="Vehicle #"+fmt.format(this.getVehicleNumber())+" "+d+" moved through intersection. Total wait time "+fmt.format(this.totalTime())+" seconds.";
		else
			result="Vehicle #"+fmt.format(this.getVehicleNumber())+" "+d+" turned right and headed "+bound+". Total wait time "+fmt.format(this.totalTime())+" seconds.";
		return result;
	}
}