package Elevator_System;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created with Eclipse IDE.
 * Developer: Adarsh Mohata
 */


public class ElevatorController {
	
	private Integer Num_Of_Elevators, Num_Of_Floors;
	private ArrayList<Elevator> Elevators;
	private LinkedList<Passenger> pickUpRequests;
	
	public ElevatorController(Integer elevatorNum, Integer floorNum){
		
		Num_Of_Elevators = elevatorNum;
		Num_Of_Floors = floorNum;
		Elevators = new ArrayList<Elevator>();
		pickUpRequests = new LinkedList<Passenger>();
		for(int i = 0; i < Num_Of_Elevators; i++){
			Elevators.add(new Elevator(0,Num_Of_Floors));
		}
		
	}
	
	
	public void pickup(Passenger passenger){
		
		this.pickUpRequests.add(passenger);
	}
	
	
	public Elevator assignElevator(Elevator assigned, Elevator elevator, Passenger passenger){
		
		
		int floorDiff, direction;
		direction = elevator.getDirection();
		floorDiff = (passenger.getFloor() - elevator.getCurrentFloor());
		
		if((Math.abs(direction - passenger.getDirection()) < 2) && (floorDiff * direction >= 0)){
			
			if(assigned == null){
				return elevator;
			}
			
			
			else if(Math.abs(assigned.getCurrentFloor() - passenger.getFloor()) > Math.abs(floorDiff)){
					
				return elevator;
			}
			
			else if(Math.abs(assigned.getCurrentFloor() - passenger.getFloor()) == Math.abs(floorDiff)){
				
				if(elevator.getDirection() != 0)		//Assign a moving elevator in case of tie
					return elevator;
			}
			
		}
			
		return assigned; 
	}
	
	public void update(Elevator elevator){
		
		if(elevator.getCurrentFloor() == elevator.getNextDestination()){
			
			elevator.removeFloor();
			elevator.checkForSwap();
			ArrayList<Passenger> Requests = elevator.getRequests();
			for(Passenger passenger: Requests){
				elevator.addNewFloor(passenger.getDestination());
			}
			elevator.checkForIdle();
			elevator.deleteRequest();
		}
		
		if(elevator.getDirection() == 1){
			elevator.moveUp();
		}
		
		else if(elevator.getDirection() == -1){
			elevator.moveDown();
		}
	}
	
	public void step(){
		
		Elevator assigned;
		Passenger passenger;
		ListIterator<Passenger> Requests = pickUpRequests.listIterator();
		while(Requests.hasNext()){
			
			assigned = null;
			passenger = Requests.next();
			for(Elevator elevator: Elevators){
				
				assigned = assignElevator(assigned, elevator, passenger);
			}
			
			if(assigned != null){
				
				if(assigned.getDirection() == 0){
					assigned.setForIdleCondition(passenger);
				}
				
				else{
					assigned.addNewRequest(passenger);
					assigned.addNewFloor(passenger.getFloor());	
				}
				
				Requests.remove();
			}
		}
		
		for(Elevator elevator: Elevators){
			
			update(elevator);
		}
			
	}
	
	public Elevator getElevator(int i){
		
		return Elevators.get(i);
	}
	
	public LinkedList<Passenger> getPickup(){
		
		return this.pickUpRequests;
	}
	
	
}

