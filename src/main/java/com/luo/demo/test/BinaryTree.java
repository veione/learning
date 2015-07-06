package com.luo.demo.test;

public class BinaryTree {

	public static void main(String[] args) {
		Tree root = new Tree(1);
		Tree t1 = new Tree(2);
		root.setLeft(t1);
		
	}

	static class Tree{
		private int data;
		private Tree left;
		private Tree right;
		
		public Tree(int data) {
			super();
			this.data = data;
		}
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public Tree getLeft() {
			return left;
		}
		public void setLeft(Tree left) {
			this.left = left;
		}
		public Tree getRight() {
			return right;
		}
		public void setRight(Tree right) {
			this.right = right;
		}
	}
}
