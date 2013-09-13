package org.libmusic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DiatonicScale implements Scale {
    Ionian(0),
    Dorian(1),
    Phrygian(2),
    Lydian(3),
    Mixolydian(4),
    Aeolian(5),
    Locrian(6);

    public static DiatonicScale MajorScale = Ionian;
    public static DiatonicScale NaturalMinorScale = Aeolian;

    private int mode;

    DiatonicScale(int mode) {
        this.mode = mode;
    }

    private DiatonicScale fromMode(int mode) {
        for (DiatonicScale ds : DiatonicScale.values()) {
            if (mode == ds.mode) {
                return ds;
            }
        }
        throw new IllegalArgumentException("Illegal value of mode: " + mode);
    }

    public DiatonicScale shiftMode(int difference) {
        int newMode = (mode + difference) % 7;
        if (newMode < 0) {
            newMode += 7;
        }
        return fromMode(newMode);
    }

    private Interval getInterval(int index) {
        if (index < 0 || index > 7) {
            throw new IllegalArgumentException("index must be between 0 and 7");
        }
        Interval T = Interval.MajorSecond;
        Interval s = Interval.MinorSecond;
        List<Interval> ionianSequence = Arrays.asList(T, T, s, T, T, T, s);
        int offset = (mode + index) % 7;
        return ionianSequence.get(offset);
    }

    public List<Interval> getIntervals() {
        List<Interval> intervals = new ArrayList<Interval>(7);
        for (int i = 0; i < 7; ++i) {
            intervals.add(getInterval(i));
        }
        return intervals;
    }
}
