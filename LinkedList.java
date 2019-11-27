package myMath;
import myMath.Monom;
import java.io.*;
import java.util.Iterator; 

//a Singly Linked List 
public class LinkedList { 
	Node head; // head of list 
	int size;
    Node sorted; 

	// Linked list Node. 
	static class Node { 
		Monom data = new Monom(0,0);
		Node next; 

		// Constructor 
		Node(Monom d) 
		{ 
			this.data.set_power(d.get_power()); 
			this.data.set_coefficient(d.get_coefficient()); 
			next = null; 
		} 
	} 
	public Iterator<Monom> iterator() {
		return new Iterator<Monom>() {
			Node current = head;
			@Override
			public boolean hasNext() {
				return current != null;
			}
			@Override
			public Monom next() {
				Monom data = new Monom(0,0);
				if (hasNext()) {
					data = current.data;
					current = current.next;
					return data;
				}
				return null;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Remove not implemented.");
			}

		};

	}


	// Method to insert a new Node 
	public static LinkedList insert(LinkedList list, Monom data) 
	{ 
		// Create a new Node with given data

		Node new_Node = new Node(data); 
		new_Node.next = null; 

		// If the Linked List is empty, 
		if (list.head == null) { 
			list.head = new_Node; 
		} 
		else { 
			Node last = list.head; 
			while (last.next != null) { 
				last = last.next; 

			} 

			// Insert the new_Node at last Node 
			last.next = new_Node; 
		} 
		list.size++;
		list.insertionSort(list.head);
		return list; 
	} 

	// Method to print the LinkedList. 
	public static void printList(LinkedList list) 
	{ 
		Node currNode = list.head; 

		System.out.print("\nLinkedList: "); 

		// Traverse through the LinkedList 
		while (currNode != null) { 
			// Print the data at current Node 
			System.out.print(currNode.data + " "); 

			// Go to next Node 
			currNode = currNode.next; 
		} 
		System.out.println("\n"); 
	} 
	public static void get_size(LinkedList list) 
	{ 

		System.out.print("size is: " +list.size); 
	}
//delete by Monom method
	public static LinkedList deleteByMonom(LinkedList list, Monom Montodelete) 
	{ 
		Node currNode = list.head, prev = null; 
		//first case
		if (currNode != null && currNode.data.get_coefficient() == Montodelete.get_coefficient() &&
				currNode.data.get_power() == Montodelete.get_power()) { 
			list.head = currNode.next; // Changed head 

			list.insertionSort(list.head);
			// Return the updated List 
			list.size--;
			return list; 
		} 

		// 
		// CASE 2: 
		// If the key is somewhere other than at head 
		// 

		while (currNode != null && currNode.data.get_coefficient() != Montodelete.get_coefficient() &&
				currNode.data.get_power() != Montodelete.get_power()) { 
			prev = currNode; 
			currNode = currNode.next; 
		} 

		// If the key was present, it should be at currNode 
		// Therefore the currNode shall not be null 
		if (currNode != null) { 
			// Since the key is at currNode 
			// Unlink currNode from linked list 
			prev.next = currNode.next; 


		} 

		// 
		// CASE 3: The key is not present 
		if (currNode == null) { 
		} 

		// return the sorted List 
		list.insertionSort(list.head);

		return list; 
	} 

	
	//sorting the list by Monom Exponents (High -> low)
    void insertionSort(Node headref)  
    { 
        sorted = null; 
        Node current = headref; 
        while (current != null)  
        { 
            Node next = current.next; 
            sortedInsert(current); 
            current = next; 
        } 
        head = sorted; 
    } 
  
    //insert and keep sorted
    void sortedInsert(Node newNode)  
    { 
        if (sorted == null || sorted.data.get_power() <= newNode.data.get_power())  
        { 
            newNode.next = sorted; 
            sorted = newNode; 
        } 
        else 
        { 
            Node current = sorted; 
            while (current.next != null && current.next.data.get_power() < newNode.data.get_power())  
            { 
                current = current.next; 
            } 
            newNode.next = current.next; 
            current.next = newNode; 
        } 
    } 
	} 

