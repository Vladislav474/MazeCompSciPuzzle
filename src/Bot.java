//this is the actual bot that moves and interacts with the environment
//the students only interact with this class via the methods available to them from the BotBrain class
public class Bot
{
    static final int WALL = 1;
    static final int MARK = 2;
    private int[][] maze;
    private pos start;
    private pos finish;
    private pos current;
    Maze parent;

    public Bot(int[][] maze, pos start, pos finish, Maze parent)
    {
        this.maze = maze;
        this.start = start;
        this.finish = finish;
        this.parent = parent;
        current = start;
    }

    public boolean moveUp()
    {
        boolean moved = checkUp();
        if(!isFinished() && moved)
        {
            current.setRow(current.getRow()-1);
            parent.updateBotLocation(current);
        }
        return moved;
    }
    public boolean moveDown()
    {
        boolean moved = checkDown();
        if(!isFinished() && moved)
        {
            current.setRow(current.getRow()+1);
            parent.updateBotLocation(current);
        }
        return moved;
    }
    public boolean moveLeft()
    {
        boolean moved = checkLeft();
        if (!isFinished() && moved)
        {
            current.setCol(current.getCol()-1);
            parent.updateBotLocation(current);
        }
        return moved;
    }
    public boolean moveRight()
    {
        boolean moved = checkRight();
        if (!isFinished() && moved)
        {
            current.setCol(current.getCol()+1);
            parent.updateBotLocation(current);
        }
        return moved;
    }
    public boolean checkUp()
    {
        if(current.getRow()>1)
        {
            return maze[current.getRow()-1][current.getCol()] != WALL;
        }
        return false;
    }
    public boolean checkDown()
    {
        if(current.getRow()< maze.length-1)
        {
            return maze[current.getRow()+1][current.getCol()] != WALL;
        }
        return false;
    }
    public boolean checkLeft()
    {
        if(current.getCol() > 0)
        {
            return maze[current.getRow()][current.getCol()-1] != WALL;
        }
        return false;
    }
    public boolean checkRight()
    {
        if(current.getCol() < maze[0].length-1)
        {
            return maze[current.getRow()][current.getCol()+1] != WALL;
        }
        return false;
    }

    public void markRoom()
    {
        maze[current.getRow()][current.getCol()] = MARK;
        parent.updateMarks(current);
    }
    public boolean upIsMarked()
    {
        if(checkUp()){ return maze[current.getRow()-1][current.getCol()] == MARK; }
        return false;
    }
    public boolean downIsMarked()
    {
        if(checkDown()){ return maze[current.getRow()+1][current.getCol()] == MARK; }
        return false;
    }
    public boolean rightIsMarked()
    {
        if(checkRight()){ return maze[current.getRow()][current.getCol()+1] == MARK; }
        return false;
    }
    public boolean leftIsMarked()
    {
        if(checkLeft()){ return maze[current.getRow()][current.getCol()-1] == MARK; }
        return false;
    }
    public boolean isFinished()
    {
        return current.getCol()==finish.getCol() && current.getRow()==finish.getRow();
    }

    public pos getCurrent()
    {
        return current;
    }

}
