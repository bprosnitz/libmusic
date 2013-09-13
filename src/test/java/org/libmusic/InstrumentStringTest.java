package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class InstrumentStringTest {
    @Test
    public void testStringPitches() {
        for (SharpNote sharpNote : SharpNote.values()) {
            Note note = sharpNote.getNote();
            for (int octave = 2; octave <= 4; ++octave) {
                Pitch basePitch = new Pitch(note, octave);
                Pitch highBasePitch = new Pitch(note, octave + 1);
                InstrumentString string = new InstrumentString(basePitch);

                Assert.assertEquals(basePitch, string.getPitch(0));
                Assert.assertEquals(highBasePitch, string.getPitch(12));

                for (int i = 0; i < 12; ++i) {
                    Pitch expectedPitch = basePitch.shiftPitch(i);
                    Assert.assertEquals(expectedPitch, string.getPitch(i));
                    Pitch expectedHighPitch = new Pitch(expectedPitch.getNote(), expectedPitch.getOctave() + 1);
                    Assert.assertEquals(expectedHighPitch, string.getPitch(i + 12));
                }
            }
        }
    }

    @Test
    public void testGetStringOffsets() {
        InstrumentString instrumentString = new InstrumentString(new Pitch("E2"));
        Collection<Integer> offsets = instrumentString.getStringOffsets (new Note("G"));
        Assert.assertEquals(Arrays.asList(3, 15), offsets);

        Chord chord = new Chord(new Note("A"), new Note("G"), new Note("F#"));
        offsets = instrumentString.getStringOffsets(chord);
        Assert.assertEquals(Arrays.asList(2, 3, 5, 14, 15, 17), offsets);
    }
}
