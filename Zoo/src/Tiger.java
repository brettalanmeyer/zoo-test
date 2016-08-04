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
	
	public String eat(Animal.Food food) {
		if(!food.equals(Animal.Food.Grain)){
			return super.eat(food);
		}
		return "";
	}

}
