import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorShould {

    @Test
    public void it_works(){
        assertThat("**").isEqualTo("**");
    }

}
