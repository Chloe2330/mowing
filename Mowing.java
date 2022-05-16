// Chloe Lin
// Period 1
// 12/31/2020
// lawn mower program
package APCS.jan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mowing
{
    public static void main(String [] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of lawns.");
        int lawns = s.nextInt();
        s.nextLine();

        // Arraylist holds the lawns, can be accessed
        List <String[][]> storage = new ArrayList<>();

        for(int i = 0; i < lawns; i ++)
        {
            System.out.println("Enter the length of the lawn.");
            int length = s.nextInt();
            s.nextLine();
            System.out.println("Enter the width of the lawn.");
            int width = s.nextInt();
            s.nextLine();
            System.out.println("Enter the row number of the initial center of mower (0-" + (length-1) + ").");
            int mowrow = s.nextInt();
            s.nextLine();
            System.out.println("Enter the column number of the initial center of mower (0-" + (width-1) + ").");
            int mowcol = s.nextInt();
            s.nextLine();

            // utilizes createLawn method
            String[][] lawngrid = createLawn(length, width, mowrow, mowcol);
            storage.add(i, lawngrid);

            // moves initial center of mower up, mows in four directions
            for(int j = mowrow; j >= 1; j--)
            {
                mowLawnUp(storage.get(i), length, width, j, mowcol);
                mowLawnDown(storage.get(i), length, width, j, mowcol);
                mowLawnLeft(storage.get(i), length, width, j, mowcol);
                mowLawnRight(storage.get(i), length, width, j, mowcol);
                // breaks for loop if mower hits a tree
                if(j == 1 || storage.get(i)[j][mowcol].equals("T ") || storage.get(i)[j-1][mowcol-1].equals("T ") || storage.get(i)[j-1][mowcol].equals("T ") || storage.get(i)[j-1][mowcol+1].equals("T ") || storage.get(i)[j][mowcol-1].equals("T ") || storage.get(i)[j][mowcol+1].equals("T ") || storage.get(i)[j+1][mowcol-1].equals("T ") || storage.get(i)[j+1][mowcol].equals("T ") || storage.get(i)[j+1][mowcol+1].equals("T "))
                {
                    break;
                }
            }

            // moves initial center of mower down, mows in four directions
            for(int k = mowrow; k <= length - 2; k++)
            {
                mowLawnUp(storage.get(i), length, width, k, mowcol);
                mowLawnDown(storage.get(i), length, width, k, mowcol);
                mowLawnLeft(storage.get(i), length, width, k, mowcol);
                mowLawnRight(storage.get(i), length, width, k, mowcol);
                // breaks for loop if mower hits a tree
                if(k == length - 2 || storage.get(i)[k][mowcol].equals("T ") || storage.get(i)[k-1][mowcol-1].equals("T ") || storage.get(i)[k-1][mowcol].equals("T ") || storage.get(i)[k-1][mowcol+1].equals("T ") || storage.get(i)[k][mowcol-1].equals("T ") || storage.get(i)[k][mowcol+1].equals("T ") || storage.get(i)[k+1][mowcol-1].equals("T ") || storage.get(i)[k+1][mowcol].equals("T ") || storage.get(i)[k+1][mowcol+1].equals("T "))
                {
                    break;
                }
            }

            // moves initial center of mower left, mows in four directions
            for(int l = mowcol; l >= 1; l--)
            {
                mowLawnUp(storage.get(i), length, width, mowrow, l);
                mowLawnDown(storage.get(i), length, width, mowrow, l);
                mowLawnLeft(storage.get(i), length, width, mowrow, l);
                mowLawnRight(storage.get(i), length, width, mowrow, l);
                // breaks for loop if mower hits a tree
                if(l == 1 || storage.get(i)[mowrow][l].equals("T ") || storage.get(i)[mowrow-1][l-1].equals("T ") || storage.get(i)[mowrow-1][l].equals("T ") || storage.get(i)[mowrow-1][l+1].equals("T ") || storage.get(i)[mowrow][l-1].equals("T ") || storage.get(i)[mowrow][l+1].equals("T ") || storage.get(i)[mowrow+1][l-1].equals("T ") || storage.get(i)[mowrow+1][l].equals("T ") || storage.get(i)[mowrow+1][l+1].equals("T "))
                {
                    break;
                }
            }

            // moves initial center of mower right, mows in four directions
            for(int m = mowcol; m < width - 2; m++)
            {
                mowLawnUp(storage.get(i), length, width, mowrow, m);
                mowLawnDown(storage.get(i), length, width, mowrow, m);
                mowLawnLeft(storage.get(i), length, width, mowrow, m);
                mowLawnRight(storage.get(i), length, width, mowrow, m);
                // breaks for loop if mower hits a tree
                if(m == width - 2 || storage.get(i)[mowrow][m].equals("T ") || storage.get(i)[mowrow-1][m-1].equals("T ") || storage.get(i)[mowrow-1][m].equals("T ") || storage.get(i)[mowrow-1][m+1].equals("T ") || storage.get(i)[mowrow][m-1].equals("T ") || storage.get(i)[mowrow][m+1].equals("T ") || storage.get(i)[mowrow+1][m-1].equals("T ") || storage.get(i)[mowrow+1][m].equals("T ") || storage.get(i)[mowrow+1][m+1].equals("T "))
                {
                    break;
                }
            }

            // arbitrary value to ensure that no "center Cs" are missed
            for(int z = 0; z < lawngrid.length; z++)
            {
                // loops through 2d array (lawngrid)
                for (int a = 1; a < lawngrid.length-1; a++)
                {
                    for (int b = 1; b < lawngrid[a].length-1; b++)
                    {
                        // detects a "center C" (surrounded by 8 other Cs), mows in four directions again
                        if(lawngrid[a][b].equals("C ") && (lawngrid[a-1][b-1].equals("C ") && lawngrid[a-1][b].equals("C ") && lawngrid[a-1][b+1].equals("C ") && lawngrid[a][b-1].equals("C ") && lawngrid[a][b+1].equals("C ") && lawngrid[a+1][b-1].equals("C ") && lawngrid[a+1][b].equals("C ") && lawngrid[a+1][b+1].equals("C ")) && (a > 0 && a <= length - 2) && (b > 0 && b <= width -2))
                        {
                            mowLawnUp(storage.get(i), length, width, a, b);
                            mowLawnDown(storage.get(i), length, width, a, b);
                            mowLawnLeft(storage.get(i), length, width, a, b);
                            mowLawnRight(storage.get(i), length, width, a, b);
                        }
                    }
                }
            }
            displayLawn(storage.get(i));
            System.out.println("\n");
        }
    }

    // creating the lawn with 12 autogenerated trees
    public static String [][] createLawn(int l, int w, int mr, int mc)
    {
        String [][] lawngrid = new String[l][w];
        for (int i = 0; i < lawngrid.length; i++)
        {
            for (int j = 0; j < lawngrid[i].length; j++)
            {
                lawngrid[i][j] = ". ";
            }
        }
        int counter = 0;
        while(counter < 12)
        {
            {
                int min = 0;
                int maxrow = l-1;
                int maxcol = w-1;
                int rangerow = maxrow - min + 1;
                int rangecol = maxcol - min + 1;
                // randomly rolling two integers within a range
                int randomrow = (int)(Math.random() * rangerow) + min;
                int randomcol = (int)(Math.random() * rangecol) + min;
                // does not allow user to place mower center on edge of lawn
                if(mr == 0 || mr == l-1 || mc == 0 || mc == w-1)
                {
                    System.out.println("cannot put mower on the edge of lawn");
                    System.exit(0);
                }
                else
                {
                    // does not allow trees to be placed on the 9 cells occupied by the mower
                    if((randomrow == mr && randomcol == mc) || (randomrow == mr-1 && randomcol == mc-1) || (randomrow == mr-1 && randomcol == mc) || (randomrow == mr-1 && randomcol == mc+1) || (randomrow == mr && randomcol == mc-1) || (randomrow == mr && randomcol == mc+1) || (randomrow == mr+1 && randomcol == mc-1) || (randomrow == mr+1 && randomcol == mc) || (randomrow == mr+1 && randomcol == mc+1))
                    {
                        lawngrid [randomrow][randomcol] = ". ";
                    }
                    else
                    {
                        // accounts for duplicate rolls (counter does not increase)
                        if(lawngrid [randomrow][randomcol].equals("T "))
                        {
                            lawngrid[randomrow][randomcol] = "T ";
                        }
                        // counter increases after every unique roll
                        else
                        {
                            lawngrid [randomrow][randomcol] = "T ";
                            counter++;
                        }
                    }
                }
            }
        }
        return lawngrid;
    }

    // displaying the lawn
    public static void displayLawn(String [][] lg)
    {
        for (int i = 0; i < lg.length; i++)
        {
            for (int j = 0; j < lg[i].length; j++)
            {
                System.out.print(lg[i][j]);
            }
            System.out.println();
        }
    }

    // mows up
    public static String [][] mowLawnUp(String [][] lg, int l, int w, int mr, int mc)
    {
        boolean check = true;
        while(check)
        {
            // mower stops when it reaches edge of lawn
            if(mr == 0 || mr == l-1)
            {
                check = false;
            }
            else
            {
                // mower stops when it hits a tree
                if(lg[mr][mc].equals("T ") || lg[mr-1][mc-1].equals("T ") || lg[mr-1][mc].equals("T ") || lg[mr-1][mc+1].equals("T ") || lg[mr][mc-1].equals("T ") || lg[mr][mc+1].equals("T ") || lg[mr+1][mc-1].equals("T ") || lg[mr+1][mc].equals("T ") || lg[mr+1][mc+1].equals("T "))
                {
                    check = false;
                }
                else
                {
                    lg[mr][mc] = "C ";
                    lg[mr-1][mc-1] = "C ";
                    lg[mr-1][mc] = "C ";
                    lg[mr-1][mc+1] = "C ";
                    lg[mr][mc-1] = "C ";
                    lg[mr][mc+1] = "C ";
                    lg[mr+1][mc-1] = "C ";
                    lg[mr+1][mc] = "C ";
                    lg[mr+1][mc+1] = "C ";
                    mr = mr - 1;
                }
            }

        }
        return lg;
    }

    // mows down
    public static String [][] mowLawnDown(String [][] lg, int l, int w, int mr, int mc)
    {
        boolean check = true;
        while(check)
        {
            // mower stops when it reaches edge of lawn
            if(mr == 0 || mr == l-1)
            {
                check = false;
            }
            else
            {
                // mower stops when it hits a tree
                if(lg[mr][mc].equals("T ") || lg[mr-1][mc-1].equals("T ") || lg[mr-1][mc].equals("T ") || lg[mr-1][mc+1].equals("T ") || lg[mr][mc-1].equals("T ") || lg[mr][mc+1].equals("T ") || lg[mr+1][mc-1].equals("T ") || lg[mr+1][mc].equals("T ") || lg[mr+1][mc+1].equals("T "))
                {
                    check = false;
                }
                else
                {
                    lg[mr][mc] = "C ";
                    lg[mr-1][mc-1] = "C ";
                    lg[mr-1][mc] = "C ";
                    lg[mr-1][mc+1] = "C ";
                    lg[mr][mc-1] = "C ";
                    lg[mr][mc+1] = "C ";
                    lg[mr+1][mc-1] = "C ";
                    lg[mr+1][mc] = "C ";
                    lg[mr+1][mc+1] = "C ";
                    mr = mr + 1;
                }
            }

        }
        return lg;
    }

    // mows left
    public static String [][] mowLawnLeft(String [][] lg, int l, int w, int mr, int mc)
    {
        boolean check = true;
        while(check)
        {
            // mower stops when it reaches edge of lawn
            if(mc == 0 || mc == w-1)
            {
                check = false;
            }
            else
            {
                // mower stops when it hits a tree
                if(lg[mr][mc].equals("T ") || lg[mr-1][mc-1].equals("T ") || lg[mr-1][mc].equals("T ") || lg[mr-1][mc+1].equals("T ") || lg[mr][mc-1].equals("T ") || lg[mr][mc+1].equals("T ") || lg[mr+1][mc-1].equals("T ") || lg[mr+1][mc].equals("T ") || lg[mr+1][mc+1].equals("T "))
                {
                    check = false;
                }
                else
                {
                    lg[mr][mc] = "C ";
                    lg[mr-1][mc-1] = "C ";
                    lg[mr-1][mc] = "C ";
                    lg[mr-1][mc+1] = "C ";
                    lg[mr][mc-1] = "C ";
                    lg[mr][mc+1] = "C ";
                    lg[mr+1][mc-1] = "C ";
                    lg[mr+1][mc] = "C ";
                    lg[mr+1][mc+1] = "C ";
                    mc = mc - 1;
                }
            }

        }
        return lg;
    }

    // mows right
    public static String [][] mowLawnRight(String [][] lg, int l, int w, int mr, int mc)
    {
        boolean check = true;
        while(check)
        {
            // mower stops when it reaches edge of lawn
            if(mc == 0 || mc == w-1)
            {
                check = false;
            }
            else
            {
                // mower stops when it hits a tree
                if(lg[mr][mc].equals("T ") || lg[mr-1][mc-1].equals("T ") || lg[mr-1][mc].equals("T ") || lg[mr-1][mc+1].equals("T ") || lg[mr][mc-1].equals("T ") || lg[mr][mc+1].equals("T ") || lg[mr+1][mc-1].equals("T ") || lg[mr+1][mc].equals("T ") || lg[mr+1][mc+1].equals("T "))
                {
                    check = false;
                }
                else
                {
                    lg[mr][mc] = "C ";
                    lg[mr-1][mc-1] = "C ";
                    lg[mr-1][mc] = "C ";
                    lg[mr-1][mc+1] = "C ";
                    lg[mr][mc-1] = "C ";
                    lg[mr][mc+1] = "C ";
                    lg[mr+1][mc-1] = "C ";
                    lg[mr+1][mc] = "C ";
                    lg[mr+1][mc+1] = "C ";
                    mc = mc + 1;
                }
            }

        }
        return lg;
    }
}
