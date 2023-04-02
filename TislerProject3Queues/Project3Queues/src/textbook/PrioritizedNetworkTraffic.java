package textbook;

public class PrioritizedNetworkTraffic {

	private int[] Q;

	private int head = -1;
	private int tail = 0;
	private int length;
	
	// Precondition: Priority queue Q is defined but not created.
	// Postcondition: Priority queue Q is created, and its size is set to "queueSize".
	//                All values of Q are initialized to -1.
	//                length is set to "queueSize".
	public PrioritizedNetworkTraffic(int queueSize)
	{
		Q = new int[queueSize];			//initialized Q to size given
		
		for (int i = 0; i < queueSize; i++ ) {			//fill Q with -1 to help with the tail
			Q[i] = -1;
		}
		
		length = queueSize;
	}
	
	
	
	// Precondition: Priority queue Q is existent.
	//               "tail" is already set to the index of Q where a new packet may be inserted. 
	//               "head" is already set to the index of Q where the packet with the highest priority is stored.
	// Postcondition: A new packet with priority "newElement" is inserted in the priority queue Q.
	//                "head" is set to the index of Q where the packet with the highest priority is stored.
	//                "tail" is set to the next index of Q that is available for storage of a new element.
	//                If Q is full, "tail" is to the index of Q where the packet with the lowest priority is stored.
	public void Enqueue(int newElement)
	{	   
		
		Q[tail] = newElement;			//insert new element at the tail
		System.out.println("At Enqueue: inserted " + newElement + " in index "+ tail);
		
		head = LocateNextHead(0, length - 1); //call upon method to get the updated head
		
		if (tail >= length - 1) {
			//Q is full, so we must now go to the one with the lowest priority
			tail = LocateNextTail(0, length - 1);  //call upon method to get the updated tail
		}
		
		else {
			//Q is not yet full, so we can just use the next available slot
			tail++;
		}

	
	}

	
	// Precondition: Priority queue Q is existent.
	//               "head" is already set to the index of Q where the packet with the highest priority is stored.
	//               If Q is empty, "head" is -1.
	//               "tail" is already set to the index of Q where a new packet may be inserted.
	// Postcondition: The packet with index equal to "head" is removed from Q and is returned to the caller.
	//                -1 is inserted in its place, namely in the cell of Q where the removed packet was previously stored. 
    //                "head" is set to the index of Q where the packet with the highest priority is stored.
    //                "tail" is set to the next index of Q that is available for storage of a new element.
    //                If Q is empty, "head" is set to -1 and "tail" is set to 0. 
	
	public int Dequeue()
	{	   

		int removedData = Q[head];			//place the highest priority data here to return at the end
		Q[head] = -1;						//replace the data at index head with -1 to indicate it is empty
		System.out.println("At Dequeue: removed " + removedData + " from index "+ head);
		
		//update the head and tail
		head = LocateNextHead(0, length - 1);
		tail = LocateNextTail(0, length - 1);
		
		//if Q is empty, head is set to -1 and tail is set to 0
		if (Q[head] == -1) {
			//if the data at the head is -1, then the whole array must consist of all -1 meaning it is empty
			head = -1;
			tail = 0;
		}
		
		return removedData;
	}
	
	// Precondition: Priority queue Q is existent.
	//               "startIndex" and "endIndex" are indices of Q that define the beginning and the end, respectively, of the subarray of Q that needs to be searched.
	//               "startIndex" and "endIndex" may refer to the entire Q. 
	// Postcondition: The index of Q, where the packet with the highest priority is stored, is found and is returned to the caller. 
	public int LocateNextHead(int startIndex, int endIndex)
	{	   
		
		int highestPriority = Q[startIndex];		//start the max at the first piece of data so we have something to compare
		int highPriorityIndex = startIndex;			//keep track of the index of the highest priority data
		for (int i = startIndex; i  <= endIndex; i++) {
			if (highestPriority < Q[i]) {
				highestPriority = Q[i];				//we have a new max, reset it
				highPriorityIndex = i;
			}
		}
		
		return highPriorityIndex;
	}
	
	
	// Precondition: Priority queue Q is existent.
	//               "startIndex" and "endIndex" are indices of Q that define the beginning and the end, respectively, of the subarray of Q that needs to be searched.
	//               "startIndex" and "endIndex" may refer to the entire Q. 
	// Postcondition: The index of Q, where the packet with the lowest priority is stored, is found and is returned to the caller. 
	public int LocateNextTail(int startIndex, int endIndex)
	{	   

		int lowestPriority = Q[startIndex];			//start the min at the first piece of data so we have something to compare
		int lowPriorityIndex = startIndex;			//keep track of the index of the lowest priority data
		for (int i = startIndex; i <= endIndex; i++) {
			if (lowestPriority > Q[i]) {			//we have a new min, reset it
				lowestPriority = Q[i];
				lowPriorityIndex = i;
			}
		}
		
		return lowPriorityIndex;
	}
	
	
	// Precondition: Priority queue Q is existent. 
	// Postcondition: The index and priority of each packet in Q are displayed on the monitor.
	public void DisplayPriorityQueue()
	{
		System.out.println("-----------------------------------------------------------");
		System.out.println("Displaying priority queue data...");

		//go throught the array displaying each index and value
		for (int i = 0; i < length; i ++) {
			System.out.println("Index: " + i + " Packet Priority: " + Q[i]);
		}
		
		System.out.println("Current Head: " + head + "; Current Tail: " + tail);
		
		System.out.println("-----------------------------------------------------------");
	}
	
}
