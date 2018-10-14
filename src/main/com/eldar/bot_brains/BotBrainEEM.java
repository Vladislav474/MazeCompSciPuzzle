package com.eldar.bot_brains;


import com.eldar.Bot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotBrainEEM implements BotBrain {

  private List<int[]> directions;
  private Bot bot;

  public BotBrainEEM(){
    directions = new ArrayList<>(4);
    directions.add(UP);
    directions.add(DOWN);
    directions.add(LEFT);
    directions.add(RIGHT);
  }

  public void solveMaze(Bot curBot){
    this.bot=curBot;
    randomMouse();
    return;
  }

  private void randomMouse()
  {
    List<int[]> possibleDirections = new ArrayList<>();
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

