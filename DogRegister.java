
// imane Boudjeddaine användarnamn: imbo4254
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class DogRegister {

	final private String errorMessage = "Error";
	private Scanner sc = new Scanner(System.in);
	private ArrayList<User> usersList = new ArrayList<>();
	private ArrayList<Dog> dogsList = new ArrayList<>();
	private ArrayList<Auction> auctionsList = new ArrayList<>();

	public static void main(String[] args) {
		new DogRegister().run();

	}

	private void run() {
		commandoLoop();
		exit();

	}

	private String acceptedInput(String output) {
		String uName;
		do {
			System.out.print(output);
			uName = inputString();
			if (uName.length() <= 1) {
				System.out.println(errorMessage + " Try again ");
			}
		} while (uName.length() <= 1);
		return uName;
	}

	private String inputString() {
		String input = sc.nextLine();
		return input.trim().toLowerCase();
	}

	private int inputInt() {
		int input = sc.nextInt();
		sc.nextLine();
		return input;
	}

	private void commandoLoop() {

		boolean running = true;
		while (running) {

			System.out.println("Choose an option:\n" + "register new dog\n" + "increase age\n" + "list dogs\n"
					+ "remove dog\n" + "register new user\n" + "list users\n" + "remove user\n" + "start auction\n"
					+ "list auctions\n" + "list bids\n" + "make bid\n" + "exit\n");

			String myCommand = inputString();
			switch (myCommand) {

			case "register new dog":
				registerNewDog();
				break;

			case "register new user":
				addUser();
				break;

			case "increase age":
				increaseAge();
				break;

			case "list dogs":
				listDogs();
				break;

			case "list users":
				listUsers();
				break;

			case "list auctions":
				listAuctions();
				break;

			case "list bids":
				listBids();
				break;

			case "remove dog":
				System.out.println(removeDog());
				break;

			case "remove user":
				System.out.println(removeUser());
				break;

			case "start auction":
				startAuction();
				break;

			case "close auction":
				closeAuction();
				break;

			case "make bid":
				makeBid();
				break;

			case "exit":
				System.out.println("Goodbye!");
				running = false;
				break;

			default:
				System.out.println(errorMessage + "\n");
				break;
			}

		}

	}

	private void registerNewDog() {
		String name = acceptedInput("Name: ");
		String breed = acceptedInput("Breed: ");

		System.out.print("Age: ");
		int age = inputInt();

		System.out.print("Weight: ");
		int weight = inputInt();

		Dog newDog = new Dog(name, breed, age, weight);

		dogsList.add(newDog);

		System.out.println(newDog + " added to the register.\n");
	}

	private void increaseAge() {
		String dogsName = acceptedInput("Enter the name of the dog: ");
		boolean aged = false;

		for (Dog dog : dogsList) {
			if (dog.getName().equalsIgnoreCase(dogsName)) {
				dog.getNewAge();
				aged = true;
			}
		}
		if (aged) {
			System.out.println(dogsName + " is now one year older. \n");
		} else
			System.out.println(errorMessage + " no such dog\n");
	}

	private String removeDog() {

		String removeDogName = acceptedInput("Enter the name of the dog: ");

		Dog dog = getDogsName(removeDogName);
		if (dog != null) {
			for (int i = 0; i < auctionsList.size(); i++) {
				if (auctionsList.get(i).getDog() == dog) {
					auctionsList.remove(i);
				}
			}
			User owner = dog.getOwner();
			if (owner != null) {
				owner.removeDog(dog);
			}
			dogsList.remove(dog);
			return dog.getName() + " is removed from the register.";
		}
		return errorMessage + " no such dog\n";
	}

private void sortDogTail() {
		for (int i = 0; i < dogsList.size() - 1; i++) {

			if (dogsList.get(i).getTailLength() > dogsList.get(i + 1).getTailLength()) {

				Collections.swap(dogsList, i + 1, i);
				i = -1;
			} else if (dogsList.get(i).getTailLength() == dogsList.get(i + 1).getTailLength()) {
				String dogA = dogsList.get(i).getName();
				String dogB = dogsList.get(i + 1).getName();
				for (int c = 0; c < dogA.length() && c < dogB.length(); c++) {
					if (dogA.charAt(c) > dogB.charAt(c)) {

						Collections.swap(dogsList, i + 1, i);
						i = -1;
						break;
					} else if (dogA.charAt(c) < dogB.charAt(c)) {
						break;
					}
				}
			}
		}
	}

		private void listDogs() {
	System.out.print("Smallest tail length to display: ");
		int answer = inputInt();
		sortDogTail();
		for (int i = 0; i < dogsList.size(); i++){

			if (answer <= dogsList.get(i).getTailLength()){
	
				System.out.println(dogsList.get(i));
			} else {
			  System.out.println(errorMessage + " no dogs in register\n");
  
			}
				
			}
		}
		
		
	

	private boolean dogExists(String name) {
		for (Dog dog : dogsList) {

			if (dog.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	private Dog getDogsName(String name) {
		for (Dog dog : dogsList) {

			if (dog.getName().equalsIgnoreCase(name)) {
				return dog;
			}
		}
		return null;
	}

	private void addUser() {
		String name = acceptedInput("Ange name: ");
		User user = getUsersName(name);

		if (user == null) {
			usersList.add(new User(name));
			System.out.println("User " + name + " added to the register");
		} else {
			System.out.println(errorMessage + " This user already exists.");
			addUser();
		}
	}

	private String removeUser() {

		String searchName = acceptedInput(" Enter the name of the user ");
		User user = getUsersName(searchName);
		if (user != null) {

			user.removeAllDogs();

			for (int i = 0; i < auctionsList.size(); i++) {
				auctionsList.get(i).removeUserBids(user);
			}
			String uName = user.getName();
			usersList.remove(user);
			return uName + " is removed from the register.";
		}
else 
		return errorMessage + "Error: no such user.";
	}

	private void listUsers() {
		for (User user : usersList) {
			System.out.println(user);
		}

	}

	private User getUsersName(String name) {
		for (User user : usersList) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	private void startAuction() {

		String dogName = acceptedInput("Enter the name of the dog: ");
		Dog dog = getDogsName(dogName);

		if (dog == null) {
			System.out.println(errorMessage + " no such dog.");
			startAuction();
			return;
		} else if (dog.getOwner() != null) {
			System.out.println(errorMessage + " this dog already has an owner.");
			return;
		} else if (auctionExists(dog)) {
			System.out.println(errorMessage + " this dog is already up for auction.");
			return;
		} else {
			auctionsList.add(new Auction(dog));
		//	if(auctions[auctions.length-1]!=null) {
				
		//		Auction[] temp = new Auction[auctions.length];
		//		temp = auctions;
		//		auctions = new Auction[auctions.length * 2];
				
		//		for(int i =0; i < temp.length; i++) {
		//	}
		//		auctions[temp.length] = new Auction (dog);
		//	}
		//	else {
		//		for(int i=0; i<auctions.length; i++) {
		//			if(auctions[i]==null) {
		//				auctions[i]=new Auction(dog);
		//				return;
		//			}
		//		}
			//}
		}

	}

	private void listAuctions() {
		if (auctionsList.size() == 0) {
			System.out.println(errorMessage + " no auctions in progress");
			return;
		}
		for (Auction auction : auctionsList) {
			System.out.println("Auction #" + auction);
		}
	}

	private boolean auctionExists(Dog dog) {
		for (Auction auction : auctionsList) {
			if (auction.getDog() == dog) {
				return true;
			}
		}
		return false;
	}

	private void closeAuction() {

		String dogName = acceptedInput("Name of dog: ");
		Dog dog = getDogsName(dogName);
		if (dog == null) {
			System.out.println(errorMessage + " the dogs name do not exist.");
			return;
		}

		for (int i = 0; i < auctionsList.size(); i++) {
			if (auctionsList.get(i).getDog() == dog) {
				Bid winningBid = auctionsList.get(i).close();
				if (winningBid != null) {
					User winningUser = winningBid.getUser();
					dog.setOwner(winningUser);
					winningUser.addDog(dog);
				}

				auctionsList.remove(i);
				return;
			}
		}
		System.out.println(errorMessage + " this dog is not up for auction.");
	}

	private void makeBid() {

		String nameInput = acceptedInput("Enter the name of the user: ");
		User user = getUsersName(nameInput);
		if (user == null) {
			System.out.println(errorMessage + " no such user.");
			return;
		}

		nameInput = acceptedInput("Enter the name of the dog: ");
		Dog dog = getDogsName(nameInput);
		if (dog == null) {
			System.out.println(errorMessage + " no such dog. ");
			return;
		}

		for (Auction auction : auctionsList) {
			if (auction.getDog() == dog) {
				while (true) {
					System.out.print("Amount to bid (min " + auction.getMyBid() + "): ");
					int tot = inputInt();

					if (tot > auction.getMyBid()) {
						auction.makeBid(user, tot);
						return;
					} else {
						System.out.println(errorMessage + " too low bid!");
					}
				}
			}
		}
		System.out.println(errorMessage + " No dog up for auction. ");
		return;
	}

	private void listBids() {
		String dogName = acceptedInput("Enter the dogs name: ");
		Dog dog = getDogsName(dogName);
		if (dog == null) {
			System.out.println(errorMessage + " No bids registred yet for this auction");
			return;
		}

		for (Auction auction : auctionsList) {
			if (auction.getDog() == dog) {
				auction.listBids();
				return;
			}
		}
		System.out.println(errorMessage + " this dog is not up for auction");
	}

	private void exit() {
		sc.close();
	}
}
