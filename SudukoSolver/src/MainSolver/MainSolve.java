package MainSolver;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import java.awt.Font;

public class MainSolve extends Canvas {
	/**
	 * 
	 */
	public static int WIN_WIDTH = 600;
	public static int WIN_HEIGHT = 600;
	public static int MATRIX_OFFSET = 50;
	public static int CELL_SIZE = 50;

	public static int Cursor_xm = 0;
	public static int Cursor_x = 0;
	public static int Cursor_ym = 0;
	public static int Cursor_y = 0;

	public static Cells cells = new Cells();

	public static final long serialVersionUID = 8331343925641205349L;
	public static boolean showPossibleValues = false;

	public static void main(String[] args) {
		JFrame frame = new JFrame("My Drawing");
		Canvas canvas = new MainSolve();
		canvas.setSize(WIN_HEIGHT, WIN_WIDTH);
		frame.add(canvas);
		frame.pack();
		frame.createBufferStrategy(3);
		frame.setVisible(true);

		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
//		            Paddle paddle = panel.getPaddle();
				int keyCode = e.getKeyCode();

				int x = Cursor_xm * 3 + Cursor_x;
				int y = Cursor_ym * 3 + Cursor_y;

				if (keyCode == KeyEvent.VK_DOWN) {
					y += 1;
				}
				if (keyCode == KeyEvent.VK_UP) {
					y -= 1;
				}
				if (keyCode == KeyEvent.VK_RIGHT) {
					x += 1;
				}
				if (keyCode == KeyEvent.VK_LEFT) {
					x -= 1;
				}
				if (keyCode >= '0' && keyCode <= '9') {
					char ch = (char) keyCode;
					cells.setValue(x, y, Character.getNumericValue(ch));
					;
				}
				if (keyCode == ' ') {
					cells.setValue(x, y, 0);
					;
				}
				if (keyCode == 'R') {
					cells = new Cells();
					;
				}
				if (keyCode == 'D') {
					if (showPossibleValues == false)
						showPossibleValues = true;
					else
						showPossibleValues = false;
					;
				}

				if (x > 8)
					x = 0;
				if (x < 0)
					x = 8;
				if (y > 8)
					y = 0;
				if (y < 0)
					y = 8;
				Cursor_xm = x / 3;
				Cursor_x = x - Cursor_xm * 3;
				Cursor_ym = y / 3;
				Cursor_y = y - Cursor_ym * 3;
				canvas.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

	}

	public void paint(Graphics g) {
		// g.fillOval(100, 100, 200, 200);
		drawMatrix(g);
		drawCursor(g);
		drawNumbers(g);
		if (showPossibleValues == true)
			drawPossibleNumbers(g);
		drawCellPossibleValuesBottom(g);
	}

	private void drawCellPossibleValuesBottom(Graphics g) {
		// TODO Auto-generated method stub
		int possibleNumbers[] = cells.getPossibleValues(Cursor_xm * 3 + Cursor_x, Cursor_ym * 3 + Cursor_y);
		for (int i = 0; i < 9; i++) {

			if (possibleNumbers[i] > -1)
				g.drawString(Integer.toString(possibleNumbers[i]), MATRIX_OFFSET + i * CELL_SIZE,
						MATRIX_OFFSET + 10 * CELL_SIZE);

		}
	}

	private void drawNumbers(Graphics g) {
		Font font = new Font("Arial", Font.BOLD, 32);
		g.setFont(font);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				Integer num = cells.getValue(x, y);
				if (num > 0)
					g.drawString(Integer.toString(num), MATRIX_OFFSET + x * CELL_SIZE + CELL_SIZE / 3,
							MATRIX_OFFSET + y * CELL_SIZE + CELL_SIZE * 7 / 10);

			}
		}

	}

	private void drawPossibleNumbers(Graphics g) {
		Font font = new Font("Arial", Font.PLAIN, 12);
		g.setFont(font);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				int num = cells.getValue(x, y);
				if (num == 0) {
					drawCellPossibleValues(g, x, y);
				}
			}
		}

	}

	private void drawCellPossibleValues(Graphics g, int ix, int iy) {
		// TODO Auto-generated method stub
		boolean possibleValues[] = cells.getPossibleValuesBool(ix, iy);
		for (int z = 0; z < 9; z++) {
			if (possibleValues[z] == true) {
				int x = z % 3;
				int y = z / 3;
				Integer num = z + 1;
				if (num > 0)
					g.drawString(Integer.toString(num), MATRIX_OFFSET + ix * CELL_SIZE + 6 + 14 * x,
							MATRIX_OFFSET + iy * CELL_SIZE + 15 + 14 * y);

			}
		}

	}

	private void drawCursor(Graphics g) {
		g.drawRect(MATRIX_OFFSET + Cursor_xm * CELL_SIZE * 3 + Cursor_x * CELL_SIZE + 5,
				MATRIX_OFFSET + Cursor_ym * CELL_SIZE * 3 + Cursor_y * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);

	}

	private void drawMatrix(Graphics g) {
		for (int xm = 0; xm < 3; xm++) {
			for (int ym = 0; ym < 3; ym++) {
				for (int zm = 0; zm < 3; zm++) {
					g.drawRect(MATRIX_OFFSET + xm * CELL_SIZE * 3 + zm, MATRIX_OFFSET + ym * CELL_SIZE * 3 + zm,
							CELL_SIZE * 3, CELL_SIZE * 3);
				}
				drawBox(g, xm, ym);
			}
		}

	}

	private void drawBox(Graphics g, int xm, int ym) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				drawCell(g, xm, ym, x, y);
			}
		}
	}

	private void drawCell(Graphics g, int xm, int ym, int x, int y) {
		g.drawRect(MATRIX_OFFSET + xm * CELL_SIZE * 3 + x * CELL_SIZE,
				MATRIX_OFFSET + ym * CELL_SIZE * 3 + y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
	}
}