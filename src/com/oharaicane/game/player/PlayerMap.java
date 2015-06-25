package com.oharaicane.game.player;

import com.oharaicane.game.blocks.Block;
import com.oharaicane.game.maths.Maths;
import com.oharaicane.game.world.World;

public class PlayerMap {

	private Player player;
	
	public PlayerMap(Player player) {
		this.player = player;
	}
	
	public void update(){
		for (Block blocks : World.worldMap){
			//System.out.println(Maths.DistanceBetweenTwoVectors(player.getPos(), blocks.getPos()));
			if(Maths.DistanceBetweenTwoVectors(player.getPos(), blocks.getPos()) < 20 
					&& !World.playerMap.contains(blocks)){
				World.playerMap.add(blocks);
			}
			if(Maths.DistanceBetweenTwoVectors(player.getPos(), blocks.getPos()) > 20
					&& World.playerMap.contains(blocks)){
				World.playerMap.remove(blocks);
			}
		}
	}

}
