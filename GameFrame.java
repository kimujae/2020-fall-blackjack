package BlackJack;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GameFrame extends JFrame {
	
	 private Dealer dealer;
	 private Visitor visitor;
	 private GameButton[][] button;
	 private JLabel[][] dealer_deck;
	 private JLabel[][] visitor_deck;
	 private JLabel label = new JLabel("");
	 private JLabel label2 = new JLabel("");
	 private JLabel welcome_label = new JLabel("");
	 private boolean getresult;
	
	
	 /** Constructor - ������, �г�, ��ư ����
	  * @param d - ���� ��ü
	  * @param p - �÷��̾� ��ü */
	 public GameFrame(Dealer d, Visitor p) {
	 	// ����, �÷��̾� ��ü ȣ��, ī�� �迭 ����, ���� ��� �ʱ�ȭ
	 	dealer = d;
	 	visitor = p;
	 	getresult = false;
	    // ��ư ��ü �ʱ� ����
	 	button = new GameButton[1][5];
	 	button[0][0] = new GameButton(dealer, visitor, this); button[0][0].setText("START");
	 	button[0][1] = new GameButton(dealer, visitor, this); button[0][1].setText("HIT");
	 	button[0][2] = new GameButton(dealer, visitor, this); button[0][2].setText("STAY");
	 	button[0][3] = new GameButton(dealer, visitor, this); button[0][3].setText("NEXTGAME");
		button[0][4] = new GameButton(dealer, visitor, this); button[0][4].setText("QUIT");
		
	 	// ���� �� ��ư ����
	 	dealer_deck = new JLabel[2][11];
	 	for(int i = 0; i < 2; i++) 
	     	for(int j = 0; j < 11; j++) {
	     		dealer_deck[i][j]= new JLabel("");
	 			dealer_deck[i][j].setBorder(new BevelBorder(BevelBorder.LOWERED));
	     	}
	 	
	 // �÷��̾� �� ��ư ����		
	 	visitor_deck = new JLabel[2][11];
	 	for(int i = 0; i < 2; i++) 
	     	for(int j = 0; j < 11; j++) {
	     		visitor_deck[i][j]= new JLabel("");
	     		visitor_deck[i][j].setBorder(new BevelBorder(BevelBorder.LOWERED));
	     	}
	 	// �����Ӱ� �г� ����
	 	Container c = getContentPane();
	 	c.setLayout(new BorderLayout());
	 	JPanel p1 = new JPanel(); // ���� �� ��ư
	 	JPanel p2 = new JPanel();
	 	JPanel p2_1 = new JPanel(); // ȯ���λ� ����
	 	JPanel p2_2 = new JPanel(); // ���� ���� ��Ȳ ���� 
	 	JPanel p2_3 = new JPanel(); // START,HIT,STAY.NEXTGAME,QUIT ��ư
	 	JPanel p3 = new JPanel(); // �÷��̾� �� ��ư
	 	label = new JLabel("���� �������Դϴ�.");
	 	label2 = new JLabel("���� Chip ���� : " + visitor.getChip());
		welcome_label = new JLabel("");
	 	
	 	p1.setLayout(new GridLayout(2,11));
	     for(int i = 0; i < 2; i++)
	     	for(int j = 0; j < 11; j++)
	     		p1.add(dealer_deck[i][j]);
	
	     p2.setLayout(new BorderLayout());
	     p2.add(p2_1, BorderLayout.NORTH);
	 	 p2_1.add(welcome_label);
	 	 p2.add(p2_2, BorderLayout.CENTER);
	 	 p2_2.add(label);
	 	 p2_2.add(label2);
	     p2.add(p2_3, BorderLayout.SOUTH);
	 	 p2_3.add(button[0][0]);
	 	 p2_3.add(button[0][1]);
	 	 p2_3.add(button[0][2]);
	 	 p2_3.add(button[0][3]);
	  	 p2_3.add(button[0][4]);
	 	
	     p3.setLayout(new GridLayout(2,11));
	     for(int i = 0; i < 2; i++) {
	     	for(int j = 0; j < 11; j++)
	     		p3.add(visitor_deck[i][j]);
	     }
	     
	 	c.add(p1, BorderLayout.NORTH);
	 	c.add(p2, BorderLayout.CENTER);
	 	c.add(p3, BorderLayout.SOUTH);
	 	
	 	setTitle("BLACKJACK");
	 	setSize(1200,500);
	 	setVisible(true);
	 }
	
	 /** update - ������ ���� ���� */
	 public void update() {
		 welcome_label.setText("Welcome To Softopia Casino");
		 
		 if ((visitor.showHand().equals("bust!") && visitor.getHavingCardsNum() == 2) || (visitor.showHand().equals("black jack!!") && visitor.getHavingCardsNum() == 2)) { 
			 for (int i = 0; i < dealer.getHavingCardsNum(); i++) {
			 		dealer_deck[0][i].setText(dealer.hand(i).getSuit());
			 		dealer_deck[1][i].setText("" + dealer.hand(i).getRank());
			 	}
			 	
			 	for (int i = 0; i < visitor.getHavingCardsNum(); i++) {
			 		visitor_deck[0][i].setText(visitor.hand(i).getSuit());
			 		visitor_deck[1][i].setText("" + visitor.hand(i).getRank());	
			 	}
	 		setResult();
		 }
		 
		 else if ((visitor.showHand().equals("bust!") && visitor.getHavingCardsNum() != 2) || (visitor.showHand().equals("black jack!!") && visitor.getHavingCardsNum() != 2)) {
		 	for (int i = 0; i < dealer.getHavingCardsNum(); i++) {
		 		dealer_deck[0][i].setText(dealer.hand(i).getSuit());
		 		dealer_deck[1][i].setText("" + dealer.hand(i).getRank());
		 	}
		 	
		 	for (int i = 0; i < visitor.getHavingCardsNum(); i++) {
		 		visitor_deck[0][i].setText(visitor.hand(i).getSuit());
		 		visitor_deck[1][i].setText("" + visitor.hand(i).getRank());	
		 	}
			setResult();
		 }
	 
		 else {
			 if (visitor.getHavingCardsNum() == 2 && !getResult()) {
				 dealer_deck[0][dealer.getHavingCardsNum()-1].setText(dealer.hand(dealer.getHavingCardsNum() - 1).getSuit());
				 dealer_deck[1][dealer.getHavingCardsNum()-1].setText(""+dealer.hand(dealer.getHavingCardsNum() - 1).getRank());
				 visitor_deck[0][visitor.getHavingCardsNum()-1].setText(visitor.hand(visitor.getHavingCardsNum() - 1).getSuit());
				 visitor_deck[1][visitor.getHavingCardsNum()-1].setText(""+visitor.hand(visitor.getHavingCardsNum() - 1).getRank());
				 dealer_deck[0][dealer.getHavingCardsNum()-2].setText("###");
				 dealer_deck[1][dealer.getHavingCardsNum()-2].setText("###");
				 visitor_deck[0][visitor.getHavingCardsNum()-2].setText(visitor.hand(visitor.getHavingCardsNum() - 2).getSuit());
				 visitor_deck[1][visitor.getHavingCardsNum()-2].setText(""+visitor.hand(visitor.getHavingCardsNum() - 2).getRank());	
			 }
			 
			 else if (!getResult()) {
				 visitor_deck[0][visitor.getHavingCardsNum()-1].setText(visitor.hand(visitor.getHavingCardsNum()-1).getSuit());
				 visitor_deck[1][visitor.getHavingCardsNum()-1].setText(""+visitor.hand(visitor.getHavingCardsNum()-1).getRank());			
			 }
		 
			 else {
				 while(dealer.wantsACard()) 
					 dealer.dealTo(dealer);	   
				
				 	for(int i = 0; i<dealer.getHavingCardsNum(); i++ ) {
				    dealer_deck[0][i].setText(dealer.hand(i).getSuit());
					dealer_deck[1][i].setText(""+dealer.hand(i).getRank());
					}
			 }
		 }
	 	}	 
		 
	 	/** setResult - ���� ����� �����ӿ� ����Ͽ� ��Ÿ���� */
	 public void setResult() { 
	 		if(!dealer.wantsACard()) {
	 		label.setText(dealer.getResult(visitor));
	 		}
	 		getresult = true;
	 		label2.setText("���� Chip ���� : " + visitor.getChip());
	 		
	 	}
	
	 	/** getResult - ������ ���� ���� ���θ� ��ȯ
	 	 * @return - ���� ����� ����Ǹ� true, ������� ������ false */
	 public boolean getResult() {
	 		return getresult;
	 	}
 }
