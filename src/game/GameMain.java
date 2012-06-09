package game;


import java.awt.Color;

import javax.swing.JFrame;


public class GameMain {

	
	public static void main(String[] args) {
		
		JFrame gameWindow = new JFrame();
		Game newGame = new Game();

		gameWindow.add(newGame);
		gameWindow.setSize(1200, 600);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.getContentPane().setBackground(Color.BLACK);
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);
		
		newGame.start();

	}
	
	
}
