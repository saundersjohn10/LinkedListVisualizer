package Projects;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LinkedListVisualizer {
	
	//define components
	private JFrame frame;
	
	private JButton addNodBtn; private JTextField addNodeTxt;
	private JButton clearBtn; 
	private JButton removeBtn; private JTextField remTxt;
	
	private JButton parseBtn; 
	private JButton parse2PointerBtn; 
	
	private JLabel treeLblLine1; //label where tree text goes
	private JLabel treeLblLine2; //label where tree text goes
	private JLabel treeLblLine3; //label where tree text goes
	
	private LinkedList<Object> list;
	
	//main method starting gui
	public static void main(String[] args) {
		LinkedListVisualizer theApp = new LinkedListVisualizer(); 
	}
		
	//constructor
	public LinkedListVisualizer() {
		initialize(); //call method to start
	}
	
	private void initialize() {
		//set up basics
		frame = new JFrame();
		frame.setTitle("Linked List Visualizer"); frame.setSize(750,200); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setLayout(new FlowLayout());
		
		//add components to frame and create settings
		addNodeTxt = new JTextField(4); frame.add(addNodeTxt);
		addNodBtn = new JButton("Add Node"); frame.add(addNodBtn);
		
		remTxt = new JTextField(4); frame.add(remTxt);
		removeBtn = new JButton("Remove Node"); frame.add(removeBtn);
		
		parseBtn = new JButton("Parse List"); frame.add(parseBtn); 
		parse2PointerBtn = new JButton("Parse List: 2 Pointers"); frame.add(parse2PointerBtn); 
		clearBtn = new JButton("Clear"); frame.add(clearBtn);
		
		//error labels
		JLabel enterNumLabel = new JLabel("Enter a number"); enterNumLabel.setForeground(Color.RED); enterNumLabel.setVisible(false);
		frame.add(enterNumLabel);
		
		//the three lines that the list text is contained on
		treeLblLine1 = new JLabel(); frame.add(treeLblLine1);
		treeLblLine2 = new JLabel(); frame.add(treeLblLine2);
		treeLblLine3 = new JLabel(); frame.add(treeLblLine3);
		
		list = new LinkedList<Object>();
		
		//add node Button pressed
		addNodBtn.addActionListener(new ActionListener() { 
			@Override 
	        public void actionPerformed(ActionEvent event) {
				Thread t = new Thread(new Runnable() { //define thread that will show the tree addition
					@Override
					public void run() {
						list.parseList(); //shows recursion through tree
						if(list.size() == 0) { //set the head node if the list is empty
							list.setHead(getFormat(addNodeTxt.getText()));
						}
						else { 
							list.addNode(getFormat(addNodeTxt.getText()));
						}
						setText(list.toString()); //show updated list
					}
				});
				t.start(); //start thread
			}
		});
		
		//clear button pressed
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				list = new LinkedList<Object>(); //set the list to a new list
				setText(list.toString()); //show empty list
			}
		});
		
		//remove button pressed
		removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Thread t = new Thread(new Runnable() { //thread showing the removal
					@Override
					public void run() {
						list.removeVisually(getFormat(remTxt.getText().toString())); //removes visually
						setText(list.toString()); //update text
					}
					
				});
				t.start(); //start the thread
			}
		});
		
		//button that parses with one pointer through the list
		parseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						list.parseList(); //parses through
						setText(list.toString()); //resets the text
					}
				});
				t.start(); 
			}
		});
		
		//button that parses with two pointers through the list
		parse2PointerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						list.parse2Pointers();
						setText(list.toString());
					}
				});
				t.start();
			}
		});
		
		frame.setVisible(true); 
	}
	
	//returns the correct format of the given node
	private static Object getFormat(Object o) {
		//first tries to parse as a int, then a double, then a boolean, then as a string
		String compare = o.toString();
		try {
			//parse int
			return Integer.valueOf(compare);
		}
		catch(NumberFormatException e1) {
			try {
				//parse as Double
				return Double.valueOf(compare);
			}
			catch(NumberFormatException e2) {
				//parse boolean, Boolean.parseBoolean() gives no exception so must do manually
				if(compare.toLowerCase().equals("true")) 
					return true;
				else if(compare.toLowerCase().equals("false"))
					return false;
				else //return string as last resort
					return compare; 
			}
		}
	}
	
	//given string, formats the text correctly (line by line) onto the gui
	private void setText(String str) {
		int length = str.length();
		if(length <= 100) {
			treeLblLine1.setText(str);
		}
		else if(length <= 200) {
			treeLblLine1.setText(str.substring(0, 101));
			treeLblLine2.setText(str.substring(101));
		}
		else if(length <= 300) {
			treeLblLine1.setText(str.substring(0, 101));
			treeLblLine2.setText(str.substring(101, 201));
			treeLblLine3.setText(str.substring(201));
		}
	}
	
	//a doubly linked list
	class LinkedList <T> {
		Node<T> head = null;
		Node<T> tail = null; //keep track of tail for quick insertion
		int size = 0; 
		
		public LinkedList() {}
		
		public void setHead(T data) { //set the root, the tail is initialized as being head
			this.head = new Node<>(data);
			this.tail = head;
			size++;
		}
		
		public void addNode(T data) {
			Node<T> toAdd = new Node<>(data);
			Node<T> newPrev = tail; //the tail node will be a prev node 
			tail.next = toAdd;
			tail = toAdd;
			tail.prev = newPrev;
			size++;
		}
		
		@Override
		public String toString() { //a custom toString() method
			Node<T> current = head;
			StringBuilder b = new StringBuilder();
			while(current != null) { //parse through the list
				b.append("(" + current + ")-> "); 
				current = current.next;
			}
			if(size != 0) //if the list is empty, don't show "null"
				b.append("null");
			return b.toString();
		}
		
		public int size() {
			return size;
		}
		
		/*---------------Methods for threads-------------*/
		public void parseList() {
			Node<T> pointerNode = head; //node that will have a *
			while(pointerNode != null) { //loop until every node has been pointed to
				StringBuilder b = new StringBuilder();
				Node<T> current = head;
				while(current != null) {
					if(current != pointerNode) { //add a * here 
						b.append("(" + current + ")-> ");
					}
					else {
						b.append("*(" + current + ")-> ");
					}
					current = current.next;
				}
				b.append("null");
				treeLblLine1.setText(b.toString()); //update text on GUI
				try {Thread.sleep(100);} catch(InterruptedException e) {} //pause thread
				pointerNode = pointerNode.next;
			}
		}
		
		public void parse2Pointers() {
			Node<T> fastPointer = head;
			Node<T> slowPointer = head;
			boolean slowMove = false;
			while(slowPointer != null) {
				StringBuilder b = new StringBuilder();
				Node<T> current = head;
				while(current != null) {
					if(current != fastPointer && current != slowPointer) { //add a * here 
						b.append("(" + current + ")-> ");
					}
					else {
						b.append("*(" + current + ")-> ");
					}
					current = current.next;
				}
				b.append("null");
				setText(b.toString()); //update text on GUI
				try {Thread.sleep(300);} catch(InterruptedException e) {} //pause thread
				//update pointers
				if(fastPointer != null) 
					fastPointer = fastPointer.next;
				if(slowMove) {
					slowPointer = slowPointer.next;
					slowMove = false;
				}
				else slowMove = true;
			}
		}
		
		public void removeVisually(T data) {
			Node<T> pointerNode = head; //node that will have a *
			boolean found = false;
			while(null != pointerNode) { //loop until every node has been pointed to
				StringBuilder b = new StringBuilder();
				Node<T> current = head;
				while(current != null) {
					if(current != pointerNode) { //add a * here 
						b.append("(" + current + ")-> ");
					}
					else if(current == pointerNode && String.valueOf(current.data).equals(String.valueOf(data))){
						b.append("found(" + current + ")-> ");
						found = true;
					}
					else{
						b.append("*(" + current + ")-> ");
					}
					current = current.next;
				}
				b.append("null");
				setText(b.toString()); //update text on GUI
				try {Thread.sleep(150);} catch(InterruptedException e) {} //pause thread
				//from here remove the node currently named pointerNode
				if(found) {
					try {Thread.sleep(700);} catch(InterruptedException e) {} //pause thread to show node has been found
					//remove the node by updating pointers
					if(pointerNode.next == null && pointerNode.prev == null) { //only one node, create a new List
						list = new LinkedList<Object>();
						return;
					}
					Node<T> previous;
					if(pointerNode.prev != null) { //check for beg of list
						previous = pointerNode.prev; 
						if(pointerNode.next != null) { //check for end of list
							Node<T> next = pointerNode.next;
							previous.next = next;
							next.prev = previous;
						}
						else { //removing the last node
							previous.next = null;
							tail = previous;
						}
							
					}
					else { //beg of list
						Node<T> next = pointerNode.next;
						next.prev = null; //set the prev to null
						list.head = (Node<Object>) next;
					}
					break;	
				}
				pointerNode = pointerNode.next; //continue to parse through list
			}
		}
	}
	
	static class Node <T> { //node class containing the data
		Node<T> next = null;
		Node<T> prev = null; //allows for a Doubly linked list
		T data = null;
		
		public Node(T data) {
			this.data = data;
		}
		
		@Override
		public String toString() { //return data at toString() call
			return data.toString();
		}
	}
	
}
