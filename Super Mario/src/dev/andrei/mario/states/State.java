package dev.andrei.mario.states;

import java.awt.Graphics;

import dev.andrei.mario.game.Handler;

public abstract class State {

	private static State currentState = null;

	protected Handler handler;
	
	public State(Handler handler){
		
		this.handler = handler;
	}
	
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
}
