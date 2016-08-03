public class Lion extends Animal {
	
	static int species = 0;
	
	public Lion(){
		Lion.species++;
	}
	
	public int getSpecies(){
		return Lion.species;
	}
	
	public void kill(){
		Lion.species--;
	}

}
