package com.luo.demo.classloader;

public class SimpleClassLoader extends ClassLoader{
	//那么SimpleClassLoader会在/usr/share中查找是否有MyClass.class，如果没有，它会退出一级目录，在/usr中查找，如此类推。
	private String path;
	//用于保存待搜索的目录
	private String[] dirs;
	
	
	public SimpleClassLoader(String path) {
		super();
		this.path = path;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.loadClass(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(name);
	}
	
	//然后我们要可以处理用户给进来的目录，将其扩展成所有需要搜索的目录
	public void extendClasspath(String path){
		
	}
}
