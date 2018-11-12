package com.eldar.bot_brains;


import com.eldar.Bot;
import static com.eldar.bot_brains.BotBrain.DOWN;
import static com.eldar.bot_brains.BotBrain.LEFT;
import static com.eldar.bot_brains.BotBrain.RIGHT;
import static com.eldar.bot_brains.BotBrain.UP;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotBrainGRSh474 implements BotBrain {

  private List<int[]> directions;
  private Bot bot;

  public BotBrainGRSh474(){
    directions = new ArrayList<>(5);
    directions.add(RIGHT);
    directions.add(DOWN);
    directions.add(LEFT);
    directions.add(UP);
    directions.add(RIGHT);
  }

  public void solveMaze(Bot curBot){
    this.bot=curBot;
    keep_to_the_left_side();//обход лабиринта по левой стороне
    return;
  }
  private void keep_to_the_left_side()
  {
      int dir = 0;
      while(!bot.isFinished()){
      for(;;){
          if(bot.checkDir(directions.get(dir))&&!bot.checkDir(directions.get(dir+1))){
          bot.moveInDir(directions.get(dir));
          }
          else if(bot.checkDir(directions.get(dir+1))){
              if(dir!=3)dir+=1;
              else dir = 0;
              bot.moveInDir(directions.get(dir));
              break;
            }
          else {
              if(dir!=0)dir-=1;
              else dir = 3;
              bot.moveInDir(directions.get(dir));
              break;
            }
        }  
    }
  }
}