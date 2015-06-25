package com.oharaicane.game.blocks;

import com.oharaicane.game.graphics.Texture;
import com.oharaicane.game.graphics.VertexArray;
import com.oharaicane.game.maths.Vector3f;
import com.oharaicane.game.utils.Index;

public class StoneWallBlock extends Block{

	public StoneWallBlock(Vector3f pos) {
		super(pos);
		this.pos = pos;
		this.mesh = new VertexArray(Index.vertcies(size, 0.0f), Index.indices(), Index.tcs());
		this.texture = new Texture("res/stonewall.png");
	}
	
	@Override
	public void update(){
		
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
}
