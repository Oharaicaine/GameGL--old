package com.oharaicane.game.gamestates;

public abstract class States {

	protected GameStateManager gsm;
	
	public abstract void init();
	
	public abstract void update();
	
	public abstract void render();

}
