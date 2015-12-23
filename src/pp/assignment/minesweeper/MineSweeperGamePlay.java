package pp.assignment.minesweeper;


import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") public class MineSweeperGamePlay extends Activity implements GamePlay, OnClickListener{

	
	MediaPlayer music;
	AssetFileDescriptor asset;
	MineBoard mineBoard;
	int score;
	int lives;
	boolean timerOver;
	CountDownTimer timer;
	User player;
	
	private int blockDimension = 24; // width of each block
	private int blockPadding = 2; // padding between blocks
	
	TextView timerText;
	TextView scoreText;
	TextView livesText;
	ImageButton smileImage;
	CheckBox sound;
	
	LinearLayout grid;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gameplay);
        sound=(CheckBox)findViewById(R.id.cb_sound);
        sound.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					playSound();
				}
				else{
					stopSound();
				}
			}
		});
        
        smileImage=(ImageButton)findViewById(R.id.btn_smiley);
        timerText=(TextView)findViewById(R.id.txt_timer);
        scoreText=(TextView)findViewById(R.id.txt_score);
        livesText=(TextView)findViewById(R.id.txt_lives);
        grid=(LinearLayout)findViewById(R.id.mineboard);
        timer=new CountDownTimer(Utils.PREF_TIMEINSECONDS*1000, 1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				timerText.setText(String.valueOf(millisUntilFinished/1000));
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				timerText.setText(String.valueOf(0));
				timerOver=true;
				finish();
			}
		};
		timerOver=false;
		lives=1;
		score=0;
		player=Utils.currentPlayer;
		mineBoard=new MineBoard(player.getUserSettings().getDifficulty(),this);
		int rows=mineBoard.getHeight();
        int cols=mineBoard.getWidth();
        grid.removeAllViews();
        LinearLayout[] lay=new LinearLayout[rows];

        LayoutParams paramsLL=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,50400/rows);
        LayoutParams paramsV=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1f);

        int i;
        int j;
        for(i=0;i<rows;i++)
        {
            lay[i]=new LinearLayout(this);
            lay[i].setOrientation(LinearLayout.HORIZONTAL);
            lay[i].setLayoutParams(paramsLL);
            lay[i].setWeightSum(cols);
            mineBoard.matrix[i] = new Block[cols];
            for(j=0;j<cols;j++)
            {
            	mineBoard.matrix[i][j]=new Block(this);
            	mineBoard.matrix[i][j].setLayoutParams(paramsV);
            	mineBoard.matrix[i][j].setId(i * cols + j);
                lay[i].addView(mineBoard.matrix[i][j]);
            }
            grid.addView(lay[i]);
        }  
		start();
    }
	
	
	
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}
	
	public void incrementScore() {
		score=score+1;
	}

	@Override
	public void setScore(int Score) {
		// TODO Auto-generated method stub
		score=Score;
	}

	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return lives;
	}

	@Override
	public void setLives(int Lives) {
		// TODO Auto-generated method stub
		lives=Lives;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		playSound();
		smileImage.setBackgroundResource(R.drawable.normalsmile);
		livesText.setText(String.valueOf(lives));
		for (int row = 0; row < mineBoard.getHeight(); row++)
		{
			for (int column = 0; column < mineBoard.getWidth(); column++)
			{	
				Block b = mineBoard.matrix[row][column];
				b.initialize(Utils.PREF_SAFE);
				b.setOnClickListener(this);
				mineBoard.matrix[row][column] = b;
				Log.i("CHECK",String.valueOf(row)+" "+String.valueOf(column));
//				mineBoard.matrix[row][column].initialize(Utils.PREF_SAFE);

				final int currentRow = row;
				final int currentColumn = column;

//				mineBoard.matrix[row][column].setOnClickListener(this);
				
			}
		}
		mineBoard.setup();
		timer.start();
	}

	public void ofinish() {
		// TODO Auto-generated method stub
		updateUI();
		if(timerOver==true){
			Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT);
			declareLoss();
			return;
		}
		else if(score>=mineBoard.getHeight()*mineBoard.getWidth()-(mineBoard.getHeight())){
			Toast.makeText(this, "All mines found!", Toast.LENGTH_SHORT);
			declareWin();
			return;
		}
		else if(lives<1){
			Toast.makeText(this, "No Lives Left!", Toast.LENGTH_SHORT);
			declareLoss();
			return;
		}
		else{
			Toast.makeText(this, "Go on!", Toast.LENGTH_SHORT);
			for (int row = 0; row < mineBoard.getHeight(); row++)
			{
				for (int column = 0; column < mineBoard.getWidth(); column++)
				{
					if (mineBoard.matrix[row][column].getType()==Utils.PREF_MINE && mineBoard.matrix[row][column].isRevealed())
					{
						declareLoss();
						return;
					}
				}
			}
		}
	}

	@Override
	public void declareWin() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
		smileImage.setBackgroundResource(R.drawable.coolsmile);
		updateHighScore();
		finish();
	}

	@Override
	public void declareLoss() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "You Lose!", Toast.LENGTH_SHORT).show();
		smileImage.setBackgroundResource(R.drawable.sadsmile);
		updateHighScore();
		finish();
	}
	
	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		livesText.setText(String.valueOf(lives));
		scoreText.setText(String.valueOf(score));
	}

	@Override
	public void updateHighScore() {
		// TODO Auto-generated method stub
		if(player.getUserSettings().getHighScore()<score){
			player.getUserSettings().setHighScore(score);
		}
		if(!player.getUserSettings().isAdvanced()){
			if(player.getUserSettings().getHighScore()>player.getUserSettings().getThreshold()){
				player.getUserSettings().makeIsAdvancedAvailable();
				Toast.makeText(this, "Advance Level Unlocked", Toast.LENGTH_SHORT).show();
			}
		}
		Utils.currentPlayer=player;
	}

	public void minimize(View v){
    	super.onBackPressed();
    }
	
	public void goon(View v){
		Toast.makeText(getApplicationContext(), "Go ON!",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public User getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public void incrementLife() {
		// TODO Auto-generated method stub
		lives=lives+1;
		updateUI();
	}

	@Override
	public void decrementLife() {
		// TODO Auto-generated method stub
		lives=lives-1;
		updateUI();
	}

	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		try {
			asset=getAssets().openFd("background.mp3");
			music=new MediaPlayer();
			music.setDataSource(asset.getFileDescriptor(),asset.getStartOffset(),asset.getLength());
			music.setLooping(true);
			music.prepare();
			music.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stopSound() {
		// TODO Auto-generated method stub
		music.stop();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		music.start();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		music.stop();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		music.pause();
	}
	
	public void recurseReveal(int x,int y){
		// TODO Auto-generated method stub
		int currentRow=x;
		int currentColumn=y;
		//Toast.makeText(MineSweeperGamePlay.this, v.getId()+" "+currentRow+" "+currentColumn, 0).show();
		//Log.d("mine", v.getId()+" "+currentRow+" "+currentColumn);
		if(mineBoard.isInMatrix(x, y) && !mineBoard.matrix[x][y].isRevealed() && mineBoard.matrix[x][y].getType()!=Utils.PREF_FLAG){			
				mineBoard.matrix[currentRow][currentColumn].reveal();
				incrementScore();
				if(mineBoard.matrix[currentRow][currentColumn].getType()==Utils.PREF_SAFE && mineBoard.matrix[currentRow][currentColumn].getValue()==0){
					
					
					recurseReveal(x-1,y-1);
					recurseReveal(x-1,y);
					recurseReveal(x-1,y+1);
					recurseReveal(x,y-1);
					recurseReveal(x,y+1);
					recurseReveal(x+1,y-1);
					recurseReveal(x+1,y+1);
					recurseReveal(x+1,y);
				}
				
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int currentRow=v.getId()/mineBoard.getWidth();
		int currentColumn=v.getId()%mineBoard.getWidth();
		//Toast.makeText(MineSweeperGamePlay.this, v.getId()+" "+currentRow+" "+currentColumn, 0).show();
		Log.d("mine", v.getId()+" "+currentRow+" "+currentColumn);
		if (mineBoard.matrix[currentRow][currentColumn].isEnabled())
		{
			
			if(mineBoard.matrix[currentRow][currentColumn].getType()==Utils.PREF_FLAG){
				incrementLife();
				incrementScore();
				mineBoard.matrix[currentRow][currentColumn].reveal();
			}
			else if(mineBoard.matrix[currentRow][currentColumn].getType()==Utils.PREF_SAFE){
				//mineBoard.matrix[currentRow][currentColumn].reveal();
				
					recurseReveal(currentRow,currentColumn);
				
				
			}
			else if(mineBoard.matrix[currentRow][currentColumn].getType()==Utils.PREF_MINE){
				decrementLife();
				if(lives>0){
					Toast.makeText(getApplicationContext(), "You Lost A Life! There is a mine here!", Toast.LENGTH_LONG).show();
					mineBoard.matrix[currentRow][currentColumn].setBackgroundResource(R.drawable.coolsmile);
					mineBoard.matrix[currentRow][currentColumn].setEnabled(false);
					timer.cancel();
					timer.start();
				}
				else{
					mineBoard.matrix[currentRow][currentColumn].reveal();
					
				}
			}
			ofinish();
		}
	}
}