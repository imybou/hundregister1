
// imane Boudjeddaine användarnamn: imbo4254
import java.util.ArrayList;
import java.util.Arrays;

public class Auction {

	private Dog dog;
	private int myBid;

	final private String errorMessage = "error";
	private ArrayList<Bid> bids = new ArrayList<>();
	private int aStartNumber;
	private static int auctionNumber = 1;

	public Auction(Dog dog) {

		this.dog = dog;
		this.myBid = 0;
		this.aStartNumber = auctionNumber;

		auctionNumber++;

		System.out.println(
				String.format("%s has been put up for auction in auction %d.", this.dog.getName(), this.aStartNumber));
	}

	public Dog getDog() {
		return this.dog;
	}

	public int getStartNumber() {
		return this.aStartNumber;
	}

	public void makeBid(User user, int tot) {
		this.myBid = tot;
		for (int i = 0; i < this.bids.size(); i++) {
			if (this.bids.get(i).getUser() == user) {
				this.bids.remove(i);
			}
		}
		this.bids.add(0, new Bid(user, tot));

		System.out.println("Bid made");
	}

	public int getMyBid() {
		return this.myBid;
	}

	public void listBids() {
		if (this.bids.size() == 0) {
			System.out.println(errorMessage + " No bids registred yet for this auction");
		} else {
			System.out.println("Here are the bids for this auction");
			for (Bid bid : this.bids) {
				System.out.println(bid);
			}
		}
	}

	public Bid close() {
		if (this.bids.size() == 0) {
			System.out.println("The auction is closed. No bids where made for " + this.dog.getName());
			return null;
		} else {
			System.out.println("The auction is closed. The winning bid was " + this.bids.get(0).getTot()
					+ " kr and was made by " + this.bids.get(0).getUser().getName());
			return this.bids.get(0);
		}

	}

	public void removeUserBids(User user) {
		for (int i = 0; i < this.bids.size(); i++) {

			if (this.bids.get(i).getUser() == user) {

				this.bids.remove(i);
			}
		}
	}

	public String toString() {
		if (this.bids.size() > 3) {
			Bid[] topTree = this.bids.subList(0, 3).toArray(new Bid[this.bids.subList(0, 3).size()]);

			return this.aStartNumber + ": " + this.dog.getName() + ". Top bids: " + Arrays.toString(topTree);
		}

		return this.aStartNumber + ": " + this.dog.getName() + ". Top bids: " + bids;

	}
}
