package dev.andrei.mario.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import dev.andrei.mario.game.Handler;
import dev.andrei.mario.gfx.ImageLoader;
import dev.andrei.mario.input.KeyManager;

public class MenuState extends State{

	private BufferedImage texture;
	private String path;
	
	private Font fontSelected, fontUnselected;
	private boolean selected = false;
	
	private final int BACK_WIDTH = 800, BACK_HEIGHT = 512;
	private final int START_X, START_Y, EXIT_X, EXIT_Y;
	
	public MenuState(Handler handler,String path){
		
		super(handler);
		this.path = path;
		
		START_X = handler.getWidth() / 2 - 50;
		START_Y = handler.getHeight() / 2 - 50;
		
		EXIT_X = START_X;
		EXIT_Y = START_Y + 50;
		
		init();
	}
	
	public void init(){
		
		texture = ImageLoader.loadImage(path);
		fontSelected = new Font("Garamond",Font.BOLD,48);
		fontUnselected = new Font("Garamond",Font.BOLD,28);
	}
	
	@Override
	public void update() {
		
		if(KeyManager.down && !selected)
			selected = true;
		else if(KeyManager.up && selected)
			selected = false;
		
		if(KeyManager.enter && selected){
			if(JOptionPane.showConfirmDialog(null, "Vrei sa iesi?", "Super Mario",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null) == JOptionPane.YES_NO_OPTION)
				System.exit(1);
		}
		else if(KeyManager.enter && !selected){
			State.setState(handler.getGame().getGameState());
			handler.getWorld().getScore().start();
		}
			
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(texture, 0, 0, BACK_WIDTH, BACK_HEIGHT, null);
		
		g.setColor(Color.BLACK);
		
		if(!selected){
			g.setFont(fontSelected);
			g.drawString("START", START_X, START_Y);
			
			g.setFont(fontUnselected);
			g.drawString("EXIT", EXIT_X, EXIT_Y);
		}
		else{
			g.setFont(fontUnselected);
			g.drawString("START", START_X, START_Y);
			
			g.setFont(fontSelected);
			g.drawString("EXIT", EXIT_X, EXIT_Y);
		}
	}	
	
}
