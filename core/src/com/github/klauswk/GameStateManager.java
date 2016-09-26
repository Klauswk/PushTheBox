package com.github.klauswk;

import java.util.Stack;

import com.github.klauswk.state.State;

public class GameStateManager {

	private Stack<State> states;
	
	public GameStateManager() {
		states = new Stack<State>();
	}

	public void push(State state){
		states.push(state);
	}
	
	public State pop(){
		return states.pop();
	}
	
	public void set(State state){
		pop();
		push(state);
	}
	
	public State peek(){
		return states.peek();
	}
}
