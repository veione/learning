package com.luo.patern;

/**
 * @author 罗辉, @date:Nov 5, 2013
 *
 */
public class ProxyPatern {

	public static void main(String[] args) {
		Wangpo wangpo = new Wangpo();
		wangpo.love();
	}
}

interface Women{
	public void love();
}

class Panjinlian implements Women{
	@Override
	public void love() {
		System.out.println("i love u....");
	}
}
class Wangpo implements Women{
	private Women women;
	@Override
	public void love() {
		this.women.love();
	}
	public Wangpo() {
		super();
		this.women=new Panjinlian();//默认传值
	}
	public Wangpo(Women women) {
		super();
		this.women = women;
	}
}