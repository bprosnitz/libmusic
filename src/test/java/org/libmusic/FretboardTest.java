package org.libmusic;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

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

    @Test
    public void testGetFretboardPositions() {
        Note note = new Note("C");
        Set<FretboardPosition> positions = Fretboard.StandardGuitarFretboard.getFretboardPositions(note);
        Assert.assertEquals(Sets.newHashSet(new FretboardPosition(5, 8),
                new FretboardPosition(5, 20), new FretboardPosition(4, 3),
                new FretboardPosition(4, 15), new FretboardPosition(3, 10),
                new FretboardPosition(3, 22), new FretboardPosition(2, 5),
                new FretboardPosition(2, 17), new FretboardPosition(1, 1),
                new FretboardPosition(1, 13), new FretboardPosition(0, 8),
                new FretboardPosition(0, 20)), positions);
    }
}
