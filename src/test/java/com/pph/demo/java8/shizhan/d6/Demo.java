package com.pph.demo.java8.shizhan.d6;

import lombok.Data;
import org.junit.Test;

/**
 * @author pph
 * @date 2019/12/10 08:09
 * @description
 */
public class Demo {

    @Data
    class Person {
        private Car car;
    }

    @Data
    class Car {
        private Insurance insurance;
    }

    @Data
    class Insurance {
        private String name;
    }

    public String getCarInsuranceName(Person person) {
//        return person.getCar().getInsurance().getName();

//        if (Objects.nonNull(person)) {
//            Car car = person.getCar();
//            if (Objects.nonNull(car)) {
//                Insurance insurance = car.getInsurance();
//                if (Objects.nonNull(insurance)) {
//                    return insurance.getName();
//                }
//            }
//        }
//        return "Unknown";

        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }

    @Test
    public void d1() {
        String name = getCarInsuranceName(new Person());
        System.out.println(name);
    }
}
