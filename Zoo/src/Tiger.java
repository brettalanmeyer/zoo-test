public class Tiger extends Animal {
	
	static int species = 0;
	
	public Tiger() {
		this.energyLevelPlay = -8;
		Tiger.species++;
	}
	
	public int getSpecies(){
		return Tiger.species;
	}
	
	public void kill(){
		Tiger.species--;
	}
	
	public void eat(Animal.Food food) {
		if(!food.equals(Animal.Food.Grain)){
			super.eat(food);
		}
	}

}
