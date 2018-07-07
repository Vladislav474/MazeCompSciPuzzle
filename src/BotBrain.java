/**
 * Created by eldar on 10/6/2017.
 */
import java.util.Random;
import java.util.Vector;


//This is the part of the code that students ought to write themselves
//a random movement implementation is provided so that they can see what the bot's actions look like
//but it is obviously a very inefficient implementation and they ought to aim for a better one
public class BotBrain
{
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;


    private Bot bot;

    public BotBrain(Bot bot)
    {
        this.bot = bot;
    }
    public void solveMaze()
    {
        randomMouse();
        return;
    }

    private void randomMouse()
    {
        Vector<Integer> possibleDirections = new Vector<Integer>();
        Random rng = new Random();

        while(!bot.isFinished())
        {
            if(bot.checkUp()){possibleDirections.add(UP);}
            if(bot.checkDown()){possibleDirections.add(DOWN);}
            if(bot.checkLeft()){possibleDirections.add(LEFT);}
            if(bot.checkRight()){possibleDirections.add(RIGHT);}
            int dir = rng.nextInt(possibleDirections.size());
            if(possibleDirections.elementAt(dir)==UP){bot.moveUp();}
            else if(possibleDirections.elementAt(dir)==DOWN){bot.moveDown();}
            else if(possibleDirections.elementAt(dir)==RIGHT){bot.moveRight();}
            else {bot.moveLeft();}
            possibleDirections.clear();
        }
        return;
    }
}
