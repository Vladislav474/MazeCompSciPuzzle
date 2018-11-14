package main.com.eldar.bot_brains;
import main.com.eldar.Bot;
import java.util.Random;

public class BotBrainGRSh474 implements BotBrain {
  private Bot bot;
  DirectionOfBot Direction;

  public BotBrainGRSh474(){
   Direction = new DirectionOfBot(0);
  }

  public void solveMaze(Bot curBot){
    this.bot=curBot;
    keep_to_the_right_side();//обход лабиринта по правой стороне
    return;
  }
  
  private void keep_to_the_right_side()
  {
      while(!bot.isFinished()){
      for(;;){
          if(bot.checkDir((Direction.get()))&&!bot.checkDir(Direction.get_clockwise_rot())){
          bot.moveInDir(Direction.get());
          if(bot.isFinished())break;
          }
          else if(bot.checkDir(Direction.get_clockwise_rot())){
              Direction.set_clockwise_rot();
              bot.moveInDir(Direction.get());
              break;
            }
          else if(bot.checkDir(Direction.get_counterclockwise_rot())){
              Direction.set_counterclockwise_rot();
              bot.moveInDir(Direction.get());
              break;
            }
          else{
          Direction.reverse_bot();
          bot.moveInDir(Direction.get());
          }
        }  
      }
   }
}
