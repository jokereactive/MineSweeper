package pp.assignment.minesweeper;


public class Settingss {
	Difficulty currentDifficulty;
	Boolean isAdvanced;
	Difficulty[] arrayOfDifficultyLevels=Utils.PREF_ARRAYDIFFICULTY;
	int[] arrayOfHighScoresOnAvailableLevels;
	Difficulty defaultBeginnerDifficultyObject=Utils.PREF_BASIC;
	Difficulty defaultAdvancedDifficultyObject=Utils.PREF_ADVANCE;
	Boolean defaultValueIsAdvanced=Utils.PREF_DEFAULTISADVANCED;
	int defaultThresholdForAdvancedActivation= Utils.PREF_THRESHOLD;
	
	public Settingss(){
		isAdvanced=false;	
		currentDifficulty=defaultBeginnerDifficultyObject;
		int n=Utils.PREF_ARRAYDIFFICULTY.length;
		int temp[]=new int[n];
		for(int i=0;i<n;i++){
			temp[i]=0;
		}
		arrayOfHighScoresOnAvailableLevels=temp;
	}
	
	public void makeIsAdvancedAvailable(){
		isAdvanced=true;
	}
	
	public Boolean isAdvanced(){
		return isAdvanced;
	}
	
	public void Settings(Difficulty CurrentDifficulty, Boolean IsAdvanced){
		isAdvanced=IsAdvanced;
		currentDifficulty=CurrentDifficulty;
		int n=Utils.PREF_ARRAYDIFFICULTY.length;
		int temp[]=new int[n];
		for(int i=0;i<n;i++){
			temp[i]=0;
		}
		arrayOfHighScoresOnAvailableLevels=temp;
	}
	
	public void Settings(Difficulty CurrentDifficulty, int[] ArrayOfHighScoresOnAvailableLevels, Boolean IsAdvanced){
		isAdvanced=IsAdvanced;
		currentDifficulty=CurrentDifficulty;
		arrayOfHighScoresOnAvailableLevels=ArrayOfHighScoresOnAvailableLevels;
	}
	
	public int getHighScore(Difficulty CurrentDifficulty){
		if(CurrentDifficulty==defaultBeginnerDifficultyObject){
			return arrayOfHighScoresOnAvailableLevels[Utils.PREF_NUMBASIC];
		}
		else if(CurrentDifficulty==defaultBeginnerDifficultyObject){
			return arrayOfHighScoresOnAvailableLevels[Utils.PREF_NUMADVACNED];
		}
		else{
			return arrayOfHighScoresOnAvailableLevels[Utils.PREF_HASHDIFFICULTY.get(CurrentDifficulty)];
		}
	}
	
	public int getThreshold(){
		return defaultThresholdForAdvancedActivation;
	}
	
	public int getHighScore(){
		if(currentDifficulty==defaultBeginnerDifficultyObject){
			return arrayOfHighScoresOnAvailableLevels[Utils.PREF_NUMBASIC];
		}
		else if(currentDifficulty==defaultBeginnerDifficultyObject){
			return arrayOfHighScoresOnAvailableLevels[Utils.PREF_NUMADVACNED];
		}
		else{
			return arrayOfHighScoresOnAvailableLevels[Utils.PREF_HASHDIFFICULTY.get(currentDifficulty)];
		}
	}
	
	public void changeDifficulty(Difficulty NewDifficulty){
		currentDifficulty=NewDifficulty;
	}
	
	public Difficulty getDifficulty(){
		return currentDifficulty;
	}
	
	public void setHighScore(Difficulty CurrentDifficulty, int highScore){
		if(CurrentDifficulty==defaultBeginnerDifficultyObject){
			arrayOfHighScoresOnAvailableLevels[Utils.PREF_NUMBASIC]=highScore;
		}
		else if(CurrentDifficulty==defaultBeginnerDifficultyObject){
			arrayOfHighScoresOnAvailableLevels[Utils.PREF_NUMADVACNED]=highScore;
		}
		else{
			arrayOfHighScoresOnAvailableLevels[Utils.PREF_HASHDIFFICULTY.get(CurrentDifficulty)]=highScore;
		}
	}
	
	public void setHighScore(int highScore){
		if(currentDifficulty==defaultBeginnerDifficultyObject){
			arrayOfHighScoresOnAvailableLevels[Utils.PREF_NUMBASIC]=highScore;
		}
		else if(currentDifficulty==defaultBeginnerDifficultyObject){
			arrayOfHighScoresOnAvailableLevels[Utils.PREF_NUMADVACNED]=highScore;
		}
		else{
			arrayOfHighScoresOnAvailableLevels[Utils.PREF_HASHDIFFICULTY.get(currentDifficulty)]=highScore;
		}
	}
}
