import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.util.*;

public class SpaceInvaders
{

public static void main(String[] args)
{
	JFrame frame = new JFrame("Space Invaders");
	long start;

            start = System.currentTimeMillis();
            JFrame frame1 = new JFrame();
            System.out.println((System.currentTimeMillis() - start) + " for first JFrame.");

	frame.setSize(500, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setVisible(true);
	frame.add(new GameFrame());
}


	
}