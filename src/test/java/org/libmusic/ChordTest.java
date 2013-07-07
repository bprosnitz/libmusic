package org.libmusic;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
}
