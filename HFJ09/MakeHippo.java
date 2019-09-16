abstract class Animal{
	private String name;

	public String getName(){
		return name;
	}

	public Animal(String theName){
		name = theName;
	}

	public Animal(){

	}
}

class Hippo extends Animal{

	// public Hippo(String name){
	// 	super(name);
	// }
}

public class MakeHippo{
	public static void main(String[] args){
		// Hippo h = new Hippo("Buffy");
		Hippo h = new Hippo();
		System.out.println(h.getName());
	}
}