import java.util.*;

public class TestGenerics3{
	public static void main(String[] args){
		new TestGenerics3().go();
	}

	public void go(){
		// Animal[] animals = {new Dog(), new Cat(), new Dog()};
		ArrayList<Animal> animals = new ArrayList<>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Dog());

		// Dog[] dogs = {new Dog(), new Dog(), new Dog()};
		ArrayList<Dog> dogs = new ArrayList<>();
		dogs.add(new Dog());
		dogs.add(new Dog());

		takeAnimals(animals);
		takeAnimals(dogs);

	}

	public void takeAnimals(ArrayList<? extends Animal> animals){
		for(Animal a: animals){
			a.eat();
		}
	}
}

