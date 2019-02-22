import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.util.*;

public class Alien extends Entity
{
	private int startY;

	public Alien(int x, int y)
	{
		super(x,y);
		startY = y;
	}

	public void update()
	{
		y+=1;
		checkCollisions();
	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(getAlienImg(), x, y, null);
		g2d.draw(getBounds());
		
	}

		public Image getAlienImg()
	{
		ImageIcon ic = new ImageIcon(<Alien.png>); //Use Alien.png
		return ic.getImage();
	}

	public void checkCollisions()
	{
		for (int i = 0; i < GameFrame.getBulletList().size(); i++)
		{
			Bullet b = GameFrame.getBulletList().get(i);
			if (getBounds().intersects(b.getBounds()))
			{
				GameFrame.removeAlien(this);
				GameFrame.removeBullet(b);
			}
		}
	}


	public Rectangle getBounds()
	{
		return new Rectangle(x,y, getAlienImg().getWidth(null), getAlienImg().getHeight(null));
	}
	
}


