package pp.assignment.minesweeper;

public class User {
	Settingss userSettings;
	String name;
	
	public User(String Name){
		name=Name;
		Settingss s=new Settingss();
		userSettings=s;
	}
	public User(String Name,Settingss UserSettings){
		name=Name;
		userSettings=UserSettings;
	}
	public String getName(){
		return name;
	}
	public Settingss getUserSettings(){
		return userSettings;
	}
	public void setUserSettings(Settingss UserSettings){
		userSettings=UserSettings;
	}
}
