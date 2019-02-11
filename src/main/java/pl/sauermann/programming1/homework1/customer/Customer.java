package pl.sauermann.programming1.homework1.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor()
public class Customer {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private BigDecimal salary;

    private static Integer counter = 1;

    public Customer(String name, String surname, int age, String salary) {
        this(counter, name, surname, age, BigDecimal.valueOf(Integer.parseInt(salary)));
        counter++;

    }

    public Customer(String name, String surname, int age, int salary) {
        this(counter, name, surname, age, BigDecimal.valueOf(salary));
        counter++;
    }
}
