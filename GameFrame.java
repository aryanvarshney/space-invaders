import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.util.ArrayList;
import java.util.Random;


public class GameFrame extends JPanel implements ActionListener
{
	Timer mainTimer;
	Player player;

	int alienCount = 4;
	public static int level = 1;
	static ArrayList<Heart> hearts = new ArrayList<Heart>();
	static ArrayList<Alien> aliens = new ArrayList<Alien>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	static ArrayList<Missile> missiles = new ArrayList<Missile>();
	Random rand = new Random();
	public GameFrame()
	{
		setFocusable(true);
		player = new Player(250,530);
		System.out.println("Inside GameFrame");
		hearts.add(new Heart(30,30));
		hearts.add(new Heart(30,60));
		hearts.add(new Heart(30,90));
		addKeyListener(new KeyAdapt(player));
		mainTimer = new Timer(10, this);
		mainTimer.start();
		startGame();
	}

	public void paint(Graphics g)
	{

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		ImageIcon ic = new ImageIcon("C:/spaceinvaders/background.png");
		g2d.drawImage(ic.getImage(),0,0,null);

		player.draw(g2d);

		for (int i = 0; i < hearts.size(); i++)
		{
			Heart tempAlien = hearts.get(i);
			tempAlien.draw(g2d); 
		}
	
		for (int i = 0; i < aliens.size(); i++)
		{
			Alien tempAlien = aliens.get(i);
			tempAlien.draw(g2d); 
		}

		for (int i = 0; i < bullets.size(); i++)
		{
			Bullet b = bullets.get(i);
			b.draw(g2d);
		}

		for (int i = 0; i < missiles.size(); i++)
		{
			Missile m = missiles.get(i);
			m.draw(g2d);
		}

		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		player.update();
		for (int i = 0; i < aliens.size(); i++)
		{
			Alien tempAlien = aliens.get(i);
			tempAlien.update();
		}

		for (int i = 0; i < bullets.size(); i++)
		{
			Bullet b = bullets.get(i);
			b.update();
		}

		for (int i = 0; i < missiles.size(); i++)
		{
			Missile m = missiles.get(i);
			m.update();
		}

		checkEnd();

		repaint();
	}

	public static void removeHeart(int h)
	{
		hearts.remove(h);
	}

	public static ArrayList<Heart> getHeartList()
	{
		return hearts;
	}


	public static void addAlien(Alien e)
	{
		aliens.add(e);
	}

	public static void removeAlien(Alien e)
	{
		aliens.remove(e);
	}

	public static ArrayList<Alien> getAlienList()
	{
		return aliens;
	}

	public static void addBullet(Bullet b)
	{
		bullets.add(b);
	}

	public static void removeBullet(Bullet b)
	{
		bullets.remove(b);
	}

	public static ArrayList<Bullet> getBulletList()
	{
		return bullets;
	}

	public static void addMissile(Missile m)
	{
		missiles.add(m);
	}

	public static void removeMissile(Missile m)
	{
		missiles.remove(m);
	}

	public static ArrayList<Missile> getMissileList()
	{
		return missiles;
	}

	public void startGame()
	{
		alienCount +=  level ;
		for (int i = 0; i < alienCount; i++) {
            for (int j = 0; j < 5; j++) {
			addAlien(new Alien(180 + 30 * j, 0 + 18 *i));
		}
	}
}

	public void checkEnd()
	{
		if (aliens.size() == 0)
		{
			level++;
			aliens.clear();
			bullets.clear();
			missiles.clear();
			JOptionPane.showMessageDialog(null, "Good work, you have completed level " + (level-1) + ". Lets move on to the next one...");
			startGame();
		}
	}
} 