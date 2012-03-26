package tetrix.core;

public class Tetromino {
	private boolean[][] Lshape = {{true, false},{true, false},{true,true}};
	private boolean[][] Jshape = {{false, true},{false, true},{true,true}};
	private boolean[][] Tshape = {{true, true, true},{false,true,false}};
	private boolean[][] Zshape = {{true, true, false},{false, true, true}};
	private boolean[][] Sshape = {{false, true, true},{true, true, false}};
	private boolean[][] Ishape = {{true, false},{true, false},{true,false},{true, false}};
	int which;
	private boolean[][] Oshape = {{true, true},{true, true}};
	public enum Shape {
		Lshape, 
		Jshape,
		Tshape,
		Zshape,
		SShape,
		Ishape,
		Oshape,
	}

	public Tetromino(){
		init();
	}

	public void fall(Shape shape){

	}

	private void init(){
		which = (int)(Math.random()*7+0.49);

	}
	public boolean[][] getShape(){
		if(which == 1)
			return Lshape;
		else if(which == 2)
			return Jshape;
		else if(which == 3)
			return Tshape;
		else if(which == 4)
			return Zshape;
		else if(which == 5)
			return Sshape;
		else if(which == 6)
			return Ishape;
		else
			return Oshape;
	}
}



