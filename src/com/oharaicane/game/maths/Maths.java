package com.oharaicane.game.maths;

public class Maths {

	private Maths() {}

	public static float DistanceBetweenTwoVectors(Vector3f vec, Vector3f vec2){
		float result = (float) Math.sqrt((vec.x-vec2.x)*(vec.x-vec2.x) + (vec.y-vec2.y)*(vec.y-vec2.y));
		return result;
	}
	
}
