package pp.assignment.minesweeper;

public interface GamePlay {
	public int getScore();
	public void setScore(int Score);
	public int getLives();
	public void setLives(int Lives);
	public void start();
	public void finish();
	public void declareWin();
	public void declareLoss();
	public void updateUI();
	public void updateHighScore();
	public User getPlayer();
	public void incrementLife();
	public void decrementLife();
	public void playSound();
	public void stopSound();
}
