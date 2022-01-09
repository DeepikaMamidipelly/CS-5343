package Assignment;

import java.util.Scanner;

public class SortingAssignmentSLL {

	public static class Node {
		int nodeValue;
		Node next;
	};

	
	public static Node sortSLL_recursive(Node head_Node) {
		// if there is only a single node
		if (head_Node.next == null) {
			return head_Node;
		}
		Node currentMinValueNode = head_Node;
		Node beforeMinValueNode = null;
		Node temp = head_Node;

		while (temp.next != null) {
			if (temp.next.nodeValue < currentMinValueNode.nodeValue) {
				currentMinValueNode = temp.next;
				beforeMinValueNode = temp;
			}
			temp = temp.next;
		}

		// swap the head node with the 'min' node
		if (currentMinValueNode != head_Node) {
			head_Node = swappingNodes(head_Node, head_Node, currentMinValueNode, beforeMinValueNode);
		}
		head_Node.next = sortSLL_recursive(head_Node.next);
		return head_Node;
	}
	

	public static void main(String args[]) {
		Node head = null;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no of nodes in SLL");
		int nodes=sc.nextInt();
		System.out.println("Enter values in SLL");
		for(int i=1;i<=nodes;i++) {
			int valu=sc.nextInt();
			head=addNode(valu);
		}
		System.out.println("Traversing S.L.L before Sorting...");
		traverseSLL(head);

		// sorting the Single linked list
		head = sortSLL_recursive(head);

		System.out.println("\n" + "Traversing S.L.L after Sorting:");
		traverseSLL(head);
	}

	// Adding Nodes to List
	public static Node headNode = null;

	static Node addNode(int new_data) {
		if (headNode == null) {
			Node newNode = new Node();
			newNode.nodeValue = new_data;
			newNode.next = null;
			headNode = newNode;
		} else {
			Node newNode = new Node();
			newNode.nodeValue = new_data;
			newNode.next = (headNode);
			headNode = newNode;
		}
		return headNode;
	}

	// function to print the linked list
	public static void traverseSLL(Node head) {
		while (head != null) {
			System.out.print(head.nodeValue + " ");
			head = head.next;
		}
	}

	
	public static Node swappingNodes(Node head_refNode, Node currentNode, Node currentMinValueNode, Node beforeMinValueNode) {
		head_refNode = currentMinValueNode;                         
		beforeMinValueNode.next = currentNode;  
		Node temp = currentMinValueNode.next;  
		currentMinValueNode.next = currentNode.next;  
		currentNode.next = temp;        
		return head_refNode;
	}
	
}
