package com.luo.demo.test;

public class BinaryFindTree<AnyType extends Comparable<? super AnyType>>{
	private Node<AnyType> root;
	
	public BinaryFindTree(){
		root=null;
	}
	public void makeEmpty(){
		root=null;
	}
	public boolean isEmpty(){
		return root==null;
	}
	public boolean contain(AnyType a){
		return contain(a,root);
	}
	public boolean contain(AnyType a, Node<AnyType> node) {
		if(node==null)return false;
		int compareResult = a.compareTo(node.element);
		if(compareResult==0)return true;
		if(compareResult<0){
			return contain(a,node.left);
		}
		return contain(a,node.right);
	}
	public AnyType findMin(){
		if(isEmpty())throw new RuntimeException();
		return findMin(root).element;
	}
	private Node<AnyType> findMin(Node<AnyType> node) {
		if(node==null)return null;
		if(node.left==null)return node;
		return findMin(node.left);//尾递归，可以替换为while
	}
	public AnyType findMax(){
		if(isEmpty())throw new RuntimeException();
		return findMax(root).element;
	}
	private Node<AnyType> findMax(Node<AnyType> node) {
		while(node!=null){
			if(node.right==null)return node;
			node = node.right;
		}
		return null;
	}
	
	public Node<AnyType> insert(AnyType x,Node<AnyType> t){
		if(t==null)return new Node<AnyType>(x);
		int compareResult = x.compareTo(t.element);
		if(compareResult<0){
			return insert(x, t.left);
		}else if(compareResult>0){
			return insert(x, t.right);
		}
		return t;
	}
	public Node<AnyType> remvoe(AnyType x,Node<AnyType> t){
		if(t==null)return t;
		int compareResult = x.compareTo(t.element);
		if(compareResult<0){
			t.left = remvoe(x, t.left);//把删除后的新节点指向t
		}else if(compareResult>0){
			t.right = remvoe(x, t.right);//把删除后的新节点指向t
		}else if(t.left!=null&&t.right!=null){//2个儿子
			t.element=findMin(t.right).element;//右子树的最小节点
			t.right= remvoe(t.element, t.right);
		}else{
			t = (t.left!=null) ? t.left : t.right;
		}
		return t;
	}
	
	
	private static class Node<AnyType>{
		AnyType element;
		Node<AnyType> left;
		Node<AnyType> right;
		public Node(AnyType element) {
			super();
			this.element = element;
		}
	}
}
