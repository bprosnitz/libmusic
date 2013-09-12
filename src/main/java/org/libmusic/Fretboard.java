package org.libmusic;

import java.util.Arrays;
import java.util.List;

public class Fretboard {
    private List<InstrumentString> strings;

    public Fretboard(List<InstrumentString> strings) {

        this.strings = strings;
    }

    public static Fretboard StandardGuitarFretboard = new Fretboard(Arrays.asList(
            new InstrumentString(new Pitch("E4")),
            new InstrumentString(new Pitch("B3")),
            new InstrumentString(new Pitch("G3")),
            new InstrumentString(new Pitch("D3")),
            new InstrumentString(new Pitch("A2")),
            new InstrumentString(new Pitch("E2"))));
    public static Fretboard StandardUkeleleFretboard = new Fretboard(Arrays.asList(
            new InstrumentString(new Pitch("A4")),
            new InstrumentString(new Pitch("E4")),
            new InstrumentString(new Pitch("C4")),
            new InstrumentString(new Pitch("G4"))));

    public InstrumentString getString(int string) {
        if (string < 0 || string >= strings.size()) {
            throw new IndexOutOfBoundsException("Invalid string index");
        }
        return strings.get(string);
    }
}
