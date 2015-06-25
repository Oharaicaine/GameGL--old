package com.oharaicane.game.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;


public class KeyboardInput extends GLFWKeyCallback{
	
	public static boolean[] keys = new boolean[512];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		//	keys if action does not equal release
		keys[key] = action != GLFW.GLFW_RELEASE;
	}
	
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}

}
