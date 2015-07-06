package com.luo.demo.test;

public class NodeTest {

	public static void main(String[] args) {
		Object[] ss =  new Object[10];
		System.out.println(ss.length);
		System.out.println(ss[0]);
		System.out.println(10>>1);
		
		
//		Node n1 = new Node(1);
//		Node pre = n1;
//		for (int i = 0; i < 8; i++) {
//			Node n2 = new Node(2);
//			pre.setNext(n2);
//			pre = n2;
//		}
	}
	
	static class Node{
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
}
