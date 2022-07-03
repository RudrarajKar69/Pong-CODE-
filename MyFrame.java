package main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MyFrame extends JFrame  implements MouseListener,KeyListener{
	
	Random rnd=new Random();
	int level=1;
	int direct=rnd.nextInt(2),dog;
	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("ICON.png"));
	MyPanel panel;
	AIPANEL panel1;
	MyPanel_KEY panel2;
	AIPANEL_KEY panel3=new AIPANEL_KEY(direct,level);
	JLabel label = new JLabel();
	MyFrame(){
		
			System.out.println("RUNNING");
			dog = OPTION();
			if(dog==1) {
				panel = new MyPanel(direct,level);
				this.add(panel);
				
			}
			else if(dog==3) {
				panel1 = new AIPANEL(direct,level);
				this.add(panel1);
			}
			else if(dog==2) {
				panel2 = new MyPanel_KEY(direct,level);
				this.add(panel2);
				addKeyListener(this);
			}
			else if(dog==4) {
				panel3=new AIPANEL_KEY(direct,level);
				this.add(panel3);
				addKeyListener(this);
			}
			if(dog>0&&dog<5) {
				this.setTitle("Ping-Pong");
				addMouseListener(this);
				this.setMinimumSize(new Dimension(600,600));
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.DISPOSE_ON_CLOSE(JFrame.EXIT_ON_CLOSE);
				this.setResizable(false);
				this.setIconImage(logo.getImage());
				this.setLocationRelativeTo(null);
				this.setVisible(true);
			}
			
		}
	
	private void DISPOSE_ON_CLOSE(int exitOnClose) {
		this.dispose();
	}

	int OPTION() {
		int Opt=0; 
		try {
			Opt = Integer.parseInt(JOptionPane.showInputDialog("Press \n1) to play with urself,\n2) to play with urself(using keys),\n3) to play with AI,\n4) to play with AI(using keys)"));			
			if(!(Opt>4||Opt<1))
			{
				level = Integer.parseInt(JOptionPane.showInputDialog("Enter the difficulty \n1)Easy\n2)Medium\n3)PRO"));
				if(level!=1) {
					level=level+level;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Enter correct option!!");
				Opt=OPTION();
			}
		}
		catch (Exception e){
			int ott = JOptionPane.showConfirmDialog(null, "Want to quit?");
			if(!(ott==0)) {
				Opt=OPTION();
			}
			else 
				this.dispose();
			
		}
		return Opt;
	}
	@Override
	//moves panel up-down
	public void keyPressed(KeyEvent e) {
		if(dog==2) {
			if(e.getKeyCode()==KeyEvent.VK_UP) {
					panel2.LRpanelY=panel2.LRpanelY-panel2.panelSpeed;
			}
			else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					panel2.LRpanelY=panel2.LRpanelY+panel2.panelSpeed;
			}
			if(e.getKeyCode()==KeyEvent.VK_W) {
				panel2.RpanelY=panel2.RpanelY-panel2.panelSpeed;
			}
			else if(e.getKeyCode()==KeyEvent.VK_S) {
				panel2.RpanelY=panel2.RpanelY+panel2.panelSpeed;
			}
		}
		else if(dog==4) {
				if(e.getKeyCode()==KeyEvent.VK_UP) {
						panel3.panelY=panel3.panelY-panel3.panelSpeed;
				}
				else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
						panel3.panelY=panel3.panelY+panel3.panelSpeed;
				}
				
			}
		}
	

	@Override public void keyTyped(KeyEvent e) {}@Override public void keyReleased(KeyEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override
	//Makes a new game occur when mosue is pressed
	public void mousePressed(MouseEvent e) {
			this.dispose();
			new MyFrame();
	}
	@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}

}
