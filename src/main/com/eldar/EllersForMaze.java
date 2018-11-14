package main.com.eldar;//this is the maze-generating algorithm, it generates a maze in O(N) time.
import java.util.Random;

public class EllersForMaze
{
    /*
    *   Пользуясь двухмерных массивом интов, создаём лабиринт, в котором 1 - стена, а 0 - комната/проход
    *   делаем это пользуясь алгоритмом Эллера (не Эйлера) который позволяет это делать в О(N) времени,
    *   и только использует пространство на 1 ряд
    *   сделан по подходу с https://github.com/mgaut72/
    *   с комментами на русском и изменениями под предоставленную задачу
     */

    static final int WALL = 1;
    static final int ROOM = 0;
    static final int UNDETERMINED = -2; //еще не сделали
    static final int SET_WALL = -1;     //пока только оффициально стена в сет

    private int shortRows;
    private int longRows;       //в кратком, создаем в 2х+1 раз больше чем дано, иначе половину
    private int longColumns;    //раз у нас будет получаться незаполненные или переполненные ряды/колонны.

    private int[][] maze;

    private pos start;              //для местоположения робота
    private pos finish;             //для проверки завершения прохода по лабиринту

    private int[] currentRow;
    private int[] nextRow;
    private int unusedSetNumber;    //итератор для того чтобы сет номер не использовался дважды

    public EllersForMaze(int rows, int columns)
    {
        shortRows = rows;

        longRows = rows*2+1;
        longColumns = columns*2+1;

        maze = new int[longRows][longColumns];

        currentRow = new int[columns*2-1];
        nextRow = new int[columns*2-1];

        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
            {
                maze[i][j] = WALL;
            }
        }

        for(int i = 0; i < nextRow.length; i++)
        {
            nextRow[i] = UNDETERMINED;
        }

        //инициализируем первый ряд, каждая комната в отдельную сет, стену между ними
        for(int i = 0; i < currentRow.length; i+=2)
        {
            currentRow[i] = i/2+1;
            if (i!=currentRow.length-1)
            {
                currentRow[i+1] = SET_WALL;
            }
            unusedSetNumber = currentRow[currentRow.length-1];
        }

        makeMaze();
    }

    private void makeMaze()
    {
        for(int i = 0; i < shortRows-1; i++)
        {
            //то что было следующим рядом становится текущим радом
            //новый следующий ряд заполняется значением "Не известно"

            if (i != 0)
            {
                for(int j = 0; j <currentRow.length; j++)
                {
                    currentRow[j] = nextRow[j];
                    nextRow[j] = UNDETERMINED;
                }
            }

            joinSets();
            makeVerticalCuts();

            for(int j = 0; j < currentRow.length;j+=2)
            {
                //для всех элементов в следующем ряду
                //уже не соединенными с какой-то сет
                //создаем новую сет
                if(nextRow[j] == UNDETERMINED)
                {
                    nextRow[j] = ++unusedSetNumber;
                }
                if(j!=nextRow.length-1)
                {
                    nextRow[j+1] = SET_WALL;
                }
            }

            //создав один ряд полностью
            //записываем результаты в массив
            for(int j = 0; j < currentRow.length; j++)
            {
                if(currentRow[j] == SET_WALL)
                {
                    maze[2*i+1][j+1] = WALL;    //Либо тут стена
                    maze[2*i+2][j+1] = WALL;    //И под ним автоматически стена
                }
                else
                {
                    maze[2*i+1][j+1] = ROOM;    //либо тут проход
                    if(currentRow[j] == nextRow[j])
                    {
                        maze[2*i+2][j+1] = ROOM;   //и под ним тоже проход
                    }
                }
            }
        }
        makeLastRow();
        makeEntranceAndExit();
    }

    private void joinSets()
    {
        Random rng = new Random();

        for(int i=1; i<currentRow.length-1; i+=2){ //checks only at wall locations

            //если мы на позиции стены, и по сторонам её комнаты из разных сет
            //И рнг позволит (чтобы не каждый раз а где то половину раз)
            //соединяем комнаты в единую сет (ту, у которой число меньше)

            if(currentRow[i] == SET_WALL && currentRow[i-1] != currentRow[i+1] && rng.nextBoolean())
            {

                currentRow[i] = 0;
                int oldSetNumber  = Math.max(currentRow[i-1],currentRow[i+1]);
                int newSetNumber = Math.min(currentRow[i-1],currentRow[i+1]);


                for(int j=0; j<currentRow.length; j++)
                {
                    if(currentRow[j] == oldSetNumber)
                    {
                        currentRow[j] = newSetNumber;
                    }
                }
            }
        }
    }


    private void makeVerticalCuts()
    {
        Random rng = new Random();
        int start = 0;     // где начнем (этот индекс включен в проход)
        int end = 0;       // где закончим (этот индекс тоже включен)

        boolean madeVertical;  /* проверяет, было ли сделано хоть одно
                                * соединение со следующим (нижним) уровнем
                                * как минимум одно обязательно
                                */

        int i;
        while (end != currentRow.length-1)
        {
            i = start;
            //сначала определяем кусок внутри которого все элементы в одной и той же сет
            while(i < currentRow.length-1 && currentRow[i] == currentRow[i+2])
            {
                i+=2;
            }
            //теперь знаем положение последнего элемента данной сет
            end = i;

            //для каждой сет, требуется чтобы было соединение вниз
            madeVertical = false;
            while(!madeVertical)
            {
                for(int j = start; j <=end; j+=2)
                {
                    if(rng.nextBoolean())
                    {
                        //соедините в одну сет
                        nextRow[j]=currentRow[j];
                        madeVertical=true;
                    }
                }
            }
            start = end+2; //передвигаем на следующую сет
        }
    }

    private void makeLastRow()
    {
        //все тоже самое, но мы не создаем соединения вниз
        for(int i=0; i<currentRow.length; i++)
        {
            currentRow[i] = nextRow[i];
        }

        for(int i = 1; i < currentRow.length-1; i+=2)
        {
            if(currentRow[i]== SET_WALL && currentRow[i-1]!=currentRow[i+1])
            {
                currentRow[i] = 0;

                int oldSetNumber = Math.min(currentRow[i-1],currentRow[i+1]);
                int newSetNumber = Math.max(currentRow[i-1],currentRow[i+1]);

                for(int j = 0; j < currentRow.length; j++)
                {
                    if(currentRow[j] == oldSetNumber)
                    {
                        currentRow[j] = newSetNumber;
                    }
                }
            }
        }

        for(int j = 0; j < currentRow.length; j++)
        {
            if(currentRow[j] == SET_WALL)
            {
                maze[longRows-2][j+1] = WALL;
            }
            else
            {
                maze[longRows-2][j+1] = ROOM;
            }
        }
    }

    private void makeEntranceAndExit()
    {
        Random rng = new Random();
        boolean exitNotFound = true;
        boolean entryNotFound = true;

        while(entryNotFound)
        {
            int potentialEntry = rng.nextInt(shortRows-1)*2+1;
            //проверяем что перед ним не стена
            if (maze[potentialEntry][1]==ROOM)
            {
                maze[potentialEntry][0] = ROOM;
                start = new pos(0,potentialEntry);
                entryNotFound = false;
            }
        }

        while(exitNotFound)
        {
            int potentialExit = rng.nextInt(shortRows-1)*2+1;
            if(maze[potentialExit][longColumns-2]==ROOM)
            {
                maze[potentialExit][longColumns-1] = ROOM;
                finish = new pos(longColumns-1,potentialExit);
                exitNotFound = false;
            }
        }
    }
    public int[][] getMaze()
    {
        return maze;
    }

    public int getHeight()
    {
        return maze.length;
    }

    public int getWidth()
    {
        return maze[0].length;
    }

    public pos getStart()
    {
        return start;
    }

    public boolean isWall(pos xy)
    {
        return maze[xy.getRow()][xy.getCol()] == WALL;
    }

    public pos getFinish() { return finish; }

    public boolean isFinished(pos current)
    {
        return finish.getCol()==current.getCol() && finish.getRow()==current.getRow();
    }

}
