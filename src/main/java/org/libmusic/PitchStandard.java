package org.libmusic;

public class PitchStandard {
    private static int NOTES_IN_OCTAVE = 12;
    public static PitchStandard A440 = new PitchStandard(440.0);

    private double frequencyA4;

    public PitchStandard(double frequencyA4) {
        this.frequencyA4 = frequencyA4;
    }

    public double getFrequency(Pitch pitch) {
        return Math.pow(2.0, ((double)(pitch.getNoteIndex() - 57)) / (double)NOTES_IN_OCTAVE) * frequencyA4;
    }
}
