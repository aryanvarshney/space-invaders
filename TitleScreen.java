import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TitleScreen implements ActionListener{               
  	JButton b=new JButton("Start Game"); 
  	JFrame frame = new JFrame("Space Invaders");
  	JTextField gameName = new JTextField("Space Invaders");
	public static void main(String args[]){
   		new TitleScreen();
	}

	public TitleScreen(){
		frame.setSize(500, 600);
		frame.setLayout(new BorderLayout());
		frame.add(b, BorderLayout.NORTH);
		b.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(b)){
			System.out.println("Game start");
			b.setVisible(false);
			frame.add(new GameFrame());
			b.setFocusable(false);
			frame.setFocusable(true);
		}
	}

	public void setFrame(boolean x){
		frame.setVisible(x);
	}
}