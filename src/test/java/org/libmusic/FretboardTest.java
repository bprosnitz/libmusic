package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FretboardTest {
    @Test
    public void testGetStrings() {
        Fretboard fretboard = new Fretboard(Arrays.asList(new InstrumentString(new Pitch("A0")),
                new InstrumentString(new Pitch("C5")), new InstrumentString(new Pitch("E3"))));
        Assert.assertEquals(new Pitch("A0"), fretboard.getString(0).getPitch(0));
        Assert.assertEquals(new Pitch("C5"), fretboard.getString(1).getPitch(0));
        Assert.assertEquals(new Pitch("E3"), fretboard.getString(2).getPitch(0));
    }

    @Test
    public void testGuitarFretboard() {
        Assert.assertEquals(new Pitch("E4"), Fretboard.StandardGuitarFretboard.getString(0).getPitch(0));
        Assert.assertEquals(new Pitch("B3"), Fretboard.StandardGuitarFretboard.getString(1).getPitch(0));
        Assert.assertEquals(new Pitch("G3"), Fretboard.StandardGuitarFretboard.getString(2).getPitch(0));
        Assert.assertEquals(new Pitch("D3"), Fretboard.StandardGuitarFretboard.getString(3).getPitch(0));
        Assert.assertEquals(new Pitch("A2"), Fretboard.StandardGuitarFretboard.getString(4).getPitch(0));
        Assert.assertEquals(new Pitch("E2"), Fretboard.StandardGuitarFretboard.getString(5).getPitch(0));

        Assert.assertEquals(new Pitch("A2"), Fretboard.StandardGuitarFretboard.getString(5).getPitch(5));
        Assert.assertEquals(new Pitch("E3"), Fretboard.StandardGuitarFretboard.getString(4).getPitch(7));
        Assert.assertEquals(new Pitch("E3"), Fretboard.StandardGuitarFretboard.getString(3).getPitch(2));
        Assert.assertEquals(new Pitch("C4"), Fretboard.StandardGuitarFretboard.getString(2).getPitch(5));
        Assert.assertEquals(new Pitch("C4"), Fretboard.StandardGuitarFretboard.getString(1).getPitch(1));
        Assert.assertEquals(new Pitch("G4"), Fretboard.StandardGuitarFretboard.getString(0).getPitch(3));

    }
}
