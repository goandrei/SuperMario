package dev.andrei.mario.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display{
	
	private JFrame frame;
	
	private Canvas canvas;
	
	private final int SCREEN_WIDTH, SCREEN_HEIGHT;
	private String title;
	
	public Display(String title,int width,int height){
		
		this.title = title;
		SCREEN_WIDTH = width;
		SCREEN_HEIGHT = height;
		
		createDisplay();
	}
	
	private void createDisplay(){
		
		frame = new JFrame(title);
		frame.setSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		canvas.setMinimumSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		canvas.setMaximumSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		
		frame.add(canvas);
		canvas.setFocusable(false);
		frame.pack();
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public Canvas getCanvas(){
		return canvas;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public int getSCREEN_WIDTH() {
		return SCREEN_WIDTH;
	}

	public int getSCREEN_HEIGHT() {
		return SCREEN_HEIGHT;
	}
	
	
	
}
