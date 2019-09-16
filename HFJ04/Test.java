class Test{
	int calcArea( int height, int width ){
		return height * width;
	}

	public static void main(String[] args){
		Test test = new Test();
		short c = 7;
		System.out.println(test.calcArea(c,15));
		long t = 42;
		System.out.println(test.calcArea((int)t,17));
	}
}