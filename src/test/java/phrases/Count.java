/**
 * @(#)Count.java
 *
 * @author Challenger
 * @version 1.0 Jan 28, 2022
 *
 * Copyright (C) 2000,2022 , TeamSun, Inc.
 */
package phrases;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Count {
  private int all;

  public int getAll() {
      return all;
  }
  public void setAll(int all) {
      this.all = all;
  }
  public static void main(String[] args) {
      String[] n ={"city","danger","decline","hand","private","spring","canal","curious","receive","lesson","people","wood"};
      Count m=new Count();
      List lst = Arrays.asList(n);
      m.take("",6,lst);
      System.out.println(m.getAll());
  }

  public  void take(String s, int total, List lst) {
      for (int i = 0; i < lst.size(); i++) {
          //System.out.println("i="+i);
          List templst=new LinkedList(lst);
          String n =  (String) templst.remove(i);// 取出来的数字
          String str = s + "," + n;
          if (total == 1) {
              all++;
              System.out.println(all + "");
          } else {
              int temp=total-1;//在同一层中total总量不能减,不能再原有变量的基础上
              take(str, temp, templst);// 这里的temp以及templst都是全新的变量和集合
          }
      }
      
  }

}



/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * Jan 28, 2022 Challenger 创建版本
 */