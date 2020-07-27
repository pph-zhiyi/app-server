package com.pph.demo.effective.other.enums;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * @Author: PPH
 * @datetime 2019-07-03 09:19
 * @Description:
 */
public enum Operation {

//    PLUS,
//    MINUS,
//    TIMES,
//    DIVIDE;
//
//    /**
//     * 运算
//     *
//     * @param x
//     * @param y
//     * @return
//     */
//    public double apply(double x, double y) {
//        switch (this) {
//            case PLUS:
//                return x + y;
//            case MINUS:
//                return x - y;
//            case TIMES:
//                return x * y;
//            case DIVIDE:
//                return x / y;
//            default:
//                throw new IllegalArgumentException();
//        }
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Operation.PLUS.apply(1.1, 2.2));
//    }


//    PLUS {
//        public double apply(double x, double y) {
//            return x + y;
//        }
//    },
//    MINUS {
//        public double apply(double x, double y) {
//            return x - y;
//        }
//    },
//    TIMES {
//        public double apply(double x, double y) {
//            return x * y;
//        }
//    },
//    DIVIDE {
//        public double apply(double x, double y) {
//            return x / y;
//        }
//    };
//
//    public abstract double apply(double x, double y);
//
//    public static void main(String[] args) {
//        System.out.println(Operation.PLUS.apply(1.1, 2.2));
//    }


    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    public abstract double apply(double x, double y);

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    // Switch on an enum to simulate a missing method
    public static Operation inverse(Operation op) {
        switch (op) {
            case PLUS:
                return Operation.MINUS;
            case MINUS:
                return Operation.PLUS;
            case TIMES:
                return Operation.DIVIDE;
            case DIVIDE:
                return Operation.TIMES;
            default:
                throw new AssertionError("Unknown op: " + op);
        }
    }

    private static final Map<String, Operation> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e -> e));

    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    public static void main(String[] args) {
        double x = 5, y = 2;
        for (Operation op : Operation.values())
            System.out.printf("%f %s %f = %f %n"
                    , x, op.symbol, y, op.apply(x, y));

        Optional<Operation> operation = Operation.fromString("a");
    }
}
