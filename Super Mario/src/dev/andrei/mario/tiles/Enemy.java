package dev.andrei.mario.tiles;

import java.awt.image.BufferedImage;

public class Enemy extends Tiles{

	public Enemy(int id, BufferedImage texture) {
		super(id, texture, 32,32);

		tiles[id] = this;
	}

}
