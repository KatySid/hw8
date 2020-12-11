package GameXO;

import javax.swing.*;
import java.awt.*;

public class StatusGame extends JFrame {
    private static final int WINDOW_X= GameWindow.WINDOW_X+50;
    private static final int WINDOW_Y= GameWindow.WINDOW_Y+50;
    private static final int WINDOW_WIDTH= GameWindow.WINDOW_WIDTH/2;
    private static final int WINDOW_HEIGHT= GameWindow.WINDOW_HEIGHT/3;
    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;
    private GameWindow gameWindow;


    public StatusGame(GameWindow gameWindow){
        this.gameWindow=gameWindow;
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Status Game");
        add(new JLabel(LogikGame.getStatusMessage(), SwingConstants.CENTER), BorderLayout.CENTER);
        setVisible(false);

    }

}
