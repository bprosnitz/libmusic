package org.libmusic;

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
}
