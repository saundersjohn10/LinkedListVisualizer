# LinkedListVisualizer
A gui displaying the insertion, deletion, and parsing through a doubly linked list

Project utilizes:
- java generics
- threads
- swing GUI (Graphical User Interface)
- Doubly Linked class

Video showing gui:

https://drive.google.com/file/d/1uJnmgU50SdX9NwmnUVn4iLVPiNxZmWZQ/view?usp=sharing

Utilizing GUI, this project allows for the visualization and interaction with a Doubly Linked List.  The user can add, remove, and parse through data of the classes String, Integer, Double, and Boolean that all contained in the same linked list.  A linked list refers to a data structure where data is contained in nodes with each one having a pointer that pointing to the next node, and it being double because each node also points to its preceding node.  

<img width="750" alt="screen shot 2018-07-30 at 4 58 45 pm" src="https://user-images.githubusercontent.com/36249204/43423267-f07d82ae-9419-11e8-8eac-b2a397800411.png">

<img width="751" alt="screen shot 2018-07-30 at 5 00 50 pm" src="https://user-images.githubusercontent.com/36249204/43423349-33cf044c-941a-11e8-8611-0b1f5d9e6893.png">

Java generics was used in the node and linked list classes, allowing for there to be different types of data inserted into the same list.  The list utilized in the back end is initialized as the type 'object', so theoretically any type of data (for example a collection) could be inserted into a node (but this was not fully utilized as I was restricted to a textfield line inputted by the user).  However, when the textfield was fetched, it tries to parse the String received as an Integer, Double, and Boolean before defaulting to it to saving it as a String (this was done using try, catch blocks and grabbing the exceptions that are thrown).

When adding a node, it starts a thread that shows the list being parsed through by adding a "*" to each element in the list as if the each node is actually being searched through which is what some Linked List algorithms utilize. In the backend, however, the list keeps track of the tail node allowing for constant time O(1) insertion rather than linear time O(n), and after the list is searched through visually, the new node is slapped onto the end and the tail pointer is updated.  

The deletion of a node is similar to adding a node in that the list is visually searched through with a pointer before actually deleting the node.  When the pointer collides with the requested deleted node, the pointer updates to say "found" then deletes the node and parsing halts.

The user can also visualize the list being parsed through using pointers that don't add or delete nodes but simply search through the list.  There is the option to use one or two pointers.  When 2 pointers is used, one "fast" pointer shifts at every cycle while the other "slow" pointer shifts every other cycle.  This is feature because having two pointers is a common solution to Linked List algorithms such as finding the middle node and checking if the list is a loop.

The project contains 2 inner classes: a static node class and a linked list class.  The node class constructs a node with a previous and next node being initialized as 'null' and have to manually updated when created.  The linked list class keeps track of the head and tail nodes and its size that are updated accordingly when the addNode() method is called.

