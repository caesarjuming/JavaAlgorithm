package Heap;

/**
 * Created by juming.wang on 2016/1/4.
 */
// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// boolean isFull( )      --> Return true if full; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws Overflow if capacity exceeded

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinaryHeap
{
    /**
     * Construct the binary heap.
     */
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = new Comparable[ capacity + 1 ];
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     * @exception Overflow if container is full.
     */
    public void insert( Comparable x ) throws Overflow
    {
        if( isFull( ) )
            throw new Overflow( );

        // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or null, if empty.
     */
    public Comparable findMin( )
    {
        if( isEmpty( ) )
            return null;
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or null, if empty.
     */
    public Comparable deleteMin( )
    {
        if( isEmpty( ) )
            return null;

        Comparable minItem = findMin();
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Test if the priority queue is logically full.
     * @return true if full, false otherwise.
     */
    public boolean isFull( )
    {
        return currentSize == array.length - 1;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 100;

    private int currentSize;      // Number of elements in heap
    private Comparable [ ] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole ){
            int child;
            Comparable tmp = array[ hole ];

            for( ; hole * 2 <= currentSize; hole = child ){
/* 4*/          child = hole * 2;
/* 5*/          if( child != currentSize &&array[ child + 1 ].compareTo( array[ child ] ) < 0 ){
                    child++;
                }
/* 8*/          if( array[ child ].compareTo( tmp ) < 0 ) {
                    array[ hole ] = array[ child ];
                } else{
                    break;
                }
            }
/*11*/      array[ hole ] = tmp;
    }

    // Test program
    public static void main( String [ ] args )
    {
        int numItems = 10000;
        BinaryHeap h = new BinaryHeap( numItems );
        int i = 37;

        try
        {
            for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                h.insert( new MyInteger( i ) );
            for( i = 1; i < numItems; i++ )
                if( ((MyInteger)( h.deleteMin( ) )).intValue( ) != i )
                    System.out.println( "Oops! " + i );

            for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                h.insert( new MyInteger( i ) );
            h.insert( new MyInteger( 0 ) );
            i = 9999999;
            h.insert( new MyInteger( i ) );
            for( i = 1; i <= numItems; i++ )
                if( ((MyInteger)( h.deleteMin( ) )).intValue( ) != i )
                    System.out.println( "Oops! " + i + " " );
        }
        catch( Overflow e )
        { System.out.println( "Overflow (expected)! " + i  ); }
    }
}

 class Overflow extends Exception
{
}
 final class MyInteger implements Comparable<Comparable>
{
    /**
     * Construct the MyInteger object with initial value 0.
     */
    public MyInteger( )
    {
        this( 0 );
    }

    /**
     * Construct the MyInteger object.
     * @param x the initial value.
     */
    public MyInteger( int x )
    {
        value = x;
    }

    /**
     * Gets the stored int value.
     * @return the stored value.
     */
    public int intValue( )
    {
        return value;
    }

    /**
     * Implements the toString method.
     * @return the String representation.
     */
    public String toString( )
    {
        return Integer.toString( value );
    }

    /**
     * Implements the compareTo method.
     * @param rhs the other MyInteger object.
     * @return 0 if two objects are equal;
     *     less than zero if this object is smaller;
     *     greater than zero if this object is larger.
     * @exception ClassCastException if rhs is not
     *     a MyInteger.
     */
    @Override
    public int compareTo(Comparable rhs )
    {
        return value < ((MyInteger)rhs).value ? -1 :
                value == ((MyInteger)rhs).value ? 0 : 1;
    }

    /**
     * Implements the equals method.
     * @param rhs the second MyInteger.
     * @return true if the objects are equal, false otherwise.
     * @exception ClassCastException if rhs is not
     *     a MyInteger.
     */
    public boolean equals( Object rhs )
    {
        return rhs != null && value == ((MyInteger)rhs).value;
    }

    /**
     * Implements the hash method.
     * @param tableSize the hash table size.
     * @return a number between 0 and tableSize-1.
     */
    public int hash( int tableSize )
    {
        if( value < 0 )
            return -value % tableSize;
        else
            return value % tableSize;
    }

    private int value;

}