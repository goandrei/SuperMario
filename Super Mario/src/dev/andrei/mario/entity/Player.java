package dev.andrei.mario.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import dev.andrei.mario.game.Handler;
import dev.andrei.mario.gfx.Animation;
import dev.andrei.mario.gfx.Assets;
import dev.andrei.mario.input.KeyManager;

public class Player extends Entity{

	private static int width = 32;
	private static int height = 52;
	
	private float speedX = 0, speedY = 0, maxSpeedX = 5;  
	private int delay = 50;
	
	private int maxY, minY;
	
	private long delay2 = 200 ,lastTime, now, timer = 0;
	private Animation currentAnimation = null;
	private Animation backwards = new Animation(Assets.backwards,3,delay);
	private Animation front = new Animation(Assets.front,3,delay);
	private Animation standingLeft = new Animation(Assets.standingLeft,2,delay);
	private Animation standingRight = new Animation(Assets.standingRight,2,delay);
	
	private boolean direction = true; // false = left true = right
	
	
	public Player(Handler handler,int x, int y) {
		super(handler,x, y, width, height);
	
		currentAnimation = standingRight;
		maxY = 467 - height;
		minY = 100;
		
		bounds.x = 0;
		bounds.y = 7;
		bounds.width = 34;
		bounds.height = 25;
		
		lastTime = System.currentTimeMillis();
	}
	
	public void init(){
		
		x = 30;
		y = 415;
		speedX = 0;
		speedY = 0;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public void getKeyInputSides(){
		
		speedX = 0;
		handler.getWorld().updateOffset(0);
		
		now = System.currentTimeMillis();
		timer += now - lastTime;
		lastTime = now;
		
		if(KeyManager.left){
			currentAnimation = backwards;
			direction = false;
			speedX = -maxSpeedX;
			return;
		}
		else if(KeyManager.right){
			currentAnimation = front;
			direction = true;
			speedX = maxSpeedX;
			return;
		}
	
		if(!direction)
			currentAnimation = standingLeft;
		else
			currentAnimation = standingRight;
	}
	
	public void getKeyInputUp(){
		
		if(KeyManager.space)
			speedY += -0.5;
		else if(y != 415)
			speedY += 0.5;
		
	}
	
	private void move(){
		
		x += speedX;
		y += speedY;
		
		if(x < 0)
			x = 0;
		
		if(x + width - handler.getWorld().getxOffset() > handler.getWidth())
			x = (int) (handler.getWidth() - width + handler.getWorld().getxOffset());
		
		if(y > maxY){
			y = maxY;
			speedY = 0;
		}
		
		if(y < minY){
			y = minY;
			speedY = 50;
		}
		
		if(y > handler.getHeight())
			handler.getGame().gameOver();
		
		if(x - handler.getWorld().getxOffset() > 480)
			handler.getGame().gameOver();
		
	}
	
	public void fall(){
		
		int xTile = x / Assets.TILE_WIDTH;
		
		int[][] t = handler.getWorld().getMyTiles();

		if(y == 415){
			if(t[14][xTile] == t[15][xTile + 1] && t[14][xTile] == 1 && speedY == 0.0f){System.out.println("Aici");
				maxY += 100;
				speedY = 5;
				speedX = 0;
				handler.getGame().setGameOver(true);
			}
			else
				speedY = 0;
		}
	}
	
	private void checkCollisions(){
		
		ArrayList<Entity> list = handler.getWorld().getEntityManager().getEntities();
		int len = list.size();
		
		for(int i = 0;i < len; ++i){
			Entity e = list.get(i);
			Rectangle rect = e.getBounds();
			if(this.getBounds().intersects(e.getBounds()) && e.isSolid()){
				e.onCollision();
			}
		}
	}
	
	
	public  int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Player.height = height;
	}

	@Override
	public void update() {

		fall();
		if(!handler.getGame().isGameOver()){
			getKeyInputSides();
			getKeyInputUp();
			checkCollisions();
		}
		move();
		if(maxSpeedX > 0)
			currentAnimation.update();
	}
	
	@Override
	public void render(Graphics g) {
		
		if(currentAnimation != null)
			g.drawImage(currentAnimation.getCurrentFrame(), (int) (x - handler.getWorld().getxOffset()), y - 20,width  , height, null);
		//g.setColor(Color.RED);
		//g.fillRect((int) (x - handler.getWorld().getxOffset()), y + bounds.y, bounds.width, bounds.height);
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}

	@Override
	public void onCollision() {
		// TODO Auto-generated method stub	
	}
	
	public void setSpeedY(int speedY){
		this.speedY = speedY;
	}
	
	public Rectangle getRectangle(){
		return bounds;
	}
	
	public void updateMaxSpeedX(int x){
		
		maxSpeedX += x;
		
		if(maxSpeedX < 0)
			maxSpeedX = 0;
	}
	
	public Animation getFront(){
		return front;
	}
	
	public Animation getBackwards(){
		return backwards;
	}
}
