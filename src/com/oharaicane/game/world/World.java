package com.oharaicane.game.world;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.oharaicane.game.blocks.Block;
import com.oharaicane.game.blocks.StoneFloorBlock;
import com.oharaicane.game.blocks.StoneWallBlock;
import com.oharaicane.game.gamestates.GameState;
import com.oharaicane.game.graphics.Shader;
import com.oharaicane.game.maths.Maths;
import com.oharaicane.game.maths.Vector3f;
import com.oharaicane.game.player.Player;
import com.oharaicane.game.utils.Utils;

public class World {
	
	private BufferedImage map;
	private float mapSize = 20;

	public static ArrayList<Block> blocks = new ArrayList<Block>();
	
	public static CopyOnWriteArrayList<Block> worldMap = new CopyOnWriteArrayList<Block>();
	public static CopyOnWriteArrayList<Block> playerMap = new CopyOnWriteArrayList<Block>();
	
	public World() {
		map = Utils.loadBufferedImage("res/map.png");
		initMap();
	}
	
	private void initMap() {
		
		for(int x = 0; x < mapSize; x++){
			for(int y = 0; y < mapSize; y++){
				int col = map.getRGB(x, y);
				switch(col & 0xFFFFFF){
					case 0x808080:
						worldMap.add(new StoneFloorBlock(new Vector3f(x-10.0f, y-10.0f, 0.0f)));
						break;
					case 0x404040:
						worldMap.add(new StoneWallBlock(new Vector3f(x-10.0f, y-10.0f, 0.0f)));
						break;	
				}
			}
		}	
	}

	public void update(){
	}
	
	public void render(){
		Shader.BASIC.enable();
		for (Block block : playerMap) {
			block.render();
		}
		Shader.BASIC.disable();
			
	}

}
