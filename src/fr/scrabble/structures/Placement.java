package fr.scrabble.structures;

public class Placement {
	
	Lettre l;
	Case c;
	int lig,col;
	
	public Placement(Lettre l, Case c, int lig, int col) {
		super();
		this.l = l;
		this.c = c;
		this.lig = lig;
		this.col = col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + col;
		result = prime * result + lig;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Placement other = (Placement) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (col != other.col)
			return false;
		if (lig != other.lig)
			return false;
		return true;
	}
	
	public Lettre getLetter() {
		return this.l;
	}
	
	public int getLine() {
		return this.lig;
	}
	
	public int getColumn() {
		return this.col;
	}
	
	public Case getCase() {
		return this.c;
	}
}
