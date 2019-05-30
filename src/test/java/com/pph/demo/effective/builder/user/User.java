package com.pph.demo.effective.builder.user;

/**
 * @Author: PPH
 * @Date: 2019-05-30 15:52
 * @Description:
 */
public class User {

    private final Integer id;

    private final String name;

    private final Boolean sex;

    private final Integer age;

    private final Double height;

    private final Double weight;

    private final String addr;

    public static class Builder {

        private final Integer id;

        private final String name;

        private Boolean sex;

        private Integer age;

        private Double height;

        private Double weight;

        private String addr;

        public Builder(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder sex(Boolean sex) {
            this.sex = sex;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder height(Double height) {
            this.height = height;
            return this;
        }

        public Builder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Builder addr(String addr) {
            this.addr = addr;
            return this;
        }

        public User builder() {
            return new User(this);
        }
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.sex = builder.sex;
        this.age = builder.age;
        this.height = builder.height;
        this.weight = builder.weight;
        this.addr = builder.addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", addr='" + addr + '\'' +
                '}';
    }
}
