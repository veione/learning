package com.luo.demo.structure;

public class LinkQueue {
	private Node head;
	private Node tail;
	private int size;
	
	public LinkQueue() {
		head = tail = null;
		size = 0;
	}
	
	public void enQueue(int data){
		Node node = new Node(data);
		if(head==null){
			head = node;
			tail = node;
		}else{
			tail.setNext(node);
			tail = node;
		}
		size++;
	}

	public int deQueue(){
		if(size==0)throw new RuntimeException("queue empty");
		int data = head.data;
		head = head.next;
		size--;
		if(size==0){
			tail=null;
			head=null;
		}
		return data;
	}
	
	public void print(){
		Node node = head;
		while(node!=null){
			System.out.print(node.data+">");
			node  = node.next;
		}
	}
	
	class Node{
		private int data;
		private Node next;
		
		public Node(int data) {
			super();
			this.data = data;
		}
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
	public static void main(String[] args) {
		LinkQueue queue = new LinkQueue();
		for (int i = 0; i < 5; i++) {
			queue.enQueue(i);
		}
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		System.out.println(queue.head.data);
		System.out.println(queue.tail.data);
		queue.print();
	}
}
