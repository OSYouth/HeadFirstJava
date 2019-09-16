class GoodDogTestDrive{

	public static void main(String[] args){
		GoodDog one = new GoodDog();
		one.setSize(70);
		GoodDog two = new GoodDog();
		two.setSize(8);
		System.out.println("Dog one: " + one.getSize());
		System.out.println("Dog two: " + two.getSize());
		one.bark();
		two.bark();
		System.out.println();
		GoodDog[] dogs = new GoodDog[4];
		for(int i = 0; i < dogs.length; i++){
			dogs[i] = new GoodDog();
			dogs[i].setSize(i*15);
			System.out.println("Dog " + i + " " + dogs[i].getSize());
			dogs[i].bark();
		}
	}
}