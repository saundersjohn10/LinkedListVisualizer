# LinkedListVisualizer
A gui displaying the insertion, deletion, and parsing through a doubly linked list

Project utilizes:
- java generics
- threads
- swing GUI (Graphical User Interface)
- Doubly Linked class

Video showing gui:
https://drive.google.com/file/d/1uJnmgU50SdX9NwmnUVn4iLVPiNxZmWZQ/view?usp=sharing

Utilizing GUI, this project allows for the visualization and interaction with a Doubly Linked List.  The user can add, remove, and parse through data of the classes String, Integer, Double, and Boolean that all contained in the same linked list.  A linked list meaning that data is contained in nodes with each one having a pointer that points to the next node and doubly linked meaning that the each node also points to its preceeding node.  

<img width="750" alt="screen shot 2018-07-30 at 4 58 45 pm" src="https://user-images.githubusercontent.com/36249204/43423267-f07d82ae-9419-11e8-8eac-b2a397800411.png">

Java generics was used in the node and linked list classes, allowing for there to be different types of data inserted into the same list.  The list utilized in the back end is initalized as the type 'object', so theoretically any type of data (for example a collection) could be inserted into a node (but this was not fully utlilized as I was restricted to a textfield line inputed by the user).  However, when the textfield was fetched, it tries to parse the String recieved as an Integer, Double, and Boolean before defaulting to it to saving it as a String (this was done using try, catch blocks and grabbing the exceptions that are thrown).

When adding a node, it starts a thread that shows the list being parsed through by adding a "*" to each element in the list as if the each node is actually being searched through which is what some Linked List algoritums utlize. In the backend, however, the list keeps track of the tail node allowing for constant time O(1) insertion rather than linear time O(n), and after the list is searched through visually, the new node is slapped onto the end and the tail pointer is updated.  

The deletion of a node is simliar to adding a node in that the list is visually searched through with a pointer before actually deleting the node.  When the pointer colides with the requested deleted node, the pointer updates and says "found" then deletes the node and the parsing through the list halts.

The user can also visualize the list being parsed through using pointers that add or delete but simply search through.  There is the option to use one or two pointers.  When 2 pointers is used, one pointer shifts at every cyle while one shifts every other cycle.  This is feature because it is a common solution to Linked List algoritums such as finding the middle node and checking if the list is a loop.

The project contains 2 inner classes: a static node class and a linked list class.  The node class is constructs a node with a previous and next node being inialized as 'null' and have to manually updated when created.  The linked list class keeps track of the head and tail nodes and its size that are updated accordingly when the addNode() method is called.
