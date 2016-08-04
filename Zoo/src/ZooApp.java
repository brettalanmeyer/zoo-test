import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZooApp {
	
	public static Zoo zoo;
	public static Animal animal;
	static JLabel label = new JLabel("My Zoo");
	
	public static void main(String[] args) {
		window();
		scan();
	}
	
	public static void print(String text){
		System.out.println(text);
		label.setText(text);
	}
	
	public static JButton newButton(String text){
		JButton button = new JButton();
		button.setText(text);
		button.setPreferredSize(new Dimension(280, 24));
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createZoo();
			}
		});
		
		return button;
	}
	
	public static <T> JButton newButton(String text, Runnable function){
		JButton button = new JButton();
		button.setText(text);
		button.setPreferredSize(new Dimension(200, 24));
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				function.run();
			}
		});
		return button;
	}
	
	public static void window(){
		
		JFrame frame = new JFrame("Brett's Zoo Program");

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		label.setPreferredSize(new Dimension(200, 24));
		panel.add(label);
		
		panel.add(newButton("Create Zoo", () -> createZoo()));
		panel.add(newButton("Add Lion", () -> add(Lion.class)));
		panel.add(newButton("Add Tiger", () -> add(Tiger.class)));
		panel.add(newButton("Add Bear", () -> add(Bear.class)));
		panel.add(newButton("Select Lion", () -> select(Lion.class)));
		panel.add(newButton("Select Tiger", () -> select(Tiger.class)));
		panel.add(newButton("Select Bear", () -> select(Bear.class)));
		panel.add(newButton("Unselect Animal", () -> unselect()));
		panel.add(newButton("Kill Lion", () -> kill(Lion.class)));
		panel.add(newButton("Kill Tiger", () -> kill(Tiger.class)));
		panel.add(newButton("Kill Bear", () -> kill(Bear.class)));
		panel.add(newButton("Eat", () -> eat()));
		panel.add(newButton("Play", () -> play()));
		panel.add(newButton("Dance", () -> dance()));
		panel.add(newButton("Soundoff", () -> soundOff()));
		panel.add(newButton("How many lions?", () -> howMany(Lion.class)));
		panel.add(newButton("How many tigers?", () -> howMany(Tiger.class)));
		panel.add(newButton("How many bears?", () -> howMany(Bear.class)));
		panel.add(newButton("How many animals?", () -> howMany()));
		
		frame.add(panel);
		frame.setSize(320, 640);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
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
			howMany();
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
		print("Zoo Created...");
	}
	
	private static void add(Class<?> clazz){
		try {
			Animal animal = (Animal) clazz.newInstance();
			zoo.addAnimal(animal);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		print(clazz.getName() + " Added...");
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
			print("A " + clazz.getName() + " has been selected...");
		} else {
			print("No " + clazz.getName() + " could be found...");
		}
	}
	
	private static void kill(Class<?> clazz){
		if(zoo.kill(clazz)){
			print("A " + clazz.getName() + " has been killed...");
		} else {
			print("A " + clazz.getName() + " could not be found...");
		}
	}
	
	private static void unselect(){
		animal = null;
		print("Animal has been unselected...");
	}
	
	private static void howMany(Class<?> clazz){
		boolean found = false;
		for(Animal animal : zoo.getAnimals()){
			if(clazz.isInstance(animal)){
				print(new Integer(animal.getSpecies()).toString());
				found = true;
				break;
			}
		}
		if(!found){
			print("0");
		}
	}
	
	private static void howMany(){
		print(new Integer(zoo.getAnimals().size()).toString());
	}
	
	private static void eat(){
		if(hasAnimal()){
			print(animal.eat(Animal.Food.Fish));
		}
	}

	private static void play(){
		if(hasAnimal()){
			print(animal.play());
		}
	}
	
	private static void dance(){
		if(hasAnimal()){
			print(animal.dance());
		}
	}

	private static void soundOff(){
		if(hasAnimal()){
			print(animal.soundOff());
		}
	}
	
	private static boolean hasAnimal(){
		boolean result = (animal != null);
		if(!result){
			print("An animal must be selected first...");
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
