package Elevator_System_Test;

import Elevator_System.ElevatorController;
import Elevator_System.Passenger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 * Created with Eclipse IDE.
 * Developer: Adarsh Mohata
 */


public class ElevatorControllerTest {

	ElevatorController elevControl;
	Passenger p1, p2, p3,p4;
	
	@Before
	public void init(){
		
		elevControl = new ElevatorController(4, 10); //4 elevators, 10 floors
		p1 = new Passenger(4, 0, -1);  //At floor 4, destination floor 0
		p2 = new Passenger(2, 6, 1);  //At floor 2, destination floor 6
		p3 = new Passenger(7, 2, -1);  //At floor 7, destination floor 2
		p4 = new Passenger(0, 5, 1);  //At floor 0, destination floor 5
	}
	
	@Test
	public void pickupTest(){
		
		elevControl.pickup(p1);
		elevControl.pickup(p2);
		elevControl.pickup(p3);
		Assert.assertEquals(elevControl.getPickup().size(), 3);
	}
	
	@Test
	public void stepTest(){
		
		// Pick up p1, p2 and p3 
		elevControl.pickup(p1);
		elevControl.pickup(p2);
		elevControl.pickup(p3);
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(0).getDirection(), 1);			//p1 and p2 assigned to elevator 0
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 1);
		Assert.assertEquals(elevControl.getElevator(0).getNextDestination(), 2);
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(1).getCurrentFloor(), 2);
		Assert.assertEquals(elevControl.getElevator(1).getNextDestination(), 7);	//p3 assigned to elevator 1
		
		// Pick up p4
		elevControl.pickup(p4);
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(2).getCurrentFloor(), 1);		//p4 assigned to elevator 2
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 3);
		Assert.assertEquals(elevControl.getElevator(0).getNextDestination(), 4);
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 4);
		Assert.assertEquals(elevControl.getElevator(1).getCurrentFloor(), 4);
		Assert.assertEquals(elevControl.getElevator(2).getCurrentFloor(), 2);
		
		// Pick up p5
		Passenger p5 = new Passenger(3, 8, 1);  //At floor 3, destination floor 8
		elevControl.pickup(p5);
				
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(2).getCurrentFloor(), 3);		//p5 assigned to elevator 2
		Assert.assertEquals(elevControl.getElevator(2).getNextDestination(), 3);
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 5);
		Assert.assertEquals(elevControl.getElevator(0).getNextDestination(), 6);
		
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 6);
		Assert.assertEquals(elevControl.getElevator(2).getCurrentFloor(), 4);

		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(1).getCurrentFloor(), 7);
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 5);
		Assert.assertEquals(elevControl.getElevator(0).getDirection(), -1);
		Assert.assertEquals(elevControl.getElevator(0).getNextDestination(), 4);
		
		
		elevControl.step();
		Assert.assertEquals(elevControl.getPickup().size(), 0);
		Assert.assertEquals(elevControl.getElevator(1).getCurrentFloor(), 6);
		Assert.assertEquals(elevControl.getElevator(1).getDirection(), -1);
		Assert.assertEquals(elevControl.getElevator(1).getNextDestination(), 2);
		Assert.assertEquals(elevControl.getElevator(2).getCurrentFloor(), 6);
		Assert.assertEquals(elevControl.getElevator(2).getDirection(), 1);
		
		// New Request, At floor 6, destination floor 3
		elevControl.pickup(new Passenger(6, 3, -1));
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(1).getCurrentFloor(), 5);
		Assert.assertEquals(elevControl.getElevator(1).getDirection(), -1);
		Assert.assertEquals(elevControl.getElevator(1).getNextDestination(), 3);
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 3);
		Assert.assertEquals(elevControl.getElevator(0).getDirection(), -1);
		Assert.assertEquals(elevControl.getElevator(0).getNextDestination(), 0);
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 2);
		Assert.assertEquals(elevControl.getElevator(1).getCurrentFloor(), 4);
		Assert.assertEquals(elevControl.getElevator(2).getCurrentFloor(), 8);
		Assert.assertEquals(elevControl.getElevator(1).getNextDestination(), 3);
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(2).getDirection(), 0);
		Assert.assertEquals(elevControl.getElevator(2).getCurrentFloor(), 8);
		
		
		elevControl.step();
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(0).getCurrentFloor(), 0);
		Assert.assertEquals(elevControl.getElevator(0).getDirection(), 0);
		Assert.assertEquals(elevControl.getElevator(3).getDirection(), 0);
		
		elevControl.step();
		Assert.assertEquals(elevControl.getElevator(1).getCurrentFloor(), 2);
		Assert.assertEquals(elevControl.getElevator(1).getDirection(), 0);
		Assert.assertEquals(elevControl.getElevator(1).getNextDestination(), 2);
		
		
	}
	
}


