package org.libmusic;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChordTest {
    @Test
    public void testConstructors() {
        Chord chord = new Chord(new Note("A"), new Note("Gb"));
        Assert.assertEquals("The chord did not contain the expected notes",
                Sets.newHashSet(chord.getNotes()), Sets.newHashSet(new Note("A"), new Note("Gb")));
        chord = new Chord(Arrays.asList(new Note("A"), new Note("B")));
        Assert.assertEquals("The chord did not contain the expected notes",
                Sets.newHashSet(chord.getNotes()), Sets.newHashSet(new Note("A"), new Note("B")));
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(new Chord(new Note("A"), new Note("Gb"), new Note("F")),
                new Chord(new Note("A"), new Note("F#"), new Note("F")));
        Assert.assertEquals(new Chord(new Note("B"), new Note("Gb"), new Note("F")),
                new Chord(new Note("B"), new Note("F#"), new Note("F"), new Note("F")));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(new Chord(new Note("A"), new Note("Gb"), new Note("F")).hashCode(),
                new Chord(new Note("A"), new Note("F#"), new Note("F")).hashCode());
        Assert.assertEquals(new Chord(new Note("B"), new Note("Gb"), new Note("F")).hashCode(),
                new Chord(new Note("B"), new Note("F#"), new Note("F"), new Note("F")).hashCode());
    }

    private Set<Note> noteSet(String ... noteNames) {
        Set<Note> notes = new HashSet<Note>();
        for (String name : noteNames) {
            notes.add(new Note(name));
        }
        return notes;
    }

    @Test
    public void testFromChordName() throws Interval.UnknownIntervalException {
        Assert.assertEquals(noteSet("D", "F#", "A"), Chord.fromChordName("D").getNotes());
        Assert.assertEquals(noteSet("E", "G#", "B", "D#"), Chord.fromChordName("Emaj7").getNotes());
        Assert.assertEquals(noteSet("A#", "D", "E#", "G#"), Chord.fromChordName("A#7").getNotes());
        Assert.assertEquals(noteSet("B", "D", "F#"), Chord.fromChordName("bm").getNotes());
        Assert.assertEquals(noteSet("D", "F", "A", "C"), Chord.fromChordName("Dm7").getNotes());
        Assert.assertEquals(noteSet("F", "A", "C#"), Chord.fromChordName("Faug").getNotes());
        Assert.assertEquals(noteSet("Gb", "A", "C"), Chord.fromChordName("Gb dim").getNotes());
        Assert.assertEquals(noteSet("Gb", "A", "C", "EB"), Chord.fromChordName("Gb dim 7").getNotes());
        Assert.assertEquals(noteSet("A", "B", "E"), Chord.fromChordName("Asus2").getNotes());
        Assert.assertEquals(noteSet("A", "D", "E"), Chord.fromChordName("Asus4").getNotes());

        Assert.assertEquals(noteSet("A", "D", "E", "F#"), Chord.fromChordName("Asus4 add 6").getNotes());
        Assert.assertEquals(noteSet("A", "D", "E", "B"), Chord.fromChordName("Asus4add9").getNotes());
    }
}
