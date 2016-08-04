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

	public String eat(Animal.Food food) {
		this.energyLevel += this.energyLevelEat;
		return "Eating...";
	}

	public String sleep() {
		this.energyLevel += this.energyLevelSleep;
		return "Sleeping...";
	}

	public String speak() {
		return "Grrr";
	}

	public String speak(String sound) {
		return sound;
	}

	public String play() {
		this.energyLevel += this.energyLevelPlay;
		String out = "Playing...";

		if (this.energyLevel > 0) {
			out.concat(this.speak("YE-AH!"));
		} else {
			out.concat(this.speak("I'm tired."));
		}
		return out;
	}

	public String dance() {
		if (this.isTrainable) {
			return this.speak("Look Ma! I'm Dancing.");
		} else {
			return this.speak("I'm not trained to dance...");
		}
	}
	
	public String soundOff(){
		return this.speak("Energy Level: " + this.energyLevel);
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
