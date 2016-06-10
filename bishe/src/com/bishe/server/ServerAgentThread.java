package com.bishe.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

public class ServerAgentThread extends Thread{
	private Socket sc;
	//private boolean flag;
	private BufferedReader in; 
	private PrintWriter out; 
	private ServerUI serverui;
	private String threadName;
	private Server server;
	private int Namecount;
	
	public ServerAgentThread(ServerUI serverui,Socket socket,Server server,int count) {
		this.serverui = serverui;
		this.sc = socket;
		this.Namecount = count;
		if(Namecount%2==0) {
		    this.threadName = "�û�"+count+"��";
		}else {
			this.threadName = "�û�"+count+"��";
		}
		this.server = server;
		try {
			in = new BufferedReader(new InputStreamReader(sc.getInputStream(), "GB2312")); 
			out = new PrintWriter(sc.getOutputStream(), true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void run () {
		String line;
		try {
			line = in.readLine();
			while (!line.contains("bye")) { 
				//����ͻ��˵�һ�θ�������������Ϣ�����������û�����Ϣ����ɫ��Ϣ
				if(line.equals("hello")) {
					for(Iterator <ServerAgentThread>iter = this.server.getServerthread().iterator();iter.hasNext();) {
						if(iter.next()==this) {
						out.println("�û�������ɫ�ֱ���:"+this.Namecount+":"+(this.Namecount)%2);
					}
				}
			}
			//������ת����ս˫����������Ϣ	
            if(line.contains("������Ϣ")){
            	String []xx = line.split("#"); 
            	String []yy = xx[1].split("->");
            	this.server.getByname(yy[1]).getOut().println(line);
            }
			//����������û����Ƚ����̴߳��߳�������ȥ����Ȼ��㲥�����пͻ���
			if(line.contains("�����û�:")) {
				//ȥ�����û��߳�
				ServerAgentThread delserverthread = this.server.getByname(this.threadName);
				this.server.getServerthread().remove(delserverthread);
				//�㲥
				Socket temps=null;
				PrintWriter os;
				for(Iterator <ServerAgentThread>iter = this.server.getServerthread().iterator();iter.hasNext();){       
					temps=(Socket)(iter.next().getSc());        
					os=new PrintWriter(temps.getOutputStream());        
					os.println(line);       
					os.flush();
					}
				String []xx = line.split(":");
				String []yy = this.server.getIpStr().split("/"+xx[1]);
				if(yy.length>0) {
				if(this.server.getIpStr().startsWith("/"+xx[1])) {
					this.server.setIpStr(yy[1]);
				}else if(this.server.getIpStr().endsWith("/"+xx[1])){
					this.server.setIpStr(yy[0]);
				}else {
					this.server.setIpStr(yy[0]+yy[1]);
				}
				}else {
					this.server.setIpStr("");
				}
				
				this.server.getServerui().getShowText().removeAll();
				this.server.getServerui().getShowText().setText(this.server.getIpStr());
			}
			//�����������ͻ��˷�����ս��Ϣ
			if(line.contains("��ս��")) {
				String []xx = line.split("��");
				String []yy = xx[1].split("->");
				this.server.getByname(yy[1]).getOut().println(yy[0]+"���㷢����ս��Ϣ");
			}
			//�����������ͻ��˷��ͽ�����ս��Ϣ
			if(line.contains("������ս��Ϣ")) {
				String []xx = line.split("Ϣ");
				String []yy = xx[1].split("->");
				this.server.getByname(yy[1]).getOut().println(line);
			}
			//�����������ͻ��˷��;ܾ���ս��Ϣ
			if(line.contains("�ܾ���ս��Ϣ")) {
				String []xx = line.split("Ϣ");
				String []yy = xx[1].split("->");
				this.server.getByname(yy[1]).getOut().println(line);
			}
			//�����������ͻ��˷��ͻ�ʤ��Ϣ
			if(line.contains("��ʤ��Ϣ")) {
				String []xx = line.split("Ϣ");
				String []yy = xx[1].split("->");
				this.server.getByname(yy[1]).getOut().println("�����ʧ���ˣ�");
			}
			//�����������ͻ��˷���������Ϣ
			if(line.contains("message")) {
				String []xx = line.split("message");
				String []yy = xx[0].split("->");
				this.server.getByname(yy[1]).getOut().println("message"+yy[0]+":"+xx[1]);
			}
			line = in.readLine(); 
			} 
			out.println("bye"); 
			sc.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void agree(String str) { 
		
	}
	
	public void refuse(String str) {
		
	}
	
	public void challenge(String str) {
		
	}
	
	public void client_leave(String str) {
		
	}
	
	public void count(String str) {
		
	}
	
	public Socket getSc() {
		return sc;
	}
	public void setSc(Socket sc) {
		this.sc = sc;
	}
/*	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}*/
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public ServerUI getServerui() {
		return serverui;
	}
	public void setServerui(ServerUI serverui) {
		this.serverui = serverui;
	}
    
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
}
