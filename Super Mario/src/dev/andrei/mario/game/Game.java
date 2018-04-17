package dev.andrei.mario.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.andrei.mario.display.Display;
import dev.andrei.mario.gfx.Assets;
import dev.andrei.mario.input.KeyManager;
import dev.andrei.mario.states.GameOverState;
import dev.andrei.mario.states.GameState;
import dev.andrei.mario.states.MenuState;
import dev.andrei.mario.states.State;

public class Game implements Runnable{

	private Display display;
	
	private Thread thread;
	
	private int width, height;
	private String title;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private State menuState;
	private State gameState;
	private State gameOverState;
	
	private KeyManager keyManager;
	
	private Assets assets;
	
	private Handler handler;
	
	private boolean running = false;
	
	private boolean gameOver = false;
	
	public Game(String title,int width,int height){
		
		this.title = title;
		this.height = height;
		this.width = width;
	}
	
	public void init(){
		
		display = new Display(title,width,height);
		
		handler = new Handler(this);
		
		keyManager = new KeyManager();
		display.getFrame().addKeyListener(keyManager);
		
		assets = new Assets();
		assets.init();
				
		gameState = new GameState(handler);
		menuState = new MenuState(handler,"/res/textures/background.png");
		gameOverState = new GameOverState(handler);
		State.setState(menuState);
	}
	
	public void gameOver(){
		
		State.setState(gameOverState);
	}
	
	public void update(){
		
		if(State.getState() == null)
			return;
		
		keyManager.update();
		State.getState().update();
	}
	
	public void render(){
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
			
		State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	
	@Override
	public void run() {
	
		init();
		
		int fps = 60;
		long lastTime = System.nanoTime();
		long now;
		long timer = 0;
		int ticks = 0;
		float timePerTick = 1000000000 / fps;
		float delta = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta > 1){
				update();
				render();
				++ticks;
				--delta;
			}
			
			if(timer >= 1000000000){
				System.out.println("FPS:" + ticks);
				timer = 0;
				ticks = 0;
			}
		}
		
	}
	
	public synchronized void start(){
		
		if(running)
			return;
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		
		if(!running)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Display getDisplay(){
		return display;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public void setKeyManager(KeyManager keyManager) {
		this.keyManager = keyManager;
	}

	public State getMenuState() {
		return menuState;
	}

	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}
	
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
}
