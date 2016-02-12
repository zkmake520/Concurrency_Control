import java.util.*;


/**
 *  Bridge Class: Responsible for keeping and maintaing all states and locks
 * 
 */
public class Bridge{
	ReentrantLock reentrantLock = new ReentrantLock();
	Condition leftWaitingQueue = reentrantLock.newCondition();
	Condition rightWaitingQueue = reentrantLock.newCondition();
	AtomicInteger leftWaitingCount = new AtomicInteger();
	AtomicInteger rightWaitingCount = new AtomicInteger();
	AtomicInteger carOnBridgeCount = new AtomicInteger();
	Lock lock = NoLock;
	private final int capacity;
	public enum Lock{
		LeftLock,RightLock, NoLock
	}
	public enum Direction{
		LeftDirection, RightDirection
	}
	public Bridge(int capacity){
		this.capacity = capacity;
	}
	public void enterBridge(String name){
		reentrantLock.lock();
		Direction dir = getDirection(name);
		//if there are already three cars on bridge
		if(carOnBridgeCount.get() == capacity){
			//increase the waiting counter and wait on queue
			waitAndWakeUp(dir,"cause bridge is full");
		}
		else{
			if(lock == NoLock ){
				carOnBridgeCount.incrementAndGet();
			}	
			else if(lock == dir){
				if(getWaitingCount(dir).get() != 0){
					//even though cars on bridge is not full, but there is car on same side waiting for it
					//which means the bridge has already allowed three cars from same direction to across it
					//thus the reverse side should have the higher priority, this car should be waiting
					waitAndWakeUp(dir,"cause there is car waiting on same side");
				}	
			}
			else{
				//increase the waiting counter and wait on queue
				waitAndWakeUp(dir,"cause of different direction");
			}
		}
		reentrantLock.unlock();
	}
	public void leaveBridge(String name){
		reentrantLock.lock();
		Direction dir = getDirection(name);
		carOnBridgeCount.decrementAndGet();
		if(carOnBridgeCount == 0{
			//this is the last car that leave the bridge, should do addition tasks to wake waiting car	
			if(getWaitingCount(getReverseDirection(dir))).get() > 0){
				//there is car waiting on another side, wake up capacity size of them
				Condition queue = getWaitingQueue(getReverseDirection(dir));
				lock = getReverseLock(dir);
				for(int i = 0;i < capacity; i++){
					queue.signal();
				}
			}
			else if(getWaitingCount(dir).get() > 0){
				// wake up cars on same side cause there is no car waiting on another side
				Condition queue = getWaitingQueue(dir);
				for(int i = 0;i < capacity; i++){
					queue.signal();
				}
			}
			else{
				//no car waiting, just need to set the lock type 
				lock = NoLock;
			}
		}
		reentrantLock.unlock();
	}

	Lock getReverseLock(Direction dir){
		if(dir == LeftDirection){
			return RightLock;
		}
		else{
			return LeftLock;
		}
	}
	Direction getReverseDirection(Direction dir){
		if(dir == LeftDirection){
			return RightDirection;
		}
		else{
			return LeftDirection;
		}
	}
	Direction getDirection(String name){
		if(name.indexOf("Left")!=-1){
			return LeftDirection;
		}
		else{
			return RightDirection;
		}
	}

	void waitAndWakeUp(Direction dir, String reason){
		getWaitingCount(dir).incrementAndGet();	
		System.out.println(name+" is waiting on queue cause "+reason+". Cars on bridge:"+carOnBridgeCount.get());
		getWaitingQueue(dir).await();
		carOnBridgeCount.incrementAndGet();
		getWaitingCount(dir).decrementAndGet();
		System.out.println(name+" is waking up from queue and begin to enter bridge. Cars on bridge:"+carOnBridgeCount.get());
	}

	AtomicInteger getWaitingCount(Direction dir){
		if(dir == LeftDirection){
			return leftWaitingCount;
		}
		else{
			return rightWaitingCount;
		}
	}
	Condition getWaitingQueue(Direction dir){
		if(dir == LeftDirection){
			return leftWaitingQueue;
		}
		else{
			return rightWaitingQueue;
		}
	}
}