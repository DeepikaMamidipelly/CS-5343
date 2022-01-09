package Assignment;

import java.util.Scanner;

public class BinarySearchAssignment {

	static int searchValue(int arrayList[], int value, int low, int highValue) {

		if (highValue < low) {
			return -1;
		} else {
			//Dividing the array into three parts{low-mid1, mid1-mid2, mid2-highValue}
			int mid1 = low + (highValue - low) / 3;
			int mid2 = highValue - (highValue - low) / 3;
			if (arrayList[mid1] == value) {
				return mid1;
			} else if (arrayList[mid2] == value) {
				return mid2;
			} else {
				//if value is less than mid1 value..find in low-mid1
				if (value < arrayList[mid1]) {
					return searchValue(arrayList, value, low, mid1 - 1);
				}
				//if value is greater than mid2 value..find in mid2-highValue
				else if (value > arrayList[mid2]) {
					return searchValue(arrayList, value, mid2 + 1, highValue);
				} 
				//mid1-mid2
				else {
					return searchValue(arrayList, value, mid1 + 1, mid2 - 1);
				}
			}

		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Incase of duplicate values it returns the first occurence index of the value");
		System.out.println("Enter no of elements you want to consider in an array");
		int arrayLength=sc.nextInt();
		int array[] = new int[arrayLength];
		System.out.print("Enter values in array in sorted order..");
		for (int i = 0; i < array.length; i++) {
			array[i] = sc.nextInt();
		}
		
		System.out.println("Elements in an array:"+"\n");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
		
		System.out.print("\n"+"Enter value to be searched...");
		int valueToSearch = sc.nextInt();
		// Search the key using searchValue
		int indexValue = searchValue(array, valueToSearch, 0, array.length - 1);
		if (indexValue == -1) {
			System.out.print("Searched Value is not found in the given array");
		} else {
			System.out.print("Index value of searched item is " + indexValue);
		}

	}

}
