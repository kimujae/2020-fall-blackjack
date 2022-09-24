package BlackJack;

public class Dealer extends CardPlayer implements CardPlayerBehavior {
	
	private CardDeck deck;

	/** Constructor - ���ο� �� ����*/
	public Dealer() {
		deck = new CardDeck();
		
	}
	
	/** dealTo - �׸� �޶�� �� ������ ī�带 �մԿ��� �� �徿 �ش� 
	 * @param p - ���� ������ ��ü */
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
    
	/** wantsACard - ī�� ��ü ���� ��, ��� ������ 16 ������ ��� ī�带 ��� �ޱ� */
	public boolean wantsACard() {
		if (this.getSumCard() <= 16) 
			return true;
	
		else 
			return false;
	}
	
	/** showHand - ����ִ� ī���� ���¸� ��ȯ */
	public String showHand() {
		if (this.getSumCard() > 21)
			return "bust!";
		
		else if (this.getHavingCardsNum() == 0)
			return "����ִ� ī�尡 �����ϴ�."; 
	
		else {
		if (this.getHavingCardsNum() == 1)
			return "#1  ###"; 
		else 
			return super.showHand() + "  �԰� : ### + " + (this.getSumCard());
		}
	}
	
	/** getFirstNum - ������ ������ ù��° ī���� ����� ��ȯ */
	public int getFirstNum() {
		return hand[0].getRank();
	}
		
	/** getResult - ���� ��� ����
	 * @param other - ������ �ܷ� �մ� �÷��̾� */
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