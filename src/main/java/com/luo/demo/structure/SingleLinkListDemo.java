package com.luo.demo.structure;

import java.util.Scanner;
import java.util.Stack;

public class SingleLinkListDemo {

	public static void main(String[] args) {
//		Node head = new Node(1);
//		Node curr = head;
//		for (int i = 1; i < 9; i++) {
//			Node node = new Node(i+1);
//			curr.setNext(node);
//			curr = node; 
//		}
//		reversePrintStack(head);
//		System.out.println("lenth:"+getLength(head));
//		System.out.println(hasCycle(head));
		
		Node head = null;
		Node pre = head;
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			Node node = new Node(scanner.nextInt());
			if(head==null){
				head = node;
			}else{
				pre.setNext(node);
			}
			pre = node;
		}
		scanner.close();
		Node newNode = insertSort(head);
		printNode(newNode);
	}

	private static Node insertSort(Node head) {
		return null;
	}

	/**
	 * 递归倒叙打印
	 * @param head
	 */
	private static void reversePrint(Node head){
		if(head==null){
			return;
		}
		reversePrint(head.getNext());
		System.out.println(head.getData());
	}
	
	/**
	 * 利用栈倒叙打印
	 * @param head
	 */
	private static void reversePrintStack(Node head){
		if(head==null){
			return;
		}
		Stack<Node> stack = new Stack<>();
		Node cur = head;
		while(cur!=null){
			stack.push(cur);
			cur = cur.getNext();
		}
		while(!stack.isEmpty()){
			System.out.println(stack.pop().getData());
		}
	}
	
	
	private static int getLength(Node head){
		int len = 0;
		Node cur = null;
		while(head!=null){
			len++;
			cur = head.getNext();
			head=cur;
		}
		return len;
	}
	
	/**
	 * 循环反转
	 * @param head
	 * @return
	 */
	private static Node reverse2(Node head) {
		if(null==head){
			return null;
		}
		Node pre = head;
		Node cur = head.getNext();
		Node next = null;
		while(null!=cur){
			next = cur.getNext();
			cur.setNext(pre);
			pre = cur;
			cur = next;
		}
		head.setNext(null);
		head = pre;
		return head;
	}

	/**
	 * 递归反转
	 * @param node
	 * @return
	 */
	private static Node reverse1(Node node) {
		if(node==null||node.getNext()==null){
			return node;
		}
		Node reverseNode = reverse1(node.getNext());//reverseNode不会变，一直是最后一个节点
		node.getNext().setNext(node);
		node.setNext(null);
		return reverseNode;
		
	}

	private static boolean hasCycle(Node head){
		boolean r = false;
		Node p1 = head;
		Node p2 = head;
		while(p1!=null&&p2!=null){
			p1=p1.getNext();
			p2=p2.getNext().getNext();
			if(p1==p2){
				r=true;
				break;
			}
		}
		return r;
	}
	
	private static void printNode(Node head) {
		if(head==null){
			return;
		}
		Node curr = head;
		System.out.print(head.getData());
		while((curr=curr.getNext())!=null){
			System.out.print(" "+curr.getData());
		}
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