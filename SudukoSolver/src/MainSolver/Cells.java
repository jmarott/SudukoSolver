package MainSolver;

public class Cells {

	Cell[][] cells = new Cell[9][9];

	public Cells() {

		initialize_cells();

	}

	public void setValue(int x, int y, int value) {

		Cell cell = cells[x][y];

		int oldValue = cell.getValue();

		if (value == oldValue)
			return;

		if (isValuePossible(x, y, value) != true && value > 0 )
			return;

		cell.setValue(value);
//	    System.out.print(x & ',' &  y  & value ) ;
//	    System.out.print(y);
		if (value != oldValue && oldValue > 0)
			addToPossibleValues(x, y, oldValue);
		if (value > 0)
			newValueEffectOnCells(x, y, value);
	}

	private boolean isValuePossible(int ix, int iy, int ivalue) {
		// TODO Auto-generated method stub
		// Scan rows, columns and box to check if value is possible
		for (int x = 0; x < 9; x++) {
			if (ivalue == cells[x][iy].getValue()) {
				return false;
			}
		}
		for (int y = 0; y < 9; y++) {
			if (ivalue == cells[ix][y].getValue()) {
				return false;
			}
		}

		int x_box = ix / 3;
		int y_box = iy / 3;
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++) {
				if (ivalue == cells[x_box * 3 + x][y_box * 3 + y].getValue()) {
					return false;
				}
			}

		return true;

	}

	public int getValue(int x, int y) {
		return cells[x][y].getValue();
	}

	private void initialize_cells() {
		for (int x = 0; x < 9; x++)
			for (int y = 0; y < 9; y++) {
				cells[x][y] = new Cell();
			}
	}

	private boolean newValueEffectOnCells(int x, int y, int value) {
		removeFromRow(y, value);
		removeFromColumn(x, value);
		removeFromBox(x, y, value);
		return true;
	}

	private void removeFromRow(int y, int value) {
		for (int x = 0; x < 9; x++) {
			cells[x][y].removePossibleValue(value);
		}
	}

	private void removeFromColumn(int x, int value) {
		for (int y = 0; y < 9; y++) {
			cells[x][y].removePossibleValue(value);
		}
	}

	private void removeFromBox(int ix, int iy, int value) {
		int x_box = ix / 3;
		int y_box = iy / 3;
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++) {
				cells[x_box * 3 + x][y_box * 3 + y].removePossibleValue(value);
			}
	}

	public int[] getPossibleValues(int x, int y) {
		return cells[x][y].getPossibleValues();
	}
	public boolean[] getPossibleValuesBool(int x, int y) {
		return cells[x][y].getPossibleValuesBool();
	}


	private void addToPossibleValues(int x, int y, int oldValue) {
		// TODO Auto-generated method stub
		addInRow(y, oldValue);
		addInColumn(x, oldValue);
		addInBox(x, y, oldValue);
	}

	private void addInRow(int y, int value) {
		for (int x = 0; x < 9; x++) {
			if (isValuePossible(x, y, value) == true) {
				cells[x][y].addPossibleValue(value);
			}
		}
	}

	private void addInColumn(int x, int value) {
		for (int y = 0; y < 9; y++) {
			if (isValuePossible(x, y, value) == true)
				cells[x][y].addPossibleValue(value);
		}
	}

	private void addInBox(int ix, int iy, int value) {
		int x_box = ix / 3;
		int y_box = iy / 3;
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (isValuePossible(x_box * 3 + x, y_box * 3 + y, value) == true)
					cells[x_box * 3 + x][y_box * 3 + y].addPossibleValue(value);

	}
}