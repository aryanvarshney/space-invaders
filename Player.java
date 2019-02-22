import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.util.*;


public class Player extends Entity
{
	int numHearts = 3;
	int velX = 0;
	int speed = 2;
	public Player(int x, int y)
	{
		super(x,y);
	}

	public void update()
	{
		x += velX;	
		checkCollisons();
	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(getPlayerImg(),x , y, null);
		g2d.draw(getBounds());
	}

	public Image getPlayerImg()
	{
		ImageIcon ic = new ImageIcon("C:/spaceinvaders/player.png");
		return ic.getImage();
	}

	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();


		if (key == KeyEvent.VK_A)
		{
			velX = -speed;
			if (x <= 100)
			{
				velX = 0;
			}
		}

		else if (key == KeyEvent.VK_D)
		{
			velX = speed;
			if (x >= 400)
			{
				velX = 0;
			}
		}

		else if (key == KeyEvent.VK_SPACE)
		{
			GameFrame.addBullet(new Bullet(x + 10, y));
		}

	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A)
		{
			velX = 0;
		}

		else if (key == KeyEvent.VK_D)
		{
			velX = 0;
		}
	}



	public void checkCollisons()
	{
		ArrayList<Missile> missiles = GameFrame.getMissileList();
		for (int i = 0; i < missiles.size(); i++)
		{
			Missile tempMissile = missiles.get(i);
			if (getBounds().intersects(missiles.get(i).getBounds()))
			{
				
				GameFrame.removeHeart(0);
				numHearts--;
				GameFrame.removeMissile(tempMissile);
			}
		}

		ArrayList<Alien> aliens = GameFrame.getAlienList();
		for (int i = 0; i < aliens.size(); i++)
		{
			Alien tempAlien = aliens.get(i);
			if (getBounds().intersects(aliens.get(i).getBounds()))
			{
				numHearts --;

			}
		}

		if (numHearts == 0)
		{
			JOptionPane.showMessageDialog(null, "You have died on level " + GameFrame.level + ". Try harder next time.");
			System.exit(0);
		}
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x,y, getPlayerImg().getWidth(null), getPlayerImg().getHeight(null));
	}
}
