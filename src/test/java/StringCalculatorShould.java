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
    public void return_zero_is_empty_provided(){
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    @Test
    public void return_self_value_is_one_value_provided(){
        assertThat(StringCalculator.add("1")).isEqualTo(1);
    }

    @Test
    public void return_add_when_one_or_more_values_provided(){
        assertThat(StringCalculator.add("2,2,2")).isEqualTo(6);
        assertThat(StringCalculator.add("1,2,3,4")).isEqualTo(10);
        assertThat(StringCalculator.add("1,1,1,1,1,1,1,1")).isEqualTo(8);
    }
}
