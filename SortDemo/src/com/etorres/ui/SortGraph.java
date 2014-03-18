package com.etorres.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class SortGraph extends JPanel {

    private int totalNumbers = 100; //total numbers to sort (hard code for now, because graph has hard coded size */
    private Integer[] numbers;
    private int comparisons;
    private Image offscreenBuffer;

    private static final long serialVersionUID = -6024569290565710817L;
    private static final int DELAY_MS = 5;
    private static final int WIDTH = 210;
    private static final int HEIGHT = 200;    

    public enum SortingAlgorithms { BUBBLE_SORT, INSERTION_SORT, QUICK_SORT, SELECTION_SORT, SHELL_SORT };
    private String[] SortingNames = new String[] {"Bubble Sort", "Insertion Sort", "Quick Sort", "Selection Sort", "Shell Sort"};

    private SortingAlgorithms algorithm = SortingAlgorithms.INSERTION_SORT; //default
    private String algorithmName = SortingNames[0];

    public SortGraph() {
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        comparisons = 0;
        shuffle();
        init();
    }

    /*
     * Shuffle the numbers array.
     */
    public void shuffle() {
        ArrayList<Integer> alTemp = new ArrayList<Integer>();
        for (int i=1; i <= totalNumbers; i++) {
            alTemp.add(i);
        }
        Collections.shuffle(alTemp);
        numbers = new Integer[alTemp.size()];
        alTemp.toArray(numbers);
    }

    public void init() {
        setBackground(Color.white);
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0, 0,  (int)d.getWidth(), (int)d.getHeight());

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color fg3D = Color.black;
        g2.setPaint(fg3D);

        int currX = 5;
        for (int i=0; i < numbers.length; i++) {
            g2.draw(new Line2D.Double(currX, d.getHeight() - 10 , currX, d.getHeight() - 10 - numbers[i])); 
            currX += 2;
        }
    }

    /** overriding this method to reduce flicker (double-buffering) */
    public void update(Graphics g)
    {
        Graphics gr; 
        if (offscreenBuffer == null ||
                (!(offscreenBuffer.getWidth(this) == getWidth()
                && offscreenBuffer.getHeight(this) == getHeight())))
        {
            offscreenBuffer = this.createImage(getWidth(), getHeight());
        }
        gr = offscreenBuffer.getGraphics();
        paint(gr); 
        g.drawImage(offscreenBuffer, 0, 0, this);     
    }    

    /** for simplicity */
    public void mySleep(int ms) {
        if (ms < 0)
            return;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public void shellsort() {
        comparisons = 0;
        int increment = numbers.length / 2;
        while (increment > 0) {
            for (int i = increment; i < numbers.length; i++) {
                int j = i;
                int temp = numbers[i];
                while (j >= increment && numbers[j - increment] > temp) {
                    //sleep and repaint after each comparison
                    comparisons++; mySleep(DELAY_MS); repaint();
                    numbers[j] = numbers[j - increment];
                    j = j - increment;
                }
                numbers[j] = temp;
            }
            if (increment == 2) {
                increment = 1;
            } else {
                increment *= (5.0 / 11);
            }
        }
    }    

    public void bubblesort() {
        comparisons = 0;
        int j;
        boolean flag = true;
        int temp;
        while ( flag )
        {
            flag = false;    //set flag to false awaiting a possible swap
            for(j=0;  j < numbers.length -1;  j++ )
            {
                if ( numbers[j] > numbers[j+1] )
                {
                    comparisons++;
                    //sleep and repaint after each comparison
                    mySleep(DELAY_MS); repaint();
                    temp = numbers[j]; //swap elements
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                    flag = true;              //shows a swap occurred  
                    repaint();
                } 
            } 
        }    
    }

    public void insertionsort() {
        comparisons = 0;
        int i, j, newValue;
        for (i = 1; i < numbers.length; i++) {
            newValue = numbers[i];
            j = i;
            while (j > 0 && numbers[j - 1] > newValue) {
                comparisons++;
                //sleep and repaint after each comparison
                mySleep(DELAY_MS); repaint();
                numbers[j] = numbers[j - 1];
                j--;
            }
            numbers[j] = newValue;
        }
    }

    /** used by quicksort */
    public int partition(Integer arr[], int left, int right)
    {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            //sleep and repaint after each comparison
            comparisons++; mySleep(DELAY_MS); repaint();
            while (arr[i] < pivot) {
                //sleep and repaint after each comparison
                comparisons++; mySleep(DELAY_MS); repaint();
                i++;
            }
            while (arr[j] > pivot) {
                //sleep and repaint after each comparison
                comparisons++; mySleep(DELAY_MS); repaint();
                j--;
            }
            if (i <= j) {
                //sleep and repaint after each comparison
                comparisons++; mySleep(DELAY_MS); repaint();
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };

        return i;
    }

    public void quicksort() {
        if (numbers == null || numbers.length == 0){
            return;
        }
        comparisons = 0;
        quickSortImpl(numbers, 0, numbers.length-1);
    }    

    public void quickSortImpl(Integer arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            //sleep and repaint after each comparison
            comparisons++; mySleep(DELAY_MS); repaint();
            quickSortImpl(arr, left, index - 1);
        }
        if (index < right) {
            //sleep and repaint after each comparison
            comparisons++; mySleep(DELAY_MS); repaint();            
            quickSortImpl(arr, index, right);
        }
    }    

    public void selectionsort() {
        comparisons = 0;
        int first, temp;  
        for (int i = numbers.length - 1; i > 0; i--)  
        {
            first = 0;
            for(int j = 1; j <= i; j ++) {
                if( numbers[ j ] > numbers[ first ] ) {   
                    //sleep and repaint after each comparison
                    comparisons++; mySleep(DELAY_MS); repaint();
                    first = j;
                }
            }
            temp = numbers[ first ];
            numbers[ first ] = numbers[ i ];
            numbers[ i ] = temp; 
        }           
    }

    public void sort() {
        comparisons = 0;
        switch (algorithm) {
        case BUBBLE_SORT:
            algorithmName = SortingNames[algorithm.ordinal()];
            bubblesort();
            break;        
        case INSERTION_SORT:
            algorithmName = SortingNames[algorithm.ordinal()]; 
            insertionsort();
            break;
        case QUICK_SORT:
            algorithmName = SortingNames[algorithm.ordinal()];
            quicksort();
            break;   
        case SELECTION_SORT:
            algorithmName = SortingNames[algorithm.ordinal()];
            selectionsort();
            break;
        case SHELL_SORT:
            algorithmName = SortingNames[algorithm.ordinal()];
            shellsort();
            break;            
        default:
            System.err.println("error - unsupported sorting algorithm: " + algorithm.toString());
            break;
        }
    }

    public int getComparisons() {
        return comparisons;
    }

    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }

    public SortingAlgorithms getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(SortingAlgorithms algorithm) {
        this.algorithm = algorithm;
        this.algorithmName = SortingNames[algorithm.ordinal()];
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
    public String[] getSortingNames() {
        return SortingNames;
    }

    public void setSortingNames(String[] sortingNames) {
        SortingNames = sortingNames;
    }
    public int getTotalNumbers() {
        return totalNumbers;
    }

    public void setTotalNumbers(int totalNumbers) {
        this.totalNumbers = totalNumbers;
    }


}
