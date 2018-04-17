package dev.andrei.mario.tiles;

import java.awt.image.BufferedImage;

public class SmallCloud extends Tiles{

	public SmallCloud(int id, BufferedImage texture) {
		super(id, texture, 64, 64);
		tiles[id] = this;
	}

}
