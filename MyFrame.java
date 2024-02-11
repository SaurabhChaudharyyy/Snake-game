package com;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame() {
		this.add(new MyPanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
	}

}
