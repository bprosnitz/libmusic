package org.libmusic;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

public class ChordQualityTest {
    @Test
    public void testGetShortName() {
        Assert.assertEquals("", ChordQuality.Major.getShortName());
        Assert.assertEquals("m", ChordQuality.Minor.getShortName());
        Assert.assertEquals("dim", ChordQuality.Diminished.getShortName());
        Assert.assertEquals("aug", ChordQuality.Augmented.getShortName());
    }

    @Test
    public void testGetRequiredOffsets() {
        Assert.assertEquals(Sets.newHashSet(0, 4, 7), ChordQuality.Major.getRequiredOffsets());
        Assert.assertEquals(Sets.newHashSet(0, 3, 7), ChordQuality.Minor.getRequiredOffsets());
        Assert.assertEquals(Sets.newHashSet(0, 3, 6), ChordQuality.Diminished.getRequiredOffsets());
        Assert.assertEquals(Sets.newHashSet(0, 4, 8), ChordQuality.Augmented.getRequiredOffsets());
    }

    @Test
    public void testFromNoteOffsets() throws ChordQuality.UnknownChordQualityException {
        Assert.assertEquals(ChordQuality.Major, ChordQuality.fromNoteOffsets(Sets.newHashSet(0, 2, 4, 7)));
        Assert.assertEquals(ChordQuality.Minor, ChordQuality.fromNoteOffsets(Sets.newHashSet(0, 3, 7)));
        Assert.assertEquals(ChordQuality.Diminished, ChordQuality.fromNoteOffsets(Sets.newHashSet(-2, 0, 3, 6)));
        Assert.assertEquals(ChordQuality.Augmented, ChordQuality.fromNoteOffsets(Sets.newHashSet(0, 4, 8)));

        try {
            ChordQuality.fromNoteOffsets(Sets.newHashSet(0, 4, 9));
            Assert.fail("Expected to hit exception");
        } catch (ChordQuality.UnknownChordQualityException e) {
            // expected
        }

        try {
            ChordQuality.fromNoteOffsets(Sets.newHashSet(0, 3, 4, 7));
            Assert.fail("Expected to hit exception");
        } catch (ChordQuality.UnknownChordQualityException e) {
            // expected
        }
    }
}
