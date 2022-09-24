package BlackJack;

public class CardDeck {

	private int card_count; // 덱에 남은 카드 수
	private Card[] deck = new Card[4 * Card.SIZE_OF_ONE_SUIT];
	// Invariant: deck[0],....,deck[card_count - 1]에 카드가 있음
	
	/** Constructor - 카드 한 벌 생성 */ 
	public CardDeck() { 
		createDeck();
	}
	
	/** createSuit - 주어진 무늬로 카드 13장 생성 */ 
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
	
	/** createDeck - createSuit 메소드를 활용하여 Deck을 만들기 */
	private void createDeck() {
		createSuit(Card.SPADES);
		createSuit(Card.HEARTS); 
		createSuit(Card.CLUBS); 
		createSuit(Card.DIAMONDS);
	}
	
	/** newCard - 임의의 새 카드 한 장을 뽑아 줌
	 * @return 카드 덱에서 임의로 한 장을 뽑아 리턴
	 * 		   없으면 카드 1벌을 새로 만들어 그 중 한 장을 뽑아 리턴 */
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
	
	/** moreCard - 카드 덱에 카드가 남아있는가의 여부를 반환
	 * @return - 덱에 카드가 남아있으면 true, 없으면 false를 리턴 */
	public boolean moreCards() { 
		return card_count > 0; 
	}
}
