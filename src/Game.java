

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Game extends GameLoop {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		setSize(350, 600);
		Thread th = new Thread(this);
		th.start();

		offscreen = createImage(350, 600);
		d = offscreen.getGraphics();
		
		addKeyListener(this);

	}

	public void paint(Graphics g) {
		g.drawImage(offscreen, 0, 0, this);
		if (loaded) {
			g.drawImage(bg, 0, 0, null);
			g.drawImage(moveRoad2, 0, k1, null);
			g.drawImage(moveRoad, 0, k2, null);
			g.drawImage(fallcar, z, c, null);
			g.drawImage(userCar, x, y, null);
			Font font = new Font("Serif", Font.BOLD, 27);
			String s = Integer.toString(score);

			if (inscriptionLevelTwo == true) {

				font = new Font("Serif", Font.BOLD, 25);
				g.setFont(font);
				g.setColor(Color.BLACK);
				g.drawString("GOD..", 131, 340);
				g.drawString("Level 2", 131, 370);
				g.setColor(Color.GREEN);
				g.drawString("GOD..", 130, 339);
				g.drawString("Level 2", 130, 369);
			}

			if (colision == true) {
				g.drawImage(gameover, 91, 200, null);
				font = new Font("Serif", Font.BOLD, 25);
				g.setFont(font);
				g.setColor(Color.BLACK);
				g.drawString("Your Score", 111, 340);
				g.drawString("is : " + s, 131, 370);
				g.setColor(Color.GREEN);
				g.drawString("Your Score", 110, 339);
				g.drawString("is : " + s, 130, 369);
			}

			g.setFont(font);

			g.setColor(Color.BLACK);
			g.drawString("Score:", 11, 21);
			g.setColor(Color.RED);
			g.drawString("Score:", 10, 20);
			g.setColor(Color.BLACK);
			g.drawString(s, 11, 51);
			g.setColor(Color.RED);
			g.drawString(s, 10, 50);
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

}
