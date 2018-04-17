package dev.andrei.mario.tiles;

import java.awt.image.BufferedImage;

public class Ground extends Tiles{

	public Ground(int id, BufferedImage texture) {
		super(id, texture,32,32);
		tiles[id] = this;
	}

}
