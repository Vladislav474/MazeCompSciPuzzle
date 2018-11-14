package main.com.eldar.bot_brains;

import main.com.eldar.Bot;

//Интерфейс, который определяет поведение любой имплементации BotBrain
public interface BotBrain {
  public static final int[] UP = {1,0};
  public static final int[] RIGHT = {0,1};
  public static final int[] DOWN = {-1,0};
  public static final int[] LEFT = {0,-1};

  //вам предоставляется робот, который способен
  //посмотреть во все четыре направления (описаные выше)
  //распознать если там стенка или проход
  //и, отдельно, может проверить если проходы рядом с ним помечены
  //и пометить текущую комнату
  //пользуясь этими функциями (находящимися в классе Бот)
  public void solveMaze(Bot curBot);

}
