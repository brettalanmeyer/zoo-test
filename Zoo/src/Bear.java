public class Bear extends Animal {
	
	static int species = 0;

	public Bear() {
		this.energyLevelSleep = 10;
		this.isTrainable = true;
		Bear.species++;
	}
	
	public int getSpecies(){
		return Bear.species;
	}
	
	public void kill(){
		Bear.species--;
	}

}
