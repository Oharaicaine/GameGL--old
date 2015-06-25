package com.oharaicane.game.player;

import org.lwjgl.glfw.GLFW;

import com.oharaicane.game.blocks.Block;
import com.oharaicane.game.input.KeyboardInput;
import com.oharaicane.game.input.MouseInput;
import com.oharaicane.game.main.Main;
import com.oharaicane.game.utils.Utils;
import com.oharaicane.game.world.World;



public class PlayerInput {

	private Player player;
	private boolean canMove = false;
	
	public PlayerInput(Player player) {
		this.player = player;
	}
	
	public void update(){
		float mx = (float)(MouseInput.mouseX / Main.width) * 20.0f - 10.0f;
		float my = (float)(MouseInput.mouseY / Main.height) * 11.25f - 5.625f;
		
		for (Block block : World.playerMap){
			if(player.getBounds().intersects(block.getBounds())){
				if(block.isSolid()){
					canMove = false;
				}else{
					canMove = true;
				}
			}
			
		}
		
		if(!KeyboardInput.keys[GLFW.GLFW_KEY_LEFT_SHIFT] && canMove){
			player.getPos().x += Utils.lerp(player.getPos().x, mx, 1.0f);
			player.getPos().y += Utils.lerp(player.getPos().y, my, 1.0f);
		}
		/*
		if (Input.keys[GLFW.GLFW_KEY_W])player.pos.y -= 0.1f;
		if (Input.keys[GLFW.GLFW_KEY_S])player.pos.y += 0.1f;
		if (Input.keys[GLFW.GLFW_KEY_A])player.pos.x -= 0.1f;
		if (Input.keys[GLFW.GLFW_KEY_D])player.pos.x += 0.1f;
		*/
	}

}
