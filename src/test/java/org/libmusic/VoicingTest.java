package org.libmusic;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class VoicingTest {
    @Test
    public void testConstructors() {
        Voicing voicing = new Voicing(new Pitch("A3"), new Pitch("Gb", 1));
        Assert.assertEquals("The chord did not contain the expected pitches",
                Sets.newHashSet(voicing.getPitches()), Sets.newHashSet(new Pitch("A3"), new Pitch("Gb", 1)));
        voicing = new Voicing(Arrays.asList(new Pitch("A4"), new Pitch("B0")));
        Assert.assertEquals("The chord did not contain the expected pitches",
                Sets.newHashSet(voicing.getPitches()), Sets.newHashSet(new Pitch("A4"), new Pitch("B0")));
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(new Voicing(new Pitch("A0"), new Pitch("Gb2"), new Pitch("F3")),
                new Voicing(new Pitch("A0"), new Pitch("F#2"), new Pitch("F3")));
        Assert.assertFalse(new Voicing(new Pitch("A0"), new Pitch("Gb2"), new Pitch("F3")).equals(
                new Voicing(new Pitch("A0"), new Pitch("F#2"), new Pitch("F4"))));
        Assert.assertEquals(new Voicing(new Pitch("B0"), new Pitch("Gb2"), new Pitch("F1")),
                new Voicing(new Pitch("B0"), new Pitch("F#2"), new Pitch("F1"), new Pitch("F1")));
        Assert.assertFalse(new Voicing(new Pitch("B0"), new Pitch("Gb2"), new Pitch("F1")).equals(
                new Voicing(new Pitch("B0"), new Pitch("F#2"), new Pitch("F1"), new Pitch("F4"))));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(new Voicing(new Pitch("A0"), new Pitch("Gb2"), new Pitch("F3")).hashCode(),
                new Voicing(new Pitch("A0"), new Pitch("F#2"), new Pitch("F3")).hashCode());
        Assert.assertFalse(new Voicing(new Pitch("A0"), new Pitch("Gb2"), new Pitch("F3")).hashCode() ==
                new Voicing(new Pitch("A0"), new Pitch("F#2"), new Pitch("F4")).hashCode());
        Assert.assertEquals(new Voicing(new Pitch("B0"), new Pitch("Gb2"), new Pitch("F1")).hashCode(),
                new Voicing(new Pitch("B0"), new Pitch("F#2"), new Pitch("F1"), new Pitch("F1")).hashCode());
        Assert.assertFalse(new Voicing(new Pitch("B0"), new Pitch("Gb2"), new Pitch("F1")).hashCode() ==
                new Voicing(new Pitch("B0"), new Pitch("F#2"), new Pitch("F1"), new Pitch("F4")).hashCode());
    }
}
