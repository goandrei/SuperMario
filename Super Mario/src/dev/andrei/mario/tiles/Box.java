package dev.andrei.mario.tiles;

import java.awt.image.BufferedImage;

public class Box extends Tiles{

	public Box(int id, BufferedImage texture) {
		super(id, texture, 32, 32);

		tiles[id] = this;
	}

}
