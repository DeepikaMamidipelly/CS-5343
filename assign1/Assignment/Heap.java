package Assignment;

public class Heap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 int arr[]= {15,1, 33, 6, 9, 13, 10, 29, 18, 5, 17, 12, 19,23,21,20};
		
		//arr[0]=15 represents the number of elements in array
		
		System.out.println("Array representation before heapifying");
		for (int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		//building min heap where parent is always less than child;
		buildHeap(arr);
		System.out.println("Array representation after heapifying");
		for (int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		
		//Heap sorting the obtained min heap
		sorting(arr);
		
		
		System.out.println("Array representation after heap sorting");
		for (int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		} 

	}

	
	static void buildHeap(int[] array) {
		int n = (array[0] / 2);
		for (int i = n; i >=1; i--) {
			heapifying(array, array[0], i);
		}
	}


    static void sorting(int arr[]){
    	
    	//replacing last element with root element;
    	//decrementing the array size by 1 and then heapifying.
    	for (int i = arr[0]; i >= 1; i--) { 
			int temp = arr[1];
			arr[1] = arr[i];
			arr[i] = temp;
			heapifying(arr, i, 1);
    	}
    }
    
	static void heapifying(int[] array, int n, int parent) {
		int smallest = parent; 
		int leftChild = 2 * parent ; 
		int rightChild = 2 * parent + 1; 

		// If left child is smaller than parent
		if (leftChild < n && array[leftChild] < array[smallest])
			smallest = leftChild;

		// If right child is smaller than parent
		if (rightChild < n && array[rightChild] < array[smallest])
			smallest = rightChild;

		// If smallest is not parent
		//swap smallest and the parent
		if (smallest != parent) {
			int swap = array[parent];
			array[parent] = array[smallest];
			array[smallest] = swap;

			// recursively call heaify function
			heapifying(array, n, smallest);
		}
	}
	
}
