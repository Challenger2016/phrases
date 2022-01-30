/**
 * @(#)Man2.java
 *
 * @author Challenger
 * @version 1.0 Jan 27, 2022
 *
 * Copyright (C) 2000,2022 , TeamSun, Inc.
 */
package com.study.phrases;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import com.google.common.collect.ImmutableList;

public class Encryption {

  private static Logger log = LogManager.getLogger(Encryption.class);
  
  private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
      ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
              ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

  public static boolean createWallet(List<String> dataList)  throws MnemonicException.MnemonicLengthException {
    
    byte[] seed = MnemonicCode.toSeed(dataList, "");
    DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
    DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
    DeterministicKey deterministicKey = deterministicHierarchy
            .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
    byte[] bytes = deterministicKey.getPrivKeyBytes();
    ECKeyPair keyPair = ECKeyPair.create(bytes);

    String address = Keys.getAddress(keyPair.getPublicKey());
    log.info("0x" + address);
    if (address.toLowerCase().indexOf(Start.address) > -1) {
      String result = "";
      for (String i : dataList) {
        result = result + " " + i;
      }
      log.info("验证完成:" + result);
      return true;
    }
    return false;
  }
}



/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * Jan 27, 2022 Challenger 创建版本
 */