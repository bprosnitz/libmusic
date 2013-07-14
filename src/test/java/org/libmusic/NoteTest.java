package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

public class NoteTest {
    public static String [] FLAT_NAMES = new String[] {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
    public static String [] SHARP_NAMES = new String[] {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};


    @Test
    public void testBasicConstructor() {
        Note note = new Note(5);
        Assert.assertEquals(5, note.getIndex());

        try {
            new Note(-1);
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            new Note(12);
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testConstructFromNoteName() {
        for (int i = 0; i < SHARP_NAMES.length; ++i) {
            Note note = new Note(SHARP_NAMES[i]);
            Assert.assertEquals(i, note.getIndex());
            Assert.assertEquals(SHARP_NAMES[i], note.getName(Note.NoteNameType.Sharp));
            Assert.assertEquals(FLAT_NAMES[i], note.getName(Note.NoteNameType.Flat));
        }

        for (int i = 0; i < FLAT_NAMES.length; ++i) {
            Note note = new Note(FLAT_NAMES[i]);
            Assert.assertEquals(i, note.getIndex());
            Assert.assertEquals(FLAT_NAMES[i], note.getName(Note.NoteNameType.Flat));
            Assert.assertEquals(SHARP_NAMES[i], note.getName(Note.NoteNameType.Sharp));
        }

        try {
            new Note("X");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testToString() {
        Assert.assertEquals("F#", new Note("Gb").toString());
        Assert.assertEquals("B", new Note("B").toString());
    }

    @Test
    public void testEqualsAndHashcode() {
        for (int i = 0; i < FLAT_NAMES.length; ++i) {
            Assert.assertEquals(new Note(FLAT_NAMES[i]), new Note(SHARP_NAMES[i]));
            Assert.assertEquals(new Note(SHARP_NAMES[i]), new Note(FLAT_NAMES[i]));
            Assert.assertEquals(new Note(FLAT_NAMES[i]).hashCode(), new Note(SHARP_NAMES[i]).hashCode());
        }
    }
}
