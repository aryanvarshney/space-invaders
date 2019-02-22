import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;

public class Minesweeper implements ActionListener{
	JFrame frame = new JFrame("Minesweeper");
	JButton reset = new JButton("Reset");
	static JButton[][] buttons = new JButton[20][20];
	Container grid = new Container();
	static Piece[][] board = new Piece[20][20];
	
	public static void main(String[] args){
		new Minesweeper();
	}
	
	public Minesweeper(){
		frame.setSize(1000, 1000);
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.NORTH);
		reset.addActionListener(this);
		grid.setLayout(new GridLayout(20,20));
		for(int i=0; i<buttons.length; i++){
			for (int j=0; j<buttons.length; j++){
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				buttons[i][j].setBackground(Color.BLACK);
				buttons[i][j].setForeground(Color.BLACK);
				grid.add(buttons[i][j]);
			}
		}
		
		frame.add(grid, BorderLayout.CENTER);
		makeBoard();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void makeBoard(){
		int xcoord, ycoord;
		
		//set the specified number of bombs
		for (int i=0; i<50; i++){
			//generate a random index
			xcoord = (int)(Math.random()*20);
			ycoord = (int)(Math.random()*20);
			
			//if there is no Piece at the generated index, set a bomb there
			if(board[ycoord][xcoord]==null){
				board[ycoord][xcoord] = new Piece(xcoord, ycoord, -1, true);
				buttons[ycoord][xcoord].setText("x");
			}

			//if there is already a Piece at the index, regenerate another index
			else{
				i--;
				continue;
			}
		}
		
		//create an empty piece in all the spaces without a bomb (so there are no more null pointer exceptions)
		for(int j = 0; j < 20; j++){
			for (int k=0; k<20; k++){
				if(board[j][k]==null)
					board[j][k] = new Piece(j,k);
			}
		}

		//assign values to the squares that are around the mines
		for(int a = 0; a < 20; a++){
			for (int b = 0; b < 20; b++){
				if(!board[a][b].getBomb()){
					board[a][b].setNumber(assign(a,b));
					buttons[a][b].setText(board[a][b].getNumber() + "");
				}
			}
		}
	}
	
	public static int assign(int y, int x){
		int counter = 0;
		if (x-1>=0 && y+1<board.length && board[y+1][x-1].getBomb()) counter++;
		if(x-1>=0 && board[y][x-1].getBomb()) counter++;
		if(x-1>=0 && y-1>0 && board[y-1][x-1].getBomb()) counter++;
		if(y+1<board.length && board[y+1][x].getBomb()) counter++;
		if(y-1>0 && board[y-1][x].getBomb()) counter++;
		if(x+1<board.length && y+1<board.length && board[y+1][x+1].getBomb()) counter++;
		if(x+1<board.length && board[y][x+1].getBomb()) counter++;
		if(x+1<board.length && y-1>0 && board[y-1][x+1].getBomb()) counter++;
		
		return counter;
	}
	
	public static void reveal(int y, int x){
		if (board[y][x].getNumber()==-1){
			return;
		}
		
		else if(board[y][x].getNumber()!=0){
			buttons[y][x].setBackground(Color.WHITE);
			board[y][x].setSquare(true);
			return;
		}
		
		else{
			buttons[y][x].setBackground(Color.WHITE);
			board[y][x].setSquare(true);
			if(x-1>=0 && y+1<board.length) reveal(y+1, x-1);
			if(x-1>=0) reveal(x-1, y);
			if(x-1>=0 && y-1>0) reveal(x-1, y-1);
			if(y+1<board.length) reveal(x, y+1);
			if(y-1>0) reveal(x, y-1);
			if(x+1<board.length && y+1<board.length) reveal(x+1, y+1);
			if(x+1<board.length) reveal(x+1, y);
			if(x+1<board.length && y-1>0) reveal(x+1, y-1);
		}
	}
	
	/*public static void reveal(int y, int x){
		for (int i = 0; i < board.length; i++) {
			
		}
	}*/
	
	public void setFrame(boolean x){
		frame.setVisible(x);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(reset)){
			new Minesweeper();
		}
		else{
			for (int i=0; i<board.length; i++){
				for (int j=0; j<board[0].length; j++){
					if(event.getSource().equals(buttons[i][j])){
						//buttons[i][j].setBackground(Color.WHITE);
						//buttons[i][j].setForeground(Color.BLACK);
						
						//if user clicks a bomb, turn all the squares white
						if(buttons[i][j].getText().equals("x")){
							for(int a=0; a<board.length; a++){
								for(int b=0; b<board.length; b++){
									buttons[a][b].setBackground(Color.WHITE);
									board[a][b].setSquare(true);
								}
							}
						}
						
						//if the user clicks a space with no bombs around it, implement reveal method
						else if(buttons[i][j].getText().equals("0")){
							reveal(i, j);
							//buttons[i][j].setBackground(Color.WHITE);
							//board[i][j].setSquare(true);
							
						}
						
						//otherwise, record the square as clicked
						else{
							buttons[i][j].setBackground(Color.WHITE);
							board[i][j].setSquare(true);
						}
					}
				}
			}
		}
		
	}
}
