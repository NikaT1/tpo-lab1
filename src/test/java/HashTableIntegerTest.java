import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import tpo.task_2.HashTableInteger;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTableIntegerTest {

    private HashTableInteger table;

    @BeforeEach
    void init() {
        table = new HashTableInteger(5);
        table.add_element(4);
        table.add_element(9);
        table.add_element(14);
        table.add_element(10);
    }

    @ParameterizedTest(name = "{index} - test insert command")
    @CsvSource({
            "4, '[10, null, null, null, 4, 14, 9, 4]'",
            "1, '[10, 1, null, null, 14, 9, 4]'"
    })
    public void testInsertNumbers(int number, String intArray) {
        Integer[] expectedArray = Arrays.stream(intArray.replace("[", "")
                        .replace("]", "")
                        .split(","))
                .map(c -> c.trim().equals("null") ? null : Integer.parseInt(c.trim()))
                .toArray(Integer[]::new);
        table.add_element(number);
        assertArrayEquals(expectedArray, table.getAsArray());
    }

    @ParameterizedTest(name = "{index} - test delete command")
    @CsvFileSource(resources = "/delete_values.csv", numLinesToSkip = 1, delimiter = ';')
    public void testDeleteNumbers(int number, boolean result, String intArray) {
        Integer[] expectedArray = Arrays.stream(intArray.replace("[", "")
                        .replace("]", "")
                        .split(","))
                .map(c -> c.trim().equals("null") ? null : Integer.parseInt(c.trim()))
                .toArray(Integer[]::new);
        boolean cur_result = table.delete_element(number);
        assertEquals(result, cur_result);
        assertArrayEquals(expectedArray, table.getAsArray());
    }

    @ParameterizedTest(name = "{index} - test find command")
    @CsvFileSource(resources = "/find_values.csv", numLinesToSkip = 1, delimiter = ';')
    public void testFindNumbers(int number, boolean result, String intArray) {
        Integer[] expectedArray = Arrays.stream(intArray.replace("[", "")
                        .replace("]", "")
                        .split(","))
                .map(c -> c.trim().equals("null") ? null : Integer.parseInt(c.trim()))
                .toArray(Integer[]::new);
        boolean cur_result = table.find_element(number);
        assertEquals(result, cur_result);
        assertArrayEquals(expectedArray, table.getAsArray());
    }
}
