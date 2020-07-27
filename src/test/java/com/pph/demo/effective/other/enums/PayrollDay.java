package com.pph.demo.effective.other.enums;

/**
 * @Author: PPH
 * @date: 2019-07-04 09:09
 * @Description:
 */
public enum PayrollDay {

//    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
//
//    private static final int MINS_PER_SHIFT = 8 * 60;
//
//    int pay(int minutesWorked, int payRate) {
//        int basePay = minutesWorked * payRate;
//        int overtimePay;
//        switch (this) {
//            case SATURDAY:
//            case SUNDAY: // Weekend
//                overtimePay = basePay / 2;
//                break;
//            default: // Weekday
//                overtimePay = minutesWorked <= MINS_PER_SHIFT ?
//                        0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
//        }
//        return basePay + overtimePay;
//    }


    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    PayrollDay() {
        this(PayType.WEEKDAY);
    }  // Default

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }


    private enum PayType {
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 :
                        (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);

        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}
