package pp.assignment.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.app.*;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ExtDifficultyChange extends Activity{
	
	CheckBox basic;
	CheckBox advance;
	int difficulty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_difficulty);
		basic=(CheckBox)findViewById(R.id.cb_basic);
		advance=(CheckBox)findViewById(R.id.cb_advancedd);
		basic.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					advance.setChecked(false);
					difficulty=Utils.PREF_NUMBASIC;
				}
				else{
					if(getIntent().getBooleanExtra(Utils.PREF_ISADVANCED, false)){
						advance.setChecked(true);
						difficulty=Utils.PREF_NUMADVACNED;	
					}
					else{
						basic.setChecked(true);
						difficulty=Utils.PREF_NUMBASIC;
					}
				}
			}
		});
		advance.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					basic.setChecked(false);
					difficulty=Utils.PREF_NUMADVACNED;
				}
				else{
					basic.setChecked(true);
					difficulty=Utils.PREF_NUMBASIC;
				}
			}
		});
		setup();
	}
	
	public void setup(){
		if(getIntent().getBooleanExtra(Utils.PREF_ISADVANCED, false)){
			advance.setEnabled(true);
			if(getIntent().getIntExtra(Utils.PREF_CURRENTDIFFICULTY, 0)==0){
				basic.setChecked(true);
				basic.setChecked(false);
			}
			else{
				basic.setChecked(false);
				advance.setChecked(true);
			}
		}
		else{
			advance.setEnabled(false);
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		submit();
		super.onBackPressed();
	}
	
	public void submit(){
		Intent intent=new Intent();
		intent.putExtra(Utils.PREF_CURRENTDIFFICULTY, difficulty);
		setResult(RESULT_OK, intent);
	}
}
