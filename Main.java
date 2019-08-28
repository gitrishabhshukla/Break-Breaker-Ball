package brickBricker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GamePlay gamePlay = new GamePlay();
		
		JFrame obj = new JFrame();
		obj.setBounds(10,10,700,600);
		obj.setTitle("Break Breaker");
		obj.setVisible(true);
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
	}

}
