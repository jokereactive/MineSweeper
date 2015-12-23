package pp.assignment.minesweeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.*;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MineSweeperGame extends Activity {

	public static User user;
	
    public static SharedPreferences loginPreferences; 
    TextView UserName;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
		Utils.Initialize();
		user=new User("Error");
		loginPreferences=getSharedPreferences(Utils.PREF_CACHE,MODE_PRIVATE);;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserName=(TextView)findViewById(R.id.txt_userName);
        onStarts();
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==Utils.PREF_CODEUSERSIGNUP){
	        
	        	if(data.getBooleanExtra(Utils.PREF_SUCCESS, false)){
	        		createNewUser(data.getStringExtra(Utils.PREF_USERNAME));
	        		UserName.setText("Welcome "+user.getName());
	        	}
	        	else{
	        		Toast.makeText(this, "You Need to have a username before you start playing.", Toast.LENGTH_SHORT).show();
	        		Intent getNewUser = new Intent(this,ExtNewUser.class);
	        		startActivityForResult(getNewUser, Utils.PREF_CODEUSERSIGNUP);
	        	} 	
	            
		}
		else if(requestCode==Utils.PREF_CODEGAMEPLAY){
				user=Utils.currentPlayer;
		}
		else if(requestCode==Utils.PREF_CODEHIGHSCORE){
				
		}
		else if(requestCode==Utils.PREF_CODEDIFFICULTY){
			    Toast.makeText(this, String.valueOf(data.getIntExtra(Utils.PREF_CURRENTDIFFICULTY, 0)), Toast.LENGTH_SHORT).show();
				user.userSettings.changeDifficulty(Utils.PREF_ARRAYDIFFICULTY[data.getIntExtra(Utils.PREF_CURRENTDIFFICULTY, 0)]);
		}
	}
	
    public void onStarts(){
    	if(loginPreferences.contains(Utils.PREF_NOTFIRSTTIME)){
    		//Load User and User Settings
    		loadUserFromFile();
    		UserName.setText("Welcome "+user.getName());
    	}
    	else{
    		//Force User Sign Up
    		Intent getNewUser = new Intent(this,ExtNewUser.class);
    	    startActivityForResult(getNewUser, Utils.PREF_CODEUSERSIGNUP);
    	}
    	
    }
     	    
    public void exit(View v){
    	writeUserToFile();
    	finish();
    }
    
    public void startNewGamePlay(View v){
    	Intent startNewGamePlay = new Intent(this,MineSweeperGamePlay.class);
    	Utils.currentPlayer=user;
	    startActivity(startNewGamePlay);
    }
    
    public void loadUserFromFile(){
    	Settingss setting=new Settingss();
    	setting.setHighScore(Utils.PREF_BASIC, loginPreferences.getInt(Utils.PREF_HIGHSCOREBASIC, 0));
    	setting.setHighScore(Utils.PREF_ADVANCE, loginPreferences.getInt(Utils.PREF_HIGHSCOREADVANCED, 0));
    	setting.changeDifficulty(Utils.PREF_ARRAYDIFFICULTY[loginPreferences.getInt(Utils.PREF_CURRENTDIFFICULTY, Utils.PREF_NUMBASIC)]);
    	if(loginPreferences.getBoolean(Utils.PREF_ISADVANCED, false)){
    		setting.makeIsAdvancedAvailable();
    	}
		user=new User(loginPreferences.getString(Utils.PREF_USERNAME, ""),setting);
    }
    
    public void writeUserToFile(){
    	loginPreferences.edit().putString(Utils.PREF_USERNAME, user.getName()).commit();
    	loginPreferences.edit().putBoolean(Utils.PREF_ISADVANCED,user.getUserSettings().isAdvanced()).commit();
    	loginPreferences.edit().putInt(Utils.PREF_HIGHSCOREADVANCED, user.getUserSettings().getHighScore(Utils.PREF_ADVANCE)).commit();
    	loginPreferences.edit().putInt(Utils.PREF_HIGHSCOREBASIC, user.getUserSettings().getHighScore(Utils.PREF_BASIC)).commit();
    	loginPreferences.edit().putInt(Utils.PREF_CURRENTDIFFICULTY, Utils.PREF_HASHDIFFICULTY.get(user.getUserSettings().getDifficulty())).commit();
    	loginPreferences.edit().putBoolean(Utils.PREF_NOTFIRSTTIME,true).commit();
    }
    
    public void createNewUser(String name){
    	Settingss setting=new Settingss();
		user=new User(name,setting);
		loginPreferences.edit().putBoolean(Utils.PREF_NOTFIRSTTIME,true).commit();
    }
    
    public void showHighScore(View v) {
    	Intent intent=new Intent(this,ExtShowHighScore.class);
    	intent.putExtra(Utils.PREF_HIGHSCOREADVANCED, user.getUserSettings().getHighScore(Utils.PREF_ADVANCE));
    	intent.putExtra(Utils.PREF_HIGHSCOREBASIC, user.getUserSettings().getHighScore(Utils.PREF_BASIC));
		startActivityForResult(intent, Utils.PREF_CODEHIGHSCORE);
	}
    
    public void changeDifficulty(View v) {
    	Intent intent=new Intent(this,ExtDifficultyChange.class);
    	intent.putExtra(Utils.PREF_ISADVANCED, user.getUserSettings().isAdvanced());
    	intent.putExtra(Utils.PREF_CURRENTDIFFICULTY, Utils.PREF_HASHDIFFICULTY.get(user.getUserSettings().getDifficulty()));
    	startActivityForResult(intent, Utils.PREF_CODEDIFFICULTY);
	}
    
    public void minimize(View v) {
    	writeUserToFile();
		super.onBackPressed();
	}
    
}
