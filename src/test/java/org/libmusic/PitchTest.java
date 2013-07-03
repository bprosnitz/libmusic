package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

public class PitchTest {
    @Test
    public void testBasicConstructor() {
        Pitch pitch = new Pitch("Gb", 2);
        Assert.assertEquals(2, pitch.getOctave());
        Assert.assertEquals("F#", pitch.getNote().getName(Note.NoteNameType.Sharp));

        try {
            new Pitch("A", -1);
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testParsingConstructor() {
        Pitch pitch = new Pitch("C#4");
        Assert.assertEquals(4, pitch.getOctave());
        Assert.assertEquals("Db", pitch.getNote().getName(Note.NoteNameType.Flat));

        pitch = new Pitch("Db4");
        Assert.assertEquals(4, pitch.getOctave());
        Assert.assertEquals("C#", pitch.getNote().getName(Note.NoteNameType.Sharp));

        pitch = new Pitch("C0");
        Assert.assertEquals(0, pitch.getOctave());
        Assert.assertEquals("C", pitch.getNote().getName(Note.NoteNameType.Flat));

        try {
            new Pitch("C");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            new Pitch("2");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            new Pitch("X0");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void assertCloseFrequencies(double reference, double computed) {
        double difference = Math.abs(reference - computed);
        Assert.assertTrue(computed + " is further than 0.01 from " + reference, difference <= 0.01);
    }

    @Test
    public void testGetFrequencies() {
        assertCloseFrequencies(16.35, new Pitch("C0").getFrequency());
        assertCloseFrequencies(4698.64, new Pitch("D8").getFrequency());
        assertCloseFrequencies(440.00, new Pitch("A4").getFrequency());
        assertCloseFrequencies(146.83, new Pitch("D3").getFrequency());
    }
}
