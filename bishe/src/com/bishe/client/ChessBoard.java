package com.bishe.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessBoard extends JPanel implements MouseListener{
	private int endI;
	private boolean focus;
	private int startI;
	private int startJ;
	private int endJ;
	private Rule rule ;
	private Pieces [][]ps = new Pieces[9][10];
	private Chess chess;
	private int count = 0;
//	public ChessBoard(Pieces[][] ps) {
//		this.ps = ps;
//		focus = false;
//		this.addMouseListener(this);
//		rule = new Rule();
//	}
	public ChessBoard(Chess chess) {
		this.chess = chess;
		startI = -1;
		startJ = -1;
		endJ = -1;
		endI = -1;
		InitPieces(ps);
		focus = false;
		this.addMouseListener(this);
		rule = new Rule();
	}
	public void paintComponent(Graphics g) {
		System.out.println("����pain����");
		g.setColor(Color.BLACK);
		for(int x = 0 ; x <= 9 ; x++) {
			g.drawLine(70, 70+70*x, 70+70*8, 70+70*x);
		}
		
		g.drawLine(70, 70, 70, 70+70*9);
		g.drawLine(630, 70, 630, 70+70*9);
		
		for(int y = 1 ; y <= 8 ; y++) {
			g.drawLine(70+70*y, 70, 70+70*y, 70+70*4);
			g.drawLine(70+70*y, 420, 70+70*y, 420+70*4);
		}
		g.drawLine(280, 70, 420, 210);
		g.drawLine(280, 210, 420, 70);
		g.drawLine(280, 560, 420, 700);
		g.drawLine(280, 700, 420, 560);
		
		g.drawLine(130, 200, 150, 220);
		g.drawLine(130, 220, 150, 200);
		
		g.drawLine(550, 200, 570, 220);
		g.drawLine(550, 220, 570, 200);
		
		g.drawLine(70, 280, 80, 270);
		g.drawLine(70, 280, 80, 290);
		
		g.drawLine(200, 270, 220, 290);
		g.drawLine(200, 290, 220, 270);
		
		g.drawLine(340, 270, 360, 290);
		g.drawLine(340, 290, 360, 270);
		
		g.drawLine(480, 270, 500, 290);
		g.drawLine(480, 290, 500, 270);
		
		g.drawLine(630, 280, 620, 270);
		g.drawLine(630, 280, 620, 290);
		
		g.drawLine(70, 490, 80, 480);
		g.drawLine(70, 490, 80, 500);
		
		g.drawLine(220, 480, 200, 500);
		g.drawLine(200, 480, 220, 500);
		
		g.drawLine(360, 480, 340, 500);
		g.drawLine(340, 480, 360, 500);
		
		g.drawLine(500, 480, 480, 500);
		g.drawLine(480, 480, 500, 500);
		
		g.drawLine(620, 480, 630, 490);
		g.drawLine(630, 490, 620, 500);
		
		g.drawLine(150, 550, 130, 570);
		g.drawLine(130, 550, 150, 570);
		
		g.drawLine(550, 550, 570, 570);
		g.drawLine(570, 550, 550, 570);
		
		g.setColor(Color.black);
		Font font1 = new Font("����", Font.BOLD, 50);// ��������
		g.setFont(font1);		 
		g.drawString("�� ��", 140, 410);// ���Ƴ��Ӻ���
		g.drawString("�h ��", 420, 410);		
		Font font = new Font("����", Font.BOLD, 30);
		g.setFont(font);// ��������	
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {// ��������
				if (ps[i][j] != null) {
					if (this.ps[i][j].getFocus() != false) {// ѡ������״̬
						g.setColor(Color.white);// ѡ�к�ı���ɫ
						g.fillOval(70 + i * 70-25 , 70 + j * 70-25 , 50, 50);// ���Ƹ�������㣨110,80�����Ӱ뾶��25��
						g.setColor(ps[i][j].getColor());// �ַ�����ɫ
					} else {
						g.setColor(Color.lightGray);
						g.fillOval(70 + i * 70-25 , 70 + j * 70-25, 50, 50);// ���Ƹ�����
						g.setColor(ps[i][j].getColor());// ���û�����ɫ
					}
					g.drawString(ps[i][j].getName(), 58 + i * 70,
							80 + j * 70);// ����
					g.setColor(Color.lightGray);// ��Ϊ��ɫ
				}
			}
		}
		this.revalidate();
	}	
	public int getEndJ() {
		return endJ;
	}

	public void setEndJ(int endJ) {
		this.endJ = endJ;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getEndI() {
		return endI;
	}

	public void setEndI(int endI) {
		this.endI = endI;
	}

	public int getStartI() {
		return startI;
	}

	public void setStartI(int startI) {
		this.startI = startI;
	}

	public int getStartJ() {
		return startJ;
	}

	public void setStartJ(int startJ) {
		this.startJ = startJ;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public Pieces[][] getPs() {
		return ps;
	}

	public void setPs(Pieces[][] ps) {
		this.ps = ps;
	}

	public Chess getChess() {
		return chess;
	}

	public void setChess(Chess chess) {
		this.chess = chess;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(this.chess.isSign()) {
		System.out.println(getPos(e)[0]+"     "+getPos(e)[1]);	
		    if(count%2==0) {
		    	if(ps[getPos(e)[0]][getPos(e)[1]]!=null) {
		    		if(this.chess.isNext()) {
		    		 noFocus(getPos(e)[0],getPos(e)[1]);
		    		 if(!this.focus) {
		    			 JOptionPane.showMessageDialog(this.chess, "���Ǳ�������", "��ʾ",
									JOptionPane.INFORMATION_MESSAGE);
		    			 count = 0;
		    			 startI = -1;
		    			 startJ = -1;
		    			 endJ = -1;
		    			 endJ = -1;
		    		 }else {//��ȷ
		    		 count++;
		    		 focus = false;
		    		 this.chess.setNext(false);
		    		 }
		    		}else {
		    			 JOptionPane.showMessageDialog(this.chess, "���ֶԷ�����", "��ʾ",
									JOptionPane.INFORMATION_MESSAGE);
		    			 count = 0;
		    			 startI = -1;
		    			 startJ = -1;
		    			 endJ = -1;
		    			 endJ = -1;
		    		}
		    	}else {
		    		JOptionPane.showMessageDialog(this.chess, "û��ѡ��Ŀ��", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
		    		count = 0;
		    		startI = -1;
		    		startJ = -1;
		    		endI = -1;
		    		endJ = -1;
		    	}
		  
		}else {
			endI = getPos(e)[0];
			endJ = getPos(e)[1];
			if((startI!=endI||startJ!=endJ)&&(endI!=-1||endJ!=-1)) {
				rule.setCanMove(true);
				if(rule.canMove(startI, startJ, endI, endJ, ps[startI][startJ].getName(),ps)) {
					//�ж�ʤ��
					if(ps[endI][endJ]!=null&&(ps[endI][endJ].getName().equals("��")||ps[endI][endJ].getName().equals("��"))) {
						System.err.println("��ϲ�㣡��ʤ��");
						JOptionPane.showMessageDialog(this.getParent(), "��ϲ��������ʤ��", "��ʾ",
								JOptionPane.INFORMATION_MESSAGE);// ������ʤ��Ϣ
						this.chess.setSign(false);
						WinMessage();
					}
					//�ƶ����Ӵ�����Ϣ
					Pieces ps_man = ps[startI][startJ];
					movePs(ps_man, endI, endJ);
					movePs(null, startI, startJ);
					this.repaint();
					this.getParent().repaint();
					//����ӵ�е�chess����clientagentthread���ַ�����������Ϣ;
					chess.getCat().move();
				}else {
					System.out.println("�����Ϲ���");
					JOptionPane.showMessageDialog(this.getParent(), "�����Ϲ���", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);

					focus = false;
					this.chess.setNext(true);
				}
				
			}else {
				System.out.println("û�ж��ط�������û��Ŀ�ĵ�");
				JOptionPane.showMessageDialog(this.getParent(), "û�ж��ط�������û��Ŀ�ĵ�", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				this.chess.setNext(true);
				}
			count = 0;
			startI = -1;
			startJ = -1;
			}
		}
    }

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*public void noJiang() {
		
	}
	public void Success() {
		
	}*/
	public int[] getPos(MouseEvent e) { // ����¼���������
		int[] pos = new int[2];
		pos[0] = -1;
		pos[1] = -1;
		Point p = e.getPoint();// ����¼������������
		double x = p.getX();
		double y = p.getY();
		if (Math.abs((x - 70) % 70) <= 25) {// ������Ӷ�Ӧ������x�±��λ��
			pos[0] = Math.round((float) (x - 70)) / 70; // round()��0.5ȡ��
		} else if (Math.abs((x - 70) % 70) >= 35) {
			pos[0] = Math.round((float) (x - 70)) / 70 + 1;
		}
		if (Math.abs((y - 70) % 70) <= 25) {// ������Ӷ�Ӧ������y�±��λ��
			pos[1] = Math.round((float) (y - 70)) / 70;
		} else if (Math.abs((y - 70) % 70) >= 35) {
			pos[1] = Math.round((float) (y - 70)) / 70 + 1;
		}
		return pos;
	}
	
	public void noFocus(int i, int j) {// �ж�������ɫ���۽�
		if (this.ps[i][j] != null)// �����λ��������
		{
			if (this.chess.getColor() == 0)// ����Ǻ췽
			{
				if (this.ps[i][j].getColor().equals(Color.red))// ��������Ǻ�ɫ
				{
					//this.ps[i][j].setFocus(true);// ����������Ϊѡ��״̬
					focus = true;// ��focus��Ϊtrue
					startI = i;// ����������
					startJ = j;
				}
			} else// ����Ǻ�
			{
				if (this.ps[i][j].getColor().equals(Color.black))// ����������Ǻ�ɫ
				{
					//this.ps[i][j].setFocus(true);// ����������Ϊѡ��״̬
					focus = true;// ��focus��Ϊtrue
					startI = i;// ����������
					startJ = j;
				}
			}
		}
	}
	public void InitPieces(Pieces [][]ps) {
	    ps[0][0] = new Pieces(Color.red,"܇",0,0);
	    ps[1][0] = new Pieces(Color.red,"�R",1,0);
	    ps[2][0] = new Pieces(Color.red,"��",2,0);
	    ps[3][0] = new Pieces(Color.red,"��",3,0);
	    ps[4][0] = new Pieces(Color.red,"��",4,0);
	    ps[5][0] = new Pieces(Color.red,"��",5,0);
	    ps[6][0] = new Pieces(Color.red,"��",6,0);
	    ps[7][0] = new Pieces(Color.red,"�R",7,0);
	    ps[8][0] = new Pieces(Color.red,"܇",8,0);
	    
	    ps[1][2] = new Pieces(Color.red,"�h",1,2);
	    ps[7][2] = new Pieces(Color.red,"�h",7,2);
	    
	    ps[0][3] = new Pieces(Color.red,"��",0,3);
	    ps[2][3] = new Pieces(Color.red,"��",2,3);
	    ps[4][3] = new Pieces(Color.red,"��",4,3);
	    ps[6][3] = new Pieces(Color.red,"��",6,3);
	    ps[8][3] = new Pieces(Color.red,"��",8,3);
	    
	    ps[0][6] = new Pieces(Color.black,"��",0,6);
	    ps[2][6] = new Pieces(Color.black,"��",2,6);
	    ps[4][6] = new Pieces(Color.black,"��",4,6);
	    ps[6][6] = new Pieces(Color.black,"��",6,6);
	    ps[8][6] = new Pieces(Color.black,"��",2,7);
	    
	    ps[1][7] = new Pieces(Color.black,"��",1,7);
	    ps[7][7] = new Pieces(Color.black,"��",7,7);
	    
	    
	    ps[0][9] = new Pieces(Color.black,"܇",0,9);
	    ps[1][9] = new Pieces(Color.black,"�R",1,9);
	    ps[2][9] = new Pieces(Color.black,"��",2,9);
	    ps[3][9] = new Pieces(Color.black,"ʿ",3,9);
	    ps[4][9] = new Pieces(Color.black,"��",4,9);
	    ps[5][9] = new Pieces(Color.black,"ʿ",5,9);
	    ps[6][9] = new Pieces(Color.black,"��",6,9);
	    ps[7][9] = new Pieces(Color.black,"�R",7,9);
	    ps[8][9] = new Pieces(Color.black,"܇",8,9);
	    
	}
	//���Է�������ʤ��Ϣ
	public void WinMessage() {
		this.chess.getCat().getOut().println("��ʤ��Ϣ"+this.chess.getMname().getText()+"->"+this.chess.getOtherSide());
	}
	
	public void movePs(Pieces ps,int x,int y) {
	this.ps[x][y] = ps;
	}
}
