/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package detectcolonies;

import java.io.*;

public class Slide {

    private char COLONY = '*', NON_COLONY = '-';
    private char[][] slideData;

    /**
     * constructor pre: Slide file contains valid slide data in the format:
     * first line: lenght of slide second line: width of slide remaining lines:
     * slide data post: Slide data has been loaded from slide file.
     */
    public Slide(String s) {

        try {
            File slideFile = new File(s);
            FileReader in = new FileReader(slideFile);
            BufferedReader readSlide = new BufferedReader(in);
            int length = Integer.parseInt(readSlide.readLine());
            int width = Integer.parseInt(readSlide.readLine());
            slideData = new char[length][width];
            for (int row = 0; row < length; row++) {
                for (int col = 0; col < width; col++) {
                    slideData[row][col] = (char) readSlide.read();
                }
                readSlide.readLine();	//read past end-of-line characters
            }
            readSlide.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem reading file.");
            System.err.println("IOException: " + e.getMessage());
        }
    }

    /**
     * Determines a colony size pre: none post: All colony cells adjoining and
     * including cell (Row, Col) have been changed to NON_COLONY, and count of
     * these cells is returned.
     */
    private int collectCells(int row, int col) {

        if ((row < 0) || (row >= slideData.length) || (col < 0) || (col >= slideData[0].length)
                || (slideData[row][col] != COLONY)) {
            return (0);
        } else {
            slideData[row][col] = NON_COLONY;
            return (1
                    + collectCells(row + 1, col)
                    + collectCells(row - 1, col)
                    + collectCells(row, col + 1)
                    + collectCells(row, col - 1)
                    + collectCells(row + 1, col + 1)
                    + collectCells(row - 1, col - 1)
                    + collectCells(row + 1, col - 1)
                    + collectCells(row - 1, col + 1));
        }
    }

    public void sort(int[][] num) {
        int i;
        boolean flag = true;   // set flag to true to begin first pass
        int temp[] = new int[4];   //holding variable

        while (flag) {
            flag = false;    //set flag to false awaiting a possible swap
            for (i = 0; i < num[0].length - 1; i++) {
                if (num[0][ i] < num[0][i + 1]) // change to > for ascending sort
                {
                    for (int n = 0; n < 3; n++) {
                        temp[n] = num[n][i];                //swap elements
                        num[n][i] = num[n][i + 1];
                        num[n][i + 1] = temp[n];
                    }
                    flag = true;              //shows a swap occurred  
                }
            }
        }
    }

    /**
     * Analyzes a slide for colonies and displays colony data pre: none post:
     * Colony data has been displayed.
     */
    public void displayColonies() {
        int[][] array = new int[3][84];
        int count = -1;
        for (int row = 0; row < slideData.length; row++) {
            for (int col = 0; col < slideData[0].length; col++) {
                for (int i = 0; i < 9; i++) {
                    if (slideData[row][col] == COLONY) {
                        count++;
                        array[0][count] = collectCells(row, col);
                        array[1][count] = row;
                        array[2][count] = col;
                    }
                }
            }
        }
        sort(array);
        for (int i = 0; i < count + 1; i++) {
            System.out.println("Colony at (" + array[2][i] + "," + array[1][i] + ") with size " + array[0][i]);
        }
    }

    /**
     * Displays a slide. pre: none post: Slide data has been displayed.
     */
    public void displaySlide() {

        for (int row = 0; row < slideData.length; row++) {
            for (int col = 0; col < slideData[0].length; col++) {
                System.out.print(slideData[row][col]);
            }
            System.out.println();
        }
    }
}
