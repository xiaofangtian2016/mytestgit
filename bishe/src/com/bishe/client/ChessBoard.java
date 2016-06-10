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
		System.out.println("调用pain方法");
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
		Font font1 = new Font("宋体", Font.BOLD, 50);// 设置字体
		g.setFont(font1);		 
		g.drawString("楚 河", 140, 410);// 绘制楚河汉界
		g.drawString("漢 界", 420, 410);		
		Font font = new Font("宋体", Font.BOLD, 30);
		g.setFont(font);// 设置字体	
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {// 绘制棋子
				if (ps[i][j] != null) {
					if (this.ps[i][j].getFocus() != false) {// 选中棋子状态
						g.setColor(Color.white);// 选中后的背景色
						g.fillOval(70 + i * 70-25 , 70 + j * 70-25 , 50, 50);// 绘制该棋子起点（110,80）棋子半径（25）
						g.setColor(ps[i][j].getColor());// 字符的颜色
					} else {
						g.setColor(Color.lightGray);
						g.fillOval(70 + i * 70-25 , 70 + j * 70-25, 50, 50);// 绘制该棋子
						g.setColor(ps[i][j].getColor());// 设置画笔颜色
					}
					g.drawString(ps[i][j].getName(), 58 + i * 70,
							80 + j * 70);// 绘字
					g.setColor(Color.lightGray);// 设为黑色
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
		    			 JOptionPane.showMessageDialog(this.chess, "不是本方棋子", "提示",
									JOptionPane.INFORMATION_MESSAGE);
		    			 count = 0;
		    			 startI = -1;
		    			 startJ = -1;
		    			 endJ = -1;
		    			 endJ = -1;
		    		 }else {//正确
		    		 count++;
		    		 focus = false;
		    		 this.chess.setNext(false);
		    		 }
		    		}else {
		    			 JOptionPane.showMessageDialog(this.chess, "该轮对方走棋", "提示",
									JOptionPane.INFORMATION_MESSAGE);
		    			 count = 0;
		    			 startI = -1;
		    			 startJ = -1;
		    			 endJ = -1;
		    			 endJ = -1;
		    		}
		    	}else {
		    		JOptionPane.showMessageDialog(this.chess, "没有选中目标", "提示",
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
					//判断胜负
					if(ps[endI][endJ]!=null&&(ps[endI][endJ].getName().equals("将")||ps[endI][endJ].getName().equals("帥"))) {
						System.err.println("恭喜你！获胜了");
						JOptionPane.showMessageDialog(this.getParent(), "恭喜您，您获胜了", "提示",
								JOptionPane.INFORMATION_MESSAGE);// 给出获胜信息
						this.chess.setSign(false);
						WinMessage();
					}
					//移动棋子传递信息
					Pieces ps_man = ps[startI][startJ];
					movePs(ps_man, endI, endJ);
					movePs(null, startI, startJ);
					this.repaint();
					this.getParent().repaint();
					//利用拥有的chess调用clientagentthread来现服务器发送消息;
					chess.getCat().move();
				}else {
					System.out.println("不符合规则");
					JOptionPane.showMessageDialog(this.getParent(), "不符合规则", "提示",
							JOptionPane.INFORMATION_MESSAGE);

					focus = false;
					this.chess.setNext(true);
				}
				
			}else {
				System.out.println("没有动地方或者是没有目的地");
				JOptionPane.showMessageDialog(this.getParent(), "没有动地方或者是没有目的地", "提示",
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
	public int[] getPos(MouseEvent e) { // 获得事件发生坐标
		int[] pos = new int[2];
		pos[0] = -1;
		pos[1] = -1;
		Point p = e.getPoint();// 获得事件发生的坐标点
		double x = p.getX();
		double y = p.getY();
		if (Math.abs((x - 70) % 70) <= 25) {// 获得棋子对应于数组x下标的位置
			pos[0] = Math.round((float) (x - 70)) / 70; // round()加0.5取整
		} else if (Math.abs((x - 70) % 70) >= 35) {
			pos[0] = Math.round((float) (x - 70)) / 70 + 1;
		}
		if (Math.abs((y - 70) % 70) <= 25) {// 获得棋子对应于数组y下标的位置
			pos[1] = Math.round((float) (y - 70)) / 70;
		} else if (Math.abs((y - 70) % 70) >= 35) {
			pos[1] = Math.round((float) (y - 70)) / 70 + 1;
		}
		return pos;
	}
	
	public void noFocus(int i, int j) {// 判断棋子颜色并聚焦
		if (this.ps[i][j] != null)// 如果该位置有棋子
		{
			if (this.chess.getColor() == 0)// 如果是红方
			{
				if (this.ps[i][j].getColor().equals(Color.red))// 如果棋子是红色
				{
					//this.ps[i][j].setFocus(true);// 将该棋子设为选中状态
					focus = true;// 将focus设为true
					startI = i;// 保存该坐标点
					startJ = j;
				}
			} else// 如果是黑
			{
				if (this.ps[i][j].getColor().equals(Color.black))// 如果该棋子是黑色
				{
					//this.ps[i][j].setFocus(true);// 将该棋子设为选中状态
					focus = true;// 将focus设为true
					startI = i;// 保存该坐标点
					startJ = j;
				}
			}
		}
	}
	public void InitPieces(Pieces [][]ps) {
	    ps[0][0] = new Pieces(Color.red,"車",0,0);
	    ps[1][0] = new Pieces(Color.red,"馬",1,0);
	    ps[2][0] = new Pieces(Color.red,"相",2,0);
	    ps[3][0] = new Pieces(Color.red,"仕",3,0);
	    ps[4][0] = new Pieces(Color.red,"帥",4,0);
	    ps[5][0] = new Pieces(Color.red,"仕",5,0);
	    ps[6][0] = new Pieces(Color.red,"相",6,0);
	    ps[7][0] = new Pieces(Color.red,"馬",7,0);
	    ps[8][0] = new Pieces(Color.red,"車",8,0);
	    
	    ps[1][2] = new Pieces(Color.red,"砲",1,2);
	    ps[7][2] = new Pieces(Color.red,"砲",7,2);
	    
	    ps[0][3] = new Pieces(Color.red,"兵",0,3);
	    ps[2][3] = new Pieces(Color.red,"兵",2,3);
	    ps[4][3] = new Pieces(Color.red,"兵",4,3);
	    ps[6][3] = new Pieces(Color.red,"兵",6,3);
	    ps[8][3] = new Pieces(Color.red,"兵",8,3);
	    
	    ps[0][6] = new Pieces(Color.black,"卒",0,6);
	    ps[2][6] = new Pieces(Color.black,"卒",2,6);
	    ps[4][6] = new Pieces(Color.black,"卒",4,6);
	    ps[6][6] = new Pieces(Color.black,"卒",6,6);
	    ps[8][6] = new Pieces(Color.black,"卒",2,7);
	    
	    ps[1][7] = new Pieces(Color.black,"炮",1,7);
	    ps[7][7] = new Pieces(Color.black,"炮",7,7);
	    
	    
	    ps[0][9] = new Pieces(Color.black,"車",0,9);
	    ps[1][9] = new Pieces(Color.black,"馬",1,9);
	    ps[2][9] = new Pieces(Color.black,"象",2,9);
	    ps[3][9] = new Pieces(Color.black,"士",3,9);
	    ps[4][9] = new Pieces(Color.black,"将",4,9);
	    ps[5][9] = new Pieces(Color.black,"士",5,9);
	    ps[6][9] = new Pieces(Color.black,"象",6,9);
	    ps[7][9] = new Pieces(Color.black,"馬",7,9);
	    ps[8][9] = new Pieces(Color.black,"車",8,9);
	    
	}
	//给对方发出获胜信息
	public void WinMessage() {
		this.chess.getCat().getOut().println("获胜信息"+this.chess.getMname().getText()+"->"+this.chess.getOtherSide());
	}
	
	public void movePs(Pieces ps,int x,int y) {
	this.ps[x][y] = ps;
	}
}
