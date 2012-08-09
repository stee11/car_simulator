/**Steven Kolln
Project 3
CSC 230 02
4/13/11
Street*/
package edu.tcnj.kollns1;
public enum Street
{
	MAIN("Main Street"),
	
	CHURCH("Church Street");
	
	private String streetName;
	
	private Street(String streetName)
	{
		this.streetName=streetName;
	}
	public String getStreetName()
	{
		return streetName;
	}
	public String toString()
	{
		return this.getStreetName();
	}
}
	
	