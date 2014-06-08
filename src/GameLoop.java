

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;

public class GameLoop extends Applet implements Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x, y, z, c;
	public int k1 = -5398;// coordinates for road`s elements
	public int k2 = -5398 - 6000;// coordinates for road`s elements
	public Image offscreen;
	public Graphics d;
	public boolean left, right, down, up;
	public boolean canleft, canright, candown, canup;// if our car can move
														// there
	public Image bg, gameover;
	public Image userCar;
	public Image fallcar;
	public boolean loaded;
	int roadway;
	boolean anotherCar = true;
	int countCar = 0;
	public boolean colision = false;
	int score = -10;
	public Image moveRoad, moveRoad2; // for road`s elements
	public boolean inscriptionLevelTwo = false;
	public boolean formulaMode = false;

	Random randum = new Random();

	public void run() {
		x = 185;
		y = 450;

		while (true) {
			movedRoad(formulaMode);

			loadcars(formulaMode);

			colision(x, y, z, c, formulaMode);
			if (colision == true) {
				break;
			}

			if (anotherCar == true) {
				fallcars(formulaMode);
				roadway = randum.nextInt(2);
				if (roadway == 1) {
					z = 115;
					c = -80;
				} else {
					z = 185;
					c = -80;
				}

			}

			if (score == 100) {
				x = 185;
				y = 450;
				c = 800;
				countCar = 0;
				formulaMode = true;
				inscriptionLevelTwo = true;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				inscriptionLevelTwo = false;
			}

			c += 19 + countCar / 4;
			k1 += 32 + countCar / 3;
			k2 += 32 + countCar / 3;

			if (k1 >= 602) {
				k1 = -5398 - 6000;
			}
			if (k2 >= 602) {
				k2 = -5398 - 6000;
			}

			if (c <= 600) {
				anotherCar = false;
			} else {

				anotherCar = true;
			}

			if (x <= 110) {
				canleft = false;
			} else {
				canleft = true;
			}
			if (x >= 186) {
				canright = false;
			} else {
				canright = true;
			}
			if (y >= 480) {
				candown = false;
			} else {
				candown = true;
			}
			if (y <= 20) {
				canup = false;
			} else {
				canup = true;
			}

			if (left == true && canleft == true) {
				x -= 15;
			}
			if (right == true && canright == true) {
				x += 15;
			}
			if (up == true && canup == true) {
				y -= 5;
				c += 30;
				k1 += 30;
				k2 += 30;
			}
			if (down == true && candown == true) {
				y += 15;
			}

			try {
				Thread.sleep(105);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public boolean colision(int x, int y, int z, int c, boolean mode) {
		int width;
		int height;
		if (mode) {
			width = 40;
			height = 120;
		} else {
			width = 40;
			height = 77;
		}

		if (x < z + width && x + width >= z && c + height > y && c < y + height) {
			gameover = new ImageIcon("images\\game-over.png")
					.getImage();
			colision = true;
			repaint();
		}

		return colision;
	}

	private void loadcars(boolean mode) {

		if (mode) {
			userCar = new ImageIcon("images\\f1-ferrari.png")
					.getImage();
			bg = new ImageIcon("images\\road.jpg").getImage();
		} else {
			userCar = new ImageIcon("images\\car-red.png")
					.getImage();
			bg = new ImageIcon("images\\road2.jpg").getImage();
		}

		loaded = true;
		repaint();
	}

	public void movedRoad(boolean mode) { // for road`s elements
		if (mode) {
			moveRoad = new ImageIcon("images\\road-moves.png")
					.getImage();
			moveRoad2 = new ImageIcon("images\\road-moves.png")
					.getImage();
		} else {
			moveRoad = new ImageIcon("images\\road-moves2.png")
					.getImage();
			moveRoad2 = new ImageIcon("images\\road-moves2.png")
					.getImage();
		}

		repaint();
	}

	public void fallcars(boolean mode) {
		countCar++;
		score += 10;
		if (mode) {
			int i = randum.nextInt(2);
			if (i == 1) {
				fallcar = new ImageIcon("images\\f1-lotus-yellow.png")
						.getImage();
			} else {
				fallcar = new ImageIcon("images\\f1-lotus-blue.png")
						.getImage();
			}
		} else {
			int i = randum.nextInt(2);
			if (i == 1) {
				fallcar = new ImageIcon("images\\car-blue.png")
						.getImage();
			} else {
				fallcar = new ImageIcon("images\\car-yellow.png")
						.getImage();
			}

		}

		repaint();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			left = true;
		}
		if (e.getKeyCode() == 39) {
			right = true;
		}
		if (e.getKeyCode() == 38) {
			up = true;
		}
		if (e.getKeyCode() == 40) {
			down = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {

			left = false;
		}
		if (e.getKeyCode() == 39) {
			right = false;
		}
		if (e.getKeyCode() == 38) {
			c -= 18;
			k1 -= 25;
			k2 -= 25;
			up = false;
		}
		if (e.getKeyCode() == 40) {
			down = false;
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}
