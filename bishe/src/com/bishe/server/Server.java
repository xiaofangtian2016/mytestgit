package com.bishe.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import com.bishe.client.Chess;
import com.pub.tool.SwingConsole;

public class Server extends Thread{
	private ServerUI serverui;
	//private boolean flag;
	private ServerSocket ss;
	private String ipStr = "";
	private List<ServerAgentThread> serverthread = new ArrayList<ServerAgentThread>();
	private int count = 0;
	
	public Server(ServerUI serverui) {
		this.serverui = serverui;
	}
	public void run() {
		try {
			ss=new ServerSocket(8000);//�����˷���������Socket����
			System.out.println("���������������ȴ��ͻ�����.....");
			while(true){
				Socket socket=ss.accept();//�ȴ��Է������ӣ�ֱ���������󵽴�ʱ�ŷ���
				ipStr = ipStr +"/�û�"+count;
				if(count%2==0) {
					ipStr = ipStr+"��";
				}else {
					ipStr = ipStr +"��";
				}
				System.err.println("�������ߵ��û���"+ipStr);
				serverui.getShowText().setText(ipStr);
				ServerAgentThread currserverthread = new ServerAgentThread(serverui,socket,this,count);
				serverthread.add(currserverthread);
				//�����пͻ��˹㲥��ǰ�����û�
				Socket temps=null;
				PrintWriter os;
				for(Iterator <ServerAgentThread>iter = serverthread.iterator();iter.hasNext();){       
					temps=(Socket)(iter.next().getSc());        
					os=new PrintWriter(temps.getOutputStream());        
					os.println("��ǰ�����û���"+ipStr);       
					os.flush();
					}
				currserverthread.start();
				count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ServerUI getServerui() {
		return serverui;
	}
	public void setServerui(ServerUI serverui) {
		this.serverui = serverui;
	}
/*	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}*/
	public ServerSocket getSs() {
		return ss;
	}
	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}
	public String getIpStr() {
		return ipStr;
	}
	public void setIpStr(String ipStr) {
		this.ipStr = ipStr;
	}
	
	public List<ServerAgentThread> getServerthread() {
		return serverthread;
	}

	public void setServerthread(List<ServerAgentThread> serverthread) {
		this.serverthread = serverthread;
	}

	public ServerAgentThread getByname(String name) {
		for(Iterator <ServerAgentThread>iter = serverthread.iterator();iter.hasNext();) {
			ServerAgentThread currthread = iter.next();
			if(currthread.getThreadName().equals(name))
				return currthread;
		}
		return null;
		
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public static void main(String[] args) {
		JFrame serverui = new ServerUI();
		serverui.setVisible(true);
		serverui.setSize(500, 500);
		
	}

}
