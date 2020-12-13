package GameXO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleMap  extends JPanel {
    private GameWindow gameWindow;
    static final int MODE_H_VS_AI = 0;
    static final int MODE_H_VS_H = 1;
    private int mode;
    private int fieldSize;
    private int winningLength;
    private int cellWidth;
    private int cellHeigth;
    private StatusGame statusGame;

    private boolean isInit;
    public BattleMap(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int cellX = e.getX()/cellWidth;
                int cellY = e.getY() / cellHeigth;
                if(!LogikGame.gamefinished) {
                    LogikGame.humanTurn(cellX, cellY);
                }
                statusGame= new StatusGame(gameWindow);
                if (LogikGame.gamefinished){

                    statusGame.setVisible(true);
                }
                repaint();
                }
        });
    }

    public void startNewGame(int mode, int fieldSize, int winningLength){
        this.mode = mode;
        this.fieldSize=fieldSize;
        this.winningLength =winningLength;
        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isInit){
            return;
        }
        cellHeigth = getHeight()/ fieldSize;
        cellWidth = getWidth()/fieldSize;

        for (int i = 1; i < fieldSize; i++) {
            int y = i*cellHeigth;
            g.drawLine(0,y, getWidth(), y);
        }

        for (int i = 1; i < fieldSize ; i++) {
            int x = i* cellWidth;
            g.drawLine(x, 0, x, getHeight());
        }

        for (int i = 0; i < LogikGame.SIZE; i++) {
            for (int j = 0; j < LogikGame.SIZE; j++) {
                if (LogikGame.map [i][j] == LogikGame.DOT_X){
                    drawX(g, j, i);
                }
            }
        }
        for (int i = 0; i < LogikGame.SIZE; i++) {
            for (int j = 0; j < LogikGame.SIZE; j++) {
                if (LogikGame.map [i][j] == LogikGame.DOT_O){
                    drawO(g, j, i);
                }
            }
        }

        if (!LogikGame.tie && LogikGame.gamefinished ){
            drawWinLine(g, LogikGame.x1, LogikGame.y1, LogikGame.x2, LogikGame.y2);}
    }

    private void drawX(Graphics g, int cellX, int cellY){
        ((Graphics2D)g).setStroke(new BasicStroke(5));
        g.setColor(Color.RED);
        g.drawLine(cellX*cellWidth, cellY*cellHeigth, (cellX+1)*cellWidth, (cellY+1)*cellHeigth);
        g.drawLine((cellX+1)*cellWidth, cellY*cellHeigth, cellX*cellWidth, (cellY+1)*cellHeigth);
    }

    private void drawO(Graphics g, int cellX, int cellY){
        ((Graphics2D)g).setStroke(new BasicStroke(5));
        g.setColor(Color.MAGENTA);
        g.drawOval(cellX*cellWidth, cellY*cellHeigth,cellWidth, cellHeigth);
    }
    private void drawWinLine(Graphics g, int x1, int y1, int x2, int y2){
        ((Graphics2D)g).setStroke(new BasicStroke(10));
        g.setColor(Color.YELLOW);
        g.drawLine( x1*cellWidth+cellWidth/2,  y1*cellHeigth+cellHeigth/2,  x2*cellWidth+cellWidth/2,  y2*cellHeigth+cellHeigth/2);
    }
}
