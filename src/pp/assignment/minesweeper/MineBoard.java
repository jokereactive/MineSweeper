package pp.assignment.minesweeper;

import java.util.Random;

import android.content.Context;
import android.util.Log;


public class MineBoard {
	Block[][] matrix;
	int height;
	int width;
	Context c;
	
	public int revealBlock(int h,int v){
		matrix[h][v].reveal();
		return matrix[h][v].getType();
	}
	
	public MineBoard(Difficulty parameters,Context con){
		height=parameters.getHeight();
		width=parameters.getWidth();
		matrix=new Block[height][width];
//		for(int i=0;i<height;i++){
//			for(int j=0;j<width;j++){
//				matrix[i][j]=new Block(con);
//				matrix[i][j].setType(Utils.PREF_SAFE);
//			}
//		}
		
	}
	
	public void setup() {
		spreadMines();
		plotPower();
		calculateValues();
	}
	
	public void spreadMines(){
		int nummines=height;
		Random random = new Random();
		int i=0;
		while( i < nummines)
		{
			int row = random.nextInt(height);
			int column = random.nextInt(width);
			if (matrix[row][column].getType()!=Utils.PREF_MINE)
			{
				matrix[row][column].setType(Utils.PREF_MINE);
				i++;
				Log.d("MINE","mine added at "+String.valueOf(row)+String.valueOf(column));
			}
			else{
				
			}
		}
	}
	
	public Boolean isInMatrix(int x,int y){
		if(x>=0 && x<height && y>=0 && y<width){
			return true;
		}
		return false;
	}
	
	public void calculateValues(){
//		declare i as 0
//		declare j as 0
//
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if(matrix[i][j].getType()==Utils.PREF_SAFE){
					int minecount=0;
					if(isInMatrix(i-1,j-1)){
						if(matrix[i-1][j-1].getType()==Utils.PREF_MINE){
							minecount+=1;
						}
					}
					if(isInMatrix(i-1,j)){
						if(matrix[i-1][j].getType()==Utils.PREF_MINE){
							minecount+=1;
						}
					}
					if(isInMatrix(i-1,j+1)){
						if(matrix[i-1][j+1].getType()==Utils.PREF_MINE){
							minecount+=1;
						}
					}
					if(isInMatrix(i,j-1)){
						if(matrix[i][j-1].getType()==Utils.PREF_MINE){
							minecount+=1;
						}
					}
					if(isInMatrix(i+1,j-1)){
						if(matrix[i+1][j-1].getType()==Utils.PREF_MINE){
							minecount+=1;
						}
					}
					if(isInMatrix(i+1,j+1)){
						if(matrix[i+1][j+1].getType()==Utils.PREF_MINE){
							minecount+=1;
						}
					}
					if(isInMatrix(i,j+1)){
						if(matrix[i][j+1].getType()==Utils.PREF_MINE){
							minecount+=1;
						}
					}
					if(isInMatrix(i+1,j)){
						if(matrix[i+1][j].getType()==Utils.PREF_MINE){
							minecount+=1;
						}
					}
					matrix[i][j].setValue(minecount);
				}
			}
		}
//		go to block at row i of the matrix while i is less than height of the matrix
//			go to block at column j of the matrix while j is less than the width of the matrix
//				if the block at (i,j) is not a mine
//					initiate the mine count to zero
//					initiate a list of 8 variables representing contribution from points (i-1,j-1),(i-1,j),(i-1,j+1),(i,j-1),(i,j),(i+1,j+1),(i+1,j-1),(i+1,j-1) in that order
//					if i is equal to zero
//						remove all points with first element as (i-1) from the list
//					end
//					if j is equal to zero
//						remove all points with second element as (j-1) from the list
//					end
//					if i is equal to height of the matrix
//						remove all points with first element as (i+1) from the list
//					end
//					if j is equal to width of the matrix
//						remove all points with second element as (j+1) from the list
//					end
//					for the remaining elements in the list
//						if the block at these elements is not a mine	
//							increment mine count
//						end
//					end
//					return minecount
//				end
//				increment j
//			end
//			increment i
//		end
	}
	
	public Block[][] getBoardMatrix(){
		return matrix;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void plotPower(){
		int count=1;
		Random random = new Random();
		while(count>0){
			int row = random.nextInt(width);
			int column = random.nextInt(height);
			if (matrix[row][column].getType()!=Utils.PREF_MINE)
			{
				matrix[row][column].setType(Utils.PREF_FLAG);
				count--;
			}
			else{
				
			}
		}
	}
}
