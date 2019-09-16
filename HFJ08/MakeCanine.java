//can not work

abstract public class Cannie extends Animal{
	public void roam();
}

public class MakeCanine{
	public void go(){
		Cannie c;
		c = new Dog();
		c = new Canine();
		c.roam();
	}
}