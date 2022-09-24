package BlackJack;

public class BlackJackStarter {

	public static void main(String[] args) {
		Dealer dealer= new Dealer();
		Visitor visitor = new Visitor();
		GameFrame frame = new GameFrame(dealer , visitor);
	
	}
}
