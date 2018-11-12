package com.eldar;

import com.eldar.bot_brains.BotBrain;
import com.eldar.bot_brains.BotBrainGRSh474;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//main class that calls other classes
//draws a maze, and places a bot at the start of the maze
//as the bot moves, its position in the maze as well as previously visited cells are tracked
//if path marking is implemented, that is also tracked.
public class Maze extends JComponent
{
    JOptionPane jOptionPane;
    EllersForMaze mazeGenerator = new EllersForMaze(400,800);
    int squareSize = 1;
    pos start;
    pos finish;
    pos current;
    private ArrayList<pos> trailArray;
    private ArrayList<pos> markedArray;
    private Bot ourBot;
    private long algoRunTime;


    public static void main(String[] args)
    {
        JFrame window = new JFrame("Maze");
        Maze thisMaze = new Maze();
        window.add(thisMaze);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        thisMaze.placeRobot();
        thisMaze.runRobot();
    }

    private void placeRobot()
    {
        start = new pos(mazeGenerator.getStart().getCol(), mazeGenerator.getStart().getRow());
        finish = new pos(mazeGenerator.getStart().getCol(), mazeGenerator.getFinish().getRow());
        trailArray = new ArrayList<>();
        markedArray = new ArrayList<>();
        ourBot = new Bot(mazeGenerator.getMaze(),mazeGenerator.getStart(),mazeGenerator.getFinish(), this);
        current = ourBot.getCurrent();
        validate();
        repaint();
    }

    public void updateBotLocation(pos p)
    {
        pos tmp = new pos(p.getCol(),p.getRow());
        trailArray.add(tmp);
        validate();
        repaint();
    }

    public void updateMarks(pos p)
    {
        pos tmp = new pos(p.getCol(),p.getRow());
        markedArray.add(tmp);
    }

    private void runRobot()
    {

        int index = 0;
        long startTime = System.currentTimeMillis();
        BotBrain algorithm = new BotBrainGRSh474();
        algorithm.solveMaze(ourBot);
        algoRunTime = System.currentTimeMillis() - startTime;
        //algoRunTime/=1000;

        JOptionPane.showMessageDialog(null,"Поздравляю, ваш алгоритм решил лабиринт за " +algoRunTime +" миллисекунд" );

    }

    public Dimension getPreferredSize()
    {
        return new Dimension(mazeGenerator.getWidth()*squareSize, mazeGenerator.getHeight()*squareSize);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        for (int x = 0; x < mazeGenerator.getWidth(); x++)
        {
            for (int y = 0; y < mazeGenerator.getHeight(); y++)
            {
                pos pstn = new pos(x,y);
                if (mazeGenerator.isWall(pstn))
                {
                    g.fillRect(x*squareSize,y*squareSize,squareSize,squareSize);
                }
            }
        }

        g.setColor(Color.RED);

        for(pos p : trailArray)
        {
            g.fillRect(p.getCol()*squareSize, p.getRow()*squareSize, squareSize, squareSize);
        }

        g.setColor(Color.BLUE);

        for(pos p : markedArray)
        {
            g.fillRect(p.getCol()*squareSize, p.getRow()*squareSize, squareSize, squareSize);
        }

        g.setColor(Color.RED);
        if(finish!=null){g.fillRect(finish.getCol()*squareSize,
                finish.getRow()*squareSize,
                squareSize,squareSize);}

        g.setColor(Color.ORANGE);

        if(current!=null) {g.fillRect(current.getCol()*squareSize,
                current.getRow()*squareSize,
                squareSize,squareSize); }

        g.setColor(Color.GREEN);
        if(start!=null){g.fillRect(start.getCol()*squareSize,
                start.getRow()*squareSize,
                squareSize,squareSize);}
    }

}
