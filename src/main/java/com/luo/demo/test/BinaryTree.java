package com.luo.demo.test;

/**
 * 二叉有序树
 * @author hui.luo
 */
public class BinaryTree {
	private TreeNode root;
	private int size;
	
	public int getMax(){
		TreeNode curNode = root;
		while (curNode.rightChild!=null) {
			curNode = curNode.rightChild;
		}
		return curNode.data;
	}

	public void prePrint(){
		prePrint0(root);
	}

	private void prePrint0(TreeNode node) {
		if(node!=null){
			System.out.print(node.data);
			prePrint0(node.leftChild);
			prePrint0(node.rightChild);
		}
	}

	private void midPrint() {
		midPrint0(root);
	}

	private void midPrint0(TreeNode node) {
		if(node!=null){
			prePrint0(node.leftChild);
			System.out.print(node.data);
			prePrint0(node.rightChild);
		}
	}

	private void createBinaryTreeByArr(int[] data) {
		for (int d : data) {
			insert(d);
		}
	}
	
	private void insert(int value) {
		if(root==null)root = new TreeNode(value);
		TreeNode curNode = root;
		TreeNode parentNode;
		while (true) {
			parentNode = curNode;
			if(value<curNode.data){
				curNode = curNode.leftChild;
				if(curNode==null){
					parentNode.leftChild=new TreeNode(value);
					break;
				}
			}else{
				curNode = curNode.rightChild;
				if(curNode==null){
					parentNode.rightChild = new TreeNode(value);
					break;
				}
			}
		}
		++size;
	}

	private class TreeNode{
		private int data;
		private TreeNode leftChild = null;
		private TreeNode rightChild = null;
		/**
		 * @param data 值
		 */
		public TreeNode(int data){
			this.data=data;
		}
	}
	
	public static void main(String[] args) {
		int[] data = {0,1,2,3,4,5,6,7,8,9};
		BinaryTree tree = new BinaryTree();
		tree.createBinaryTreeByArr(data);
		tree.prePrint();
//		tree.midPrint();
	}
}
