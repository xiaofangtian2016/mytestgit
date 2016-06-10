package com.bishe.client;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.bishe.server.ServerAgentThread;
import com.pub.tool.SwingConsole;

public class Chess extends JFrame implements ActionListener,KeyListener{
	
	private ClientAgentThread cat;
	private int color;//0������췽1������ڷ�.
	private ChessBoard chessboard;
	private Rule rule;
	private boolean next;//������һ����ɲ�������
	private boolean sign;
	
	private JButton jbChallenge;
	private JButton jbAccChallenge;
	private JButton jbRefChallenge;
	private JButton jbConnect;
	private JButton jbBreak;
	private JTextField localhost;
	private JTextField Mname;
	private JComboBox playername;
	private JTextField jtfPort;
	
	private JLabel jlPort;
	private JLabel jllocalhost;
	private JLabel jlplayername;
	private JLabel jlMname;
	private JLabel jlchglist;
	
	private String otherSide;
	private JComboBox chgList;
	
	private JButton jbhelp;
	private JLabel jlshowhelp = new JLabel();
	
	private JTextArea jtChat;
	private JTextArea jtMessage;
	private JButton jbNewGame;
	//private JButton jbSend;
	private JScrollPane sp;
	
	public Chess() {
		sign = false;
		rule = new Rule();
		chessboard = new ChessBoard(this);
		jbChallenge = new JButton("������ս");
		jbAccChallenge = new JButton("������ս");
		jbRefChallenge = new JButton("�ܾ���ս");
		jbBreak = new JButton("�Ͽ�");
		jbConnect = new JButton("����");
		Mname = new JTextField("�ǳ�");
		Mname.setEditable(false);
		playername = new JComboBox();
		chgList = new JComboBox();
		jtfPort = new JTextField("8000");
		jtfPort.setEditable(false);
		
		jbhelp = new JButton("����");
		jlshowhelp = new JLabel();
		jlshowhelp.setText("<html><head></head><body>��ӭʹ�ñ�ϵͳ!<p>ע�⣺ÿһ�κ췽���ߣ�������Ϣ���»س�����</p><p>����������£�</p><p>��1�� ����˧��������˧�����е����ԣ���˫�����������Ŀ�ꡣ��ֻ���ڡ��Ź���֮�ڻ�����Ͽ��£�������ң�ÿ���߶�ֻ�ܰ����ߺͺ����߶�һ�񡣽���˧������ͬһ������ֱ����ԣ��������߷����塣 </p><p>��2�� ʿ���ˣ���ʿ���ˣ��ǽ���˧���������ڣ���Ҳֻ���ڡ��Ź������߶�����������·��ֻ���ǡ��Ź����ڵ�б�ߡ�</p> <p>��3�����ࣩ�����ࣩ����Ҫ�����Ƿ��أ������Լ��Ľ���˧���������߷���ÿ��ѭ�Խ����������׳ơ�����������ࣩ�Ļ��Χ���ڡ��ӽ硱���ڵı�����أ����ܹ��ӣ���������ߵġ����������һ�����ӵ�ʱ�򣬾Ͳ����ߣ��׳ơ������ۡ���</p><p>��4�� ������������������������ۺ��ߡ����߾������ߣ�ֻҪ�������������������ơ����һ�����Կ���ʮ�߸��㣬���С�һ��ʮ�Ӻ���֮�ơ�</p><p>��5�� �ڣ����ڲ����ӵ�ʱ���߶��복��ȫ��ͬ��</p><p>��6�� �������߶��ķ�����һֱһб�����Ⱥ��Ż�������һ��Ȼ����б����һ���Խ��ߣ��׳ơ������ա�����һ�ο��ߵ�ѡ�����Դﵽ���ܵİ˸��㡣���С��������硱֮˵�������Ҫȥ�÷����б�����ӵ�ס������޷��߹�ȥ���׳ơ������ȡ���</p><p>��7�� �䣨�����������䣩��δ����ǰ��ֻ����ǰһ�����ߣ������Ժ󣬳����ܺ����⣬���������ƶ�����Ҳֻ��һ��һ������ʹ�����������䣩������Ҳ�����ǿ�����С����ӵ����Ӷ��������֮˵��</p></body></html>");
		jtChat = new JTextArea();
		jtMessage = new JTextArea();
		sp = new JScrollPane(jtMessage);
		jtMessage.setEditable(false);
		jbNewGame = new JButton("����һ��");
		
		String strlocalhost = null;
		try {
			strlocalhost = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.err.println("��ȡ������ַ�쳣");
			e.printStackTrace();
		}
		localhost = new JTextField(strlocalhost.substring(strlocalhost.indexOf('/')));
		localhost.setEditable(false);
		jlPort = new JLabel("�˿ں�:");
		jlMname = new JLabel("�ǳ�:");
		jllocalhost = new JLabel("������:");
		jlplayername = new JLabel("���:");
		jlchglist = new JLabel("��ս��:");
		
		setLayout(null);
		chessboard.setBounds(10, 10, 800, 1000);
		localhost.setBounds(810, 10, 140, 30);
		jtfPort.setBounds(810, 50, 140, 30);
		Mname.setBounds(810, 90, 140, 30);
		jbConnect.setBounds(810, 130, 65, 30);
		jbBreak.setBounds(880, 130, 65, 30);
		playername.setBounds(810, 170, 140, 30);
		jbChallenge.setBounds(810, 210, 140, 30);
		jbAccChallenge.setBounds(810, 250, 140, 30);
		jbRefChallenge.setBounds(810, 290, 140, 30);
		jlchglist.setBounds(750,330,140,30);
		chgList.setBounds(810,330,140,30);
		
		jbhelp.setBounds(810, 370, 140, 30);
		jbNewGame.setBounds(810, 410, 140, 30);
		
		jtChat.setBounds(810, 450, 140, 60);
		sp.setBounds(810, 530, 140, 100);
		     
		jllocalhost.setBounds(750, 10, 50, 30);
		jlPort.setBounds(750, 50, 50, 30);
		jlMname.setBounds(750, 90, 50, 30);
		jlplayername.setBounds(750, 170, 50, 30);
		
		jbChallenge.addActionListener(this);
		jbAccChallenge.addActionListener(this);
		jbRefChallenge.addActionListener(this);
		jbBreak.addActionListener(this);
		jbConnect.addActionListener(this);
		jbhelp.addActionListener(this);
		jbNewGame.addActionListener(this);
		jtChat.addKeyListener(this);
		
		//��ʼ��ʱ�������ӣ�������ť��Ϊ�����á�
		jbChallenge.setEnabled(false);
		jbBreak.setEnabled(false);
		jbAccChallenge.setEnabled(false);
		jbRefChallenge.setEnabled(false);
		jbNewGame.setEnabled(false);
		jtChat.setEditable(false);
		
		add(chessboard);
		add(localhost);
		add(jtfPort);
		add(Mname);
		add(jbConnect);
		add(jbBreak);
		add(playername);
		add(jbChallenge);
		add(jbAccChallenge);
		add(jbRefChallenge);
		
		add(jllocalhost);
		add(jlPort);
		add(jlMname);
		add(jlplayername);
		
		add(jlchglist);
		add(chgList);
		
		add(jbhelp);
		add(jbNewGame);
		add(jtChat);
		add(sp);
		add(sp);
		setVisible(true);
		
		
		
	}
	public void jbChallenge_event() {
		String str;
		str =(String)this.getPlayername().getSelectedItem();
		if(str==null||str.equals("")) {
			JOptionPane.showMessageDialog(this, "û��ѡ�����", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(str.equals(this.Mname.getText())) {
			JOptionPane.showMessageDialog(this, "�������Լ�������ս", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if((this.color==0&&str.endsWith("��"))||(this.color==1&&str.endsWith("��"))) {
			JOptionPane.showMessageDialog(this, "������ͬɫ��ҷ�����ս", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		this.getCat().getOut().println("��ս��"+this.Mname.getText()+"->"+str);
		
	}
	
	public void jbAccChallenge_event() {
		otherSide = (String)this.chgList.getSelectedItem();
		if(otherSide==null||otherSide.equals("")) {
			JOptionPane.showMessageDialog(this, "û��ѡ����ս��", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		sign = true;
		jbNewGame.setEnabled(true);
		jtChat.setEditable(true);
		this.getCat().getOut().println("������ս��Ϣ"+this.Mname.getText()+"->"+otherSide);
		jbAccChallenge.setEnabled(false);
		for(int i = 0 ; i<chgList.getItemCount() ; i++) {
			if(((String)chgList.getItemAt(i)).equals(otherSide)){
				continue;
			}
			this.getCat().getOut().println("�ܾ���ս��Ϣ"+this.Mname.getText()+"->"+(String)chgList.getItemAt(i));
		}
		playername.setEnabled(false);
		chgList.setEnabled(false);
		jbAccChallenge.setEnabled(false);
		jbChallenge.setEnabled(false);
		jbRefChallenge.setEnabled(false);
		
	}
	
	public void jbRefChallenge_event() {
		int delIndex = 0;
		String str = (String)this.chgList.getSelectedItem();
		if(str==null||str.equals("")) {
			JOptionPane.showMessageDialog(this, "û��ѡ����ս��", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		for(int i = 0;i<this.getChgList().getItemCount();i++) {
			if(this.getChgList().getItemAt(i)!=null&&((String)(this.getChgList().getItemAt(i))).equals(str))
			delIndex = i;
		}
		this.getChgList().removeItemAt(delIndex);
		this.getCat().getOut().println("�ܾ���ս��Ϣ"+this.Mname.getText()+"->"+str);
	}
	
	public void jbBreak_event() {
		String strlocalhost = null;
		this.getCat().getOut().println("�����û�:"+this.Mname.getText());
		//System.exit(0);
		this.getCat().getOut().println("bye");
		jbConnect.setEnabled(false);
		jbChallenge.setEnabled(false);
		jbBreak.setEnabled(false);
		jbAccChallenge.setEnabled(false);
		jbRefChallenge.setEnabled(false);
		playername.setEnabled(false);
		chgList.setEnabled(false);
	}
	
	public void jbConnect_event() {
		cat = new ClientAgentThread(this);
		cat.start();
		jbConnect.setEnabled(false);
		jbChallenge.setEnabled(true);
		jbBreak.setEnabled(true);
		jbAccChallenge.setEnabled(true);
		jbRefChallenge.setEnabled(true);
	}
	
	public void jbhelp_event() {
		 JDialog showhelp = new JDialog();
		 Point p = new Point(200,200);
		 showhelp.add(jlshowhelp);
		 showhelp.setSize(600,600);
		 showhelp.setVisible(true);
		 showhelp.setLocation(p);
	}
	
	public void jbNewGame_event() {
		int i = 0;
		int j = 0;
		for(i = 0 ; i < 9 ;i++) {
			for(j = 0 ;j <10 ; j++) {
				this.chessboard.getPs()[i][j] = null;
			}
		}
		this.chessboard.InitPieces(this.chessboard.getPs());
		this.chessboard.repaint();
		this.repaint();
		setOtherSide(null);
		this.getChgList().removeAllItems();
		this.setSign(false);
		this.getJtChat().setEditable(false);
		this.getJtMessage().setText("");
		jbChallenge.setEnabled(true);
		jbAccChallenge.setEnabled(true);
		jbRefChallenge.setEnabled(true);
		playername.setEnabled(true);
		chgList.setEnabled(true);
		
	}
	
	public void jbSend_event() {
		String message = jtChat.getText();
		this.getJtMessage().append(this.getMname().getText()+":"+message+"\r\n");
		this.getCat().getOut().println(this.getMname().getText()+"->"+this.getOtherSide()+"message"+message);
		jtChat.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if((boolean)e.getSource().equals(jbChallenge)) {
			jbChallenge_event();
		}
		if((boolean)e.getSource().equals(jbAccChallenge)) {
			jbAccChallenge_event();
		}
		if((boolean)e.getSource().equals(jbRefChallenge)) {
			jbRefChallenge_event();
		}
		if((boolean)e.getSource().equals(jbBreak)) {
			jbBreak_event();
		}
		if((boolean)e.getSource().equals(jbConnect)) {
			jbConnect_event();
		}
		if((boolean)e.getSource().equals(jbhelp)) {
			jbhelp_event();
		}
		if((boolean)e.getSource().equals(jbNewGame)) {
			jbNewGame_event();
		}
		
	}
	public ClientAgentThread getCat() {
		return this.cat;
	}
	public void setCat(ClientAgentThread cat) {
		this.cat = cat;
	}
	public ChessBoard getChessboard() {
		return chessboard;
	}
	public void setChessboard(ChessBoard chessboard) {
		this.chessboard = chessboard;
	}
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public JButton getJbChallenge() {
		return jbChallenge;
	}
	public void setJbChallenge(JButton jbChallenge) {
		this.jbChallenge = jbChallenge;
	}
	public JButton getJbAccChallenge() {
		return jbAccChallenge;
	}
	public void setJbAccChallenge(JButton jbAccChallenge) {
		this.jbAccChallenge = jbAccChallenge;
	}
	public JButton getJbRefChallenge() {
		return jbRefChallenge;
	}
	public void setJbRefChallenge(JButton jbRefChallenge) {
		this.jbRefChallenge = jbRefChallenge;
	}
	public JButton getJbConnect() {
		return jbConnect;
	}
	public void setJbConnect(JButton jbConnect) {
		this.jbConnect = jbConnect;
	}
	public JButton getJbBreak() {
		return jbBreak;
	}
	public void setJbBreak(JButton jbBreak) {
		this.jbBreak = jbBreak;
	}
	public JTextField getLocalhost() {
		return localhost;
	}
	public void setLocalhost(JTextField localhost) {
		this.localhost = localhost;
	}
	public JTextField getMname() {
		return Mname;
	}
	public void setMname(JTextField mname) {
		Mname = mname;
	}
	public JComboBox getPlayername() {
		return playername;
	}
	public void setPlayername(JComboBox playername) {
		this.playername = playername;
	}
	public JTextField getJtfPort() {
		return jtfPort;
	}
	public void setJtfPort(JTextField jtfPort) {
		this.jtfPort = jtfPort;
	}
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	
	public JLabel getJlchglist() {
		return jlchglist;
	}
	public void setJlchglist(JLabel jlchglist) {
		this.jlchglist = jlchglist;
	}
	
	
	public JComboBox getChgList() {
		return chgList;
	}
	public void setChgList(JComboBox chgList) {
		this.chgList = chgList;
	}
	
	
	public String getOtherSide() {
		return otherSide;
	}
	public void setOtherSide(String otherSide) {
		this.otherSide = otherSide;
	}
	
	
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public boolean isSign() {
		return sign;
	}
	public void setSign(boolean sign) {
		this.sign = sign;
	}
	public JButton getJbNewGame() {
		return jbNewGame;
	}
	public void setJbNewGame(JButton jbNewGame) {
		this.jbNewGame = jbNewGame;
	}
	
	public JTextArea getJtChat() {
		return jtChat;
	}
	public void setJtChat(JTextArea jtChat) {
		this.jtChat = jtChat;
	}
	
	public JTextArea getJtMessage() {
		return jtMessage;
	}
	public void setJtMessage(JTextArea jtMessage) {
		this.jtMessage = jtMessage;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 10){
			String message = jtChat.getText();
			if(message != null) {
			this.getJtMessage().append(this.getMname().getText()+":"+message+"\r\n");
			this.getCat().getOut().println(this.getMname().getText()+"->"+this.getOtherSide()+"message"+message);
			jtChat.setText("");
			}
			jtChat.setCaretPosition(0);
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		SwingConsole.run(new Chess(), 1000, 1100);
	}
	
}
