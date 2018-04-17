package dev.andrei.mario.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.andrei.mario.game.Handler;

public class EntityManager {

	private ArrayList<Entity> entities;
	private Handler handler;
	
	public EntityManager(Handler handler){
		
		this.handler = handler;
		entities = new ArrayList<Entity>();
	}
	
	public void update(){
		
		int len = entities.size();
		
		for(int i = 0;i < len; ++i)
			entities.get(i).update();
		
	}
	
	public void render(Graphics g){
		
		int len = entities.size();
		
		for(int i = 0;i < len; ++i){
			entities.get(i).render(g);
		}
	}
	
	public void addEntity(Entity e){
		
		entities.add(e);
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
}
