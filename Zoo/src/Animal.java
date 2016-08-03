import java.util.ArrayList;
import java.util.Comparator;

public abstract class Animal implements Comparator<Animal> {

	static ArrayList<Animal> species = new ArrayList<>();

	protected int		energyLevel			= 0;
	protected int		energyLevelSleep	= 8;
	protected int		energyLevelEat		= 3;
	protected int		energyLevelPlay		= -5;

	protected boolean	isTrainable			= false;
	
	public enum Food {
		Steak, Fish, Grain
	};
	
	public int getSpecies(){
		return Animal.species.size();
	}

	public int getEnergyLevel() {
		return this.energyLevel;
	}

	public void eat(Animal.Food food) {
		System.out.println("Eating...");
		this.energyLevel += this.energyLevelEat;
	}

	public void sleep() {
		System.out.println("Sleeping...");
		this.energyLevel += this.energyLevelSleep;
	}

	public void speak() {
		this.speak("Grrr");
	}

	public void speak(String sound) {
		System.out.println(sound);
	}

	public void play() {
		System.out.println("Playing...");
		this.energyLevel += this.energyLevelPlay;

		if (this.energyLevel > 0) {
			this.speak("YE-AH!");
		} else {
			this.speak("I'm tired.");
		}
	}

	public void dance() {
		if (this.isTrainable) {
			this.speak("Look Ma! I'm Dancing.");
		} else {
			this.speak("I'm not trained to dance...");
		}
	}
	
	public void soundOff(){
		this.speak();
		this.speak("Energy Level: " + this.energyLevel);
	}
	
	public void kill(){
	}
	
	@Override
	public int compare(Animal animal1, Animal animal2) {
		if (animal1.getClass().equals(animal2.getClass())) {
			if (animal1.getEnergyLevel() == animal2.getEnergyLevel()) {
				return 1;
			}
		}

		return -1;
	}

}
