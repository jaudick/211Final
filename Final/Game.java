import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game implements ActionListener, KeyListener{
	
	public Renderer renderer;
	public final int WIDTH = 800, HEIGHT = 800;
	public boolean started = false;
	public boolean over = false;
	public static Game game;
	public Rectangle player;
	public int tick;
	public int doubleJump = 0;
	
	public int yPath;
	
	public Game() {
		renderer = new Renderer();
		JFrame frame = new JFrame();
		Timer timer = new Timer(20, this);
		
		
		frame.add(renderer);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Game");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addKeyListener(this);
		
		
		player = new Rectangle(WIDTH /2 - 10, HEIGHT - 170, 20, 20);
		
		timer.start();
	}
	
	
	public void repaint(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.gray);
		g.fillRect(0, HEIGHT - 150, WIDTH, 150);
		
		
		g.setColor(Color.cyan);
		g.fillRect(player.x, player.y, player.width, player.height);
	
	}
	
	public void jump() {
		if (!started) {
			started = true;
		}
		
		if (!over && doubleJump < 2) 
		{
			if (yPath > 0) 
			{
				yPath = 0;
			}
			yPath -= 20;
			doubleJump++;
		}
	}
	public static void main(String[] args) {
		game = new Game();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		int gameSpeed = 20;
		tick++;
		if (started) {
			
			
			if (tick % 2 == 0 && yPath < 20) {
				yPath += 2;
			}
			player.y += yPath;
			
			if (player.y > HEIGHT - 170) 
			{
				player.y = HEIGHT - 170;
				yPath = 0;
				doubleJump = 0;
			}
		}
		renderer.repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			jump();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}


