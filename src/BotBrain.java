/**
 * Created by eldar on 10/6/2017.
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;


//This is the part of the code that students ought to write themselves
//a random movement implementation is provided so that they can see what the bot's actions look like
//but it is obviously a very inefficient implementation and they ought to aim for a better one
public class BotBrain
{
    static final int[] UP = {1,0};
    static final int[] RIGHT = {0,1};
    static final int[] DOWN = {-1,0};
    static final int[] LEFT = {0,-1};
    List<int[]> directions;

    private Bot bot;

    public BotBrain(Bot bot)
    {
        this.bot = bot;
        directions = new LinkedList<>();
        directions.add(UP);
        directions.add(DOWN);
        directions.add(LEFT);
        directions.add(RIGHT);
    }
    public void solveMaze()
    {
        randomMouse();
        return;
    }

    private void randomMouse()
    {
        List<int[]> possibleDirections = new LinkedList<>();
        Random rng = new Random();

        while(!bot.isFinished())
        {
            for(int[] direction : directions){
                if(bot.checkDir(direction)){possibleDirections.add(direction);}
            }
            int dir = rng.nextInt(possibleDirections.size());
            bot.moveInDir(possibleDirections.get(dir));
            possibleDirections.clear();
        }
        return;
    }
}
