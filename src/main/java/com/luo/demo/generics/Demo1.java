package com.luo.demo.generics;
/**
 * 泛型替换Object
 * @author hui.luo
 *
 */
public class Demo1 {
	
	private static class Demo{
		private Object x;
		private Object y;
		public Object getX() {
			return x;
		}
		public void setX(Object x) {
			this.x = x;
		}
		public Object getY() {
			return y;
		}
		public void setY(Object y) {
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.setX(1);
		demo.setX("abc");
		
		int a = (int) demo.getX();//this be error
	}
}
