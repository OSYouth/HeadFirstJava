import java.util.*;

public class TestGenerics2{
	public static void main(String[] args){
		new TestGenerics2().go();
	}

	public void go(){
		// Animal[] animals = {new Dog(), new Cat(), new Dog()};
		ArrayList<Animal> animals = new ArrayList<>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Dog());
		// Dog[] dogs = {new Dog(), new Dog(), new Dog()};
		takeAnimals(animals);
		// takeAnimals(dogs);
	}

	public void takeAnimals(ArrayList<Animal> animals){
		for(Animal a: animals){
			a.eat();
		}
	}
}

