package tetrix.core;

public class T extends Tetromino{

	public T(int startX){
		this(startX, 150, 20);
	}

	public T(int startX, int leftIn){
		this(startX, leftIn, 20);
	}
	
	public T(int startX, int leftIn, int fallspeed){
		super(startX, leftIn, fallspeed);
	}

	public void build() {		
		Square[] s = super.getSquares();
		for(int i = 0; i < 4; i++){
			s[i] = new Square(new Position(super.getLeftIn(0)+(20*super.getStartX())+i*20, 100));
			if(i > 2)
				s[i] = new Square(new Position(super.getLeftIn(-40)+(20*super.getStartX())+i*20, 120));
		}
	}

	public boolean isPainted(float y, float x){
		Square[] s = super.getSquares();
		if(s[1].getY() == y+20 && s[1].getX() == x)
			return true;
		else if(s[2].getY() == y+20 && s[2].getX() == x)
			return true;
		else if(s[3].getY() == y+20 && s[3].getX() == x)
			return true;

		return false;
	}
}
