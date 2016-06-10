package com.bishe.client;

import java.awt.Color;

public class Rule {
	private boolean canMove = true;
	private int x;
	private int y;
    public Rule() {
    }
    
    public boolean canMove(int startI,int startJ,int endI,int endJ,String name,Pieces [][]ps) {
    	int maxI = startI > endI ? startI : endI;
    	int minI = startI <= endI ? startI : endI;
    	int maxJ = startJ > endJ ? startJ : endJ;
    	int minJ = startJ <= endJ ? startJ : endJ;
    	if(ps[endI][endJ]!=null&&ps[startI][startJ].getColor()==ps[endI][endJ].getColor()) {
    		return false;
    	}
    	if(name.equals("車")) {
    		ju(maxI, minI, maxJ, minJ,ps);
    	}else if(name.equals("馬")) {
    		ma(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("相")||name.equals("象")) {
    		xiang(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("士")||name.equals("仕")) {
    		shi(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("将")||name.equals("帥")) {
    		jiang(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("炮")||name.equals("砲")) {
    		pao(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("兵")||name.equals("卒")) {
    		bing(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}
		return canMove;
    	
    }
    /**
     * 車的规则
     * @param maxI
     * @param minI
     * @param maxJ
     * @param minJ
     */
	public void ju(int maxI, int minI, int maxJ, int minJ,Pieces [][]ps) {// 对"車"的处理方法
		int j = 0;
		int i = 0;
		if (maxI == minI)// 走竖线
		{
			for (j = minJ + 1; j < maxJ; j++) {
				if (ps[maxI][j] != null)// 如果中间有棋子
				{
					canMove = false;// 不可以走棋
					break;
				}
			}
		} else if (maxJ == minJ)//走横线
		{
			for (i = minJ + 1; i < maxJ; i++) {
				if (ps[i][maxJ] != null)// 如果中间有棋子
				{
					canMove = false;// 不可以走棋
					break;
				}
			}
		} else if (maxI!= minI && maxJ != minJ)// 如果不在同一条线上
		{
			canMove = false;// 不可以走棋
		}
	}
	/**
	 * 馬的规则
	 * @param maxI
	 * @param minI
	 * @param maxJ
	 * @param minJ
	 * @param startI
	 * @param startJ
	 * @param endI
	 * @param endJ
	 */
    
	public void ma(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ,Pieces[][] ps) {// 对"馬"的处理方法
		int a = maxI - minI;
		int b = maxJ - minJ;// 获得两坐标点之间的差
		if (a == 1 && b == 2)// 如果是竖着的"日"字
		{
			if (startJ > endJ)// 如果是从下向上走
			{
				if (ps[startI][startJ - 1] != null)// 如果马腿处有棋子
				{
					canMove = false;// 不可以走
				}
			} else {// 如果是从上往下走
				if (ps[startI][startJ + 1] != null)// 如果马腿处有棋子
				{
					canMove = false;// 不可以走
				}
			}
		} else if (a == 2 && b == 1)// 如果是横着的"日"
		{
			if (startI > endI)// 如果是从右往左走
			{
				if (ps[startI - 1][startJ] != null)// 如果马腿处有棋子
				{
					canMove = false;// 不可以走
				}
			} else {// 如果是从左往右走
				if (ps[startI + 1][startJ] != null)// 如果马腿处有棋子
				{
					canMove = false;// 不可以走
				}
			}
		} else if (!((a == 2 && b == 1) || (a == 1 && b == 2)))
// 如果不时"日"字
		{
			canMove = false;// 不可以走
		}
	}
    /**
     * 相或象的规则
     * @param maxI
     * @param minI
     * @param maxJ
     * @param minJ
     * @param startI
     * @param startJ
     * @param endI
     * @param endJ
     */
    public void xiang(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ,Pieces [][]ps) {// 对"相"的处理
		int a = maxI - minI;
		int b = maxJ - minJ;// 获得X,Y坐标的差
		if (a == 2 && b == 2)// 如果是"田"字
		{
			//判断是否过河
			if(startJ<=4) {//如果是上面的相
			    if (endJ > 4){
				canMove = false;// 不可以走
			}
			    }else {
				if(endJ<5) {
					canMove = false;
				}
			}
			if (ps[(int)((maxI + minI) / 2)][(int)((maxJ + minJ) / 2)] != null)
// 如果"田"字中间有棋子
			{
				canMove = false;// 不可以走
			}
		} else {
			canMove = false;// 如果不是"田"字，不可以走
		}
	}
   /**
    * 士或仕的规则
    * @param maxI
    * @param minI
    * @param maxJ
    * @param minJ
    * @param startI
    * @param startJ
    * @param endI
    * @param endJ
    */
    public void shi(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ,Pieces[][] ps) {
		int a = maxI - minI;
		int b = maxJ - minJ;// 获得X,Y坐标的差
		if (a == 1 && b == 1)// 如果是小斜线
		{
			if (startJ > 4)// 如果是下方的士
			{
				if (endJ < 7) {
					canMove = false;// 如果下方的士越界，不可以走
				}
			} else {// 如果是上方的仕
				if (endJ > 2) {
					canMove = false;// 如果上方的仕越界，不可以走
				}
			}
			if (endI > 5 || endI < 3)// 如果左右越界，则不可以走
			{
				canMove = false;
			}
		} else {// 如果不是小斜线
			canMove = false;// 不可以走
		}
	}
    /**
     * 将或帥的规则
     * @param maxI
     * @param minI
     * @param maxJ
     * @param minJ
     * @param startI
     * @param startJ
     * @param endI
     * @param endJ
     */
    public void jiang(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ,Pieces[][] ps) {// 对"帥"、"將"的处理
		int a = maxI - minI;
		int b = maxJ - minJ;// 获得X,Y坐标的差
		if ((a == 1 && b == 0) || (a == 0 && b == 1)) {// 如果走的是一小格
			if (startJ > 4)// 如果是下方的将
			{
				if (endJ < 7)// 如果越界
				{
					canMove = false;// 不可以走
				}
			} else {// 如果是上方的将
				if (endJ > 2)// 如果越界
				{
					canMove = false;// 不可以走
				}
			}
			if (endI > 5 || endI < 3)// 如果左右越界，不可以走
			{
				canMove = false;
			}
		} else {// 如果走的不是一格，不可以走
			canMove = false;
		}
	}
    /**
     * 炮的规则
     * @param maxI
     * @param minI
     * @param maxJ
     * @param minJ
     * @param startI
     * @param startJ
     * @param endI
     * @param endJ
     */
    
    public void pao(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ,Pieces[][] ps) {// 对"炮"、"砲"的处理
    	int j = 0;
    	int i = 0;
		if (maxI == minI)// 如果走的竖线
		{
			if (ps[endI][endJ] != null)// 如果终点有棋子
			{
				int count = 0;//计间隔的棋子数
				for (j = minJ + 1; j < maxJ; j++) {
					if (ps[minI][j] != null)// 判断起点与终点之间有几个棋子
					{
						count++;
					}
				}
				if (count != 1) {// 如果不止一个棋子
					canMove = false;// 不可以走
				}
			} else if (ps[endI][endJ] == null)// 如果终点没有棋子
			{
				for (j = minJ + 1; j < maxJ; j++) {
					if (ps[minI][j] != null)// 如果起止点之间有棋子
					{
						canMove = false;// 不可以走
						break;
					}
				}
			}
		} else if (maxJ == minJ)// 如果走的是横线
		{
			if (ps[endI][endJ] != null)// 如果终点有棋子
			{
				int count = 0;
				for (i = minI + 1; i < maxI; i++) {
					if (ps[i][minJ] != null)// 判断起点与终点之间有几个棋子
					{
						count++;
					}
				}
				if (count != 1)// 如果不止一个棋子
				{
					canMove = false;// 不可以走
				}
			} else if (ps[endI][endJ] == null)// 如果终点没有棋子
			{
				for (i = minI + 1; i < maxI; i++) {
					if (ps[i][minJ] != null)// 如果起止点之间有棋子
					{
						canMove = false;// 不可以走
						break;
					}
				}
			}
		} else if (maxJ != minJ && maxI != minI) {
// 如果走的既不是竖线，也不是横线，则不可以走
			canMove = false;
		}
	}
    /**
     * 兵或卒的规则
     * @param maxI
     * @param minI
     * @param maxJ
     * @param minJ
     * @param startI
     * @param startJ
     * @param endI
     * @param endJ
     */
	public void bing(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ,Pieces [][]ps) {// 对"兵"的处理
		if(ps[startI][startJ].getColor()==Color.red) {
		if (startJ < 5)// 如果还没有过河
		{
			if (startI != endI)// 如果不是向前走，横左边变化
			{
				canMove = false;// 不可以走
			}
			if (endJ - startJ != 1)// 如果走的不是一格
			{
				canMove = false;// 不可以走
			}
		} else {// 如果已经过河
			if (startI == endI) {// 如果走的是竖线，横坐标相同
				if (endJ - startJ != 1)// 如果走的不是一格
				{
					canMove = false;// 不可以走
				}
			} else if (startJ == endJ) {// 如果走的是横线
				if (maxI - minI != 1) {// 如果走的不是一格
					canMove = false;// 不可以走
				}
			} else if (startI != endI && startJ != endJ) {
// 如果走的既不是竖线，也不是横线，则不可以走
				canMove = false;
			}
		}
		}
		if(ps[startI][startJ].getColor()==Color.black) {
			if (startJ >= 5)// 如果还没有过河
			{
				if (startI != endI)// 如果不是向前走，横左边变化
				{
					canMove = false;// 不可以走
				}
				if (startJ - endJ != 1)// 如果走的不是一格
				{
					canMove = false;// 不可以走
				}
			} else {// 如果已经过河
				if (startI == endI) {// 如果走的是竖线，横坐标相同
					if (startJ -endJ  != 1)// 如果走的不是一格
					{
						canMove = false;// 不可以走
					}
				} else if (startJ == endJ) {// 如果走的是横线
					if (maxI - minI != 1) {// 如果走的不是一格
						canMove = false;// 不可以走
					}
				} else if (startI != endI && startJ != endJ) {
	// 如果走的既不是竖线，也不是横线，则不可以走
					canMove = false;
				}
			}
		}
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/*public Pieces[][] getPs() {
		return ps;
	}

	public void setPs(Pieces[][] ps) {
		this.ps = ps;
	}
	public void movePs(Pieces ps,int x,int y) {
		this.ps[x][y] = ps;
	}*/
	
}
