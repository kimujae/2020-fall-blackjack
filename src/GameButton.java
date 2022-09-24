package BlackJack;

import javax.swing.*;
import java.awt.event.*;

public class GameButton extends JButton implements ActionListener {
 private Dealer dealer;
 private Visitor visitor;
 private GameFrame view;
 
 public GameButton(Dealer d, Visitor p, GameFrame v) {
	 dealer = d;
	 visitor = p;
	 view = v;
	 addActionListener(this);
 }
 
 public void actionPerformed(ActionEvent e) {
	 String s;
	 s = getText();
	 
	 // START ��ư - ����-�÷��̾�-����-�÷��̾� ������ ī�� ��
	 if(s.equals("START")) {
		 if(dealer.getHavingCardsNum() == 0) {
				 dealer.dealTo(dealer);
				 dealer.dealTo(visitor);
				 dealer.dealTo(dealer);
				 dealer.dealTo(visitor);
				 view.update();
		 }
	 }
	 // HIT ��ư - �÷��̾ ī�带 ���� �� ������ �÷��̾�� ī�� �ֱ�
	 else if(s.equals("HIT")) {
		 if (!view.getResult()) {
			 if(visitor.wantsACard()) {
				 if(dealer.getHavingCardsNum() != 0 && dealer.getHavingCardsNum() <= 2) {
					 dealer.dealTo(visitor);
					 view.update();
				 }
				 if(view.getResult()) {
					 while(dealer.wantsACard())
					 dealer.dealTo(dealer);	
					 view.setResult();
					 view.update();
				 }
			 }
		 }
	 }
	 // STAY ��ư - ������ ī�带 ���� �� ������ �������� ī�� �ֱ�, �ƴϸ� ��� ���
	 else if(s.equals("STAY")) {
		 if(!(dealer.getHavingCardsNum() == 0 && visitor.getHavingCardsNum() == 0)) {
		 while(dealer.wantsACard())
			 dealer.dealTo(dealer);	   
		 
		 if(view.getResult())
			 return;
		 
		 view.setResult();
		 view.update();
		 }
	 }
	 // MoreGame ��ư - ������ ������ Ȱ��ȭ�Ǿ�, ���ο� ��Ʈ�� ������
	 else if (s.equals("NEXTGAME")) {
		 if(!view.getResult())
			 return;
		   view.dispose();
		   dealer= new Dealer();
		   visitor.setFresh();
		   view = new GameFrame(dealer, visitor);
	 }
	 // QUIT ��ư - ���� ����
	 else if(s.equals("QUIT"))
		 System.exit(0);
	 }
}