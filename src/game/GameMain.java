package game;


import javax.swing.JFrame;


public class GameMain {

	
	public static void main(String[] args) {
		
		JFrame gameWindow = new JFrame();
		Game newGame = new Game();

		gameWindow.add(newGame);
		gameWindow.setSize(1200, 600);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);
		
		newGame.start();

	}
	
	
}
