/**Steven Kolln
Processor*/
package edu.tcnj.kollns1;
import java.io.*;
import java.util.Random;
import javafoundations.*;
/**The car number starts at 1 and will have a limit at 100 effectivly creating 100 cars. 8 queues to represent each street lane.*/
public class Processor
{
	private int carNum=1, time=0;
	private final int LIMIT=101;
	private boolean NSFull=false, EWFull=false, bothFull=false;
	private LinkedQueue<Vehicle> MainEastStraight=new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> MainEastRight=new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> MainWestStraight=new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> MainWestRight=new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> ChurchNorthStraight=new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> ChurchNorthRight=new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> ChurchSouthStraight=new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> ChurchSouthRight=new LinkedQueue<Vehicle>();
	private PrintStream print;
	public Processor() throws IOException
	{
		print=new PrintStream("output.txt");
	}
	/**Will continue untill both the east and west direction queues are empty and the count is 100 and the same with the north south.*/
	public void go()
	{
		this.prePopulate();
		while (!bothFull)
		{
			this.NorthSouth();
			this.populate1();
			this.EastWest();
			this.populate2();
			if (EWFull&&NSFull)
				bothFull=true;
		}
	}
	/**Generates a random number 5-10 and each car will be randomly put into one of the eight queues*/
	private void prePopulate()
	{
		Random gen=new Random();
		/**How many cars to add*/
		int add=(gen.nextInt(6)+5);
		while (add>0)
		{
			boolean straight;
			int straightNum=gen.nextInt(2);
			/**Right lane or straight lane*/
			if (straightNum==0)
				straight=true;
			else
				straight=false;
				/**Determines whether going east west north or south*/
			int directionNumber=gen.nextInt(4);
			Direction direction=null;
			Street street=null;
			switch (directionNumber)
			{
				case 0:
					direction=Direction.N;
					street=Street.CHURCH;
					break;
				case 1:
					direction=Direction.S;
					street=Street.CHURCH;
					break;
				case 2:
					direction=Direction.E;
					street=Street.MAIN;
					break;
				case 3:
					direction=Direction.W;
					street=Street.MAIN;
					break;
				default:
					System.out.println("There was an error with your direction. Please look into this problem.");
			}
			/**Creates a new car dependent on the information given. Finds the lane and direction and applies it to the appropriate queue*/
			Vehicle car=new Vehicle(carNum, time, direction, street, straight);
			carNum++;
			if (car.getStraight())
			{
				switch (car.getDirection())
				{
					case N:
						//System.out.println("I was put in North straight");
						//System.out.println(car);
						ChurchNorthStraight.enqueue(car);
						//System.out.println(car+" enetred curchnorthstriahgt");
						break;
					case S:
						//System.out.println("I was put in South straight");
						//System.out.println(car);
						ChurchSouthStraight.enqueue(car);
						//System.out.println(car+" enetred curchsouthstraight");
						break;
					case W:
						//System.out.println("I was put in West straight");
						//System.out.println(car);
						MainWestStraight.enqueue(car);
						//System.out.println(car+" enetred Mainweststraight");
						break;
					case E:
						//System.out.println("I was put in East straight");
						//System.out.println(car);
						MainEastStraight.enqueue(car);
						//System.out.println(car+" enetred maineaststraight");
						break;
					default:
						System.out.println("There wan an error enquing cars in the pre propulate method. You should look into this.");
				}
			}
			else
			{
				switch (car.getDirection())
				{
					case N:
						//System.out.println("I was put in North Right");
						//System.out.println(car);
						ChurchNorthRight.enqueue(car);
						//System.out.println(car+" enetred curchnorthright");
						break;
					case S:
						//System.out.println("I was put in South Right");
						//System.out.println(car);
						ChurchSouthRight.enqueue(car);
						//System.out.println(car+" enetred curchsouthright");
						break;
					case W:
						//System.out.println("I was put in West Right");
						//System.out.println(car);
						MainWestRight.enqueue(car);
						//System.out.println(car+" enetred MainwestRight");
						break;
					case E:
						//System.out.println("I was put in East Right");
						//System.out.println(car);
						MainEastRight.enqueue(car);
						//System.out.println(car+" enetred MainEastRight");
						break;
					default:
						System.out.println("There wan an error enquing cars in the pre propulate method. You should look into this.");
				}
			}
			add--;	
		}
		print.println("--Simulation starting. Processing of North and South traffic will begin--");
		
	}
	private void NorthSouth()
	{
		/**Will be true until a car is dequeued in any lane*/
		boolean allEmpty=true;
		/**Light is 6 seconds long*/
		int rounds=2;
		/**Dequeues and prints the car info that left*/
		while (rounds>0)
		{
			time+=3;
			if (ChurchNorthStraight.size()!=0)
			{
				Vehicle car=ChurchNorthStraight.dequeue();
				car.setDepartureTime(time);
				print.println("[Time "+time+"] "+car);
				//System.out.println("Departure time was "+car.totalTime());
				allEmpty=false;
			}
			if (ChurchNorthRight.size()!=0)
			{
				Vehicle car=ChurchNorthRight.dequeue();
				car.setDepartureTime(time);
				print.println("[Time "+time+"] "+car);
				//System.out.println("Departure time was "+car.totalTime());
				allEmpty=false;
			}
			if (ChurchSouthStraight.size()!=0)
			{
				Vehicle car=ChurchSouthStraight.dequeue();
				car.setDepartureTime(time);
				print.println("[Time "+time+"] "+car);
				//System.out.println("Departure time was "+car.totalTime());
				allEmpty=false;
			}
			if (ChurchSouthRight.size()!=0)
			{
				Vehicle car=ChurchSouthRight.dequeue();
				car.setDepartureTime(time);
				print.println("[Time "+time+"] "+car);
				//System.out.println("Departure time was "+car.totalTime());
				allEmpty=false;
			}
			//print.println("WENT THROUGH LOOP "+(3-(rounds-1)));
			rounds--;
		}
		/**This will become true at the end when there are 100 cars and none left in the queue*/
		if (carNum==LIMIT&&allEmpty)
			NSFull=true;
		print.println("--Light changed. Processing of East and West traffic will begin--");
	}
	/**Same as prepoluate. Although generates different number of cars.*/
	public void populate1()
	{
		Random gen=new Random();
		int add=(gen.nextInt(8)+5);
		while (add>0)
		{
			/**If the max capacity is reach, will break out to prevent further car instantiation*/
			if (carNum==LIMIT)
			{
				break;
			}
			boolean straight;
			int straightNum=gen.nextInt(2);
			if (straightNum==0)
				straight=true;
			else
				straight=false;
			int directionNumber=gen.nextInt(4);
			Direction direction=null;
			Street street=null;
			switch (directionNumber)
			{
				case 0:
					direction=Direction.N;
					street=Street.CHURCH;
					break;
				case 1:
					direction=Direction.S;
					street=Street.CHURCH;
					break;
				case 2:
					direction=Direction.E;
					street=Street.MAIN;
					break;
				case 3:
					direction=Direction.W;
					street=Street.MAIN;
					break;
				default:
					System.out.println("There was an error with your direction. Please look into this problem.");
			}
			Vehicle car=new Vehicle(carNum, time, direction, street, straight);
			carNum++;
			if (car.getStraight())
			{
				switch (car.getDirection())
				{
					case N:
						//System.out.println("I was put in North straight");
						//System.out.println(car);
						ChurchNorthStraight.enqueue(car);
						break;
					case S:
						//System.out.println("I was put in South straight");
						//System.out.println(car);
						ChurchSouthStraight.enqueue(car);
						break;
					case W:
						//System.out.println("I was put in West straight");
						//System.out.println(car);
						MainWestStraight.enqueue(car);
						break;
					case E:
						//System.out.println("I was put in East straight");
						//System.out.println(car);
						MainEastStraight.enqueue(car);
						break;
					default:
						System.out.println("There wan an error enquing cars in the pre propulate method. You should look into this.");
				}
			}
			else
			{
				switch (car.getDirection())
				{
					case N:
						//System.out.println("I was put in North Right");
						//System.out.println(car);
						ChurchNorthRight.enqueue(car);
						break;
					case S:
						//System.out.println("I was put in South Right");
						//System.out.println(car);
						ChurchSouthRight.enqueue(car);
						break;
					case W:
						//System.out.println("I was put in West Right");
						//System.out.println(car);
						MainWestRight.enqueue(car);
						break;
					case E:
						//System.out.println("I was put in East Right");
						//System.out.println(car);
						MainEastRight.enqueue(car);
						break;
					default:
						System.out.println("There wan an error enquing cars in the pre propulate method. You should look into this.");
				}
			}
			add--;	
		}	
	}
	/**Same as NorthSouth only with the east and west queues*/
	public void EastWest()
	{
		boolean allEmpty=true;
		int rounds=3;
		while (rounds>0)
		{
			time+=3;
			if (MainEastStraight.size()!=0)
			{
				Vehicle car=MainEastStraight.dequeue();
				car.setDepartureTime(time);
				print.println("[Time "+time+"] "+car);
				//System.out.println("Departure time was "+car.totalTime());
				allEmpty=false;
			}
			if (MainEastRight.size()!=0)
			{
				Vehicle car=MainEastRight.dequeue();
				car.setDepartureTime(time);
				print.println("[Time "+time+"] "+car);
				//System.out.println("Departure time was "+car.totalTime());
				allEmpty=false;
			}
			if (MainWestStraight.size()!=0)
			{
				Vehicle car=MainWestStraight.dequeue();
				car.setDepartureTime(time);
				print.println("[Time "+time+"] "+car);
				//System.out.println("Departure time was "+car.totalTime());
				allEmpty=false;
			}
			if (MainWestRight.size()!=0)
			{
				Vehicle car=MainWestRight.dequeue();
				car.setDepartureTime(time);
				print.println("[Time "+time+"] "+car);
				//System.out.println("Departure time was "+car.totalTime());
				allEmpty=false;
			}
			//print.println("WENT THROUGH LOOP "+(3-(rounds-1)));
			rounds--;
		}
		if (allEmpty&&carNum==LIMIT)
			EWFull=true;
		print.println("--Light changed. Processing of North and South traffic will begin--");
	}
	/**Again, same as previous populate except with different number of cars*/
	public void populate2()
	{
		Random gen=new Random();
		int add=(gen.nextInt(13)+3);
		while (add>0)
		{
			if (carNum==LIMIT)
			{
				break;
			}
			boolean straight;
			int straightNum=gen.nextInt(2);
			if (straightNum==0)
				straight=true;
			else
				straight=false;
			int directionNumber=gen.nextInt(4);
			Direction direction=null;
			Street street=null;
			switch (directionNumber)
			{
				case 0:
					direction=Direction.N;
					street=Street.CHURCH;
					break;
				case 1:
					direction=Direction.S;
					street=Street.CHURCH;
					break;
				case 2:
					direction=Direction.E;
					street=Street.MAIN;
					break;
				case 3:
					direction=Direction.W;
					street=Street.MAIN;
					break;
				default:
					System.out.println("There was an error with your direction. Please look into this problem.");
			}
			Vehicle car=new Vehicle(carNum, time, direction, street, straight);
			carNum++;
			if (car.getStraight())
			{
				switch (car.getDirection())
				{
					case N:
						//System.out.println("I was put in North straight");
						//System.out.println(car);
						ChurchNorthStraight.enqueue(car);
						break;
					case S:
						//System.out.println("I was put in South straight");
						//System.out.println(car);
						ChurchSouthStraight.enqueue(car);
						break;
					case W:
						//System.out.println("I was put in West straight");
						//System.out.println(car);
						MainWestStraight.enqueue(car);
						break;
					case E:
						//System.out.println("I was put in East straight");
						//System.out.println(car);
						MainEastStraight.enqueue(car);
						break;
					default:
						System.out.println("There wan an error enquing cars in the pre propulate method. You should look into this.");
				}
			}
			else
			{
				switch (car.getDirection())
				{
					case N:
						//System.out.println("I was put in North Right");
						//System.out.println(car);
						ChurchNorthRight.enqueue(car);
						break;
					case S:
						//System.out.println("I was put in South Right");
						//System.out.println(car);
						ChurchSouthRight.enqueue(car);
						break;
					case W:
						//System.out.println("I was put in West Right");
						//System.out.println(car);
						MainWestRight.enqueue(car);
						break;
					case E:
						//System.out.println("I was put in East Right");
						//System.out.println(car);
						MainEastRight.enqueue(car);
						break;
					default:
						System.out.println("There wan an error enquing cars in the pre propulate method. You should look into this.");
				}
			}
			add--;	
		}
	}
	
}