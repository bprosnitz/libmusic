package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

public class PitchStandardTest {
    public void assertCloseFrequencies(double reference, double computed) {
        double difference = Math.abs(reference - computed);
        Assert.assertTrue(computed + " is further than 0.01 from " + reference, difference <= 0.01);
    }

    @Test
    public void testGetA440Frequencies() {
        assertCloseFrequencies(16.35, PitchStandard.A440.getFrequency(new Pitch("C0")));
        assertCloseFrequencies(4698.64, PitchStandard.A440.getFrequency(new Pitch("D8")));
        assertCloseFrequencies(440.00, PitchStandard.A440.getFrequency(new Pitch("A4")));
        assertCloseFrequencies(146.83, PitchStandard.A440.getFrequency(new Pitch("D3")));
    }

    @Test
    public void testAtypicalPitchStandards() {
        assertCloseFrequencies(430.00, new PitchStandard(430.00).getFrequency(new Pitch("A4")));
    }
}
