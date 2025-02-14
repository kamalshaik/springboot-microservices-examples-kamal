package com.skh;

public class TestMain {

	public static void main(String[] args) {
		 Employee e = new Employee();
		 e = null;
		 
//		 System.gc();
		 Runtime.getRuntime().gc();
		 System.out.println("Main method is done!");

	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Finalize() method called.");;
	}

}
