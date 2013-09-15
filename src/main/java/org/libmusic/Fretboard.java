package org.libmusic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public int numStrings() {
        return strings.size();
    }

    public Set<FretboardPosition> getFretboardPositions(NoteProvider noteProvider) {
        Set<FretboardPosition> fretboardPositions = new HashSet<FretboardPosition>();
        for (int string = 0; string < numStrings(); ++string) {
            InstrumentString instrumentString = getString(string);
            for (int position : instrumentString.getStringOffsets(noteProvider)) {
                fretboardPositions.add(new FretboardPosition(string, position));
            }
        }
        return fretboardPositions;
    }
}
