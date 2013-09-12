package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

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
}
