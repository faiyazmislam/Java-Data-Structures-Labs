//Faiyaz Islam
//CIS 2168
//Project: Josephus Problem, 9/17/2021
//The program will reveal who will survive in the Josephus game given n, number of people, and k, the position killed

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class CircularLinkedList<E> implements Iterable<E> {

	// Your variables
	Node<E> head;
	Node<E> tail;
	int size;  // BE SURE TO KEEP TRACK OF THE SIZE
	
	// implement this constructor
	public CircularLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	// I highly recommend using this helper method
	// Return Node<E> found at the specified index
	private Node<E> getNode(int index) {

		Node<E> currentNode = head;				//starts at the first node

		if(index < 0 || index >= size){			//checks that the index is within the list
			throw new IndexOutOfBoundsException("Given index is out of bounds");
		}
		else{
			for(int i = 0; i < index; i++){		//goes through each node until it finds the target node
				currentNode = currentNode.next;
			}
		}

		return currentNode;
	}

	// attach a node to the end of the list
	public boolean add(E item) {
		this.add(size,item);
		return true;
	}

	public void add(int index, E item){

		if(index < 0 || index > size){		//checks that the index is within the list, if not then the error is thrown
			throw new IndexOutOfBoundsException("Given index is out of bounds");
		}

		Node<E> newNode = new Node<>(item);		//the new data is placed inside a new node

		if(size == 0){				//if the list is empty, then the new node becomes the head and the tail
			head = newNode;
			tail = newNode;

		} else if(index == 0){		//if the new node is placed at the start, the address for the previous head is
			newNode.next = head;	//given to it before it is set to be the new head
			head = newNode;

		} else if(index == size){	//if new node is placed at the end, the tail points to it,
			tail.next = newNode;	//the new node points to the head, and then the node is set as the new tail
			newNode.next = head;
			tail = newNode;

		} else{
			Node<E> before = getNode(index - 1);	//the node before the given index is identified
			newNode.next = before.next;				//the node that the prev node was pointing to is now given to the new one
			before.next = newNode;					//the prev node now points to the new one
		}

		size++;
	}

	public E remove(int index) {

		if(index < 0 || index >= size){		//checks that the index is within the list, if not then the error is thrown
			throw new IndexOutOfBoundsException("Given index is out of bounds");
		}

		E removedData = getNode(index).item;	//stores the data of the removed node

		if(size == 1){		//if there is only one node, then the head is removed and the list is empty
			removedData = head.item;
			head = null;

		} else if(index == 0){			//if the head is removed, the node that the head was pointing to becomes the new head
			removedData = head.item;	//the tail now points to the new head
			head = head.next;
			tail.next = head;

		} else if(index == size - 1){	//if the tail is removed, the node before it now points to the head
			removedData = tail.item;	//the previous node is now set as the new tail
			getNode(index - 1).next = head;
			tail = getNode(index - 1);

		} else{
			removedData = getNode(index).item;			//the node before the given index is stored
			Node<E> before = getNode(index - 1);	//the prev node now points to the node that removed node pointed to
			before.next = getNode(index).next;

		}

		size--;
		return removedData;
	}

	// Turns your list into a string
	// Useful for debugging
	public String toString(){
		Node<E> current =  head;
		StringBuilder result = new StringBuilder();
		if(size == 0){
			return "";
		}
		if(size == 1) {
			return head.item.toString();
			
		}
		else{
			do{
				result.append(current.item);
				result.append(" ==> ");
				current = current.next;
			} while(current != head);
		}
		return result.toString();
	}

	public Iterator<E> iterator(){
		return new ListIterator<E>();
	}
	
	// provided code for different assignment
	// you should not have to change this
	// change at your own risk!
	// this class is not static because it needs the class it's inside of to survive!
	private class ListIterator<E> implements Iterator<E>{
		
		Node<E> nextItem;
		Node<E> prev;
		int index;
		
		@SuppressWarnings("unchecked")
		//Creates a new iterator that starts at the head of the list
		public ListIterator(){
			nextItem = (Node<E>) head;
			index = 0;
		}

		// returns true if there is a next node
		// this is always should return true if the list has something in it
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size != 0;
		}
		
		// advances the iterator to the next item
		// handles wrapping around back to the head automatically for you
		public E next() {
			// TODO Auto-generated method stub
			prev =  nextItem;
			nextItem = nextItem.next;
			index =  (index + 1) % size;
			return prev.item;
	
		}
		
		// removed the last node was visted by the .next() call 
		// for example if we had just created a iterator
		// the following calls would remove the item at index 1 (the second person in the ring)
		// next() next() remove()
		public void remove() {
			int target;
			if(nextItem == head) {
				target = size - 1;
			} else{ 
				target = index - 1;
				index--;
			}
			CircularLinkedList.this.remove(target); //calls the above class
		}
		
	}
	
	// It's easiest if you keep it a singly linked list
	// SO DON'T CHANGE IT UNLESS YOU WANT TO MAKE IT HARDER
	private static class Node<E>{
		E item;
		Node<E> next;
		
		public Node(E item) {
			this.item = item;
		}
		
	}



	public static void main(String[] args){

		Scanner keyboard = new Scanner(System.in);				//n and k from the user is recorded
		System.out.println("Number of people in the circle: ");
		int n = keyboard.nextInt();
		System.out.println("Kill which position: ");
		int k = keyboard.nextInt();
        keyboard.close();

		CircularLinkedList<Integer> josephus = new CircularLinkedList<>();	//list is created
		for (int i = 1; i <= n; i++){
			josephus.add(i);
		}

		System.out.println(josephus.toString());

		Iterator<Integer> iter = josephus.iterator();	//iterator is initialized

		while(josephus.size > 1){			//the while loop is active until one person remains
			for(int i = 0; i < k; i++){		//loop circles through the people up to k times
				iter.next();
			}
			iter.remove();					//person is removed
			System.out.println(josephus.toString());
		}

		System.out.println("Survivor: " + josephus.toString()); //survivor is identified
	}
}
