package com.oharaicane.game.gamestates;

import java.util.ArrayList;

public class GameStateManager {

	private static ArrayList<States> gameStates;
	private static int currentState;
	//private static State currentState = null;
	
	public static final int GAMESTATE = 0;
	
	public static boolean gamestateLoaded = false;
	
	public GameStateManager(){
		
		
		gameStates = new ArrayList<States>();
		
		gameStates.add(new GameState());

		currentState = GAMESTATE;
		
	}
	
	public static void setState(int state){
		currentState = state;
		gameStates.get(currentState).init();
		/*
		if(!gamestateLoaded)gameStates.get(currentState).init();
		
		if(gameStates.get(currentState) == gameStates.get(LOADSTATE)){
			gameStates.get(GAMESTATE).init();
			gamestateLoaded = true;
		}
		*/
	}

	
	 public void update() {
		 gameStates.get(currentState).update();
	 }
	 
	 public void render(){
		 gameStates.get(currentState).render();
	 }
 
}
