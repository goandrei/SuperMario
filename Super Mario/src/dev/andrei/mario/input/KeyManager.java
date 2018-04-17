package dev.andrei.mario.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	public static boolean up, down, enter, left, right, space;
	public static boolean keys[];
	
	public void update(){
		
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		enter = keys[KeyEvent.VK_ENTER];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
	}
	
	
	public KeyManager(){
		
		keys = new boolean[256];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	
	}

	public static boolean getDown(){
		return down;
	}
	
	public static boolean getUp(){
		return up;
	}
	
}
