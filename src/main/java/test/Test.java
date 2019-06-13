package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class User{
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

public class Test {

    public static void main(String[] args) {

        List<User> list = new ArrayList<>();

        list.add(new User(1,"z3"));
        list.add(new User(2,"l4"));
        list.add(new User(3,"w5"));
        list.add(new User(4,"w2"));

        final Map<String, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getName));

        System.out.println(collect);


    }
}
