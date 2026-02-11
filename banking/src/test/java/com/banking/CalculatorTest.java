package com.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void shouldAddTwoNumbers(){

        int result = calculator.add(2,3);
        assertEquals(5,result);
        assertEquals(10,calculator.add(6,4));
        assertEquals(0,calculator.add(0,0));
    }

    @Test
    void shouldSubtractTwoNumbers() {

        int result = calculator.subtract(5, 2);
        assertEquals(3, result);
        assertEquals(-5,calculator.subtract(5,10));
    }

    @Test
    void shouldMultiplyTwoNumbers() {

        int result = calculator.multiply(5, 2);
        assertEquals(10, result);
        assertEquals(0,calculator.multiply(0,100));
    }

    @Test
    void shouldDivideTwoNumbers(){
        assertEquals(5,calculator.divide(25,5));
        assertThrows(IllegalArgumentException.class,()->{
            calculator.divide(25,0);
        });
        assertEquals(-5, calculator.divide(-25,5));
        assertEquals(-5, calculator.divide(25,-5));
        assertEquals(5, calculator.divide(-25,-5));
        assertEquals(25, calculator.divide(25,1));

    }
}
