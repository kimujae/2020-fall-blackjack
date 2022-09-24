package BlackJack;

public class Dealer extends CardPlayer implements CardPlayerBehavior {
	
	private CardDeck deck;

	/** Constructor - 새로운 덱 생성*/
	public Dealer() {
		deck = new CardDeck();
		
	}
	
	/** dealTo - 그만 달라고 할 때까지 카드를 손님에게 한 장씩 준다 
	 * @param p - 블랙잭 참여자 객체 */
	public void dealTo(CardPlayer p) {
		Card c = deck.newCard();
		if (p.getHavingCardsNum() < 2)
			p.receiveCard(p.getHavingCardsNum(), c);	
		
		else if (p != this && p.getHavingCardsNum() < 21) 
			p.receiveCard(p.getHavingCardsNum(), c);
	
		else {
			while (wantsACard()) {
				c = deck.newCard();
				p.receiveCard(p.getHavingCardsNum(), c);
			}
		}
	}
    
	/** wantsACard - 카드 전체 공개 후, 등급 총합이 16 이하일 경우 카드를 계속 받기 */
	public boolean wantsACard() {
		if (this.getSumCard() <= 16) 
			return true;
	
		else 
			return false;
	}
	
	/** showHand - 들고있는 카드의 상태를 반환 */
	public String showHand() {
		if (this.getSumCard() > 21)
			return "bust!";
		
		else if (this.getHavingCardsNum() == 0)
			return "들고있는 카드가 없습니다."; 
	
		else {
		if (this.getHavingCardsNum() == 1)
			return "#1  ###"; 
		else 
			return super.showHand() + "  함계 : ### + " + (this.getSumCard());
		}
	}
	
	/** getFirstNum - 가려진 딜러의 첫번째 카드의 등급을 반환 */
	public int getFirstNum() {
		return hand[0].getRank();
	}
		
	/** getResult - 게임 결과 판정
	 * @param other - 딜러와 겨룰 손님 플레이어 */
	public String getResult(Visitor other) {

		if (this.getSumCard() == 0 && other.getSumCard() == 0)
			return "";
		else if(this.getSumCard() > 21 && other.getSumCard() > 21) {
			return "Dealer : " + this.getSumCard() + " VS Player : " + other.getSumCard() + " --- " + " We Draw.";
		}
		else if (this.getSumCard() > 21 && other.getSumCard() < 21 ) {
			other.setChip(1);
			return "Dealer : " + this.getSumCard() + " VS Player : " + other.getSumCard() + " --- " + " Dealer Bust! Player Win.";
		}
		
		else if (other.getSumCard() > 21 && this.getSumCard() < 21 ) {
			other.setChip(-1);
			return "Player : " + other.getSumCard() + " --- " + " Player Bust! Dealer Win.";
		}
		
		else if (this.getSumCard() == 21 && other.getSumCard() != 21) {
			other.setChip(-1);
			return "Dealer : " + this.getSumCard() + " VS Player : " + other.getSumCard() + " --- " + " Black Jack! Dealer Win.";
		}
		
		else if (other.getSumCard() == 21 && this.getSumCard() != 21) {
			other.setChip(2);
			return "Player : " + other.getSumCard() + " --- " +  " Black Jack! Player Win.";
		}
		
		else if (this.getSumCard() > other.getSumCard()) {
			other.setChip(-1);
			return "Dealer : " + this.getSumCard() + " VS Player : " + other.getSumCard() + " --- " + " Dealer Win.";
		}
		
		else if (this.getSumCard() < other.getSumCard()) {
			other.setChip(1);
			return "Dealer : " + this.getSumCard() + " VS Player : " + other.getSumCard() + " --- " + " Player Win.";
		}
		
		else 
			return "Dealer : " + this.getSumCard() + " VS Player : " + other.getSumCard() + " --- " + " We Draw.";
	}
	
	
}