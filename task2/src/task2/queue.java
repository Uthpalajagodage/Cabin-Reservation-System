package task2;
public class queue 
{
    private int rear;   //reat of the queue
    private int capacity;    //Size of queue array
    private int itemsnum; //no of items of the queue
    private int frontqueue;    //front of the queue
    private cabin[] myqueue;
    private cabin ship;
    
    //constuctor
    public queue(int capacity)
    {
        this.capacity = capacity; //set array size
        this.myqueue = new cabin[capacity];
        this.frontqueue = 0;
        this.rear =- 1;
        this.itemsnum = 0;   //no items
    }
    
 
    //adding items to the queue
    public void enqueue(cabin cabin)
    {
        //check whether queue is full
        if(this.rear == this.capacity)
        {
            System.out.println("Queue is full.");
        }
        else
        {
            if(this.rear == this.capacity-1)
            {
                this.rear = -1;
            }
           cabin.setpassager();
            this.myqueue[++this.rear] = cabin;
            this.itemsnum++;
        }
        
    }
    
  
    //remove items to the queue
    public cabin dequeue()
    {
        //checking queue is empty
        if (this.itemsnum == 0)
        {
            System.out.println("The queue is empty");
            return null;
        }
        else
        {
            cabin temp = this.myqueue[this.frontqueue++];
            if(this.frontqueue == this.capacity)
            {
                this.frontqueue = 0;
            }
            this.itemsnum--;
            return temp;
        }
    } 
    
}