package GameXO;

import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame {
    private static final int WINDOW_X= GameWindow.WINDOW_X+50;
    private static final int WINDOW_Y= GameWindow.WINDOW_Y+50;
    private static final int WINDOW_WIDTH= GameWindow.WINDOW_WIDTH-100;
    private static final int WINDOW_HEIGHT= GameWindow.WINDOW_HEIGHT-100;
    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;


    private GameWindow gameWindow;
    JRadioButton rbHVsAi;
    JRadioButton rbHVsH;
    ButtonGroup gameMode;

    JSlider jslFieldSize;
    JSlider jslFieldLength;

    public SettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Setting Game");

        setLayout(new GridLayout(8,1));
        add(new JLabel("Choose game mode"));
        rbHVsAi = new JRadioButton("Human Vs Ai", true);
        add(rbHVsAi);
        rbHVsH = new JRadioButton("Human Vs Human");
        rbHVsH.setEnabled(false);
        add(rbHVsH);
        gameMode = new ButtonGroup();
        gameMode.add(rbHVsAi);
        gameMode.add(rbHVsH);

        jslFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        jslFieldLength = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        add(new JLabel("Choose size"));
        jslFieldSize.setMajorTickSpacing(1);
        jslFieldSize.setPaintTicks(true);
        jslFieldSize.setPaintLabels(true);
        add(jslFieldSize);
        add(new JLabel("Choose winning length"));
        jslFieldLength.setMajorTickSpacing(1);
        jslFieldLength.setPaintTicks(true);
        jslFieldLength.setPaintLabels(true);
        add(jslFieldLength);
        jslFieldSize.addChangeListener(e -> {
            jslFieldLength.setMaximum(jslFieldSize.getValue());
        });

        JButton btnStartAGame = new JButton("Start a game");
        add(btnStartAGame);
        btnStartAGame.addActionListener(e -> {
            setVisible(false);

        int mode;
        if (rbHVsAi.isSelected()){
            mode = BattleMap.MODE_H_VS_AI;
        } else {
            mode = BattleMap.MODE_H_VS_H;
        }

        int fieldSize = jslFieldSize.getValue();
        int winningLength = jslFieldLength.getValue();

        LogikGame.SIZE = fieldSize;
        LogikGame. DOTS_TO_WIN = winningLength;
        LogikGame.initMap();
        LogikGame.gamefinished = false;
        gameWindow.startNewGame(mode, fieldSize, winningLength);
        setVisible(false);
        });
        setVisible(false);
    }
}
