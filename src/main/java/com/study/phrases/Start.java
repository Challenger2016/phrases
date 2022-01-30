package com.study.phrases;
 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException;

public class Start {
  
    public static String address;
    
    private static Logger log = LogManager.getLogger(Start.class);
    
	private int all;

	public int getAll() {
		return all;
	}
	public void setAll(int all) {
		this.all = all;
	}
	public static void main(String[] args) throws MnemonicLengthException {
	    String known = System.getProperty("known");
	    String sourceData = System.getProperty("sourceData");
	    address = System.getProperty("address");
	    
	    log.info("known_param:" + known);
	    log.info("sourceData_param:" + sourceData);
	    log.info("address_param:" + address);
	    
	    if (address == null || address.trim().isEmpty()) {
	      log.info("address_empty");
	      return;
	    }
	    
	    String[] knowns = known.split(",");
        List<String> knownList = new ArrayList<>();
        for (int j =0; j < knowns.length; j++) {
          if (knowns[j] != null && !"".equals(knowns[j].trim())) {
            knownList.add(knowns[j]);
            log.info("knowns:" + j + ":" + knowns[j]);
          }
        }
        
        String[] sourceDatas = sourceData.split(",");
        List<String> sourceDataList = new ArrayList<>();
        for (int j = 0; j < sourceDatas.length; j++) {
          if (sourceDatas[j] != null && !"".equals(sourceDatas[j].trim())) {
            sourceDataList.add(sourceDatas[j]);
            log.info("knowns:" + j + ":" + sourceDatas[j]);
          }
        }
        
	    int unKnownCount = 12 - knownList.size();
		
		Start m=new Start();
        m.take("", unKnownCount, sourceDataList, knownList);
	}
 
	public  void take(String s, int total, List<String> lst, List<String> knownList) throws MnemonicLengthException {
		for (int i = 0; i < lst.size(); i++) {
			//System.out.println("i="+i);
			List<String> templst=new LinkedList<>(lst);
			String n =  (String) templst.remove(i);// 取出来的数字
			String str = "".equals(s)? n : s + "," + n;
			
			if (total == 1) {
				all++;
				log.info(all+"");
				String[] sf = str.split(",");
				
				List<String> dataList = new ArrayList<>();
				for (String known : knownList) {
				  dataList.add(known);
                }

				assembly(dataList, sf);
				
				if (Encryption.createWallet(dataList)) {
				  for (String add : dataList) {
				    log.info("单词:" + add);
				  }
				  System.exit(0);
				}
			} else {
				int temp=total-1;//在同一层中total总量不能减,不能再原有变量的基础上
				take(str, temp, templst, knownList);// 这里的temp以及templst都是全新的变量和集合
			}
		}
		
	}
	
	private void assembly(List<String> dataList, String[] sf) {
	  for (int j =0; j < sf.length; j++) {
        int index = 0;
        switch (j) {
          case 0:
            index = 6;
            break;
          case 1:
            index = 7;
            break;
          case 2:
            index = 8;
            break;
          case 3:
            index = 9;
            break;
          case 4:
            index = 10;
            break;
          case 5:
            index = 11;
            break;
          case 6:
            index = 12;
            break;
          default:
            break;
        }
        dataList.add(index, sf[j]);
      }
	}
}
