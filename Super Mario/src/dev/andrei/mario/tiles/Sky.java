package dev.andrei.mario.tiles;

import java.awt.image.BufferedImage;

public class Sky extends Tiles{

	public Sky(int id, BufferedImage texture) {
		super(id, texture,32,32);
		tiles[id] = this;
	}

}
