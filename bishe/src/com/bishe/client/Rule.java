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
    	if(name.equals("܇")) {
    		ju(maxI, minI, maxJ, minJ,ps);
    	}else if(name.equals("�R")) {
    		ma(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("��")||name.equals("��")) {
    		xiang(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("ʿ")||name.equals("��")) {
    		shi(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("��")||name.equals("��")) {
    		jiang(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("��")||name.equals("�h")) {
    		pao(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}else if(name.equals("��")||name.equals("��")) {
    		bing(maxI, minI, maxJ, minJ, startI, startJ, endI, endJ,ps);
    	}
		return canMove;
    	
    }
    /**
     * ܇�Ĺ���
     * @param maxI
     * @param minI
     * @param maxJ
     * @param minJ
     */
	public void ju(int maxI, int minI, int maxJ, int minJ,Pieces [][]ps) {// ��"܇"�Ĵ�����
		int j = 0;
		int i = 0;
		if (maxI == minI)// ������
		{
			for (j = minJ + 1; j < maxJ; j++) {
				if (ps[maxI][j] != null)// ����м�������
				{
					canMove = false;// ����������
					break;
				}
			}
		} else if (maxJ == minJ)//�ߺ���
		{
			for (i = minJ + 1; i < maxJ; i++) {
				if (ps[i][maxJ] != null)// ����м�������
				{
					canMove = false;// ����������
					break;
				}
			}
		} else if (maxI!= minI && maxJ != minJ)// �������ͬһ������
		{
			canMove = false;// ����������
		}
	}
	/**
	 * �R�Ĺ���
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
			int startJ, int endI, int endJ,Pieces[][] ps) {// ��"�R"�Ĵ�����
		int a = maxI - minI;
		int b = maxJ - minJ;// ����������֮��Ĳ�
		if (a == 1 && b == 2)// ��������ŵ�"��"��
		{
			if (startJ > endJ)// ����Ǵ���������
			{
				if (ps[startI][startJ - 1] != null)// ������ȴ�������
				{
					canMove = false;// ��������
				}
			} else {// ����Ǵ���������
				if (ps[startI][startJ + 1] != null)// ������ȴ�������
				{
					canMove = false;// ��������
				}
			}
		} else if (a == 2 && b == 1)// ����Ǻ��ŵ�"��"
		{
			if (startI > endI)// ����Ǵ���������
			{
				if (ps[startI - 1][startJ] != null)// ������ȴ�������
				{
					canMove = false;// ��������
				}
			} else {// ����Ǵ���������
				if (ps[startI + 1][startJ] != null)// ������ȴ�������
				{
					canMove = false;// ��������
				}
			}
		} else if (!((a == 2 && b == 1) || (a == 1 && b == 2)))
// �����ʱ"��"��
		{
			canMove = false;// ��������
		}
	}
    /**
     * �����Ĺ���
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
			int startJ, int endI, int endJ,Pieces [][]ps) {// ��"��"�Ĵ���
		int a = maxI - minI;
		int b = maxJ - minJ;// ���X,Y����Ĳ�
		if (a == 2 && b == 2)// �����"��"��
		{
			//�ж��Ƿ����
			if(startJ<=4) {//������������
			    if (endJ > 4){
				canMove = false;// ��������
			}
			    }else {
				if(endJ<5) {
					canMove = false;
				}
			}
			if (ps[(int)((maxI + minI) / 2)][(int)((maxJ + minJ) / 2)] != null)
// ���"��"���м�������
			{
				canMove = false;// ��������
			}
		} else {
			canMove = false;// �������"��"�֣���������
		}
	}
   /**
    * ʿ���˵Ĺ���
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
		int b = maxJ - minJ;// ���X,Y����Ĳ�
		if (a == 1 && b == 1)// �����Сб��
		{
			if (startJ > 4)// ������·���ʿ
			{
				if (endJ < 7) {
					canMove = false;// ����·���ʿԽ�磬��������
				}
			} else {// ������Ϸ�����
				if (endJ > 2) {
					canMove = false;// ����Ϸ�����Խ�磬��������
				}
			}
			if (endI > 5 || endI < 3)// �������Խ�磬�򲻿�����
			{
				canMove = false;
			}
		} else {// �������Сб��
			canMove = false;// ��������
		}
	}
    /**
     * ���򎛵Ĺ���
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
			int startJ, int endI, int endJ,Pieces[][] ps) {// ��"��"��"��"�Ĵ���
		int a = maxI - minI;
		int b = maxJ - minJ;// ���X,Y����Ĳ�
		if ((a == 1 && b == 0) || (a == 0 && b == 1)) {// ����ߵ���һС��
			if (startJ > 4)// ������·��Ľ�
			{
				if (endJ < 7)// ���Խ��
				{
					canMove = false;// ��������
				}
			} else {// ������Ϸ��Ľ�
				if (endJ > 2)// ���Խ��
				{
					canMove = false;// ��������
				}
			}
			if (endI > 5 || endI < 3)// �������Խ�磬��������
			{
				canMove = false;
			}
		} else {// ����ߵĲ���һ�񣬲�������
			canMove = false;
		}
	}
    /**
     * �ڵĹ���
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
			int startJ, int endI, int endJ,Pieces[][] ps) {// ��"��"��"�h"�Ĵ���
    	int j = 0;
    	int i = 0;
		if (maxI == minI)// ����ߵ�����
		{
			if (ps[endI][endJ] != null)// ����յ�������
			{
				int count = 0;//�Ƽ����������
				for (j = minJ + 1; j < maxJ; j++) {
					if (ps[minI][j] != null)// �ж�������յ�֮���м�������
					{
						count++;
					}
				}
				if (count != 1) {// �����ֹһ������
					canMove = false;// ��������
				}
			} else if (ps[endI][endJ] == null)// ����յ�û������
			{
				for (j = minJ + 1; j < maxJ; j++) {
					if (ps[minI][j] != null)// �����ֹ��֮��������
					{
						canMove = false;// ��������
						break;
					}
				}
			}
		} else if (maxJ == minJ)// ����ߵ��Ǻ���
		{
			if (ps[endI][endJ] != null)// ����յ�������
			{
				int count = 0;
				for (i = minI + 1; i < maxI; i++) {
					if (ps[i][minJ] != null)// �ж�������յ�֮���м�������
					{
						count++;
					}
				}
				if (count != 1)// �����ֹһ������
				{
					canMove = false;// ��������
				}
			} else if (ps[endI][endJ] == null)// ����յ�û������
			{
				for (i = minI + 1; i < maxI; i++) {
					if (ps[i][minJ] != null)// �����ֹ��֮��������
					{
						canMove = false;// ��������
						break;
					}
				}
			}
		} else if (maxJ != minJ && maxI != minI) {
// ����ߵļȲ������ߣ�Ҳ���Ǻ��ߣ��򲻿�����
			canMove = false;
		}
	}
    /**
     * ������Ĺ���
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
			int startJ, int endI, int endJ,Pieces [][]ps) {// ��"��"�Ĵ���
		if(ps[startI][startJ].getColor()==Color.red) {
		if (startJ < 5)// �����û�й���
		{
			if (startI != endI)// ���������ǰ�ߣ�����߱仯
			{
				canMove = false;// ��������
			}
			if (endJ - startJ != 1)// ����ߵĲ���һ��
			{
				canMove = false;// ��������
			}
		} else {// ����Ѿ�����
			if (startI == endI) {// ����ߵ������ߣ���������ͬ
				if (endJ - startJ != 1)// ����ߵĲ���һ��
				{
					canMove = false;// ��������
				}
			} else if (startJ == endJ) {// ����ߵ��Ǻ���
				if (maxI - minI != 1) {// ����ߵĲ���һ��
					canMove = false;// ��������
				}
			} else if (startI != endI && startJ != endJ) {
// ����ߵļȲ������ߣ�Ҳ���Ǻ��ߣ��򲻿�����
				canMove = false;
			}
		}
		}
		if(ps[startI][startJ].getColor()==Color.black) {
			if (startJ >= 5)// �����û�й���
			{
				if (startI != endI)// ���������ǰ�ߣ�����߱仯
				{
					canMove = false;// ��������
				}
				if (startJ - endJ != 1)// ����ߵĲ���һ��
				{
					canMove = false;// ��������
				}
			} else {// ����Ѿ�����
				if (startI == endI) {// ����ߵ������ߣ���������ͬ
					if (startJ -endJ  != 1)// ����ߵĲ���һ��
					{
						canMove = false;// ��������
					}
				} else if (startJ == endJ) {// ����ߵ��Ǻ���
					if (maxI - minI != 1) {// ����ߵĲ���һ��
						canMove = false;// ��������
					}
				} else if (startI != endI && startJ != endJ) {
	// ����ߵļȲ������ߣ�Ҳ���Ǻ��ߣ��򲻿�����
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
