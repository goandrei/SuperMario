package dev.andrei.mario.tiles;

import java.awt.image.BufferedImage;

public class Mountain extends Tiles{

	public Mountain(int id, BufferedImage texture) {
		super(id, texture, 128, 64);

		tiles[id] = this;
	}

}
