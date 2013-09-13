package org.libmusic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InstrumentString {
    private static int NUM_FRETS = 24;

    private Pitch basePitch;

    public InstrumentString(Pitch basePitch) {
        this.basePitch = basePitch;
    }

    public Pitch getPitch(int fret) {
        if (fret < 0 || fret > NUM_FRETS) {
            throw new IllegalArgumentException("Invalid fret index: " + fret);
        }
        return basePitch.shiftPitch(fret);
    }

    public List<Integer> getStringOffsets(NoteProvider noteProvider) {
        Set<Note> notes = noteProvider.getNotes();
        List<Integer> offsets = new ArrayList<Integer>();
        for (int i = 0; i < 24; ++i) {
            if (notes.contains(basePitch.shiftPitch(i).getNote())){
                offsets.add(i);
            }
        }
        return offsets;
    }
}
