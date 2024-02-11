package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;



public class MyPanel extends JPanel implements ActionListener{
	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEIGHT = 500;
	static final int OBJECT_SIZE = 25;
	static final int GAME_UNIT = (SCREEN_WIDTH * SCREEN_HEIGHT)/ OBJECT_SIZE;
	static final int DELAY = 75;
	final int[] x = new int[GAME_UNIT];
	final int[] y = new int[GAME_UNIT];
	
	int bodyParts = 8;
	int appleEaten =0 ;
	int appleX =0 ;
	int appleY =0 ;
	char direction = 'R';
	boolean gameRunning = false;
	Timer myTimer;
	Random myRandom;
	
	public MyPanel() {

		myRandom = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT ));
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.addKeyListener(new myKeyAdapter());
		startGame();
	    
	}
	
	
	public void startGame() {
		newApple();
		gameRunning = true;
		myTimer = new Timer(DELAY, this);
		
		myTimer.start();
	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		
	}
	
	public void draw(Graphics g) {
	
		if(gameRunning) {
		g.setColor(Color.green);
		g.fillOval(appleX, appleY,OBJECT_SIZE, OBJECT_SIZE );
		
		for(int i=0; i<bodyParts; i++) {
			g.setColor(Color.BLUE);
			g.fillRect(x[i], y[i],OBJECT_SIZE, OBJECT_SIZE );
			}
		}
		else gameOver(g);
	}
	
	public void newApple() {
		appleX = myRandom.nextInt((int)(SCREEN_WIDTH/OBJECT_SIZE))*OBJECT_SIZE ;
		appleY = myRandom.nextInt((int)(SCREEN_HEIGHT/OBJECT_SIZE))*OBJECT_SIZE;
	}
	
	
	public void move() {
		
		 for(int i = bodyParts; i > 0; i-- ){
			x[i] = x[i-1]; 
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case'R' :
			x[0] = x[0] + OBJECT_SIZE;
			break;
		
		case'L' :
			x[0] = x[0] - OBJECT_SIZE;
			break;
		
		case'U' :
			y[0] = y[0] - OBJECT_SIZE;
			break;
		
		case'D' :
			y[0] = y[0] + OBJECT_SIZE;
			break;
		}
		
	}
	
	public void checkApple() {
		if(x[0] == appleX && y[0] == appleY) {
			bodyParts++;
			appleEaten++;
			newApple();
			
		}
		
	}

	public void checkCollision() {
		for(int i= bodyParts; i>0; i--) {
			if(x[i]== x[0] && y[i] == y[0]) {
				gameRunning = false;
			}
		}
	
		if(x[0] < 0) {
			gameRunning = false;
		}
		if(y[0] < 0) {
			gameRunning = false;
		}
		if(x[0] > SCREEN_WIDTH) {
			gameRunning = false;
		}
		
		if(y[0] > SCREEN_HEIGHT) {
			gameRunning = false;
		}
		
		
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Times", Font.BOLD, 65));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameRunning) {
			move();
			checkApple();
			checkCollision();
		}
		repaint();
		}
	
	
	public class myKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT :
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			
				case KeyEvent.VK_RIGHT :
					if(direction != 'L') {
						direction = 'R';
					}
					break;	
				
				case KeyEvent.VK_UP :
					if(direction != 'D') {
						direction = 'U';
					}
					break;	
				
				case KeyEvent.VK_DOWN :
					if(direction != 'U') {
						direction = 'D';
					}
					break;	
			}
				
			
	
		}
		
	}

}
