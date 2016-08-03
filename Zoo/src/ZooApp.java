import java.util.List;
import java.util.Scanner;

public class ZooApp {
	
	public static Zoo zoo;
	public static Animal animal;
	
	public static void main(String[] args) {
		scan();
	}
	
	public static void scan(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine().toLowerCase();
		
		if(!command.equals("help") && !command.equals("create zoo") && zoo == null){
			System.out.println("A zoo must be created first...");
			scan();
			return;
		}
		
		switch(command){
		
		case "create zoo":
			createZoo();
			break;
		case "add lion":
			add(Lion.class);
			break;
		case "add tiger":
			add(Tiger.class);
			break;
		case "add bear":
			add(Bear.class);
			break;
		case "select lion":
			select(Lion.class);
			break;
		case "select tiger":
			select(Tiger.class);
			break;
		case "select bear":
			select(Bear.class);
			break;
		case "unselect animal":
			unselect();
			break;
		case "kill lion":
			kill(Lion.class);
			break;
		case "kill tiger":
			kill(Tiger.class);
			break;
		case "kill bear":
			kill(Bear.class);
			break;
		case "eat":
			eat();
			break;
		case "play":
			play();
			break;
		case "dance":
			dance();
			break;
		case "soundoff":
			soundOff();
			break;
		case "how many lions?":
			howMany(Lion.class);
			break;
		case "how many tigers?":
			howMany(Tiger.class);
			break;
		case "how many bears?":
			howMany(Bear.class);
			break;
		case "how many animals?":
			howManyAnimals();
			break;
		case "help":
			help();
			break;
		default:
			System.out.println("Command Not Found...");
			break;
				
		}
		
		scan();
		
	}
	
	private static void createZoo(){
		zoo = new Zoo();
		System.out.println("Zoo Created...");
	}
	
	private static void add(Class<?> clazz){
		try {
			Animal animal = (Animal) clazz.newInstance();
			zoo.addAnimal(animal);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(clazz.getName() + " Added...");
	}
	
	private static void select(Class<?> clazz){
		boolean found = false;
		List<Animal> animals = zoo.getAnimals();
		for(Animal animal : animals){
			if(clazz.isInstance(animal)){
				ZooApp.animal = animal;
				found = true;
				break;
			}
		}
		if(found){
			System.out.println("A " + clazz.getName() + " has been selected...");
		} else {
			System.out.println("No " + clazz.getName() + " could be found...");
		}
	}
	
	private static void kill(Class<?> clazz){
		if(zoo.kill(clazz)){
			System.out.println("A " + clazz.getName() + " has been killed...");
		} else {
			System.out.println("A " + clazz.getName() + " could not be found...");
		}
	}
	
	private static void unselect(){
		animal = null;
		System.out.println("Animal has been unselected...");
	}
	
	private static void howMany(Class<?> clazz){
		boolean found = false;
		for(Animal animal : zoo.getAnimals()){
			if(clazz.isInstance(animal)){
				System.out.println(animal.getSpecies());
				found = true;
				break;
			}
		}
		if(!found){
			System.out.println(0);
		}
	}
	
	private static void howManyAnimals(){
		System.out.println(zoo.getAnimals().size());
	}
	
	private static void eat(){
		if(hasAnimal()){
			animal.eat(Animal.Food.Fish);
		}
	}

	private static void play(){
		if(hasAnimal()){
			animal.play();
		}
	}
	
	private static void dance(){
		if(hasAnimal()){
			animal.dance();
		}
	}

	private static void soundOff(){
		if(hasAnimal()){
			animal.soundOff();
		}
	}
	
	private static boolean hasAnimal(){
		boolean result = (animal != null);
		if(!result){
			System.out.println("An animal must be selected first...");
		}
		return result;
	}
	
	private static void help(){
		System.out.println("Commands:");
		System.out.println(" - create zoo");
		System.out.println(" - add lion");
		System.out.println(" - add tiger");
		System.out.println(" - add bear");
		System.out.println(" - select lion");
		System.out.println(" - select tiger");
		System.out.println(" - select bear");
		System.out.println(" - unselect animal");
		System.out.println(" - kill lion");
		System.out.println(" - kill tiger");
		System.out.println(" - kill bear");
		System.out.println(" - eat");
		System.out.println(" - play");
		System.out.println(" - dance");
		System.out.println(" - soundoff");
		System.out.println(" - how many lions?");
		System.out.println(" - how many tigers?");
		System.out.println(" - how many bears?");
		System.out.println(" - how many animals?");
		System.out.println(" - help");
	}
}
