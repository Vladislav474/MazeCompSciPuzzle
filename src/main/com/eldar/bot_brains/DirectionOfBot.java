package main.com.eldar.bot_brains;
import static main.com.eldar.bot_brains.BotBrain.DOWN;
import static main.com.eldar.bot_brains.BotBrain.LEFT;
import static main.com.eldar.bot_brains.BotBrain.RIGHT;
import static main.com.eldar.bot_brains.BotBrain.UP;
import java.util.ArrayList;
import java.util.List;

public class DirectionOfBot {
    private static List<int[]> direction;
    private static int now_direction; // текущее направение робота
    public DirectionOfBot(int dir){
        now_direction=dir;
        direction = new ArrayList<>(4);
        direction.add(DOWN);   //0
        direction.add(LEFT);   //1
        direction.add(UP);     //2
        direction.add(RIGHT);  //3
}
    //запрос направления по часовой стреклке от текущего
    public int[] get_clockwise_rot(){
        if(now_direction==0){
            return direction.get(3);
        }
        else{
            return direction.get(now_direction-1);
        }
    }
    //изменение направления по часовой стрелке от текущего
     public int[] set_clockwise_rot(){
        if(now_direction==0){
            now_direction=3;
        }
        else{
            now_direction-=1;
        }
        return direction.get(now_direction);
    }
     //запрос направления против часовой стреклки от текущего
    public int[] get_counterclockwise_rot(){
            if(now_direction==3){
            return direction.get(0);
        }
        else{
            return direction.get(now_direction+1);
        }
    } 
    //изменение направления против часовой стрелки от текущего
        public int[] set_counterclockwise_rot(){
            if(now_direction==3){
            now_direction=0;
        }
        else{
            now_direction+=1;
        }
        return direction.get(now_direction);
    }  
        public void reverse_bot(){
          if(now_direction==2||now_direction==3)now_direction-=2;
          else if(now_direction==1)now_direction=3;
          else if(now_direction==0)now_direction=2;
        }
    public int[] get(){
        return direction.get(now_direction);
    }
}
