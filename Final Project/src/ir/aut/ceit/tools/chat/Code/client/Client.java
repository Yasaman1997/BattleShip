package ir.aut.ceit.tools.chat.Code.client;

import java.net.*;
	import java.io.*;
	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;
	
	public  class Client extends Applet implements ActionListener,TextListener
	{
		Button bt1,bt2,bt3;
		TextField tf1,tf2,tf3;
		TextArea ta1,ta2;
		Label lb1,lb2,lb3;
		
		String msg,imsg,sad;
		dialog dob;
		frame fob;
		InetAddress iad;
		int port;
		Exception exob;
		
		boolean flg;
		Socket s;
		InputStream ips;
		OutputStream ops;
		connect cnt;
		public static void main(String arg[])
		{
			Client cob=new Client();
			frame fob=new frame("Client Messenger");
			
			cob.fob=fob;
			cob.init();
			cob.start();
			fob.addWindowListener(fob);
			fob.add("Center",cob);
			fob.setLocation(200,100);
			fob.setBackground(Color.lightGray);
			fob.setForeground(Color.blue);
			fob.setSize(470,435);
			fob.setResizable(false);
			fob.show();
		}
		
		public void init()
		{
			try
			{
				iad=InetAddress.getLocalHost();
			}
			catch(Exception e){System.out.println(e);}
			bt1=new Button("Start");
			bt3=new Button("Clear");
			bt2=new Button("Send");
			lb1=new Label("Server :");
			lb2=new Label("Port :");
			lb3=new Label("Notification  :");
			tf2=new TextField("8080",4);
			tf3=new TextField("",42);
			tf1=new TextField("localhost",8);
			ta1=new TextArea("",15,55,1);
			ta2=new TextArea("",4,48,1);
			setLayout(new FlowLayout(FlowLayout.CENTER));
			bt1.addActionListener(this);
			bt2.addActionListener(this);
			bt3.addActionListener(this);
			ta1.addTextListener(this);
			ta2.addTextListener(this);
			ta1.setBackground(Color.black);
			ta1.setForeground(Color.green);
			ta2.setForeground(Color.magenta );
			tf1.setForeground(Color.red);
			tf2.setForeground(Color.red);
			tf3.setForeground(Color.red);
			tf3.setFocusable(false);
			ta1.setFocusable(false);
			ta2.setFocusable(false);
			bt2.setEnabled(false);
			bt3.setEnabled(false);
			requestFocus();
			add(lb1);
			add(tf1);
			add(lb2);
			add(tf2);
			add(bt1);
			add(bt3);
			add(ta1);
			add(ta2);
			add(bt2);
			add(lb3);
			add(tf3);	
			cnt=new connect(this);
		}
		public void textValueChanged(TextEvent te)
		{
			if(ta1.getText().equals(""))
			{
				bt3.setEnabled(false);
			}
			else
			{
				bt3.setEnabled(true);
			}
			if(ta2.getText().endsWith("\n"))
			{
				imsg=ta2.getText();
				ta1.append("Client :  "+imsg);
				ta2.setText("");
				if(flg)
				{
					readdat();
				}
			}
		}
		
			
		public void actionPerformed(ActionEvent ae)
		{
			String str;
			str=ae.getActionCommand();
			if(str.equals(bt3.getLabel()))
			{
				ta1.setText("");
			}	
			if(str.equals("Stop"))
			{
				flg=false;
				bt2.setEnabled(false);
				tf1.setFocusable(true);
				tf2.setFocusable(true);
				ta2.setFocusable(false);
				bt1.setLabel("Start");
				try
				{
					s.close();
					tf3.setText("Client Disconnected from Server .");
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
			if(str.equals("Start"))
			{
				if(tf1.getText().equals(""))
				{
					msg="Please Enter Server IP Address.";
					dob=new dialog(fob,"Invalid Input",1,msg);
					dob.setVisible(true);
				}	
				else if(tf2.getText().equals(""))
				{
					msg="Please Enter Port No..";
					dob=new dialog(fob,"Invalid Input",1,msg);
					dob.setVisible(true);
				}else
				{
					try{
							sad=tf1.getText();
							exob=new Exception("Port must be Number.");
							msg="Port must be Number.";
							port=Integer.parseInt(tf2.getText());
							bt1.setEnabled(false);
							tf1.setFocusable(false);
							tf2.setFocusable(false);
							cnt.t=new Thread(cnt);
							cnt.t.start();
						}catch(Exception ex)
						{
							dob=new dialog(fob,"Invalid Port",1,msg);
							dob.setVisible(true);
						}
				}
			}
			if(str.equals(bt2.getLabel()))
			{
				imsg=ta2.getText();
				ta1.append("Client :  "+imsg+"\n");
				imsg=ta2.getText()+"\n";
				ta2.setText("");
				if(flg)
				{
					readdat();
				}
			}	
		}
		
		void readdat()
		{
			try
			{
				msg="Server is Disconnected.";
				exob=new Exception(msg);
				ops.write(imsg.getBytes());
			}
			catch(Exception ex)
			{
				flg=false;
				bt1.setEnabled(true);
				bt1.setLabel("Start");
				bt2.setEnabled(false);
				tf1.setFocusable(true);
				tf2.setFocusable(true);
				ta2.setFocusable(false);
				dob=new dialog(fob,"IO Error",1,msg);
				dob.setVisible(true);
			}
		}
			
	}
	
	class connect implements Runnable
	{
		Client cob;
		Thread t;
		dialog dob;
		String msg;
		Exception exc;
		byte bt[];
		connect(Client icob)
		{
			cob=icob;
		}
		
		public void run()
		{
			try
			{
				msg="No Any Server Exist OR Port is Already in Use.";
				exc=new Exception(msg);
				cob.s=new Socket(cob.sad,cob.port);
				
				cob.tf3.setText("Client Connected to Server.");
				cob.bt1.setLabel("Stop");
				cob.bt1.setEnabled(true);
				cob.bt2.setEnabled(true);
				cob.ta2.setFocusable(true);
				cob.tf1.setFocusable(false);
				cob.tf2.setFocusable(false);
				
				cob.ops=cob.s.getOutputStream();
				cob.ips=cob.s.getInputStream();
				msg="Server is Disconnected.";
				exc=new Exception(msg);
				cob.flg=true;
				while(cob.flg)
				{
					try
					{
						bt=new byte[100];
						cob.ips.read(bt);
						cob.ta1.append("Server :  "+new String(bt)+"\n");
					}
					catch(Exception ex)
					{
						cob.flg=false;
						cob.bt1.setEnabled(true);
						cob.bt1.setLabel("Start");
						cob.bt2.setEnabled(false);
						cob.tf1.setFocusable(true);
						cob.tf2.setFocusable(true);
						cob.ta2.setFocusable(false);
						dob=new dialog(cob.fob,"IO Error",1,msg);
						dob.setVisible(true);
					}
				}
			}
			catch(Exception ex)
			{
				cob.bt1.setEnabled(true);
				cob.bt1.setLabel("Start");
				cob.bt2.setEnabled(false);
				cob.tf1.setFocusable(true);
				cob.tf2.setFocusable(true);
				cob.ta2.setFocusable(false);
				dob=new dialog(cob.fob,"Server/Port Error",1,msg);
				dob.setSize(400,80);
				dob.setVisible(true);
			}	
		}
	}
	class frame extends java.awt.Frame implements WindowListener
	{
		Button bt;
		frame()
		{
			super();
			
		}
		frame(String title)
		{
			super(title);
		}
		public void windowActivated(WindowEvent e) {}

		public void windowClosed(WindowEvent e) {}

		public void windowClosing(WindowEvent e)
		{
			dispose();
			System.exit(0);
		}

		public void windowDeactivated(WindowEvent e) {}

		public void windowDeiconified(WindowEvent e) {}

		public void windowIconified(WindowEvent e) {}
	
		public void windowOpened(WindowEvent e) {}
	}
	
	class dialog extends Dialog implements ActionListener
	{
		int tp;
		String msg;
		boolean fg;
		
		Button bt1,bt2;
		Label lb;
		dialog(Frame par,String ttl,int type,String msgi)
		{
			super(par,ttl,true);
			setLayout(new FlowLayout());
			setSize(320,80);
			setLocation(par.getX()+10,par.getY()+20);
			setResizable(false);
			tp=type;
			msg=msgi;
			draw_di();
		}
		void draw_di()
		{
			switch(tp)
			{
				case 1:
				{
					bt1=new Button("OK");
					lb=new Label(msg);
					add(lb);
					add(bt1);
					bt1.addActionListener(this);
					break;
				}
				case 2:
				{
					bt1=new Button("Yes");
					bt2=new Button("No");
					lb=new Label(msg);
					add(lb);
					add(bt1);
					add(bt2);
					bt1.addActionListener(this);
					bt2.addActionListener(this);
					break;
				}
			}
		}
		public void actionPerformed(ActionEvent ae)
		{
			String str=ae.getActionCommand();
			switch(tp)
			{
				case 1:
				{
					if(str.equals(bt1.getLabel()))
					{
						dispose();
					}	
					break;
				}
				case 2:
				{
					if(str.equals(bt1.getLabel()))
					{
						fg=true;
						dispose();
					}else if(str.equals(bt2.getLabel()))
					{
						fg=false;
						dispose();
					}
					break;
				}
			}
		}
	}