package Elevator_System;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Created with Eclipse IDE.
 * Developer: Adarsh Mohata
 */

//Direction
//	+1 for UP
//	-1 for DOWN
//	 0 for IDLE 

public class Elevator {
	
	private Integer CurrentFloor, direction;	
	TreeSet<Integer> UpGoalFloors, DownGoalFloors, CurrentDestination, OppositeDestination; 
	 ArrayList< ArrayList<Passenger> > UpPendingRequests, DownPendingRequests, PendingRequests ;

	
	public Elevator(Integer StartFloor, Integer MaxFloor){
		
		CurrentFloor = StartFloor;
		UpGoalFloors = new TreeSet<Integer>();
		DownGoalFloors = new TreeSet<Integer>(Collections.reverseOrder());
		UpPendingRequests = new ArrayList< ArrayList<Passenger> >(MaxFloor);
		DownPendingRequests = new ArrayList< ArrayList<Passenger> >(MaxFloor);
		for(int i = 0; i < MaxFloor; i++){
			UpPendingRequests.add(new ArrayList<Passenger>());
		}
		for(int i = 0; i < MaxFloor; i++){
			DownPendingRequests.add(new ArrayList<Passenger>());
		}
		direction = 0; 
		PendingRequests = UpPendingRequests;
		CurrentDestination = UpGoalFloors;
		OppositeDestination = DownGoalFloors;
	}
	
	public int getCurrentFloor(){
	
		return CurrentFloor;
		
	}
	
	public int getDirection(){
		
		return direction;
		
	}
	
	public void swapDirection(){
		
		if(direction == 1){
			
			CurrentDestination = DownGoalFloors;
			OppositeDestination = UpGoalFloors;
			PendingRequests = DownPendingRequests;
		}
		
		else if(direction == -1){
			
			CurrentDestination = UpGoalFloors;
			OppositeDestination = DownGoalFloors;
			PendingRequests = UpPendingRequests;
		}
		
		direction *= -1;
		
		if(CurrentFloor == getNextDestination())
			removeFloor();
	}
	
	public void addNewFloor(Integer newDestination){
		
		CurrentDestination.add(newDestination);
	}
	
	public void addNewRequest(Passenger passenger){
		
		PendingRequests.get(passenger.getFloor()).add(passenger);
	}
	
	public int getNextDestination(){
		
		if(CurrentDestination.size() == 0){
			return CurrentFloor;
		}
			
		
		return CurrentDestination.first();
	}
	
	public ArrayList<Passenger> getRequests(){
		
		return PendingRequests.get(CurrentFloor);
	}
	
	public void removeFloor(){
		
		if(CurrentDestination.size() > 0){
			CurrentDestination.pollFirst();
		}
	}
	
	public void moveUp(){
		
		CurrentFloor++;
	}
	
	public void moveDown(){
		
		CurrentFloor--;
	}
	
	public void setForIdleCondition(Passenger passenger){
		
		int passengerFloor, passengerDirection;
		passengerFloor = passenger.getFloor();
		passengerDirection = passenger.getDirection();
		
		if(passengerFloor == CurrentFloor){
			
			direction = passengerDirection;
			
			if(passengerDirection == 1){
				
				CurrentDestination = UpGoalFloors;
				OppositeDestination = DownGoalFloors;
				PendingRequests = UpPendingRequests;
			}
			
			else{
				
				CurrentDestination = DownGoalFloors;
				OppositeDestination = UpGoalFloors;
				PendingRequests = DownPendingRequests;
			}
			
			addNewRequest(passenger);
		}
		
		else if(passengerFloor > CurrentFloor){
			
			CurrentDestination = UpGoalFloors;
			direction = 1;
			PendingRequests = UpPendingRequests;
			if(passengerDirection == -1){
				
				DownPendingRequests.get(passengerFloor).add(passenger);
				DownGoalFloors.add(passengerFloor);
			}
			
			else{
				
				addNewRequest(passenger);
			}
			
			addNewFloor(passengerFloor);
			
		}
		
		else{
			
			CurrentDestination = DownGoalFloors;
			direction = -1;
			PendingRequests = DownPendingRequests;
			if(passenger.getDirection() == 1){
				
				UpPendingRequests.get(passengerFloor).add(passenger);
				UpGoalFloors.add(passengerFloor);
			}
			
			else{
				
				addNewRequest(passenger);
			}
			addNewFloor(passengerFloor);
		}
	}
	
	public void checkForSwap(){
		
		
		if(CurrentDestination.size() == 0){
			
			if(OppositeDestination.size() > 0){
				
				swapDirection();
			}
			
		}
	}
	
	
	public void checkForIdle(){
		
		if(CurrentDestination.size() == 0){
			
			direction = 0;
		}
	}
	
	public void deleteRequest(){
		
		PendingRequests.get(CurrentFloor).clear();
	}
	
}
