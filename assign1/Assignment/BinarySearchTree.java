package Assignment;

public class BinarySearchTree {
	static Node root = null;
	
	//creating a nodes for binary search tree
	class Node {
		int value;
		Node rightChild;
		Node leftChild;

		public Node(int value, Node rightChild, Node leftChild) {
			super();
			this.value = value;
			this.rightChild = rightChild;
			this.leftChild = leftChild;
		}

	}
 
	//Method for inserting nodes in Binary Search Tree
	public Node insertNodes(Node root, int value) {
		
		//If intial node is null, create a node and make that as root node
		if (root == null) {
			//creating a node
			Node node = new Node(value, null, null);
			root = node;
		}
		/*If root is not null and value to be inserted is less than value of root node, it is inserted at 
		left side of the root */
		else if (root.value > value) {
			root.leftChild = insertNodes(root.leftChild, value);
		}
		/*If root is not null and value to be inserted is greater than value of root node, it is inserted at 
		right side of the root */
		else if (root.value < value) {
			root.rightChild = insertNodes(root.rightChild, value);
		}
		return root;
	}

	public static void main(String[] args) {
		BinarySearchTree binarySearchTreeObject = new BinarySearchTree();
		
		//calling insertNodes method for inserting new values
		root = binarySearchTreeObject.insertNodes(root, 40);
		binarySearchTreeObject.insertNodes(root, 60);
		binarySearchTreeObject.insertNodes(root, 20);
		binarySearchTreeObject.insertNodes(root, 80);
		binarySearchTreeObject.insertNodes(root, 50);
		binarySearchTreeObject.insertNodes(root, 10);
		binarySearchTreeObject.insertNodes(root, 30);
		binarySearchTreeObject.insertNodes(root, 15);
		binarySearchTreeObject.insertNodes(root, 5);
		binarySearchTreeObject.insertNodes(root, 35);
		binarySearchTreeObject.insertNodes(root, 25);
		binarySearchTreeObject.insertNodes(root, 45);
		binarySearchTreeObject.insertNodes(root, 55);
		binarySearchTreeObject.insertNodes(root, 70);
		binarySearchTreeObject.insertNodes(root, 90);
		binarySearchTreeObject.insertNodes(root, 32);
		binarySearchTreeObject.insertNodes(root, 33);
		binarySearchTreeObject.insertNodes(root, 48);
		binarySearchTreeObject.insertNodes(root, 46);
		
		//calling inorderTravsersal method for traversing the BST using inOrder traversal 
		System.out.println("Inorder Traversal of BinarySearchTree");
		inorderTravsersal(root);
		//calling deleteNode method to delete the value 40
		root = deleteNode(root, 40);
		System.out.println();
		System.out.println("Inorder Traversal of BinarySearchTree after deleting 40");
		//calling inorderTravsersal method for traversing the BST using inOrder traversal after deleting 40
		inorderTravsersal(root);
		//calling deleteNode method to delete the value 20
		root = deleteNode(root, 20);
		System.out.println();
		System.out.println("Inorder Traversal of BinarySearchTree after deleting 20");
		//calling inorderTravsersal method for traversing the BST using inOrder traversal after deleting 20
		inorderTravsersal(root);

	}

	// inorderTravsersal method for traversing using inorder(left-root-right)
	public static void inorderTravsersal(Node root) {
		if (root == null)
			return;
		
		if (root.leftChild != null) {
			inorderTravsersal(root.leftChild);
		}
		System.out.print(root.value + " ");
		if (root.rightChild != null) {
			inorderTravsersal(root.rightChild);
		}
	}

	//deleteNode method for deleting a value in BST
	public static Node deleteNode(Node root, int value) {
		if (root == null) {
			return root;
		}
		// search for the value to be deleted in left sub tree
		if (root.value > value) {
			root.leftChild = deleteNode(root.leftChild, value);
			return root;
		} // search  for the value to be deleted in right sub tree
		else if (root.value < value) {
			root.rightChild = deleteNode(root.rightChild, value);
			return root;
		} // found the value to be deleted.
		else {
			//if the value to be deleted does not have leftchild, then return rightchild.
			if (root.leftChild == null) {
				return root.rightChild;
			}//if the value to be deleted does not have rightchild, then return leftchild.
			if (root.rightChild == null) {
				return root.leftChild;
			} //if the value to be deleted have both right and left child,then find successor and replace the 
			//value to be deleted with successor and then arrange it as BST again.
			else {
				//finding the next max value in the tree after the value to be deleted.
				//Successor can be found by traversing first right and then left till the end.
				root.value = getSuccessor(root.rightChild);
				
				//delete the link of the successor node
				root.rightChild = deleteNode(root.rightChild, root.value);
				return root;
			}
		}
	}

	//getSuccessor method to get successor value
	public static int getSuccessor(Node root) {
		if (root == null) {
			return -1;
		}
		if (root.leftChild != null) {
			return getSuccessor(root.leftChild);
		} else {
			return root.value;
		}
	}

}
