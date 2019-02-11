package pl.sauermann.programming1.homework1.customer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerServices {

    private Customer[] people = new Customer[]{
            new Customer("Anna", "Nowak   ", 33, "1200"),
            new Customer("Beata", "Kowalska", 22, "1200"),
            new Customer("Marek", " Nowak", 25, "1250"),
            new Customer("Adam", "Twardowski", 33, "4100"),
            new Customer("Monika  ", "Kos", 25, "2500"),
            new Customer("Adam ", "Rudy", 45, "3333"),
            new Customer("Marek", "Rudy", 15, 2210),
            new Customer("Adam", "Madej", 15, 3333)
    };

    /**
     * Exercise 1
     */
    public List<Customer> parseCustomerArrayToList() {
        return Arrays.asList(people);
    }

    /**
     * Exercise 2
     */
    public List<String> getNameAndSurnameCustomerList() {
        List<String> customerList = new ArrayList<>();
        for (Customer customer : parseCustomerArrayToList()) {
            customerList.add(customer.getName().trim()
                    + " " + customer.getSurname().trim());
        }
        return customerList;
    }

    /**
     * Exercise 2 using stream
     */
    public List<String> getNameAndSurnameCustomerListWithStream() {
        return parseCustomerArrayToList().stream()
                .map(customer -> customer.getName().trim()
                        + " " + customer.getSurname().trim())
                .collect(Collectors.toList());
    }

    /**
     * Exercise 3
     */
    public Map<Integer, Customer> provideIdCustomerMap() {
        Map<Integer, Customer> result = new HashMap<>();
        for (Customer customer : parseCustomerArrayToList()) {
            result.put(customer.getId(), customer);
        }
        return result;
    }

    /**
     * Exercise 3 using stream
     */
    public Map<Integer, Customer> provideIdCustomerMapWithStream() {
        return parseCustomerArrayToList().stream()
                .collect(Collectors.toMap(
                        Customer::getId,
                        customer -> customer));
    }

    /**
     * Exercise 4
     */
    public Map<BigDecimal, List<Customer>> groupCustomersBySalary() {
        Map<BigDecimal, List<Customer>> result = new HashMap<>();
        for (Customer customer : parseCustomerArrayToList()) {
            if (result.containsKey(customer.getSalary())) {
                List<Customer> customersInnerList = new ArrayList<>(result.get(customer.getSalary()));
                customersInnerList.add(customer);
                result.put(customer.getSalary(), customersInnerList);
            } else {
                result.put(customer.getSalary(), Collections.singletonList(customer));
            }
        }
        return result;
    }

    /**
     * Exercise 4 using stream
     */
    public Map<BigDecimal, List<Customer>> groupCustomersBySalaryWithStream() {
        return parseCustomerArrayToList().stream()
                .collect(Collectors.groupingBy(
                        Customer::getSalary)
                );
    }

    /**
     * Exercise 5
     */
    public Map<BigDecimal, Integer> getStatisticsSalaryToPersonsCount() {
        Map<BigDecimal, Integer> result = new HashMap<>();
        for (Customer customer : parseCustomerArrayToList()) {
            if (result.containsKey(customer.getSalary())) {
                Integer counter = result.get(customer.getSalary()) + 1;
                result.put(customer.getSalary(), counter);
            } else {
                result.put(customer.getSalary(), 1);
            }
        }
        return result;
    }

    /**
     * Exercise 5 with stream
     */
    public Map<BigDecimal, Long> getStatisticsSalaryToPersonsCountWithStream() {
        return parseCustomerArrayToList().stream()
                .collect(Collectors.groupingBy(
                        Customer::getSalary,
                        Collectors.counting()
                ));
    }

    /**
     * Exercise 6
     */
    public Map<String, Map<BigDecimal, Long>> getNameWithSalaryCounterMap() {
        Map<String, Map<BigDecimal, Long>> result = new HashMap<>();
        for (Customer customer : parseCustomerArrayToList()) {
            String customerName = customer.getName().trim();
            BigDecimal customerSalary = customer.getSalary();
            if (result.containsKey(customerName)) {
                if (result.get(customerName).containsKey(customerSalary)) {
                    Long counter = result.get(customerName).get(customerSalary);
                    result.get(customerName).put(customerSalary, ++counter);
                } else {
                    result.get(customerName)
                            .put(customerSalary, 1L);
                }
            } else {
                HashMap<BigDecimal, Long> value = new HashMap<>();
                value.put(customerSalary, 1L);
                result.put(customerName, value);
            }
        }
        return result;
    }

    /**
     * Exercise 6 using stream
     */
    public Map<String, Map<BigDecimal, Long>> getNameWithSalaryCounterMapWithStream() {
        return parseCustomerArrayToList().stream()
                .collect(Collectors.groupingBy(
                        customer -> customer.getName().trim(),
                        Collectors.groupingBy(
                                Customer::getSalary,
                                Collectors.counting()
                        )));
    }

    /**
     * Exercise 7
     */
    public Map<String, BigDecimal> getNameWithSumOfSalaryMap() {
        Map<String, BigDecimal> result = new HashMap<>();
        for (Customer customer : parseCustomerArrayToList()) {
            String customerName = customer.getName().trim();
            if (result.containsKey(customerName)) {
                BigDecimal sumValue = result.get(customerName).add(customer.getSalary());
                result.put(customerName, sumValue);
            } else {
                result.put(customerName, customer.getSalary());
            }
        }
        return result;
    }

    /**
     * Exercise 7 with stream
     */
    public Map<String, BigDecimal> getNameWithSumOfSalaryMapWithStream() {
        return parseCustomerArrayToList().stream()
                .collect(Collectors.toMap(
                        customer -> customer.getName().trim(),
                        Customer::getSalary,
                        BigDecimal::add));
    }
}
