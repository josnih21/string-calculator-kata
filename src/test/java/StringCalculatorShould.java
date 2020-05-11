import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorShould {
/*/
    TO-DO LIST
    - Empty string --> "";
    - "1" --> 1;
    - "3,2" --> 5;
 */

    @Test
    public void return_zero_when_string_is_empty(){
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    @Test
    public void return_same_value_when_one_value(){
        assertThat(StringCalculator.add("1")).isEqualTo(1);
        assertThat(StringCalculator.add("1.1")).isEqualTo(1.1);
    }

    @Test
    public void return_added_input_values(){
        assertThat(StringCalculator.add("1,2")).isEqualTo(3);
        assertThat(StringCalculator.add("1,2,3")).isEqualTo(6);
        assertThat(StringCalculator.add("3,3,3,3,3")).isEqualTo(15);
    }

    @Test
    public void return_add_with_different_delimiters(){
        assertThat(StringCalculator.add("1,2\n3")).isEqualTo(6);
        assertThat(StringCalculator.add("1\n2\n3")).isEqualTo(6);
        //assertThat(StringCalculator.add("1,2,\n3")).isEqualTo("error");
    }

    @Test
    public void return_add_with_custom_delimiters(){
        assertThat(StringCalculator.add("//.\n1.2.3")).isEqualTo(6);
    }

}
