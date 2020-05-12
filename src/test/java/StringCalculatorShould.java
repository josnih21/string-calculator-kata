import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorShould {
/*/
    TO-DO LIST
    - Empty string --> "";
    - "1" --> 1;
    - "3,2" --> 5;
    - check separator with more than one character of lenght
 */

    @Test
    public void return_zero_when_string_is_empty(){
        assertThat(StringCalculator.add("")).isEqualTo("0");
    }

    @Test
    public void return_same_value_when_one_value(){
        assertThat(StringCalculator.add("1")).isEqualTo("1.0");
        assertThat(StringCalculator.add("1.1")).isEqualTo("1.1");
    }

    @Test
    public void return_added_input_values(){
        assertThat(StringCalculator.add("1,2,3")).isEqualTo("6.0");
        assertThat(StringCalculator.add("3,3,3,3,3")).isEqualTo("15.0");
    }

    @Test
    public void return_add_with_different_delimiters(){
        assertThat(StringCalculator.add("1,2\n3")).isEqualTo("6.0");
        assertThat(StringCalculator.add("1\n2\n3")).isEqualTo("6.0");
    }

    @Test
    public void return_error_message_when_end_with_delimiter(){
        assertThat(StringCalculator.add("1,2,")).isEqualTo("Number expected but EOF found");
    }
    @Test
    public void return_add_with_custom_delimiters(){
        assertThat(StringCalculator.add("//.\n1.2.3")).isEqualTo("6.0");
        assertThat(StringCalculator.add("//;\n1;2;3")).isEqualTo("6.0");
        assertThat(StringCalculator.add("//;\n1;2\n3")).isEqualTo("6.0");
        assertThat(StringCalculator.add("//k\n1k2k3k")).isEqualTo("6.0");
        assertThat(StringCalculator.add("//.\n4.2.3")).isEqualTo("9.0");
    }
    @Test
    public void return_error_message_when_negative_numbers_provided(){
        assertThat(StringCalculator.add("-1,2,3")).isEqualTo("Negative numbers not allowed : -1.0");
        assertThat(StringCalculator.add("-2,3,4,-5")).isEqualTo("Negative numbers not allowed : -2.0,-5.0");
    }

    @Test void return_result_of_multiplicate_provided_values(){
        assertThat(StringCalculator.add("1,2")).isEqualTo("2.0");
    }

}
