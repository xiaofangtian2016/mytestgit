package com.bishe.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ClientAgentThread extends Thread{
	private BufferedReader in; 
	private PrintWriter out; 
	private Chess chess;
	private Socket socket=null;
	public ClientAgentThread(Chess chess) {
		this.chess = chess;
	    try {
			socket=new Socket("127.0.0.1",8000);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			out = new PrintWriter(socket.getOutputStream(),true);
			//color = -1;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		String line;
		try {
			out.println("hello");
			line = in.readLine();
			while (!line.equals("bye")) {
			 System.out.println(line);
			//客户端从服务器得到用户名，颜色信息
			if(line.contains("用户名和颜色分别是:")) {
				String []xx = line.split(":");
				int sc = new Integer(xx[2]).intValue();
				if(xx[2].equals("0")) {
					this.chess.getMname().setText("用户"+xx[1]+"红");
				}else {
					this.chess.getMname().setText("用户"+xx[1]+"黑");
				}
				this.chess.setColor(sc);
				if(sc%2==0) {
					this.chess.setNext(true);
				}else {
					this.chess.setNext(false);
				}
				//this.chess.getChessboard().repaint();
				//this.chess.getChessboard().getParent().repaint();
				//this.chess.repaint();
				
			}
			//客户端接收到坐标信息
			if(line.contains("坐标信息")) {
				String []xx = line.split("#");
				int startI = new Integer(xx[2]).intValue();
				int startJ = new Integer(xx[3]).intValue();
				int endI = new Integer(xx[4]).intValue();
				int endJ = new Integer(xx[5]).intValue();
 			Pieces ps_man =this.chess.getChessboard().getPs()[startI][startJ];
			this.chess.getChessboard().movePs(ps_man, endI, endJ);
			this.chess.getChessboard().movePs(null, startI, startJ);
			this.chess.getChessboard().repaint();
			this.chess.repaint();
			this.chess.setNext(true);
			}
			//当有用户登陆是添加用户
			if(line.contains("当前在线用户有")) {
				String []xx = line.split("有");
				String []ipstr = xx[1].split("/");
				for(int i = 0;i<ipstr.length;i++) {
					if(chess.getPlayername().getItemAt(i)!=null&&((String)(chess.getPlayername().getItemAt(i))).equals(ipstr[i]))
						continue;
					this.chess.getPlayername().addItem(ipstr[i]);
				}
				//this.chess.getChessboard().repaint();
				//this.chess.getChessboard().getParent().repaint();
				//this.chess.repaint();
			}
			//当有用户下线时，去除一些用户
			if(line.contains("下线用户:")) {
				String []ipstr = line.split(":");
				int delIndex = 0;
				for(int i = 0;i<chess.getPlayername().getItemCount();i++) {
					if(chess.getPlayername().getItemAt(i)!=null&&((String)(chess.getPlayername().getItemAt(i))).equals(ipstr[1]))
					delIndex = i;
				}
				this.chess.getPlayername().removeItemAt(delIndex);
				
				//this.chess.getChessboard().repaint();
				//this.chess.getChessboard().getParent().repaint();
				//this.chess.repaint();
				
			}
			//客户端处理挑战请求
			
			if(line.contains("向你发送挑战信息")) {
				JOptionPane.showMessageDialog(this.chess, line, "提示",
						JOptionPane.INFORMATION_MESSAGE);
				String []chgname = line.split("向"); 
				this.chess.getChgList().addItem(chgname[0]);
				//this.chess.getChessboard().repaint();
				//this.chess.getChessboard().getParent().repaint();
				//this.chess.repaint();
			}
			
			//客户端处理接受挑战信息
			
			if(line.contains("接受挑战信息")) {
				this.chess.setSign(true);
				this.chess.getJbNewGame().setEnabled(true);
				this.chess.getJtChat().setEditable(true);
				String []xx = line.split("息");
				String []yy = xx[1].split("->");
				JOptionPane.showMessageDialog(this.chess, yy[0]+"接受你的挑战", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				this.chess.setOtherSide(yy[0]);
				this.chess.getPlayername().setEnabled(false);
				this.chess.getChgList().setEnabled(false);
				this.chess.getJbAccChallenge().setEnabled(false);
				this.chess.getJbChallenge().setEnabled(false);
				this.chess.getJbRefChallenge().setEnabled(false);
			}
			//客户端处理接受挑战信息
			if(line.contains("拒绝挑战信息")) {
				System.err.println("收到拒绝挑战信息的玩家是："+this.getChess().getMname().getText());
				String []xx = line.split("息");
				String []yy = xx[1].split("->");
				JOptionPane.showMessageDialog(this.chess, yy[0]+"拒绝你的挑战", "提示",
						JOptionPane.INFORMATION_MESSAGE);
			}
			//博弈失败信息 
			if(line.contains("这局您失败了！")) {
				this.chess.setSign(false);
				JOptionPane.showMessageDialog(this.chess, "这局您失败了！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				this.chess.setSign(false);
			}
			//客户端处理发来聊天信息
			if(line.contains("message")) {
				String []xx = line.split("message");
				this.chess.getJtMessage().append(xx[1]+"\r\n");
			}
			line = in.readLine(); 
			} 
			out.println("--- See you, bye! ---"); 
			socket.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void busy() {
		
	}
	
	public String refuse() {
		return null;
		
	}
	
	public String agree() {
		return null;
		
	}
	
	public void move(){
			//out.println("startI = "+chess.getChessboard().getStartI()+",startJ = "+chess.getChessboard().getStartJ()+",endI = "+chess.getChessboard().getEndI()+",endJ = "+chess.getChessboard().getEndJ()); 
		    out.println("坐标信息#"+chess.getMname().getText()+"->"+chess.getOtherSide()+"#"+chess.getChessboard().getStartI()+"#"+chess.getChessboard().getStartJ()+"#"+chess.getChessboard().getEndI()+"#"+chess.getChessboard().getEndJ()); 
	}
	
	public String rename() {
		return null;
		
	}
	
	public String[] player_list() {
		return null;
		
	}
	
	public int count() {
		return 0;
		
	}
	
	public void server_down() {
		
	}
	
	public void challenge() {
		
	}

	public Chess getChess() {
		return chess;
	}

	public void setChess(Chess chess) {
		this.chess = chess;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

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
}
