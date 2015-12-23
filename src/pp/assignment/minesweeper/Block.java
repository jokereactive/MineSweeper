package pp.assignment.minesweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class Block extends Button{
	int value;
	int type;
	Boolean isRevealed;
	
	public Block(Context context)
	{
		super(context);
	}

	public Block(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public Block(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}
	
	
	
	public void initialize(int Type){
		type=Type;
		isRevealed=false;
		value=-1;
	}
	
	public void reveal(){
		if (isRevealed){
			return;
		}
		else{
			isRevealed=true;
			this.setEnabled(false);
		}

		if (type==Utils.PREF_MINE)
		{
			//this.setText("M");
			this.setBackgroundResource(R.drawable.mine);
			
		}
		else if(type==Utils.PREF_FLAG)
		{
			this.setText("F");
			this.setBackgroundResource(R.drawable.flag);
		}
		else{
			this.setText(String.valueOf(value));
		}
	}
	
	public void setValue(int val){
		value=val;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setType(int Type){
		type=Type;
	}
	
	public int getType(){
		return type;
	}
	
	public Boolean isRevealed(){
		return isRevealed;
	}
}
