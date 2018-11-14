package main.com.eldar;

import java.util.Objects;

//mini-class used for tracking position within the maze
public class pos
{
    private int col, row;

    public pos(int col, int row)
    {
        this.col = col;
        this.row = row;
    }

    public int getCol()
    {
        return col;
    }

    public int getRow()
    {
        return row;
    }

    public void setCol(int col)
    {
        this.col = col;
    }
    public void setRow(int row)
    {
        this.row = row;
    }

  @Override
  public boolean equals(final Object o){
    if(this==o) return true;
    if(o==null||getClass()!=o.getClass()) return false;
    final pos position = (pos)o;
    return position.col==this.col&&position.row==this.row;
  }
  @Override
  public int hashCode(){
    return Objects.hash(col, row);
  }
}
