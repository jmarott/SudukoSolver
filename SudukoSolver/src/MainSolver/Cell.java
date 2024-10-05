package MainSolver;

public class Cell {

	private int value = 0;
	private boolean  possibleValues[] = { true, true, true, true, true, true, true, true, true }; 
	
	public Cell() {
	
	}

	
	
	public int getValue() {
		return value;
	}

	public void setValue(int ivalue) {
		this.value = ivalue;
	}

	public int[] getPossibleValues() {
		int counter = 0;
		int possiblevaluelist[] = new int[9];
		for (int x = 0; x < 9 ; x++) {
			    if ( this.possibleValues[ x  ] ) {
			    	possiblevaluelist[ counter++ ] = x + 1;
			    }	
		}		
		return possiblevaluelist;
	}
	public boolean[] getPossibleValuesBool() {
		return this.possibleValues;
	}

	public void removePossibleValue(int ivalue) {
		this.possibleValues[ ivalue - 1 ] = false ;
	}

	public void addPossibleValue(int ivalue) {
		this.possibleValues[ ivalue - 1 ] = true ;
	}

}
