package com.oharaicane.game.gamestates;

import com.oharaicane.game.player.Player;
import com.oharaicane.game.world.World;

public class GameState extends States {

	public Player player;
	public World world;
	
	public GameState() {
		
	}

	@Override
	public void init() {
		world = new World();
		player = new Player();

	}

	@Override
	public void update() {
		player.update();

	}

	@Override
	public void render() {
		world.render();
		player.render();

	}

}
