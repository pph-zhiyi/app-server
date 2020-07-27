package com.pph.demo.effective.sigleton;

/**
 * @Author: PPH
 * @date: 2019-06-06 16:57
 * @Description:
 */
public class Elvis {

    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public void leaveTheBuilding(){
    }
}
