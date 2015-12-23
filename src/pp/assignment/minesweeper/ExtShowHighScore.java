package pp.assignment.minesweeper;

import android.os.Bundle;
import android.app.*;
import android.widget.TextView;

public class ExtShowHighScore extends Activity{
	
	TextView highscorebasic;
	TextView highscoreadvance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_showhighscore);
		highscorebasic=(TextView)findViewById(R.id.txt_highscoreBasic);
		highscoreadvance=(TextView)findViewById(R.id.txt_highscoreAdvance);
		displayHighScore();
		
	}

	private void displayHighScore() {
		// TODO Auto-generated method stub
		highscoreadvance.setText(String.valueOf(getIntent().getIntExtra(Utils.PREF_HIGHSCOREADVANCED, 0)));
		highscorebasic.setText(String.valueOf(getIntent().getIntExtra(Utils.PREF_HIGHSCOREBASIC, 0)));
	}
}
