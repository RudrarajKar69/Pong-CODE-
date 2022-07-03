package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MyPanel extends JLabel implements ActionListener {
	public boolean running=true;
	Random rnd = new Random();
	Timer timer;
	final int panelSpeed=50;
	int panelWidth=10,panelHeight=100,mousePos=100,ballX=550/2,ballY=500/2,ballSpeedY=rnd.nextInt(4)+1,ballSpeedX=5,level=1;
	int mousePosY=600/2-panelHeight,acc=rnd.nextInt(level)+1;
	int SCORE,AISCORE,DIRECT;
	int [] panelist=new int[panelHeight],AI=new int[panelHeight];
	boolean rgb=rnd.nextBoolean();
	MyPanel(int direct,int level){
		this.level=level;
		DIRECT=direct;
		if(DIRECT==0)
			ballSpeedX=ballSpeedX*-1;
		if(DIRECT==1)
			ballSpeedX=ballSpeedX*1;
		timer = new Timer(75, this);
		timer.start();
	}
	
	protected void paintComponent(Graphics g) {
		draw(g);
	}
	
	 void draw(Graphics g) {
		 	if(running) {
		 		backGround(g);
		 	}
		 	else if(!running){
		 		if(AISCORE==10) {
					g.setColor(Color.green);
					g.setFont( new Font("Ink Free",Font.BOLD, 45));
					FontMetrics metrics1 = getFontMetrics(g.getFont());
					g.drawString("Right Player Winner", (600 - metrics1.stringWidth("Right Player Winner"))/2, 400/2);
				}
				if(SCORE==10) {
					g.setColor(Color.green);
					g.setFont( new Font("Ink Free",Font.BOLD, 45));
					FontMetrics metrics1 = getFontMetrics(g.getFont());
					g.drawString("Left Player Winner", (600 - metrics1.stringWidth("Left Player Winner"))/2, 400/2);
				}
				g.setColor(Color.red);
				g.setFont( new Font("Ink Free",Font.BOLD, 75));
				FontMetrics metrics2 = getFontMetrics(g.getFont());
				g.drawString("Click to interact", (600 - metrics2.stringWidth("Click to interact"))/2, 600/2);
		 	}
	}
	 
	 void backGround(Graphics g) {
		 	g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			if(!rgb) {
				g.setColor(Color.white);
			}
			else if(rgb) {
				g.setColor(new Color((rnd.nextInt(200)+55),(rnd.nextInt(200)+55),(rnd.nextInt(200)+55)));
			}
			g.drawLine(580/2, 600, 580/2, 0);
			
			if(!rgb) {
				g.setColor(Color.white);
			}
			else if(rgb) {
				g.setColor(new Color((rnd.nextInt(200)+55),(rnd.nextInt(200)+55),(rnd.nextInt(200)+55)));
			}
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("L-Score: "+SCORE+" , R-Score: "+AISCORE, (600 - metrics.stringWidth("L-Score: "+SCORE+" , R-Score: "+AISCORE))/2, g.getFont().getSize());
		
			racket(g);
			AIracket(g);
			ball(g);
	 }
	void racket(Graphics g) {
		if(!rgb) {
			g.setColor(Color.white);
		}
		else {
			g.setColor(new Color((rnd.nextInt(200)+55),(rnd.nextInt(200)+55),(rnd.nextInt(200)+55)));
		}
		g.fillRect(0, mousePosY, panelWidth, panelHeight);
	}
	
	void AIracket(Graphics g) {
		if(!rgb) {
			g.setColor(Color.white);
		}
		else {
			g.setColor(new Color((rnd.nextInt(200)+55),(rnd.nextInt(200)+55),(rnd.nextInt(200)+55)));
		}
		g.fillRect(570, mousePosY/*ballY*/, panelWidth, panelHeight);
	}
	
	//makes ball
	void ball(Graphics g) {
		if(!rgb) {
			g.setColor(Color.red);
		}
		else if(rgb){
			g.setColor(new Color((rnd.nextInt(200)+55),(rnd.nextInt(200)+55),(rnd.nextInt(200)+55)));
		}
		g.fillOval(ballX, ballY, 10, 10);
		checkWall();
		checkRacket();
	}
	void checkRacket() {
		//Checks ball collision with left panel
		for(int i=0;i<AI.length;i++) {
			if(ballY==AI[i]) {
				if(ballX==(570-panelWidth)) {
					ballSpeedX=ballSpeedX*-1;
					//AISCORE++;
					if(ballSpeedY<0) {
						ballSpeedY=ballSpeedY-acc;
						}
					else {
						ballSpeedY=ballSpeedY+acc;
					}
				}
			}
		}
		//Checks ball collision with right panel
		for(int i=0;i<panelist.length;i++) {
			if(ballY==panelist[i]) {
				if(ballX==(0+panelWidth)) {
					ballSpeedX=ballSpeedX*-1;
					//SCORE++;
					if(ballSpeedY<0) {
						ballSpeedY=ballSpeedY-acc;
						}
					else {
						ballSpeedY=ballSpeedY+acc;
					}
				}
			}
		}
		//Moves ball left-right
		ballX=ballX+ballSpeedX;
		
	}
	
	void checkWall() {
		//Checks if ball is below upper margin
		if(ballY<10) {
			ballSpeedY=ballSpeedY*-1;
		}
		//Checks if ball is above lower margin
		if(ballY>600-50) {
			ballSpeedY=ballSpeedY*-1;
		}
		//ball moves up-down
		ballY=ballY+ballSpeedY;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(ballX<-10) {
			ballX=550/2;ballY=500/2;ballSpeedY=rnd.nextInt(4)+1;ballSpeedX=5;DIRECT=rnd.nextInt(2);acc=rnd.nextInt(level)+1;rgb=rnd.nextBoolean();
			if(DIRECT==0)
				ballSpeedX=ballSpeedX*-1;
			else if(DIRECT==1)
				ballSpeedX=ballSpeedX*1;
			AISCORE++;
		}
		if (ballX>600) {
			ballX=550/2;ballY=500/2;ballSpeedY=rnd.nextInt(4)+1;ballSpeedX=5;DIRECT=rnd.nextInt(2);rgb=rnd.nextBoolean();acc=rnd.nextInt(level)+1;
			if(DIRECT==0) 
				ballSpeedX=ballSpeedX*-1;
			else if(DIRECT==1)
				ballSpeedX=ballSpeedX*1;
			SCORE++;
		}
		if(AISCORE==10||SCORE==10) {
			running=false;
		}
		if(running) {
			Point pos = getMousePosition();
			if(pos!=null) {
				if(mousePosY!=pos.y) {
					if(mousePosY<pos.y) 
						mousePosY= mousePosY+panelSpeed;
					if(mousePosY>pos.y)
						mousePosY=mousePosY-panelSpeed;
					}
			}
			for(int i=0;i<panelist.length;i++) {
				panelist[i]=mousePosY+i;
			}
			
			for(int i=0;i<AI.length;i++) {
				AI[i]=mousePosY/*ballY*/+i;
			}	
		}
		repaint();
	}	
}
