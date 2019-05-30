package com.pph.demo.effective.builder.user;

/**
 * @Author: PPH
 * @Date: 2019-05-30 15:51
 * @Description:
 */
public class Test {

    public static void main(String[] args) {

        User user = new User.Builder(1, "哈哈")
                .sex(true)
                .age(20)
                .height(180.8)
                .weight(62.5)
                .addr("杭州")
                .builder();
        System.out.println(user.toString());
    }
}
