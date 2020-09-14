package test;
public class Model 
{
	public enum Button { None, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9 }
	public enum Player { None, PlayerX, PlayerO }
	public enum GameState { InProgress, Draw, Win}
	
	
	private Player[][] board;
	private int scoreCard[];
	private GameState gamestate;
	private Player currentPlayer;
	int turns;
	
	public Model()
	{
		board = new Player[3][3];
		scoreCard = new int[2];
		gamestate = GameState.InProgress;
		currentPlayer = Player.PlayerX;
	}
	
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public GameState getGameState()
	{
		return gamestate;
	}
	
	public int[] getScoreCard()
	{
		return scoreCard;
	}
	
	public void setButton(Button button)
	{
		if(gamestate != GameState.InProgress) return;
			
		switch (button)
		{
		 case Button1: { board[0][0] = currentPlayer; break; }
		 case Button2: { board[0][1] = currentPlayer; break; }
		 case Button3: { board[0][2] = currentPlayer; break; }
		 case Button4: { board[1][0] = currentPlayer; break; }
		 case Button5: { board[1][1] = currentPlayer; break; }
		 case Button6: { board[1][2] = currentPlayer; break; }
		 case Button7: { board[2][0] = currentPlayer; break; }
		 case Button8: { board[2][1] = currentPlayer; break; }
		 case Button9: { board[2][2] = currentPlayer; break; }
		}
		if(checkforWin() == true)
		{
			gamestate = GameState.Win;
			//System.out.println("Game over");
			updateScoreCard();
		}
		else 
		{
			togglePlayer();
		}
		updateTurn();
	}
	
	private boolean checkforWin()
	{
		if(currentPlayer == Player.None) return false;
		
		boolean win = false;

		//horizontal
		if(board[0][0] == currentPlayer && board[0][1] == currentPlayer && board[0][2] == currentPlayer )
			win = true;
		else if ( board[1][0] == currentPlayer && board[1][1] == currentPlayer && board[1][2] == currentPlayer )
			win = true;
		else if ( board[2][0] == currentPlayer && board[2][1] == currentPlayer && board[2][2] == currentPlayer )
			win = true;
		//vertical 
		else if( board[0][0] == currentPlayer && board[1][0] == currentPlayer && board[2][0] == currentPlayer )
			win = true;
		else if( board[0][1] == currentPlayer && board[1][1] == currentPlayer && board[2][1] == currentPlayer )
			win = true;
		else if( board[0][2] == currentPlayer && board[1][2] == currentPlayer && board[2][2] == currentPlayer )
			win = true;
		//diagonal
		else if( board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer )
			win = true;
		else if( board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer )
			win = true;
		
		return win;
	}
	
	private void togglePlayer()
	{
		if(currentPlayer == Player.PlayerX)
			currentPlayer = Player.PlayerO;
		else 
			currentPlayer = Player.PlayerX;

		//System.out.println("togglePlayer() : " + currentPlayer);
	}
	
	public void reset()
	{
		nextMatch();
		scoreCard[0]=0; 
		scoreCard[1]=0;
	}
	
	public void nextMatch()
	{
		for(int i=0;i<3;i++)
		for(int j=0;j<3;j++)
			board[i][j] = Player.None;
		
		turns = 0;
		currentPlayer = Player.PlayerX;
		gamestate = GameState.InProgress;
	}
	
	private void updateTurn()
	{
		turns++;
		if(turns == 9 && gamestate != GameState.Win)
			gamestate = GameState.Draw;
	}
	
	private void updateScoreCard()
	{
		if(currentPlayer == Player.PlayerX)
			scoreCard[0]++;
		else
			scoreCard[1]++;
	}
}
