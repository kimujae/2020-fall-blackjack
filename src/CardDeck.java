package BlackJack;

public class CardDeck {

	private int card_count; // ���� ���� ī�� ��
	private Card[] deck = new Card[4 * Card.SIZE_OF_ONE_SUIT];
	// Invariant: deck[0],....,deck[card_count - 1]�� ī�尡 ����
	
	/** Constructor - ī�� �� �� ���� */ 
	public CardDeck() { 
		createDeck();
	}
	
	/** createSuit - �־��� ���̷� ī�� 13�� ���� */ 
	private void createSuit(String which_suit) {
		for(int i = 1; i <= Card.SIZE_OF_ONE_SUIT; i++) {
			if(i<10) {
			deck[card_count] = new Card(which_suit, i); 
			}
			else {
				deck[card_count] = new Card(which_suit, 10);
			}
			
			card_count = card_count + 1;
		} 
	}
	
	/** createDeck - createSuit �޼ҵ带 Ȱ���Ͽ� Deck�� ����� */
	private void createDeck() {
		createSuit(Card.SPADES);
		createSuit(Card.HEARTS); 
		createSuit(Card.CLUBS); 
		createSuit(Card.DIAMONDS);
	}
	
	/** newCard - ������ �� ī�� �� ���� �̾� ��
	 * @return ī�� ������ ���Ƿ� �� ���� �̾� ����
	 * 		   ������ ī�� 1���� ���� ����� �� �� �� ���� �̾� ���� */
	public Card newCard() { 
		if (! this.moreCards()) 
			createDeck();
		int index = (int)(Math.random() * card_count);
		Card card_to_deal = deck[index];
		for (int i = index+1; i < card_count; i++)
			deck[i-1] = deck[i];
		card_count = card_count - 1;
		return card_to_deal;
	}
	
	/** moreCard - ī�� ���� ī�尡 �����ִ°��� ���θ� ��ȯ
	 * @return - ���� ī�尡 ���������� true, ������ false�� ���� */
	public boolean moreCards() { 
		return card_count > 0; 
	}
}
