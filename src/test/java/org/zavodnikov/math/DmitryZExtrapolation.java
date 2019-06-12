package org.zavodnikov.math;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author dprizentsov
 */
public class DmitryZExtrapolation {
  public static void main(String[] args) {
    DmitryZExtrapolation t = new DmitryZExtrapolation(10);
    t.add(1).add(1).add(1).add(2).add(2).add(5).add(2).add(3).add(1);
    t.filter();
    System.out.println("x=15 y=" + t.dmitryzNext(15));
    System.out.println("Exeed 5 at x=" + t.dmitryzExceed(5));
  }
  
  int frame;
  LinkedList<Integer> history = new LinkedList<>();
  public DmitryZExtrapolation(int frame) {
    this.frame = frame;
  }
  public DmitryZExtrapolation add(int i) {
    if (history.size() < frame) {
      history.addLast(i);
    } else {
      history.addLast(i);
      history.pollFirst();
    }
    return this;
  }
  
  public static LinkedList<Integer> medianFilter(LinkedList<Integer> history) {
    int takeN = 3;
    int index = 1;
    int[] group = new int[takeN];
    LinkedList<Integer> result = new LinkedList<>();
    for (int i = 0; i <= history.size() - takeN; i++) {
      int offset = i;
      copyTo(history, offset, group);
      Arrays.sort(group);
      result.add(group[index]);
    }
    return result;
  }
  
  //private double filterScale;
  public void filter() {
    LinkedList<Integer> newHistory = medianFilter(history);
    //filterScale = history.size() / (double)newHistory.size();
    history = newHistory;
  }
  
  static void copyTo(LinkedList<Integer> history, int offset, int[] group) {
    for (int i = 0; i < group.length; i++) {
      group[i] = history.get(offset + i);
    }
  }
  
  public void dmitryzLine(double[] p1, double[] p2) {
    int groupLen = history.size() / 2;
    double y1 = 0;
    for (int i = 0; i < groupLen; i++) {
      y1 += history.get(i);
    }
    y1 = y1 / groupLen;
    
    double y2 = 0;
    for (int i = history.size() - 1; i >= history.size() - groupLen; i--) {
      y2 += history.get(i);
    }
    y2 = y2 / groupLen;
    
    float part = history.size() / (float)4;
    float x1 = part;
    float x2 = part*3;
    p1[0] = x1;
    p1[1] = y1;
    p2[0] = x2;
    p2[1] = y2;
  }
  
  public double dmitryzNext(int x) {
    double[] p1 = new double[2];
    double[] p2 = new double[2];
    dmitryzLine(p1, p2);
    double x1 = p1[0];
    double y1 = p1[1];
    double x2 = p2[0];
    double y2 = p2[1];
    /*
    Ax+By+C=0
    A=y1-y2
    B=x2-x1
    C=x1y2-x2y1

    (y1-y2)x+(x2-x1)y+(x1y2-x2y1)=0
    By=-C-Ax
    y=(-C-Ax)/B
    y=(-(x1y2-x2y1)-(y1-y2)x)/(x2-x1)
    y=((x2y1-x1y2)-(y1-y2)x)/(x2-x1)
    */
    return ((x2*y1-x1*y2)-(y1-y2)*x)/(x2-x1);
  }
  
  public double dmitryzExceed(int y) {
    double[] p1 = new double[2];
    double[] p2 = new double[2];
    dmitryzLine(p1, p2);
    double x1 = p1[0];
    double y1 = p1[1];
    double x2 = p2[0];
    double y2 = p2[1];
    /*
    (y1-y2)x+(x2-x1)y+(x1y2-x2y1)=0
    (y1-y2)x=-(x2-x1)y-(x1y2-x2y1)
    x=(-(x2-x1)y-(x1y2-x2y1))/(y1-y2)
    x=((x2-x1)y+(x1y2-x2y1))/(y2-y1)
    */
    return ((x2-x1)*y+(x1*y2-x2*y1))/(y2-y1);
  }
  
  public String toString() {
    return "<DmitryZExtrapolation frame " + frame + " " + history + ">"; 
  }
}
