// imane Boudjeddaine användarnamn: imbo4254
public class Bid {

	private User user;
	private int tot;

	public Bid(User name, int tot) {

		this.user = name;
		this.tot = tot;
	}

	public User getUser() {
		return this.user;
	}

	public int getTot() {
		return this.tot;
	}

	public String toString() {
		return String.format(this.user + " " + this.tot + " kr");
	}

}