import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class KeyAdapt extends KeyAdapter
{
	Player p;

	public KeyAdapt(Player player)
	{
		p = player;
	}

	public void keyPressed(KeyEvent e)
	{
		p.keyPressed(e);
	}

	public void keyReleased(KeyEvent e)
	{
		p.keyReleased(e);
	}
}