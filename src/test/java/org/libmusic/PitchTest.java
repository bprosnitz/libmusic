package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

public class PitchTest {
    @Test
    public void testBasicConstructor() {
        Pitch pitch = new Pitch("Gb", 2);
        Assert.assertEquals(2, pitch.getOctave());
        Assert.assertEquals("F#", pitch.getNote().getName(Note.NoteNameType.Sharp));

        Note note = new Note("B");
        pitch = new Pitch(note, 3);
        Assert.assertEquals(3, pitch.getOctave());
        Assert.assertEquals(new Note("B"), pitch.getNote());

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

    @Test
    public void testShiftPitch() {
        Assert.assertEquals(new Pitch("A#4"), new Pitch("A3").shiftPitch(13));
        Assert.assertEquals(new Pitch("C2"), new Pitch("C3").shiftPitch(-12));
        Assert.assertEquals(new Pitch("A3"), new Pitch("A3").shiftPitch(0));
        Assert.assertEquals(new Pitch("D4"), new Pitch("A3").shiftPitch(5));
        Assert.assertEquals(new Pitch("C3"), new Pitch("C3").shiftPitch(0));
        Assert.assertEquals(new Pitch("C#3"), new Pitch("C3").shiftPitch(1));
        Assert.assertEquals(new Pitch("B2"), new Pitch("C3").shiftPitch(-1));
        Assert.assertEquals(new Pitch("C3"), new Pitch("B2").shiftPitch(1));
        Assert.assertEquals(new Pitch("A1"), new Pitch("C3").shiftPitch(-15));
        Assert.assertEquals(new Pitch("C#2"), new Pitch("C3").shiftPitch(-11));
    }

    @Test
    public void testToString() {
        Assert.assertEquals("F#4", new Pitch("Gb4").toString());
        Assert.assertEquals("D0", new Pitch("D0").toString());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(new Pitch("Gb4"), new Pitch("F#4"));
        Assert.assertEquals(new Pitch("B0"), new Pitch("B", 0));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(new Pitch("Gb4").hashCode(), new Pitch("F#4").hashCode());
        Assert.assertEquals(new Pitch("B0").hashCode(), new Pitch("B", 0).hashCode());
    }
}
