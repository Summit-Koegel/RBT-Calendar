import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {
	//comment
    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public int blackHeight;//red0 black 1 dblblack 2
        public Node(T data) { 
        	this.data = data;
        	blackHeight = 0;
        }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && this.equals(parent.leftChild);
        }
        public boolean isRightChild() {
        	
            return parent != null && this.equals(parent.rightChild);
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    @Override
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) { root = newNode; size++; return true; } // add first node to an empty tree
        else{
            boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
            if (returnValue) size++;
            else throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            root.blackHeight = 1;
            return returnValue;
        }
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    protected boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) return false;

            // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                newNode.blackHeight =0;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                newNode.blackHeight =0;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }
    
    /**
     * removes a given node (not value)
     * this is done because of how I use this method in 
     * RBC, where I search for the node to remove first
     * NODE MUST BE ON TREE or else stuff may break
     * @param toRemove a specific node
     */
    protected  void removeNode(Node<T>toRemove) {
    	//step 1 remove as if binary search tree
    	
    	size--;
    	if(toRemove.rightChild==null&&toRemove.leftChild==null) {
    		try {
    			Node<T> loopnd = toRemove;
    			
    			
    		}catch(Exception e){
    			
    		}
    		//two null leafs below
    		if(toRemove.parent == null) {
    			//Tree is gone
    			root = null;
    		}else if(toRemove.isLeftChild()){
    			
    			toRemove.parent.leftChild = null;
    			if(toRemove.blackHeight != 0) {
    				balanceDblB(toRemove.parent, true);
    			}//no prob for red
    			return;
    		}else {
    			//System.out.println(toRemove.parent.rightChild.data+"148");
    			toRemove.parent.rightChild = null;
    			if(toRemove.blackHeight != 0) {
    				balanceDblB(toRemove.parent, false);
    			}//no prob for removing red
    			return;
    		}
    	}
    	else if(toRemove.rightChild!=null&&toRemove.leftChild==null) {
    		//replace with right child
    		//only right child
    		if(toRemove.parent == null) {
    			root = toRemove.rightChild;
    		}else if(toRemove.isLeftChild()){
    			toRemove.parent.leftChild = toRemove.rightChild;
    			if(toRemove.rightChild != null)
    				toRemove.rightChild.parent = toRemove.parent;
    			if(rbh(toRemove) !=0) {
    				//black
    				balanceDblB(toRemove.parent,true);
    				//balance double black put on right child
    				//if replacement is red, this will recolor replacement
    				//if black will balance
    			}
    			//if it is red, the replacement will be black causing no prob
    			return;
    		}else {//right child
    			toRemove.parent.rightChild = toRemove.rightChild;
    			if(toRemove.rightChild != null)
    				toRemove.rightChild.parent = toRemove.parent;
    			if(rbh(toRemove) !=0) {
    				//black
    				balanceDblB(toRemove.parent,false);
    				//balance double black put on right child
    				//if replacement is red, this will recolor replacement
    				//if black will balance
    			}
    		}
    	}
    	else if(toRemove.rightChild==null&&toRemove.leftChild!=null) {
    		//replace with left child
    		//only left child
    		if(toRemove.parent == null) {
    			root = toRemove.leftChild;
    		}else if(toRemove.isLeftChild()){
    			toRemove.parent.leftChild = toRemove.leftChild;
    			toRemove.leftChild.parent = toRemove.parent;
    			if(rbh(toRemove) !=0) {
    				//black
    				balanceDblB(toRemove.parent,true);
    				//balance double black put on right child
    				//if replacement is red, this will recolor replacement
    				//if black will balance
    			}
    		}else {
    			toRemove.parent.rightChild = toRemove.leftChild;
    			toRemove.leftChild.parent = toRemove.parent;
    			if(rbh(toRemove) !=0) {
    				//black
    				balanceDblB(toRemove.parent,true);
    				//balance double black put on right child
    				//if replacement is red, this will recolor replacement
    				//if black will balance
    			}
    		}
    	}else {
    		//the fun case: two kids 
    		//replace with next node (left most of right subtree
    		Node<T> rep = toRemove.rightChild;
    		while(rep.leftChild != null) {
    			rep = rep.leftChild;
    		}
    		if(rep.equals(toRemove.rightChild)) {//replace with immediate right child
    			toRemove.data = rep.data;
    			toRemove.rightChild = rep.rightChild;
    			if(rep.rightChild != null) {
    				rep.rightChild.parent = toRemove;
    			}
    			//TODO: add balancing
    		}else {
    			toRemove.data = rep.data;
    			
    			rep.parent.leftChild = rep.rightChild;
    			if(rep.rightChild != null) {
    				rep.rightChild.parent = rep.parent;
    			}
    			
    			
    		}
    		
    		
    		
    		
    		
    		/*
    		Node<T> repl = toRemove.rightChild;
    		while(repl.leftChild!= null) {
    			repl = repl.leftChild;
    			
    		}
    		
    		if(repl.parent ==toRemove) {//the first right node was used to replace
    			//repl has no left children
    			toRemove.data = repl.data;
    			
    				
    			
	    		toRemove.rightChild = repl.rightChild;
	    		if(toRemove.rightChild != null)
	    			toRemove.rightChild.parent = toRemove;
    			
    			balanceDblB(toRemove,false);
    		}else {//a left node was used to replace
    			toRemove.data = repl.data; // replace subtree root
    			repl.parent.leftChild=repl.rightChild;
    			if(repl.rightChild != null)
    				repl.rightChild.parent = repl.parent;
    			//now repl.parent's left child is double black
    			balanceDblB(repl.parent,true);
    			}
    			*/

    		
    		
    		
    		
    	}
    	
    }
    
    /**
     * helper method, makes making null nodes black easier
     * @param node
     * @return
     */
    private int rbh(Node<T> node) {
    	if(node == null) {
    		return 1;
    	}
    	return node.blackHeight;
    }
    /**
     * balances when removing creates a DblB
     * 
     * @param parent
     * @param leftChild whether the node's left cild is the double
     */
    protected void balanceDblB(Node<T> parent, boolean leftChild) {
    	if(true) {
    		return;
    	}
    	if(parent == null) {
    		//root is double black
    		root.blackHeight =1;
    		return;
    	}
    	
    	if(leftChild) {
    		if(rbh(parent.leftChild)==0) {
    			parent.leftChild.blackHeight =1;
    			return;//double black +red =black
    		}
    	}
    	
    }
    
    
    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    	
    	if(child == null||parent==null) {
    		throw new IllegalArgumentException("null element");
    	}
    	if(child.parent== null) {
    		throw new IllegalArgumentException("child is orphan");
    	}
    	if(child.isLeftChild()) {
    		child.parent = parent.parent;
    		try {
	    		if(parent.equals(child.parent.leftChild)) {
	    			child.parent.leftChild = child;
	    		}else {
	    			child.parent.rightChild = child;
	    		}
    		}catch(NullPointerException e) {
    			//grandparent is null
    		}
    		parent.parent = child;
    		parent.leftChild = child.rightChild;
    		child.rightChild = parent;
    		if(child.parent ==null) {
    			root = child;
    		}
    		
    		
    	}else {
    		child.parent = parent.parent;
    		
    		try {
	    		if(parent.equals(child.parent.leftChild)) {
	    			child.parent.leftChild = child;
	    		}else {
	    			child.parent.rightChild = child;
	    		}
    		}catch(NullPointerException e) {
    			//grandparent is null
    		}
    		parent.parent = child;
    		parent.rightChild = child.leftChild;
    		child.leftChild = parent;
    		if(child.parent == null) {
    			root = child;
    		}
    		
    	} 
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }

    /**
     * Returns an iterator over the values in in-order (sorted) order.
     * @return iterator object that traverses the tree in in-order sequence
     */
    @Override
    public Iterator<T> iterator() {
        // use an anonymous class here that implements the Iterator interface
        // we create a new on-off object of this class everytime the iterator
        // method is called
        return new Iterator<T>() {
            // a stack and current reference store the progress of the traversal
            // so that we can return one value at a time with the Iterator
            Stack<Node<T>> stack = null;
            Node<T> current = root;

            /**
             * The next method is called for each value in the traversal sequence.
             * It returns one value at a time.
             * @return next value in the sequence of the traversal
             * @throws NoSuchElementException if there is no more elements in the sequence
             */
            public T next() {
                // if stack == null, we need to initialize the stack and current element
                if (stack == null) {
                    stack = new Stack<Node<T>>();
                    current = root;
                }
                // go left as far as possible in the sub tree we are in un8til we hit a null
                // leaf (current is null), pushing all the nodes we fund on our way onto the
                // stack to process later
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
                // as long as the stack is not empty, we haven't finished the traversal yet;
                // take the next element from the stack and return it, then start to step down
                // its right subtree (set its right sub tree to current)
                if (!stack.isEmpty()) {
                    Node<T> processedNode = stack.pop();
                    current = processedNode.rightChild;
                    return processedNode.data;
                } else {
                    // if the stack is empty, we are done with our traversal
                    throw new NoSuchElementException("There are no more elements in the tree");
                }

            }

            /**
             * Returns a boolean that indicates if the iterator has more elements (true),
             * or if the traversal has finished (false)
             * @return boolean indicating whether there are more elements / steps for the traversal
             */
            public boolean hasNext() {
                // return true if we either still have a current reference, or the stack
                // is not empty yet
                return !(current == null && (stack == null || stack.isEmpty()) );
            }

        };
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // use the inorder Iterator that we get by calling the iterator method above
        // to generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        Iterator<T> treeNodeIterator = this.iterator();
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (treeNodeIterator.hasNext())
            sb.append(treeNodeIterator.next());
        while (treeNodeIterator.hasNext()) {
            T data = treeNodeIterator.next();
            sb.append(", ");
            sb.append(data.toString());
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        LinkedList<Node<T>> q = new LinkedList<>();
        q.add(this.root);
        while(!q.isEmpty()) {
            Node<T> next = q.removeFirst();
            if(next.leftChild != null) q.add(next.leftChild);
            if(next.rightChild != null) q.add(next.rightChild);
            output += next.data.toString();
            if(!q.isEmpty()) output += ", ";
        }
        return output + " ]";
    }

    @Override
    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "/nin order: " + this.toInOrderString();
    }
    protected void enforceIns(Node<T> nd) {
    	if(nd.parent == null) {
    		nd.blackHeight =1;
    		return;
    	}
    	if(nd.parent.parent == null) {
    		//parent is root, always fine
    		nd.parent.blackHeight =1;
    		return;
    	}
    	if(nd.parent.blackHeight !=0) {//black parent
    		return;//adding a red node w/ black dad is fine
    	}
    	//now parent is red
    	if(nd.isLeftChild()) {
    		
    		if(nd.parent.isRightChild()) {//rotate and try again
    			rotate(nd,nd.parent);
    			enforceIns(nd.rightChild);
    			return;
    		}
    		if(rbh(nd.parent.rightChild)!=0) {//black aunt
    			rotate(nd.parent,nd.parent.parent);
    			//rotate + color swap
    			nd.parent.blackHeight = 1;
    			nd.parent.rightChild.blackHeight = 0;//old grandp
    			return;
    		}
    		//red aunt
    		nd.parent.blackHeight =1;
    		
    		//make aunt black
    		nd.parent.parent.rightChild.blackHeight =1;
    		nd.parent.parent.blackHeight = 0;
    		//gpa red
    		enforceIns(nd.parent.parent);
    		
    		
    		
    		
    	}else {//RIGHT CHILD! don't pull a fast one
    		if(nd.parent.isLeftChild()) {//rotate and try again
    			rotate(nd,nd.parent);
    			enforceIns(nd.leftChild);
    			return;
    		}
    		if(rbh(nd.parent.leftChild)!=0) {//black aunt
    			rotate(nd.parent,nd.parent.parent);
    			//rotate + color swap
    			nd.parent.blackHeight = 1;
    			nd.parent.leftChild.blackHeight = 0;//old grandp
    			return;
    		}
    		//red aunt
    		nd.parent.blackHeight =1;
    		
    		//make aunt black
    		nd.parent.parent.leftChild.blackHeight =1;
    		nd.parent.parent.blackHeight = 0;
    		//gpa red
    		enforceIns(nd.parent.parent);
    	}
    }
    /**
     * checks for problems w/ RB tree prop. + fixes
     * @param nd node added/problem node MUST BE RED
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> nd) {
    	enforceIns(nd);
    	
    }


    
    
    
//
//    /**
//	 * If adding with a black aunt (null in this example) works well
//	 */
//	@Test
//	public void testBlackAunt() {
//		try {
//			RedBlackTree<Integer> test = new RedBlackTree<Integer>();
//			test.insert(100);
//			test.insert(50);
//			
//			//left child 50 right null
//			test.insert(25);
//			//left insert with red uncle
//			System.out.println();
//			assertEquals(test.toLevelOrderString(),"[ 50, 25, 100 ]");
//		}catch(Exception e){
//			fail("unexpected exception");
//		}
//	}
//	
//	/**
//	 * tests when adding a red element with a red parent and red aunt
//	 * tests color swaps, and that they are in right position
//	 */
//	@Test
//	public void testRedAuntColors(){
//		try {
//			RedBlackTree<Integer> test = new RedBlackTree<Integer>();
//			test.insert(100);
//			test.insert(50);
//			
//			//left child 50 right 75 red
//			test.insert(150);
//			if(test.root.blackHeight==0)fail("red root");
//			if(test.root.rightChild.blackHeight!=0)fail("wrong color on insert");
//			test.insert(75);
//			if(test.root.blackHeight==0)fail("red root");
//			if(test.root.leftChild.blackHeight==0)fail("bad recolor");
//			if(test.root.leftChild.rightChild.blackHeight!=0)fail("bad recolor");
//			//left insert with red uncle
//			//check colors of all nodes
//			assertEquals(test.toLevelOrderString(),"[ 100, 50, 150, 75 ]");
//			
//		}catch(Exception e){
//			fail("unexpected exception");
//		}
//	}
//	
//	/**
//	 * Tests red black tree toLevelOrder with many elements
//	 * I calculated these by hand
//	 */
//	@Test
//	public void testBigKahuna() {
//		//adding a bunch of elements
//		RedBlackTree<Integer> test = new RedBlackTree<Integer>();
//		int[] elements = new int[] {5,26,84,62,20,79,100,16,86,27,41,75,83};
//		for(int i =0; i< elements.length; i++) {
//			test.insert(elements[i]);
//		}
//		assertEquals(test.toLevelOrderString(),"[ 41, 26, 79, 16, 27, 62, 86, 5, 20, 75, 84, 100, 83 ]");
//		
//	}
 
    
//    

}
