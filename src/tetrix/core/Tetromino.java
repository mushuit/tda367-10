package tetrix.core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class representing several squares put into a shape.
 * 
 * @author Magnus Huttu
 *
 */

public class Tetromino {
	private Square[] square;
	private Position[] squarePos;
	private int blockForm;
	private int size;
	private int x;
	private boolean[] hasSquare;
	private int fallspeed;

	public Tetromino() throws SlickException {
		this((int)((Math.random()*10)+0.49));
	}

	public Tetromino(int x) throws SlickException {
		this(x, 1);

	}	

	public Tetromino(int x, int fallspeed) throws SlickException {
		this(x,(int)((Math.random()*7)+0.49), fallspeed);

	}	

	public Tetromino(int position, int blockForm, int fallspeed) throws SlickException {
		squarePos = new Position[size];
		hasSquare = new boolean[8];
		this.blockForm = blockForm;
		this.size = 8;
		this.x = position;
		this.fallspeed = fallspeed;
		square = new Square[4];
		init();
		build();

	}

	public void init(){
		for(boolean b : hasSquare)
			b = false;
	}

	public float falling(float f){
		return f+fallspeed;
	}

	public void update(){
		for(Position p : squarePos){
			if(p != null)
				p.setY(falling(p.getY()));
		}
	}

	private void build(){

		switch (blockForm) {

		case 1:
			hasSquare[0] = true;
			hasSquare[1] = true;
			hasSquare[2] = true;
			hasSquare[3] = true;
			hasSquare[4] = false;
			hasSquare[5] = false;
			hasSquare[6] = false;
			hasSquare[7] = false;
			break;

		case 2: 
			hasSquare[0] = false;
			hasSquare[1] = true;
			hasSquare[2] = true;
			hasSquare[3] = true;
			hasSquare[4] = false;
			hasSquare[5] = false;
			hasSquare[6] = true;
			hasSquare[7] = false;
			break;

		case 3: 
			hasSquare[0] = false;
			hasSquare[1] = true;
			hasSquare[2] = true;
			hasSquare[3] = false;
			hasSquare[4] = false;
			hasSquare[5] = false;
			hasSquare[6] = true;
			hasSquare[7] = true;
			break;

		case 4: 
			hasSquare[0] = false;
			hasSquare[1] = false;
			hasSquare[2] = true;
			hasSquare[3] = true;
			hasSquare[4] = false;
			hasSquare[5] = true;
			hasSquare[6] = true;
			hasSquare[7] = false;
			break;

		case 5: 
			hasSquare[0] = false;
			hasSquare[1] = true;
			hasSquare[2] = true;
			hasSquare[3] = true;
			hasSquare[4] = false;
			hasSquare[5] = false;
			hasSquare[6] = false;
			hasSquare[7] = true;
			break;

		case 6: 
			hasSquare[0] = false;
			hasSquare[1] = true;
			hasSquare[2] = true;
			hasSquare[3] = true;
			hasSquare[4] = false;
			hasSquare[5] = true;
			hasSquare[6] = false;
			hasSquare[7] = false;
			break;

		case 7: 
			hasSquare[0] = false;
			hasSquare[1] = false;
			hasSquare[2] = true;
			hasSquare[3] = true;
			hasSquare[4] = false;
			hasSquare[5] = false;
			hasSquare[6] = true;
			hasSquare[7] = true;
			break;


		default: 
			break;

		}
		//TODO fix logic
		int i = 0;
		int o = 0;
		for(boolean b : hasSquare){
			if(b && i < 4){
				square[o] = new Square(new Position(150+(20*x)+i*20, 100));
				System.out.println(square[o].getPos().toString());
				o++;
			}
			else if(b){
				square[o] = new Square(new Position(150+(20*x)+i*20, 120));
				System.out.println(square[o].getPos().toString());
				o++;
			}
			i++;
		}
	}

	public Position[] getPos(){
		Position[] pos = new Position[4];
		int i = 0;
		for(Square s : square){
			System.out.println(s.getPos().toString());
			pos[i] = s.getPos();
					i++;
		}
			
		return pos;
	}
	
	
	public Position getPos(int h){
		Position[] pos = new Position[4];
		int i = 0;
		for(Square s : square){
			if(hasSquare[i])
			pos[i] = s.getPos();
					i++;
		}
			
		return pos[h];
	}
	
	public Square[] getSquares(){
		return square;
	}
}



