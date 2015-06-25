package com.oharaicane.game.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GLContext;

import com.oharaicane.game.gamestates.GameStateManager;
import com.oharaicane.game.graphics.Shader;
import com.oharaicane.game.input.KeyboardInput;
import com.oharaicane.game.input.MouseInput;
import com.oharaicane.game.maths.Matrix4f;
import com.oharaicane.game.player.Player;
import com.oharaicane.game.world.World;

public class Main implements Runnable{
	public static int width = 1200;
	public static int height = 675;
	
	private Thread thread;
	private boolean running = false;
	
	private long window;
	private GLFWKeyCallback keyCallback;
	private GLFWCursorPosCallback mouseCallback;
	private GameStateManager gsm;
	
	public void start() {
		running = true;
		thread = new Thread(this, "Main");
		thread.start();
	}
	
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ticks, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			if (glfwWindowShouldClose(window) == GL_TRUE)
				running = false;
		}
		
		glfwDestroyWindow(window);
		keyCallback.release();
		mouseCallback.release();
		glfwTerminate();
	}
	
	private void init() {
		keyCallback = new KeyboardInput();
		mouseCallback = new MouseInput();
		
		if(glfwInit() != GL_TRUE){
			System.err.println("Could not initialize GLFW!");
			return;
		}

		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width, height, "Demo", NULL, NULL);
		if(window == NULL){
			System.err.println("Could not create GLFW window!");
			return;
		}
		
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (GLFWvidmode.width(vidmode) - width) / 2, (GLFWvidmode.height(vidmode) - height) / 2);
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwSetKeyCallback(window, keyCallback);
		glfwSetCursorPosCallback(window, mouseCallback);

		glfwShowWindow(window);
		GLContext.createFromCurrent();
		
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glEnable(GL_DEPTH_TEST);
		glActiveTexture(GL_TEXTURE1);
		
		//	Start Shaders
		Shader.loadAll();
		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
		Shader.BASIC.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.BASIC.setUniform1i("tex", 1);
		Shader.PLAYER.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.PLAYER.setUniform1i("tex", 1);
		//	End Shaders
		
		//	Load Objects
		gsm = new GameStateManager();
		GameStateManager.setState(GameStateManager.GAMESTATE);
		
	}

	
	
	private void update() {
		glfwPollEvents();
		
		//	Start Update
		gsm.update();
		//	End Update
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		//	Start Rendering
		gsm.render();
		//	End Rendering
		
		//	error code
		int error = glGetError();
		if(error != GL_NO_ERROR)System.out.println(error);
		glfwSwapBuffers(window);
	}
	
	
	public static void main(String[] args) {
		new Main().start();

	}

}
