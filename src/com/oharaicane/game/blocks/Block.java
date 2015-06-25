package com.oharaicane.game.blocks;

import java.awt.geom.Rectangle2D;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import com.oharaicane.game.graphics.Shader;
import com.oharaicane.game.graphics.Texture;
import com.oharaicane.game.graphics.VertexArray;
import com.oharaicane.game.maths.Matrix4f;
import com.oharaicane.game.maths.Vector3f;

public class Block {
	
	protected static float size = 1.0f;
	protected VertexArray mesh;
	protected Texture texture;
	
	protected Vector3f pos;
	protected Rectangle2D.Float bounds;

	public Block(Vector3f pos) {	
		bounds = new Rectangle2D.Float(pos.x - (size/2.0f), pos.y - (size/2.0f), size, size);
	}
	
	public void update() {
	}
	
	public void render() {

		Shader.BASIC.setUniformMat4f("ml_matrix", Matrix4f.translate(pos));
		texture.bind();
		mesh.render();

	}
	
	public static float getSize() {
		return size;
	}
	
	public Vector3f getPos() {
		return pos;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public Rectangle2D.Float getBounds() {
		return bounds;
	}
	
	
}
