##Elevator Control System

###Design
The Elevator Control system has two major classes: 'Elevator' and 'ElevatorController'. A helper class, 'Passenger' is also present.

#####Elevator

The Elevator manages details about its current floor, direction, Goal Floors (in upward as well as downward direction), Pending Requests(Passengers) at each floor.  

The Elevator's direction can have the following values:

+1 : Elevator moving up

-1 : Elevator moving down

 0 : Elevator idle


#####ElevatorController

The Elevator controller controls all the elevators and manages the next step of the system.  It also handles the pick up requests from the passengers and assign them to elevators accordingly.


#####Passenger

The Passenger is a simple class used for interaction with the elevator. It is characterised by its current floor, destination floor and direction. 

* Querying the state of the elevators (what floor are they on and where they are going) can be performed through getElevator( int id ) in the ElevatorController and then getCurrentFloor() and getNextDestination() from the Elevator.

* Receiving a pickup request is performed through pickup( Passenger passenger ) in the ElevatorController.

* step() in ElevatorController is used for time-stepping the simulation.

* update(Elevator elevator) is used for updating the status of the elevator in every step. It is responsible for executing the pending requests at the current floor and  setting the direction of the elevator accordingly.

###Assumptions

* The elevator maximum capacity is not bound.
* It is assumed that the any elevator will not fail and keep on working. i.e. load distribution on failure of a node in the distributed system is not considered for now.
* The system is not thread safe. Synchronisation between pick up and step has to be taken care of in case of multithreading.

###Algorithm

The pick up requests are added to the PickUpRequests list of the ElevatorController.

In every step:

* All the pick up requests in the list are processed and  a suitable elevator is searched for each of them. If all the elevators are moving to the opposite direction i.e. busy, then the request is kept for the next step.

* For each request, the elevator either moving in the same direction or idle and nearest to its floor is assigned. If there is a tie between moving and idle elevators, moving one is chosen. The request is then added to the Pending Request list of the corresponding floor in the assigned elevator.

* For each elevator, if the current floor is a possible destination in the Goal Floors of the elevator then, all the pending requests in the corresponding floor of the elevator are processed and new goal floors  are added to its set of Goal Floors. The direction is also set depending on the Goal Floors and Pending Requests. Eventually, the elevator moves up or down depending on its direction.  

###Possible Improvements

Some of the possible improvements in the system which could be done :

* The maximum capacity of the elevators can be defined and during assignment of elevators to the list, load capacity should be checked.

* In case of failure of an elevator, the requests assigned to the elevator will be lost and therefore will not be processed. So, instead of assigning requests to Pending Request list of the elevators, a global Pending Request list can be created which could be used by all the elevators. However, special care has to be taken for concurrency control when multiple elevators are accessing the same list.

* Better Error handling and exception handling should be done.

Note: Testing has been done for some simple test cases but complex cases have not been considered yet. 

   
