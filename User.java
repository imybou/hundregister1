
// imane Boudjeddaine användarnamn: imbo4254
import java.util.ArrayList;
import java.util.Arrays;

public class User {

	private String name;
	private ArrayList<Dog> dogs = new ArrayList<>();

	public User(String name) {

		this.name = name;

	}

	public String getName() {
		return this.name;
	}

	public void addDog(Dog dog) {
		this.dogs.add(dog);
	}

	public void removeDog(Dog dog) {
		dog.setOwner(null);
		this.dogs.remove(dog);
	}

	public void removeAllDogs() {
		for (Dog dog : this.dogs) {
			dog.setOwner(null);
		}
		this.dogs.clear();
	}

	public String toString() {
		String[] ownedDogs = new String[this.dogs.size()];
		for (int i = 0; i < ownedDogs.length; i++) {
			ownedDogs[i] = this.dogs.get(i).getName();
		}
		return this.name + " " + Arrays.toString(ownedDogs);
	}

}