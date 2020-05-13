import exceptions.DifferentDelimiters;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorShould {
/*/
    TO-DO LIST
    - Empty string --> "";
    - "1" --> 1;
    - "3,2" --> 5;
    - check separator with more than one character of lenght
 */
    StringCalculator stringCalculator = null;

    @BeforeEach
    public void setup(){
        stringCalculator = new StringCalculator();
    }

    @Test
    public void return_zero_when_string_is_empty(){
        assertThat(stringCalculator.add("")).isEqualTo("0");
    }

    @Test
    public void return_same_value_when_one_value(){
        assertThat(stringCalculator.add("1")).isEqualTo("1.0");
        assertThat(stringCalculator.add("1.1")).isEqualTo("1.1");
    }

    @Test
    public void return_added_input_values(){
        assertThat(stringCalculator.add("1,2,3")).isEqualTo("6.0");
        assertThat(stringCalculator.add("3,3,3,3,3")).isEqualTo("15.0");
    }

    @Test
    public void return_add_with_different_delimiters(){
        assertThat(stringCalculator.add("1,2\n3")).isEqualTo("6.0");
        assertThat(stringCalculator.add("1\n2\n3")).isEqualTo("6.0");
    }

    @Test
    public void return_error_message_when_end_with_delimiter(){
        assertThat(stringCalculator.add("1,2,")).isEqualTo("Number expected but EOF found");
    }

    @Test
    public void return_add_with_custom_delimiters(){
        assertThat(stringCalculator.add("//.\n1.2.3")).isEqualTo("6.0");
        assertThat(stringCalculator.add("//;\n1;2;3")).isEqualTo("6.0");
        assertThat(stringCalculator.add("//;\n1;2\n3")).isEqualTo("6.0");
        assertThat(stringCalculator.add("//k\n1k2k3k")).isEqualTo("6.0");
        assertThat(stringCalculator.add("//.\n4.2.3")).isEqualTo("9.0");
    }

    @Test
    public void return_error_message_when_negative_numbers_provided(){
        assertThat(stringCalculator.add("-1,2,3")).isEqualTo("Negative numbers not allowed : -1.0");
        assertThat(stringCalculator.add("-2,3,4,-5")).isEqualTo("Negative numbers not allowed : -2.0,-5.0");
    }

    @Test
    public void return_result_of_multiplicate_provided_values(){
        assertThat(stringCalculator.add("1,2")).isEqualTo("2.0");
    }
}
