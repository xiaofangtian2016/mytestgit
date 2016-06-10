package com.bishe.client;

import java.awt.Color;

public class Pieces {
	private Color color;
	private boolean focus;
	private String name;
	int x;
	int y;
	public  Pieces(Color color,String name,int x,int y) {
		this.color = color;
		this.name = name;
		this.x = x;
		this.y = y;
		this.focus = false;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean getFocus() {
		return focus;
	}
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	

}
