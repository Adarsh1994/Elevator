package Elevator_System;

/**
 * Created with Eclipse IDE.
 * Developer: Adarsh Mohata
 */


public class Passenger {

	private Integer CurrentFloor, GoalFloor, direction;
	
	public Passenger(Integer source, Integer destination, Integer dir){
		
		CurrentFloor = source;
		GoalFloor = destination;
		direction = dir;
	}

	public int getFloor(){
		
		return CurrentFloor;
	}
	
	
	// 	+1 for UP
	//	-1 for DOWN
	public int getDirection(){
		
		return direction;
	}
	
	
	public int getDestination(){
		
		return GoalFloor;
	}

}
