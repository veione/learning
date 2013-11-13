package com.luo.patern;

/**
 * 1.在编码时不能预见需要创建哪种类的实例。
 * 2.系统不应依赖于产品类实例如何被创建、组合和表达的细节
 * 简单工厂模式，包含3个：1工厂、2 抽象产品（抽象类或接口）、3具体产品
 * @author 罗辉1, @date:Nov 13, 2013
 *
 */
public class SimpleFactoryPatern {

	public static void main(String[] args) {
		Car car = Driver.driveCar("BMW");
		car.drive();
	}
}
class Driver{
	public static Car driveCar(String carName){
		if("BMW".equalsIgnoreCase(carName)){
			return new BMW();
		}else if("Benz".equalsIgnoreCase(carName)){
			return new Benz();
		}else{
			return null;
		}
	}
}
interface Car{
	void drive();
}
class BMW implements Car{
	@Override
	public void drive() {
		System.out.println("drive BMW car");
	}
}
class Benz implements Car{
	@Override
	public void drive() {
		System.out.println("drive Benz car");
	}
}