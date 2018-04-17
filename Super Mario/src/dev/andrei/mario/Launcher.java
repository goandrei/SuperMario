package dev.andrei.mario;

import dev.andrei.mario.game.Game;

public class Launcher {

	public static void main(String args[]){
		
		Game game = new Game("Super Mario",800,512);
		game.start();
	}
}
