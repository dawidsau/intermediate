package pl.sauermann.programming1.homework1.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerServicesTest {

    private final String ISSUE_MESSAGE_CLASSIC = "Issue with basic modifier exist";
    private final String ISSUE_MESSAGE_STREAM = "Issue with stream modifier exist";
    private CustomerServices services;

    @BeforeEach
    void setUp() {
        services = new CustomerServices();
    }

    @Test
    void shouldParseArrayToList() {
        List<Customer> result = services.parseCustomerArrayToList();

        assertNotNull(result);
        assertEquals(8, result.size());
        assertEquals("Adam", result.get(7).getName());
    }

    @Test
    void shouldReturnListWithUsersNameAndSurname() {
        List<String> result = services.getNameAndSurnameCustomerList();
        List<String> resultWithStream = services.getNameAndSurnameCustomerListWithStream();

        assertNotNull(result, ISSUE_MESSAGE_CLASSIC);
        assertEquals(8, result.size(), ISSUE_MESSAGE_CLASSIC);
        assertEquals("Adam Madej", result.get(7).trim(), ISSUE_MESSAGE_CLASSIC);

        assertNotNull(resultWithStream, ISSUE_MESSAGE_STREAM);
        assertEquals(8, resultWithStream.size(), ISSUE_MESSAGE_STREAM);
        assertEquals("Adam Madej", resultWithStream.get(7).trim(), ISSUE_MESSAGE_STREAM);
    }

    @Test
    void shouldReturnIdCustomerMap() {
        Map<Integer, Customer> result = services.provideIdCustomerMap();
        Map<Integer, Customer> resultWithStream = services.provideIdCustomerMapWithStream();

        assertNotNull(result, ISSUE_MESSAGE_CLASSIC);
        assertEquals(8, result.size(), ISSUE_MESSAGE_CLASSIC);

        assertNotNull(resultWithStream, ISSUE_MESSAGE_STREAM);
        assertEquals(8, resultWithStream.size(), ISSUE_MESSAGE_STREAM);
    }

    @Test
    void shouldReturnCustomersGroupedBySalary() {
        Map<BigDecimal, List<Customer>> result = services.groupCustomersBySalary();
        Map<BigDecimal, List<Customer>> resultWithStream = services.groupCustomersBySalaryWithStream();

        long actualResult = expectedValuePreparation(result);
        long actualResultWithStream = expectedValuePreparation(resultWithStream);

        assertNotNull(result, ISSUE_MESSAGE_CLASSIC);
        assertEquals(2L, actualResult, ISSUE_MESSAGE_STREAM);

        assertNotNull(resultWithStream, ISSUE_MESSAGE_STREAM);
        assertEquals(2L, actualResultWithStream, ISSUE_MESSAGE_STREAM);
    }

    private long expectedValuePreparation(Map<BigDecimal, List<Customer>> resultWithStream) {
        return resultWithStream.get(BigDecimal.valueOf(3333)).stream()
                .map(Customer::getName)
                .filter(name -> name.trim().equals("Adam"))
                .count();
    }

    @Test
    void shouldReturnStatisticsCustomersSalary() {
        Map<BigDecimal, Integer> result = services.getStatisticsSalaryToPersonsCount();
        Map<BigDecimal, Long> resultWithStream = services.getStatisticsSalaryToPersonsCountWithStream();

        assertNotNull(result, ISSUE_MESSAGE_CLASSIC);
        assertEquals(2, result.get(BigDecimal.valueOf(3333)), ISSUE_MESSAGE_STREAM);

        assertNotNull(resultWithStream, ISSUE_MESSAGE_STREAM);
        assertEquals(2L, resultWithStream.get(BigDecimal.valueOf(3333)), ISSUE_MESSAGE_STREAM);
    }

    @Test
    void shouldReturnStatisticsCustomersSalaryByNames() {
        Map<String, Map<BigDecimal, Long>> result = services.getNameWithSalaryCounterMap();
        Map<String, Map<BigDecimal, Long>> resultWithStream = services.getNameWithSalaryCounterMapWithStream();

        assertNotNull(result, ISSUE_MESSAGE_CLASSIC);
        assertEquals(2, result.get("Adam").get(BigDecimal.valueOf(3333)), ISSUE_MESSAGE_STREAM);

        assertNotNull(resultWithStream, ISSUE_MESSAGE_STREAM);
        assertEquals(2L, resultWithStream.get("Adam").get(BigDecimal.valueOf(3333)), ISSUE_MESSAGE_STREAM);
    }

    @Test
    void shouldReturnMapWithSumOfSalaryCustomersWithSameName() {
        Map<String, BigDecimal> result = services.getNameWithSumOfSalaryMap();
        Map<String, BigDecimal> resultWithStream = services.getNameWithSumOfSalaryMapWithStream();

        assertEquals(BigDecimal.valueOf(10766), result.get("Adam"));
        assertEquals(BigDecimal.valueOf(10766), resultWithStream.get("Adam"));
    }
}