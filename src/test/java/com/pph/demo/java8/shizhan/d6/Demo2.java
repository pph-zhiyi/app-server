package com.pph.demo.java8.shizhan.d6;

import com.pph.demo.java8.shizhan.d6.model.Car;
import com.pph.demo.java8.shizhan.d6.model.Insurance;
import com.pph.demo.java8.shizhan.d6.model.Person;
import org.junit.Test;

import java.util.Optional;

/**
 * @author: pph
 * @date: 2019/12/10 09:09
 * @description:
 */
public class Demo2 {

    public String getCarInsuranceName(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    @Test
    public void d1() {
        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();
        insurance.setName("aaa");
        car.setInsurance(Optional.of(insurance));
        person.setCar(Optional.of(car));
        String name = getCarInsuranceName(person);

        System.out.println(name);
    }
}
