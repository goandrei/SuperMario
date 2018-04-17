package dev.andrei.mario.states;

import java.awt.Graphics;

import dev.andrei.mario.game.Handler;
import dev.andrei.mario.game.Score;
import dev.andrei.mario.worlds.World;

public class GameState extends State{
	
	private World world;
	private Score score;
	private Handler handler;
	
	public GameState(Handler handler){
		
		super(handler);
		world = new World(handler,"src/res/textures/world.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void update() {
		
		world.update();
	}

	@Override
	public void render(Graphics g) {
		
		world.render(g);
	}
	
	public Score getScore(){
		return score;
	}

}
