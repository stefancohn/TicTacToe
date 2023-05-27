package main;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GamePanel extends JPanel implements ActionListener {
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel menu_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton playButton = new JButton();
    JPanel playButtonPanel = new JPanel();
    JButton[] buttons = new JButton[9];
    JButton playAgain = new JButton();
    JPanel winPanel = new JPanel();
    JLabel winText = new JLabel();
    
    int width = 1000;
    int height = 1000;
    Boolean playerOneTurn = true;
    String[] absBoard = {"","","", 
                        "","","", 
                        "","",""};
    Boolean tieCondition = false;

    GamePanel() {
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground((Color.black));
        frame.setTitle("Tic Tac Toe"); 
        frame.setResizable(false);

        textfield.setText("Tic Tac Toe");
        textfield.setForeground(Color.blue);
        textfield.setOpaque(true);
        textfield.setBackground(Color.BLACK);
        textfield.setFont(new Font("Comic Sans MS", Font.BOLD, 75)); 
        textfield.setHorizontalAlignment(JLabel.CENTER);

        int tPanelHeight = 100;
        title_panel.setLayout(new BorderLayout());
        title_panel.setBackground(Color.BLACK);

        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 80));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        button_panel.setBounds(0, tPanelHeight, width, height - tPanelHeight);
        button_panel.setBackground(Color.white);
        button_panel.setLayout(new GridLayout(3,3));

        playButton.setBounds(250, 250, 500, 150);
        playButton.setForeground(Color.blue);
        playButton.setBackground(Color.WHITE);
        playButton.addActionListener(this);
        playButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 75));
        playButton.setText("PLAY");
        playButton.setOpaque(true);
        playButton.setBorderPainted(false);

        playButtonPanel.setLayout(new BorderLayout());
        playButtonPanel.setBounds(250, 250, 500, 150);
        playButtonPanel.setBackground(Color.WHITE);
        playButtonPanel.add(playButton, BorderLayout.CENTER);

        playAgain.addActionListener(this);

        frame.add(playButtonPanel); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i =0; i < 9; i++) {
            if (e.getSource() == buttons[i] && playerOneTurn == true && absBoard[i].isEmpty()) {
                buttons[i].setText("X");
                togglePlayerTurn();
                absBoard[i] = "X";
                checkPlayerOneWin();
                checkForTie();
            }
            else if (e.getSource() == buttons[i] && playerOneTurn == false && absBoard[i].isEmpty()) {
                buttons[i].setText("O");
                togglePlayerTurn();
                absBoard[i] = "O";
                checkPlayerTwoWin();
                checkForTie();
            }
        }
        if (e.getSource() == playButton) {
            frame.setLayout(new BorderLayout());
            frame.remove(playButtonPanel);
            title_panel.add(textfield);
            frame.add(title_panel, BorderLayout.NORTH);
            frame.add(button_panel, BorderLayout.CENTER);
            frame.revalidate(); // Add this line to refresh the frame    
        }
        if (e.getSource() == playAgain) {
             // Restart the program by creating a new instance of GamePanel
             frame.dispose(); // Close the current frame
             new GamePanel(); // Create a new instance of GamePanel
        }
    }

    public void togglePlayerTurn() {
        playerOneTurn = !playerOneTurn;
    }

    public void playerWins() {
        winPanel.setLayout(new BorderLayout());
        winPanel.setBackground(Color.blue);
        
        if (playerOneTurn == false) {
            winText.setText("X Wins!");
        } else {
            winText.setText("O Wins!");
        }
        winText.setForeground(Color.white);
        winText.setOpaque(false);
        winText.setFont(new Font("Comic Sans MS", Font.BOLD, 100));
        winText.setHorizontalAlignment(JLabel.CENTER); 


        playAgain.setForeground(Color.blue);
        playAgain.setBackground(Color.WHITE);
        playAgain.setFont(new Font("Comic Sans MS", Font.PLAIN, 75));
        playAgain.setText("Play Again?");
        playAgain.setOpaque(true);
        playAgain.setBorderPainted(false);
        playAgain.setPreferredSize(new Dimension(250, 250));

        frame.remove(button_panel);
        frame.remove(title_panel);
        winPanel.add(winText, BorderLayout.CENTER);
        winPanel.add(playAgain, BorderLayout.SOUTH);
        frame.add(winPanel);
    }

    public void checkPlayerOneWin() {
        if (absBoard[0] == "X" && absBoard[1] == "X" && absBoard[2] == "X") {
            playerWins();
        }
        else if (absBoard[3] == "X" && absBoard[4] == "X" && absBoard[5] == "X") {
            playerWins();
        }
        else if(absBoard[6] == "X" && absBoard[7] == "X" && absBoard[8] == "X") {
            playerWins();
        }
        else if(absBoard[0] == "X" && absBoard[4] == "X" && absBoard[8] == "X") {
            playerWins();
        }
        else if(absBoard[2] == "X" && absBoard[4] == "X" && absBoard[6] == "X") {
            playerWins();
        }
        else if(absBoard[0] == "X" && absBoard[3] == "X" && absBoard[6] == "X") {
            playerWins();
        }
        else if(absBoard[1] == "X" && absBoard[4] == "X" && absBoard[7] == "X") {
            playerWins();
        }
        else if(absBoard[2] == "X" && absBoard[5] == "X" && absBoard[8] == "X") {
            playerWins();
        }
    }

    public void checkPlayerTwoWin() {
        if (absBoard[0] == "O" && absBoard[1] == "O" && absBoard[2] == "O") {
            playerWins();
        }
        else if (absBoard[3] == "O" && absBoard[4] == "O" && absBoard[5] == "O") {
            playerWins();
        }
        else if(absBoard[6] == "O" && absBoard[7] == "O" && absBoard[8] == "O") {
            playerWins();
        }
        else if(absBoard[0] == "O" && absBoard[4] == "O" && absBoard[8] == "O") {
            playerWins();
        }
        else if(absBoard[2] == "O" && absBoard[4] == "O" && absBoard[6] == "O") {
            playerWins();
        }
        else if(absBoard[0] == "O" && absBoard[3] == "O" && absBoard[6] == "O") {
            playerWins();
        }
        else if(absBoard[1] == "O" && absBoard[4] == "O" && absBoard[7] == "O") {
            playerWins();
        }
        else if(absBoard[2] == "O" && absBoard[5] == "O" && absBoard[8] == "O") {
            playerWins();
        }
    }

    public void checkForTie() {
        Boolean flag = true;
        for (int i = 0; i < absBoard.length; i++) {
            if (absBoard[i].isEmpty()) {
                flag = false;
                break;
            }
        }
        if (flag == true) {
            frame.setLayout(new BorderLayout());
            winPanel.setLayout(new BorderLayout());
            winPanel.setBackground(Color.blue);
            winText.setForeground(Color.white);
            winText.setOpaque(false);
            winText.setFont(new Font("Comic Sans MS", Font.BOLD, 100));
            winText.setHorizontalAlignment(JLabel.CENTER); 
            winText.setText("Tie!");
    
    
            playAgain.setForeground(Color.blue);
            playAgain.setBackground(Color.WHITE);
            playAgain.setFont(new Font("Comic Sans MS", Font.PLAIN, 75));
            playAgain.setText("Play Again?");
            playAgain.setOpaque(true);
            playAgain.setBorderPainted(false);
            playAgain.setPreferredSize(new Dimension(250, 250));
    
            frame.remove(button_panel);
            frame.remove(title_panel);
            winPanel.add(winText, BorderLayout.CENTER);
            winPanel.add(playAgain, BorderLayout.SOUTH);
            frame.add(winPanel);
        }
    }

}