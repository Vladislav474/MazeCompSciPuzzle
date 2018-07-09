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

    public boolean moveInDir(int[] dir)
    {
        boolean moved = checkDir(dir);
        if(!isFinished() && moved)
        {
            current.setRow(current.getRow()+dir[0]);
            current.setCol(current.getCol()+dir[1]);
            parent.updateBotLocation(current);
        }
        return moved;
    }
    public boolean checkDir(int[] dir)
    {
        boolean leftRight = dir[1] != 0;
        boolean upDown = dir[0] != 0;
        if(leftRight){
            if(current.getCol()+dir[1]>=0&&current.getCol()+dir[1]<=maze.length-1){
                return maze[current.getRow()][current.getCol()+dir[1]] != WALL;
            }
        }
        else if(upDown){
            if(current.getRow()+dir[0]>=0&&current.getRow()+dir[0]<=maze[0].length-1){
                return maze[current.getRow()+dir[0]][current.getCol()] != WALL;
            }
        }
        return false;
    }

    public void markRoom()
    {
        maze[current.getRow()][current.getCol()] = MARK;
        parent.updateMarks(current);
    }
    public boolean dirIsMarked(int[] dir)
    {
        if(checkDir(dir)){ return maze[current.getRow()+dir[0]][current.getCol()+dir[1]] == MARK; }
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
