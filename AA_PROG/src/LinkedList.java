//LinkedList Implementation in Java
public class LinkedList {
	
	static Node head;
	
	static class Node{
		Node next;
		int data;
		Node(int d){
			data=d;
			next=null;
		}
		
	}
	
	public static void push(int data)
	{
		Node New_node=new Node(data);
		New_node.next=head;
		head=New_node;
	}
	
	public static void pushinbetween(Node prev_node,int data) {
		
		Node new_node=new Node(data);
		new_node.next=prev_node.next;
		prev_node.next=new_node;
	}
	
    public static void pushatlast(int data) {
		
		Node new_node=new Node(data);
		if(head==null)
		{
			head=new Node(data);
			return;
			
		}
		
		new_node.next=null;
		Node last=head;
		while(last.next!=null)
			last=last.next;
		
		last.next=new_node;
		return;
	}
	
	
	
	static void printList() {
		
		Node n=head;
		while(n!=null)
		{
			System.out.println(n.data);
			n=n.next;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList llist=new LinkedList();
		llist.head=new Node(5);
		Node second=new Node(6);
		Node third=new Node(7);
		
		llist.head.next=second;
		second.next=third;
		
		push(8);
		pushinbetween(second,10);
		pushatlast(15);
		printList();
		
		
		

	}

}
