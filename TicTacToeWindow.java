package test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class TicTacToeWindow extends JFrame{

	private JButton[] buttons;
	private JPanel buttonPanel;
	private JPanel scorePanel;
	private JPanel controlPanel;
	private JLabel playerYscore;
	private JLabel playerXscore;
	private Model  model;
	private JLabel gameResult;
	private JButton restartBtn;
	private JButton continueBtn;
	private JLabel currentPlayer;
	private JPanel currentPlayerPanel;
	final String Current_Player_Text = "Current Player : ";
	private JLabel scoreCardLabel;
	private JLabel winningLabel;
	private JLabel xWiningImgLabel;
	private JLabel oWiningImgLabel;
	
	public TicTacToeWindow() {
		
		model = new Model();
		initialize();
	}
	
	private void initButtonPanel() 
	{
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 3));
		
		buttons= new JButton[9];
		for(int i=0;i<9;i++)
		{
			buttons[i] = new JButton();
			buttons[i].setBackground(new Color(206, 223, 245));
			buttons[i].setFont(new Font("Ubuntu", Font.BOLD,70));
			buttonPanel.add(buttons[i]);
		}
	}
	
	private void initScorePanel()
	{
		scorePanel = new JPanel();
		scorePanel.setSize(270, 100);
		scorePanel.setLocation(340, 65);
		GridLayout layout = new GridLayout(2,3);
		layout.setVgap(-50);
		layout.setHgap(-50);
		scorePanel.setLayout(layout);
		Border blackline = BorderFactory.createRaisedBevelBorder();
		scorePanel.setBorder(blackline);
		
		Icon imgNextMatch = new ImageIcon(this.getClass().getResource("winning.png"));
		
		JLabel playerX = new JLabel("Player X :");
		playerX.setHorizontalAlignment(SwingConstants.CENTER);
		playerX.setFont(new Font("Ubuntu",Font.BOLD,15));
		
		playerXscore = new JLabel("0");
		playerXscore.setHorizontalAlignment(SwingConstants.CENTER);
		playerXscore.setFont(new Font("Tahoma", Font.BOLD, 14));
				
		JLabel playerY = new JLabel("Player O :");
		playerY.setHorizontalAlignment(SwingConstants.CENTER);
		playerY.setFont(new Font("Ubuntu",Font.BOLD,15));
		
		playerYscore = new JLabel("0");
		playerYscore.setHorizontalAlignment(SwingConstants.CENTER);
		playerYscore.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		 xWiningImgLabel = new JLabel(imgNextMatch);
	     oWiningImgLabel = new JLabel(imgNextMatch);
		
		scorePanel.add(xWiningImgLabel);
		scorePanel.add(playerX);
		scorePanel.add(playerXscore);
		scorePanel.add(oWiningImgLabel);
		scorePanel.add(playerY);
		scorePanel.add(playerYscore);	
		
		xWiningImgLabel.setVisible(false);
		oWiningImgLabel.setVisible(false);
	}
	
	
	private void initControlPanel()
	{
		controlPanel = new JPanel();
		controlPanel.setSize(293, 35);
		controlPanel.setLocation(330, 220);
		FlowLayout layout = new FlowLayout();
		controlPanel.setLayout(layout);
		
		Icon ImgRestart = new ImageIcon(this.getClass().getResource("restart.png"));
		restartBtn = new JButton("Restart",ImgRestart);
		restartBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		restartBtn.setSize(90, 38);
		restartBtn.setLocation(500, 228);
		restartBtn.setHorizontalAlignment(SwingConstants.LEADING);
		restartBtn.setBackground(new Color(255, 69, 0));		
		Icon imgNextMatch = new ImageIcon(this.getClass().getResource("next.png"));
		continueBtn = new JButton("Next Match",imgNextMatch);
		continueBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		continueBtn.setLocation(351, 226);
		continueBtn.setSize(90, 40);
		continueBtn.setBackground(new Color(46, 139, 87));	
		
		controlPanel.add(continueBtn);
		controlPanel.add(restartBtn);
	}
	
	private void initGameResultLabel()
	{
		gameResult = new JLabel("win");
        gameResult.setForeground(new Color(30, 144, 255));
		gameResult.setBounds(41,100,236,100);
		gameResult.setFont(new Font("Tahoma", Font.BOLD, 60));
		gameResult.setVisible(false);
		
	}
	
	private void initCurrentPlayerLabel()
	{
		
		currentPlayer = new JLabel();	
		currentPlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		currentPlayer.setVerticalAlignment(SwingConstants.BOTTOM);
		currentPlayerPanel = new JPanel(null);
		currentPlayerPanel.setBackground(new Color(0, 255, 0));
		currentPlayer.setFont(new Font("Tahoma", Font.BOLD, 15));
		currentPlayerPanel.setLayout(new FlowLayout());
		currentPlayerPanel.add(currentPlayer);
	}
	
	private void subscribeButtonActionListener()
	{
		onButtonClick(0, Model.Button.Button1);
		onButtonClick(1, Model.Button.Button2);
		onButtonClick(2, Model.Button.Button3);
		onButtonClick(3, Model.Button.Button4);
		onButtonClick(4, Model.Button.Button5);
		onButtonClick(5, Model.Button.Button6);
		onButtonClick(6, Model.Button.Button7);
		onButtonClick(7, Model.Button.Button8);
		onButtonClick(8, Model.Button.Button9);
	}
	
	private void onButtonClick(int buttonIndex , Model.Button Button)
	{
		buttons[buttonIndex].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons[buttonIndex].setText(getCurrentPlayerToText());
				buttons[buttonIndex].setEnabled(false);
				model.setButton(Button);
				updateBoardForGameState();
				currentPlayer.setText(Current_Player_Text + getCurrentPlayerToText());
			}
		});
	}
	
	private void subscribeControlPanelActionListner()
	{
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetView();
			}
		});
		
		continueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMatch();
				
			}
		});
	}
	
	private String getCurrentPlayerToText()
	{
		if(model.getCurrentPlayer() == Model.Player.PlayerO) return "O";
		return "X";
	}
	
	private void updateBoardForGameState()
	{
		Model.GameState gamestate = model.getGameState();
		if(gamestate == Model.GameState.Draw)
		{
			enableBoard(false);
			gameResult.setVisible(true);
			gameResult.setText("Draw");
			
		}
		else if (gamestate == Model.GameState.Win)
		{
			enableBoard(false);
			gameResult.setVisible(true);
			gameResult.setText(getCurrentPlayerToText()+" Wins");
			updateScoreCard();
			updateViewOnWin(true);
		}
	}
	
	private void updateScoreCard()
	{
		int scoreCard[] = model.getScoreCard();
		playerXscore.setText(scoreCard[0]+"");
		playerYscore.setText(scoreCard[1]+"");
	}
	
	private void enableBoard(boolean enable)
	{
		for(int i = 0; i<9;i++)
		{
			buttons[i].setEnabled(enable);
		}
	}
	
	public void nextMatch()
	{
		model.nextMatch();
		gameResult.setVisible(false);
		enableBoard(true);
		updateViewOnWin(false);
		for(int i = 0;i< 9 ;i++)
			buttons[i].setText("");

		currentPlayer.setText(Current_Player_Text + getCurrentPlayerToText());
	}
	
	public void resetView()
	{
		model.reset();
		nextMatch();
		updateScoreCard();
		xWiningImgLabel.setVisible(false);
		oWiningImgLabel.setVisible(false);
	}
	
	private void updateViewOnWin(boolean win)
	{
		winningLabel.setVisible(win);
		
		xWiningImgLabel.setVisible(false);
		oWiningImgLabel.setVisible(false);
		int scoreCard[] = model.getScoreCard();
		if(scoreCard[0] > scoreCard[1])
			xWiningImgLabel.setVisible(true);
		else if(scoreCard[0] < scoreCard[1])
			oWiningImgLabel.setVisible(true);		
	}
	
	private void initialize() { 
		this.getContentPane().setBackground(new Color(240, 240, 240));
		this.setBackground(new Color(240, 240, 240));
		this.setBounds(100, 100, 650, 350);
		this.setTitle("Tic Tac Toe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		initButtonPanel();
		initScorePanel();
		initControlPanel();
		initGameResultLabel();
		initCurrentPlayerLabel();
		subscribeButtonActionListener();
		subscribeControlPanelActionListner();
		
		currentPlayer.setText("Current Player : " + getCurrentPlayerToText());
		
        JPanel panel = new JPanel(null);
        buttonPanel.setBounds(0, 0,315,315);
        currentPlayerPanel.setBounds(315, 0,335, 30);
        
        scoreCardLabel = new JLabel("Score Card");
        scoreCardLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        scoreCardLabel.setSize(88, 50);
        scoreCardLabel.setLocation(426, 50);
  
        Icon imgIcon = new ImageIcon(this.getClass().getResource("winningGif.gif"));
        winningLabel = new JLabel(imgIcon);
        winningLabel.setBounds(0, 0,315,315);
        winningLabel.setVisible(false);
        
		panel.add(gameResult);
		panel.add(winningLabel);
		panel.add(buttonPanel);
        panel.add(currentPlayerPanel);
        panel.add(scoreCardLabel);
        panel.add(scorePanel);
        panel.add(controlPanel);
        
        this.setContentPane(panel);		
	}
	
}
