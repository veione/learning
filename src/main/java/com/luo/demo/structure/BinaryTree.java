package com.luo.demo.structure;

import java.util.Stack;

public class BinaryTree {
	private TreeNode root = null;
	
	public BinaryTree(){
		root = new TreeNode(1,"rootNode(A)");
	}
	
	public void createBinaryTree(){
		TreeNode newNodeB = new TreeNode(2,"B");
        TreeNode newNodeC = new TreeNode(3,"C");
        TreeNode newNodeD = new TreeNode(4,"D");
        TreeNode newNodeE = new TreeNode(5,"E");
        TreeNode newNodeF = new TreeNode(6,"F");
        root.leftChild=newNodeB;
        root.rightChild=newNodeC;
        newNodeB.leftChild=newNodeD;
        newNodeB.rightChild=newNodeE;
        newNodeC.rightChild=newNodeF;
	}

	private int height() {
		return height(root);
	}

	private int height(TreeNode node) {
		if(node==null){
			return 0;
		}else{
			int i = height(node.leftChild);
			int j = height(node.rightChild);
			return (i<j)?(j+1):(i+1);
		}
	}

	private int size() {
		return size(root);
	}
	
	private int size(TreeNode node) {
		if(node==null){
			return 0;
		}else{
			int i = size(node.leftChild);
			int j = size(node.rightChild);
			return 1+i+j;
		}
	}

	private static void postOrder(TreeNode node) {
		if(node!=null){
			postOrder(node.leftChild);
			postOrder(node.rightChild);
			visited(node);
		}
	}

	private static void inOrder(TreeNode node) {
		if(node!=null){
			inOrder(node.leftChild);
			visited(node);
			inOrder(node.rightChild);
		}
	}
	
	private static void preOrder(TreeNode node) {
		if(node!=null){
			visited(node);
			preOrder(node.leftChild);
			preOrder(node.rightChild);
		}
	}
	private static void postOrder2(TreeNode p) {
		
	}

	/**
	 * 中根非递归
	 * @param p
	 */
	private static void inOrder2(TreeNode p) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = p;
		while(node!=null||!stack.isEmpty()){
			//存在左子树
			while(node!=null){
				stack.push(node);
				node = node.leftChild;
			}
			if(!stack.isEmpty()){
				node = stack.pop();
				visited(node);
				node = node.leftChild;
			}
		}
	}
	
	/**
	 * 先根非递归
	 * @param p
	 */
	private static void preOrder2(TreeNode p) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = p;
		while(node!=null||!stack.isEmpty()){
			while(node!=null){
				visited(node);
				stack.push(node);
				node = node.leftChild;
			}
			while(!stack.isEmpty()){
				node = stack.pop();
				node = node.rightChild;
				if(node!=null){
					visited(node);
					stack.push(node);
					node = node.leftChild;
				}
			}
		}
	}
	
	private static void visited(TreeNode node) {
		System.out.println("key:"+node.key+"--data:"+node.data);;  
	}

	private class TreeNode{
		private int key = 0;
		private String data;
		private TreeNode leftChild = null;
		private TreeNode rightChild = null;
		/**
		 * @param key 层
		 * @param data 值
		 */
		public TreeNode(int key,String data){
			this.key=key;
			this.data=data;
		}
	}
	
	/** 
     *           A 
     *     B          C 
     *  D     E            F 
     */  
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.createBinaryTree();
		System.out.println("tree size :" +tree.size());
		System.out.println("tree height :" +tree.height());
		
		System.out.println("*******(先跟遍历)[ABDECF]遍历*****************");
		preOrder(tree.root);
		System.out.println("*******(中跟遍历)[DBEACF]遍历*****************");
		inOrder(tree.root);
		System.out.println("*******(后跟遍历)[DEBFCA]遍历*****************");
		postOrder(tree.root);
		
		System.out.println("*******非递归(先跟遍历)[ABDECF]遍历*****************");
		preOrder2(tree.root);
		System.out.println("*******非递归(中跟遍历)[DBEACF]遍历*****************");
		inOrder2(tree.root);
		System.out.println("*******非递归(后跟遍历)[DEBFCA]遍历*****************");
		postOrder2(tree.root);
	}

}
