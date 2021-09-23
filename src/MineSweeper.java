import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;


public class MineSweeper
{
  Random random = new Random();
  public int random1, random2, nummarked = 0;
  private int numrows, numcols, numcells, nummines = 0;
  private Cell[][] cells;

  public int sqrt(int x) // for square root of numcells
  {
    if (x == 0 || x == 1)
    {
      return x;
    }
    int i = 1, result = 1;

    while (result <= x)
    {
      i++;
      result = i * i;
    }
    return i - 1;
  }

  public MineSweeper()
  {
    numcells = 16;
    numrows = sqrt(numcells);
    numcols = numrows;
    cells = new Cell[numrows][numcols];
    for (int i = 0; i < numrows; i++)
    {
      for (int j = 0; j < numcols; j++)
      {
        cells[i][j] = new Cell(i, j);
      }
    }
    while (nummines < 3) // set amount of mines
    {
      int randomInteger = (int) (random.nextDouble() * numrows);
      int randomInteger2 = (int) (random.nextDouble() * numrows);
      if (cells[randomInteger][randomInteger2].getMined())
      {
        continue;
      } else
      {
        cells[randomInteger][randomInteger2].setMined(true);
        nummines++;
      }
    }

    for (int i = 0; i < numrows; i++) //for adjcount
    {

      for (int j = 0; j < numcols; j++)
      {
        int temp2 = 0;
        for (int z = 0; z < 8; z++)
        {
          if (getAdjCell(i, j, z).getMined())
          {
            temp2++;
          }
        }
        cells[i][j].setAdjCount(temp2);
      }
    }
  }

  public int getAdjMineCount(int i, int j)
  {
    int mineCount = 0;
    for (int m = 0; m < 8; m++)
    {
      if (getAdjCell(i, j, m).getMined())
      {
        mineCount++;
      }
    }
    return mineCount;
  }

  public int getMarkCount()
  {
    int count = 0;
    for (int i = 0; i < numrows; i++)
    {
      for (int j = 0; j < numcols; j++)
      {
        if (cells[i][j].getMarked())
        {
          count++;
        }
      }
    }
    return count;
  }

  public void show()
  {
    System.out.println(
        "Status: " + new DecimalFormat("00").format(nummarked) + "/" +
            new DecimalFormat("00").format(nummines));
    System.out.print("  ");
    for (int z = 0; z < numcols; z++)
    {
      System.out.print(" " + z);
    }
    System.out.println();
    System.out.print("  ");
    for (int a = 0; a < numcols; a++)
    {
      System.out.print(" " + "_");
    }
    System.out.println();
    for (int i = 0; i < numrows; i++)
    {
      System.out.print(i + "| ");
      for (int j = 0; j < numcols; j++)
      {
        cells[i][j].show();
        System.out.print(" ");
        if (j == numcols - 1)
        {
          System.out.print("| " + i);
        }
      }
      System.out.println("");
    }
    System.out.print("  ");
    for (int b = 0; b < numcols; b++)
    {
      System.out.print(" " + "_");
    }
    System.out.println();
    System.out.print("  ");
    for (int b = 0; b < numcols; b++)
    {
      System.out.print(" " + b);
    }
    System.out.println();
  }

  public void uncoverAll()
  {
    for (int i = 0; i < numrows; i++)
    {
      for (int j = 0; j < numcols; j++)
      {
        cells[i][j].setUncovered();
      }
    }
  }

  public Cell getAdjCell(int r, int c, int direction)
      throws ArrayIndexOutOfBoundsException
  {
    Cell temp = new Cell(0, 0);
    try
    {
      switch (direction)
      {
        case 0:
          if (cells[r][c + 1] != null)
          {
            temp = cells[r][c + 1];
          } else
          {
            temp = null;
          }
          break;

        case 1:
          if (cells[r - 1][c + 1] != null)
          {
            temp = cells[r - 1][c + 1];
          } else
          {
            temp = null;
          }
          break;

        case 2:
          if (cells[r - 1][c] != null)
          {
            temp = cells[r - 1][c];
          } else
          {
            temp = null;
          }
          break;

        case 3:
          if (cells[r - 1][c - 1] != null)
          {
            temp = cells[r - 1][c - 1];
          } else
          {
            temp = null;
          }
          break;

        case 4:
          if (cells[r][c - 1] != null)
          {
            temp = cells[r][c - 1];
          } else
          {
            temp = null;
          }
          break;

        case 5:
          if (cells[r + 1][c - 1] != null)
          {
            temp = cells[r + 1][c - 1];
          } else
          {
            temp = null;
          }
          break;

        case 6:
          if (cells[r + 1][c] != null)
          {
            temp = cells[r + 1][c];
          } else
          {
            temp = null;
          }
          break;

        case 7:
          if (cells[r + 1][c + 1] != null)
          {
            temp = cells[r + 1][c + 1];
          } else
          {
            temp = null;
          }
          break;
      }
    } catch (Exception e)
    {
    }
    return temp;
  }

  public boolean allNonMinesUncovered()
  {
    boolean tempbool = true;
    for (int i = 0; i < numrows; i++)
    {
      for (int j = 0; j < numcols; j++)
      {
        if (cells[i][j].getCovered() && !cells[i][j].getMined())
        {
          tempbool = false;
        }
      }
    }
    return tempbool;
  }


  public boolean allMinesMarked()
  {
    boolean tempbool = true;
    int counter = 0;
    for (int i = 0; i < numrows; i++)
    {
      for (int j = 0; j < numcols; j++)
      {
        if (cells[i][j].getMined() && !cells[i][j].getMarked())
        {
          tempbool = false;
        }
      }
    }
    return tempbool;
  }

  public void game()
  {
    String[] splitInput = new String[5];
    String input = new String("Lets go!");
    Scanner scanner = new Scanner(System.in);

    while (!input.isEmpty())
    {
      int i = 0;
      int arg1, arg2;

      System.out.print("> ");
      input = scanner.nextLine();
      splitInput = input.split(" ");

      if (splitInput[i].equals("show"))
      {
        show();
      } else if (splitInput[i].equals("u"))
      {
        arg1 = Integer.parseInt(splitInput[i + 1]);
        arg2 = Integer.parseInt(splitInput[i + 2]);

        if (cells[arg1][arg2].getMined())
        {
          uncoverAll();
          show();
          System.out.println("You blew up! Game over.");
          System.exit(-1);
        } else
        {
          cells[arg1][arg2].setUncovered();
          if (allMinesMarked() && allNonMinesUncovered())
          {
            System.out.println("You Win! Game over.");
            show();
            System.exit(-1);
          }
        }
      } else if (splitInput[i].equals("m"))
      {
        arg1 = Integer.parseInt(splitInput[i + 1]);
        arg2 = Integer.parseInt(splitInput[i + 2]);
        if (!cells[arg1][arg2].getCovered())
        {
          break;
        } else if (cells[arg1][arg2].getMarked())
        {
          cells[arg1][arg2].setMarked(false);
          nummarked--;
        } else
        {
          cells[arg1][arg2].setMarked(true);
          nummarked++;
          if (allMinesMarked() && allNonMinesUncovered())
          {
            System.out.println("You Win! Game over.");
            show();
            System.exit(-1);
          }
        }
      } else if (splitInput[i].equals("q"))
      {
        System.exit(-1);
      }   /* else if (splitInput[i].equals("test"))              my test
       {
          uncoverAll();
       }*/ else
      {
        System.out.println("Bad input. Try again.");
      }
      i++;
    }
  }

  public static void play()
  {
    MineSweeper mine = new MineSweeper();
    mine.game();

  }

  public static void main(String[] args)

  {
    System.out.println(
        "*Minesweeper game by Christopher Martinez*\ntype 'show' to see " +
            "current state of field\ntype 'u' then cell number to uncover " +
            "cell in the format 'u x y' (seperated by space)\ntype 'm' then " +
            "cell number to mark cell in the format 'm x y' (seperated by " +
            "space)\n");
    play();
  }
}


