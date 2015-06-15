package com.luo.demo.structure;

import java.util.Scanner;

public class LinkedListSummary {

	private static class Node{
		private int data;
		private Node next;
		public Node(int data) {
			super();
			this.data = data;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Node head = null;
		if(scanner.hasNext()){
			head = new Node(scanner.nextInt());
		}
		Node temp = head;
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			if(n==99){
				break;		
			}
			temp.next=new Node(n);
			temp=temp.next;
		}
		System.out.println("length:"+getLength(head));
//		reverseList(head);
		head = reverseListRec(head);
		displayNodes(head);
	}

	private static Node reverseList(Node head) {
		if(head==null||head.next==null)return head;  
        Node pre=null;
        Node nex=null;  
        while(head!=null){  
            nex=head.next;  
            head.next=pre;  
            pre=head;  
            head=nex;  
        }  
        return pre; 
	}
	
	/**
	 * a b c d e f g
	 * 
	 * @param head
	 * @return
	 */
	private static Node reverseListRec(Node head) {
		if(head==null||head.next==null)return head;  
        Node reHead=reverseListRec(head.next);
        head.next.next=head;  
        head.next=null;  
        return reHead;
	}

	private static int getLength(Node head) {
		int i = 0;
		Node current = head;
		while(current!=null){
			i++;
			current = current.next;
		}
		return i;
	}

	private static void displayNodes(Node head) {
		Node current = head;
		while (current!=null) {
			System.out.println(current.data+">");
			current = current.next;
		}
	}

}
