package com.mrinmoy.reactive;

import com.mrinmoy.reactive.utility.MathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class ReactiveApplicationTests {
    MathUtils mathUtils = new MathUtils();

    /*@Test
    void contextLoads() {
    }*/

    @Test
    void itShoulAddNumbers() {

        //given
        int numberOne = 10;
        int numberTwo = 20;

        //When
        int result = mathUtils.add(numberOne, numberTwo);

        //then
        int expected =31;
        assertThat(result).isEqualTo(expected);

    }

}
