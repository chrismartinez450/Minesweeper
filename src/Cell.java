public class Cell
{
  private int row, col;
  private boolean covered, marked, mined;
  private int adjcount;

  public Cell(int r, int c)
  {
    this.row = r;
    this.col = c;
    this.covered = true;
    this.marked = false;

  }

  public void show()
  {
    if (covered == true && !marked)
    {
      System.out.print("?");
    } else if (covered == true && marked)
    {
      System.out.print("X");
    } else if (!covered && mined)
    {
      System.out.print("*");
    } else if (!covered && !mined && adjcount == 0)
    {
      System.out.print("_");
    } else if (!covered && !mined && adjcount > 0)
    {
      System.out.print(adjcount);
    }

  }

  public boolean getMined()
  {
    return (mined);
  }

  public void setMined(boolean m)
  {
    if (m == true)
    {
      mined = true;
    } else
    {
      mined = false;
    }
  }

  public boolean getCovered()
  {
    return covered;
  }

  public void setUncovered()
  {
    covered = false;
  }

  public boolean getMarked()
  {
    return marked;
  }

  public void setMarked(boolean m)
  {
    if (m == true)
    {
      marked = true;
    } else
    {
      marked = false;
    }
  }

  public int getAdjCount()
  {
    return adjcount;
  }

  public void setAdjCount(int c)
  {
    adjcount = c;
  }

  public int getRow()
  {
    return row;
  }

  public int getCol()
  {
    return col;
  }
}
