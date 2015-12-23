package pp.assignment.minesweeper;

public class Difficulty {
	String name;
	int width;
	int height;
	public Difficulty(String Name, int Width, int Height){
		name=Name;
		if(Width>0){
			width=Width;
		}
		else{
			width=0;
		}
		if(Height>0){
			height=Height;
		}
		else{
			height=0;
		}
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}
