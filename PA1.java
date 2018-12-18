import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Programer: Khoa Phan
 * Course: CS146-06
 * Description: The program simulates a hospital waiting room.
 * Simulations: Insert a patient
 * 				Take out the patient with highest priority
 * 				Heap sort
 * 				BuildMaxHeap
 *				Increase key for the patient's priority number
 *
 */
public class PA1 {
	private static int size = 20; // default 20 patients
    public static void main(String[] args) 
    {	        
	        int priority;   	 // to hold patient's priority number
	        int ind;	    	 // to hold patient's index number
	        String name; 		 // to hold patien's name
	        char repeat = 'r';   // set and control the loop of menu       
	        ArrayList<Integer> list = new ArrayList<Integer>(size); // arraylist hold patient's priority number from 1 to 20
	        for(int i = 1; i <= size; i++) 
	        {
	            list.add(i);
	        }     
	        
	        System.out.println("The list of patients in the waiting room with their priority numbers:");
	        System.out.println("*********************************************************************");
	        Random rand = new Random(); // create a random method 
	        
	        Patient[] array = new Patient[size];// create an array patient
	        int i =0;
	        do
	        {
		        while(list.size() > 0 && i < size) 
		        {
		        	array[i] = new Patient();
		            int index = rand.nextInt(list.size()); //random priority number in array list
		              	  
		            String[] names = { "Dung", "Phuc", "Ryan", "Aron", "Kris","Jenny", "Bob", "Jason",
		            "Vivian", "Julian", "Vinh", "Julie", "Jacob", "Kevin", "Rose", "Jack",  "Katy",
		            "Alissa", "Tom", "Jerry", "Malvin", "Justin", "Lee", "Zhang", "Fa", "Bel",  "Ella",
		            "Austin", "Zac", "Jake", "Logan", "Ken"}; //create an array string contains patients' names

		            array[i].setPatientName(names[i]); // set patient name to the array
		            array[i].setPriority(list.remove(index));// set patient priority to the array and avoid duplicate priority number	            
			        System.out.println("Index: " + (i) + " " + array[i].toString()); //display a random list of patient's index, name, priority number
		            i++;
		        } 
		        //Display the menu
		        System.out.println("\t\t\tWelcome to Hospital waiting room");    	        
		        System.out.println("**********************************************************************");	
		        System.out.println("\t\t\t\t Menu ");
		        System.out.println("1. Check to see which patient has the highest priority number");
		        System.out.println("2. Call the person has highest priority number in the room and \n delete he/she from the list and Register for the new patient into\n the waiting list with their priority numbers ");
		        System.out.println("3. Increase the priority number of a person in the waiting list");
		        System.out.println("4. Arrange the waiting list by acesding order of priority numbers");	
		        System.out.println("5. Arrange the waiting list with build Max Heap style");	
		        System.out.println("0. Exit");
		        System.out.print("Please choose an option that you want to operate: ");
		        
		        Scanner input = new Scanner(System.in); //needed for scanner 
		        switch (input.nextInt())
		        {
			        case 1: 
			        			buildMaxHeap(array); //build a max heap tree
			        			heapMaximum(array); // call the hightest priority but not extract out of the tree
			        			System.out.println("The patient with the highest priority number is: \n " + heapMaximum(array)); //display the highest priority
			        			System.out.println("*******************************************************************");	
				        	break;	
			         case 2:
					        	buildMaxHeap(array); //build a max heap tree
					 			System.out.println("Call in the patient with highest priority of the array: \n " + heapExtractMax(array)); //display the highest priority has been taking out of the list
					 			System.out.println("*******************************************************************");
					 			//Display the heap sorted list after extracting the highest priority
					 			heapSort(array);
					 			for(int k = 0; k < array.length; k++)
					 			{
					 				System.out.println("Index: " + (k) + " " + array[k].toString());
					 			}
					 			maxHeapInsert(array);// insert a new patient to the list to maintain the room always have 20 patients;
				        	 	System.out.println("*******************************************************************");	
								//Display the list of heap sorted list after insert new patient
				        	 	heapSort(array);
					 			for(int k = 0; k < array.length; k++)
					 			{
					 				System.out.println("Index: " + (k) + " " + array[k].toString());
					 			}
				            break;	
			         case 3:
			        	 	System.out.println("**************************************************************");	
			        	 	//Display the heap sorted for the users to choose an person they want to increase key 
			        	 	heapSort(array);
					 			for(int k = 0; k < array.length; k++)
					 			{
					 				System.out.println("Index: " + (k) + " " + array[k].toString());
					 			}
					 		System.out.println("**************************************************************");	
					        	Scanner scan = new Scanner(System.in); //needed for scanner
					        	int index; // to hold index of patient
					        	do // do while loop to repeat the step when condition is true
					        	{
						 			System.out.print("Enter the index of the patient that you want to increase their priority numbers 0-19: ");
						 			index = scan.nextInt();
					        	}
					        	while(index > 19 || index < 0);				 			
					 			
					 			System.out.print("Enter the new patient's priority number that they want to increase: ");
					 			priority = scan.nextInt();		 			
					 			
					 			if(array[index].getPriority() < priority) //check if the priority number is duplicated
					 				checkduplicate(array, index, priority);
					 			
					 			heapIncreaseKey(array,index, priority); // increase key for the selected patient 
					 			heapSort(array); //Display the heap sorted list again after increasing key for selected patient
					 			for(int k = 0; k < array.length; k++)
					 			{
					 				System.out.println("Index: " + (k) + " " + array[k].toString());
					 			}
					 		break;
			         case 4: 
			        	 	//Display the heap sorted list of patient
			        		 heapSort(array);
			        		 for(int k = 0; k < array.length; k++)
					 			{					 				
					 				System.out.println("Index: " + (k) + " " + array[k].toString());
					 			}	
				        	break;
			         case 5:
			        	 	//Display the list with build Max Heap style
			        	 	 buildMaxHeap(array);
			        	 	for(int k = 0; k < array.length; k++)
					 			{					 				
					 				System.out.println("Index: " + (k) + " " + array[k].toString());
					 			}
			        	 	break;
			         case 0:
			        	 //Exit the program
			        	 repeat = 'n';
			        	 break;
			        	 	// return menu if user entered the option that is not in the list
			         		default:
			        		System.out.println("You entered an invalid option. Please choose a correct option"); 
		        }
		     
		        }
	        while (repeat != 'n' );
    }
    

	/**
	 * Exchange 2 patients
	 * @param patient1 a Patient object
	 * @param patient2 another Patient object
	 */   
    
	public static void swap(Patient patient1,Patient patient2){
    	        Patient tempPatient = new Patient();
    	        tempPatient.setPatientName(patient1.getPatientName());
    	        tempPatient.setPriority(patient1.getPriority());
    	        patient1.setPatientName(patient2.getPatientName());
    	        patient1.setPriority(patient2.getPriority());
    	        patient2.setPatientName(tempPatient.getPatientName());
    	        patient2.setPriority(tempPatient.getPriority());
        }

	/**
	 * Maintain the property of max heap
	 * @param array a Patient array
	 * @param nodes an Integer representing an index of patient
	 * @param size an Integer representing a number of waiting patients
	 */
    	public static void maxHeapify(Patient[] array, int nodes, int size)
    	{
    		int largest = nodes;
    		int left = 2*nodes +1;
    		int right = 2*nodes + 2;
    		if (left <= size-1 && array[left].getPriority() > array[largest].getPriority())
    		{
    			largest = left;
    		}
    		if (right <= size-1 && array[right].getPriority() > array[largest].getPriority())
    		{
    			largest = right;
    		}
    		if (largest != nodes)
    		{
    			swap(array[nodes], array[largest]);
    			maxHeapify(array, largest, size);
    		}
    	}

	/**
	 * build a max heap tree, the max priority will go to the first index of array
	 * @param array a Patient array
	 */
    	public static void buildMaxHeap(Patient[] array)
    		{
    			for (int i = size -1 /2; i >= 0; i--)
    			{
    				maxHeapify(array, i, size);
    			}
    		}

	/**
	 * sort max heap tree, the max priority will go to the last index of array
	 * @param array a Patient array
	 */
    	public static void heapSort(Patient[] array)
    		{    			
    			int newsize = size;
    			buildMaxHeap(array);
    			for (int i = size - 1; i >= 1; i--)
    			{
    				swap(array[0], array[i]);
    				newsize = newsize - 1;
    				maxHeapify(array, 0, newsize);
    			}
    		}

	/**
	 * avoid two or more patients to have same priority
	 * @param array a Patient array
	 * @param index an Integer representing an index of patient
	 * @param priority an Integer representing a priority of patient
	 */
	
	
	public static void checkduplicate (Patient[] array, int index, int priority )
	{
		
		for(int i = 0; i<size; i++) 
		{
			if(i != index && array[i].getPriority() == priority)
			{
				array[i].setPriority(array[i].getPriority() +1 );
				checkduplicate(array, i, array[i].getPriority());
			}
		}
	}

	/**
	 * Insert a patient to max heap tree
	 * @param array a Patient array
	 */
	public static void maxHeapInsert(Patient[] array)
    		{
	    			Scanner scan = new Scanner(System.in);
	    			System.out.print("Enter the new patient name: ");
	    			String name = scan.next();
	    			int priorityNumber;
	    			do{
	        			System.out.print("Enter priority number starts from 1 : ");
	    				priorityNumber = scan.nextInt();
	    			} while(priorityNumber < 1);
	    			checkduplicate(array, size, priorityNumber);
	    			array[size] = new Patient(name, priorityNumber);
	    			heapIncreaseKey(array, size, priorityNumber);
					size = size + 1;
    		 }

	/**
	 * Return the patient with highest priority, and remove that patient out of the array
	 * @param array a Patient array
	 * @return the patient with highest priority
	 */
    	public static Patient heapExtractMax(Patient[] array)
    		{
    			swap(array[0], array[size -1]);
    			Patient max = array[size-1];
    			array[size - 1] = new Patient();
    			size = size  -1;
    			maxHeapify (array, 0, size);    			
    			return max;
    		}

	/**
	 * Maintain the property of max heap after modification of key
	 * @param array a Patient array
	 * @param index an Integer representing an index of patient
	 * @param prior_number an Integer representing a priority of patient
	 */
	public static void heapIncreaseKey(Patient[] array, int index, int prior_number)
    		{
    			if (array[index].getPriority() >= prior_number)
    			{
    				System.out.println("The new priority is less than or equal to the patient's old priority");
    				System.out.println("**************************************************************");
    				return;
    			}
				array[index].setPriority(prior_number);				
				
    		}

	/**
	 * Return the patient with highest priority
	 * @param array a Patient array
	 * @return the patient with highest priority
	 */
	public static Patient heapMaximum(Patient[] array)
    		{				
    			return array[0];
    		}

	/**
	 * A class representing patients with their index number, names and priority numbers
	 */
	public static class Patient {

		String patientName;
		int priorityNumber;		

		/**
		 * Constructor: requires specific name and priority
		 * @param name a String representing a name of patient
		 * @param priority an Integer representing a priority number of patient
		 */
		public Patient(String name, int priority)
		{
			this.patientName = name;
			this.priorityNumber = priority;
		}

		/**
		 * Constructor: requires nothing
		 */
		public Patient()
		{
		}

		/**
		 * Sets a name to this patient
		 * @param name a String representing a name of patient
		 */
		public void setPatientName(String name)
		{
			this.patientName = name;
		}

		/**
		 * Sets a priority number to this patient
		 * @param priority an Integer representing a priority number of patient
		 */
		public void setPriority(int priority)
		{
			this.priorityNumber = priority;
		}

		/**
		 * Returns the name of this patient
		 * @return the name of patient
		 */
		public String getPatientName()
		{
			return this.patientName;
		}

		/**
		 * Returns the priority number of this patient
		 * @return the priority number of this patient
		 */
		public int getPriority()
		{
			return this.priorityNumber;
		}

		/**
		 * Returns a String representation of this patient
		 * @return a String representation of this patient
		 */
		public String toString()
		{
			return "\t-Patient:" + this.patientName + "\t\t\tPriority number: " + this.priorityNumber;
		}		
	}
}
