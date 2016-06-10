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
		    this.threadName = "用户"+count+"红";
		}else {
			this.threadName = "用户"+count+"黑";
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
				//如果客户端第一次给服务器发送信息服务器返回用户名信息和颜色信息
				if(line.equals("hello")) {
					for(Iterator <ServerAgentThread>iter = this.server.getServerthread().iterator();iter.hasNext();) {
						if(iter.next()==this) {
						out.println("用户名和颜色分别是:"+this.Namecount+":"+(this.Namecount)%2);
					}
				}
			}
			//服务器转发对战双方的坐标信息	
            if(line.contains("坐标信息")){
            	String []xx = line.split("#"); 
            	String []yy = xx[1].split("->");
            	this.server.getByname(yy[1]).getOut().println(line);
            }
			//如果有下线用户首先将该线程从线程数组中去掉，然后广播给所有客户端
			if(line.contains("下线用户:")) {
				//去掉该用户线程
				ServerAgentThread delserverthread = this.server.getByname(this.threadName);
				this.server.getServerthread().remove(delserverthread);
				//广播
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
			//服务器帮助客户端发送挑战信息
			if(line.contains("下战书")) {
				String []xx = line.split("书");
				String []yy = xx[1].split("->");
				this.server.getByname(yy[1]).getOut().println(yy[0]+"向你发送挑战信息");
			}
			//服务器帮助客户端发送接受挑战信息
			if(line.contains("接受挑战信息")) {
				String []xx = line.split("息");
				String []yy = xx[1].split("->");
				this.server.getByname(yy[1]).getOut().println(line);
			}
			//服务器帮助客户端发送拒绝挑战信息
			if(line.contains("拒绝挑战信息")) {
				String []xx = line.split("息");
				String []yy = xx[1].split("->");
				this.server.getByname(yy[1]).getOut().println(line);
			}
			//服务器帮助客户端发送获胜信息
			if(line.contains("获胜信息")) {
				String []xx = line.split("息");
				String []yy = xx[1].split("->");
				this.server.getByname(yy[1]).getOut().println("这局您失败了！");
			}
			//服务器帮助客户端发送聊天消息
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
