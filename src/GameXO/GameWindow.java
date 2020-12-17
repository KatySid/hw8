package GameXO;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    static final int WINDOW_X= 300;
    static final int WINDOW_Y= 100;
    static final int WINDOW_WIDTH= 505;
    static final int WINDOW_HEIGHT= 555;
    private SettingWindow settingWindow;
    private BattleMap battleMap;


    public GameWindow(){
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        settingWindow = new SettingWindow(this);
        battleMap = new BattleMap(this);

        add(battleMap, BorderLayout.CENTER);
        JPanel panel = new JPanel(new GridLayout(1,2));
        JButton buttonSNG = new JButton("Start New Game");
        JButton buttonExit = new JButton("Exit");
        panel.add(buttonSNG);
        panel.add(buttonExit);
        add(panel, BorderLayout.SOUTH);
        buttonExit.addActionListener(e ->{
                System.exit(0);
        });

        buttonSNG.addActionListener(e -> {
            settingWindow.setVisible(true);
        });

        setVisible(true);

    }
    public void startNewGame(int mode, int fieldSize, int winningLength){
        battleMap.startNewGame(mode, fieldSize, winningLength);
    }
}
