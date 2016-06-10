package com.bishe.server;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ServerUI extends JFrame implements ActionListener{

	private JButton jbStart;
	private JButton jbStop;
	private JTextField jtfPort;
	private JTextField showText;
	private JLabel jlPort;
	//private boolean isStart = false;
	
	public ServerUI() {
		showText = new JTextField();
		jlPort = new JLabel("端口号:");
		//showText.setSize(290, 480);
		jtfPort = new JTextField("8000");
		jbStart = new JButton("启动");
		jbStop = new JButton("关闭");
		setLayout(null);
		showText.setBounds(10, 10, 290, 440);
		jlPort.setBounds(320, 30, 60, 30);
		jtfPort.setBounds(380, 30, 90, 30);
		jbStart.setBounds(320, 70, 70, 30);
		jbStop.setBounds(400, 70, 70, 30);
		add(showText);
		add(jlPort);
		add(jtfPort);
		add(jbStart);
		add(jbStop);
		jbStart.addActionListener(this);
		jbStop.addActionListener(this);
	}
	
	public void jbStart_event() {
		//setStart(true);
		new Server(this).start();
		jbStart.setEnabled(false);
		
	}
	public void jbStop_event() {
		System.out.println("关闭服务器");
		//setStart(false);
		System.exit(0);
	}
	
	public void refreshList() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if((boolean)e.getSource().equals(jbStart)) {
			jbStart_event();
		}
		if((boolean)e.getSource().equals(jbStop)) {
			jbStop_event();
		}
		
	}
	
/*	public boolean isStart() {
		return isStart;
	}
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}*/

	public JButton getJbStart() {
		return jbStart;
	}

	public void setJbStart(JButton jbStart) {
		this.jbStart = jbStart;
	}

	public JButton getJbStop() {
		return jbStop;
	}

	public void setJbStop(JButton jbStop) {
		this.jbStop = jbStop;
	}

	public JTextField getJtfPort() {
		return jtfPort;
	}

	public void setJtfPort(JTextField jtfPort) {
		this.jtfPort = jtfPort;
	}

	public JTextField getShowText() {
		return showText;
	}

	public void setShowText(JTextField showText) {
		this.showText = showText;
	}

	public JLabel getJlPort() {
		return jlPort;
	}

	public void setJlPort(JLabel jlPort) {
		this.jlPort = jlPort;
	}
	
}
