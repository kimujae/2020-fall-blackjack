package BlackJack;

public class CardPlayer {

	public Card[] hand;
    private int sum;
    private int having_cards_num;
    private int number_of_ace;

    /** Constructor - �տ� ��� �ִ� ī�� �����, ��� �� �ʱ�ȭ */
	public CardPlayer() {
	having_cards_num =0;	
	hand = new Card[11];
	sum =0;
	number_of_ace = 0;
	}
	
	/** receiveCard - ī�带 �޴� �޼ҵ�
	 * @param n - ī�带 ���� ����(�ش� ī�尡 ù��°�� ���� ī���̸� n==1)
	 * @param c - ���� ���� ī��
	 * return - ī�� ���� 21�� ������ bust�� false, ���������� ������ true */
	public boolean receiveCard(int n, Card c) {
		if(sum > 21) 
			return false;
		
		else if(c.getRank()==1 && sum <11) {
				hand[n] = c;
				number_of_ace ++;
				sum =sum +11;
				having_cards_num++;
			}
		else {
			hand[n] = c;
			sum = sum + c.getRank();
			having_cards_num++;
		    
		}
		while(sum > 21 && number_of_ace>0) {
			sum -=10;
			number_of_ace -=1;
			
		}
		return true;
	}
	
	/** showHand - �տ� ��� �ִ� ī�� �����ֱ�
	 * return - ��� �ִ� ī���� ���̿� ��� */
	public String showHand() {
		return "#" + this.getHavingCardsNum() + " = " + hand[this.getHavingCardsNum()-1].getSuit() + " " +hand[this.getHavingCardsNum()-1].getRank()+"\n" ;
		}
	
	/** getSumCard - ����ִ� ī���� ��� ������ ��ȯ */
	public int getSumCard() { return sum; }
	
	/** getHavingCardNum - ��� �ִ� ī�� �� ���� ��ȯ */
	public int getHavingCardsNum() { return having_cards_num; }
	
	/** hand - ī�� �迭���� Ư�� ī�� ������ ��ȯ
	 * @param i - ��ȯ�ϰ���� ī���� �ε��� */
	public Card hand(int i) { return hand[i]; }
	
	/** setFresh - �÷��̾ ��� �ִ� ī���� ���¸� �ʱ�ȭ */
	public void setFresh() {
		having_cards_num = 0;	
		hand = new Card[11];
		sum = 0;
		number_of_ace = 0;  
		}
}
