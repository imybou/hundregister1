// imane Boudjeddaine användarnamn: imbo4254
public class Dog {

	private String name;
	private String breed;
	private int age;
	private int weight;
	private User owner;

	public Dog(String name, String breed, int age, int weight) {

		this.name = name;
		this.breed = breed;
		this.age = age;
		this.weight = weight;
		this.owner = null;

	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public int getAge() {
		return age;
	}

	public void getNewAge() {
		this.age++;

	}

	public double getWeight() {
		return weight;
	}

	public double getTailLength() {
		double taillength;

		if (breed.equalsIgnoreCase("tax") || breed.equalsIgnoreCase("dachshund")) {
			taillength = 3.7;

		} else {
			taillength = age * weight / 10.0;
		}
		return taillength;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}

	public String toString() {
		if (this.owner == null) {
			return String.format(
					name + " " + breed + " " + age + " year" + " " + weight + " kg " + "tail = " + getTailLength());
		} else {
			return String.format(name + " " + breed + " " + age + " year" + " " + weight + " kg " + "tail = "
					+ getTailLength() + " owned by " + this.owner.getName());
		}
	}
}
