package com.luo.demo.structure;

public class DoubleLinkedList {
	
	static class Node{
		private String data;
		private Node prev = this;
		private Node next = this;
		
		public Node(String v){
			this.data = v;
		}
	}
	private Node head = new Node(null);//头节点
	private int size;
	public boolean addFirst(String v){
		addAfter(new Node(v),head);
		return true;
	}
	public boolean addLast(String v){
		addBefore(new Node(v),head);
		return true;
	}
	public boolean add(String v){
		addLast(v);
		return true;
	}
	public boolean add(int index,String v){
		addBefore(new Node(v), getNode(index));
		return true;
	}
	public boolean remove(int index){
		removeNode(getNode(index));
		return true;
	}
	
	
	private void removeNode(Node node) {
		
	}
	private Node getNode(int index) {
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException();		
		}
		Node node = head.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	private void addBefore(Node newNode, Node cNode) {
		newNode.next = cNode;
		newNode.prev = cNode.prev;
		newNode.prev.next = newNode;
		cNode.prev = newNode;
		size++;
	}
	
	private void addAfter(Node newNode, Node cNode) {
		newNode.prev = cNode;
		newNode.next = cNode.next;
	}


	public static void main(String[] args) {

	}

}
