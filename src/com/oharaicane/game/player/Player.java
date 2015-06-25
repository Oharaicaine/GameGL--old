package com.oharaicane.game.player;


import java.awt.geom.Rectangle2D;

import com.oharaicane.game.graphics.Shader;
import com.oharaicane.game.graphics.Texture;
import com.oharaicane.game.graphics.VertexArray;
import com.oharaicane.game.maths.Matrix4f;
import com.oharaicane.game.maths.Vector3f;
import com.oharaicane.game.utils.Index;


public class Player {

	private float size = 1.0f;
	private VertexArray mesh;
	private Texture texture;
	private PlayerInput playerInput;
	private PlayerMap playerMap;
	private Vector3f pos = new Vector3f();
	private Rectangle2D.Float bounds;
	
	public Player() {
		mesh = new VertexArray(Index.vertcies(size, 0.2f), Index.indices(), Index.tcs());
		texture = new Texture("res/player.png");
		bounds = new Rectangle2D.Float(pos.x, pos.y, size, size);
		playerInput = new PlayerInput(this);	
		playerMap = new PlayerMap(this);
	}
	
	public void update() {
		bounds = new Rectangle2D.Float(pos.x + (size/2.0f), pos.y + (size/2.0f), size, size);
		playerInput.update();
		playerMap.update();

		
	}
	
	public void render() {
		Shader.BASIC.enable();
		Shader.BASIC.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(-pos.x, -pos.y, 0.0f)));
		Shader.BASIC.disable();
		
		Shader.PLAYER.enable();
		texture.bind();
		mesh.render();
		Shader.PLAYER.disable();

	}
	
	public float getSize() {
		return size;
	}
	
	public Vector3f getPos() {
		return pos;
	}
	
	public Rectangle2D.Float getBounds() {
		return bounds;
	}
	

}
