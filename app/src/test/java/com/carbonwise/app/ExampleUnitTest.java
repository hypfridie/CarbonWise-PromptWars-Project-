package com.carbonwise.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void carbonCalculator_transportEmissions() {
        com.carbonwise.app.util.CarbonCalculator calc = new com.carbonwise.app.util.CarbonCalculator();
        double emissions = com.carbonwise.app.util.CarbonCalculator.calculateTransportEmissions("petrol", 10);
        assertTrue(emissions > 0);
    }
}
