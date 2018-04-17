package dev.andrei.mario.tiles;

import java.awt.image.BufferedImage;

public class BigCloud extends Tiles{

	public BigCloud(int id, BufferedImage texture) {
		
		super(id, texture, 128, 64);
		tiles[id] = this;
	}

}
