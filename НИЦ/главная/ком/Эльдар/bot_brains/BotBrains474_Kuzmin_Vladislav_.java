package com.eldar.bot_brains;

import com.eldar.Bot;

/**
 *
 * @author Vlad
 */
public class BotBrains474_Kuzmin_Vladislav_ implements BotBrain
{
    private Bot bot;
    DirectionOfBot Direction;

  public BotBrains474_Kuzmin_Vladislav_()
  {
   Direction = new DirectionOfBot(0);
  }

  public void solveMaze(Bot curBot)
  {
    this.bot=curBot;
    
    while(!bot.isFinished())
    found_way();//обход лабиринта держась левой стороны  
    
  }

  private void found_way()
  {//Используем алгоритм левой стены 
      if(bot.checkDir(Direction.get_counterclockwise_rot()))
      {//если есть путь налево то пооврачиваем и делаем шаг
            Direction.set_counterclockwise_rot();
            bot.moveInDir(Direction.get());
      }
      else if(bot.checkDir((Direction.get())))
      {//если есть путь прямо то делаем шаг
            bot.moveInDir(Direction.get());
      }
      else if(bot.checkDir(Direction.get_clockwise_rot()))
      {//если путь направо есть то пооврачиваем и делаем шаг
            Direction.set_clockwise_rot();
            bot.moveInDir(Direction.get());
             
      }
      else
      { //если путей нет - необходимо развернуться
           Direction.reverse_bot();
           bot.moveInDir(Direction.get());
      }

  }
}
