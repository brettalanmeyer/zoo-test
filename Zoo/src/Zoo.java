import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Zoo {
	
	private List<Animal> animals = new ArrayList<>();
	
	public void addAnimal(Animal animal){
		this.animals.add(animal);
	}
	
	public List<Animal> getAnimals(){
		return this.animals;
	}
	
	public boolean kill(Class<?> clazz){
		Iterator<Animal> iterator = animals.iterator();
		while(iterator.hasNext()){
			Animal animal = iterator.next();
			if(clazz.isInstance(animal)){
				animal.kill();
				iterator.remove();
				return true;
			}
		}
		return false;
	}
	
}
