package pp.assignment.minesweeper;

import java.util.HashMap;

public class Utils {
	public static User currentPlayer;
	
	public static String PREF_USERNAME="userName";
	public static String PREF_NOTFIRSTTIME="notFirstTime";
	public static String PREF_ISADVANCED="isAdvanced";
	public static String PREF_HIGHSCOREBASIC="highscorebasic";
	public static String PREF_HIGHSCOREADVANCED="highscoreadvanced";
	public static String PREF_CURRENTDIFFICULTY="currentDifficulty";
	public static String PREF_SUCCESS="SUCCESS";
	
	public static int PREF_SAFE=0;
	public static int PREF_MINE=1;
	public static int PREF_FLAG=2;
	
	public static int PREF_CODEGAMEPLAY=0;
	public static int PREF_CODEUSERSIGNUP=1;
	public static int PREF_CODEHIGHSCORE=2;
	public static int PREF_CODEDIFFICULTY=3;
	
	public static String PREF_CACHE="CACHE";
	
	public static int PREF_TIMEINSECONDS=60;
	
	public static int PREF_THRESHOLD=5;
	
	public static boolean PREF_DEFAULTISADVANCED=false;
	
	public static int PREF_NUMBASIC=0;
	public static int PREF_NUMADVACNED=1;
	public static Difficulty PREF_BASIC;
	public static Difficulty PREF_ADVANCE;
	
	public static Difficulty[] PREF_ARRAYDIFFICULTY=new Difficulty[2];
	public static HashMap<Difficulty, Integer> PREF_HASHDIFFICULTY;;
	
	public static void Initialize(){
		currentPlayer=new User("Error");
		PREF_BASIC=new Difficulty("Basic",5,5);
		PREF_ADVANCE=new Difficulty("Advanced",10,10);
		
		PREF_ARRAYDIFFICULTY[0]=PREF_BASIC;
		PREF_ARRAYDIFFICULTY[1]=PREF_ADVANCE;
		PREF_HASHDIFFICULTY=new HashMap<Difficulty, Integer>();
		PREF_HASHDIFFICULTY.put(PREF_BASIC,0);
		PREF_HASHDIFFICULTY.put(PREF_ADVANCE,1);
	}
}
