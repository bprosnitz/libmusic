package org.libmusic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pitch {
    public static int NOTES_IN_OCTAVE = 12;

    private final Note note;
    private final int octave;

    public Pitch(Note note, int octaveNumber) {
        this.note = note;
        this.octave = octaveNumber;
    }

    public Pitch(String noteName, int octaveNumber) {
        this.note = new Note(noteName);
        if (octaveNumber < 0) {
            throw new IllegalArgumentException("Bad octave: " + octaveNumber);
        }
        this.octave = octaveNumber;
    }

    public Pitch(String fullNoteName) {
        Pattern pattern = Pattern.compile("([A-Z][b#]*)([0-9]+)");
        Matcher matcher = pattern.matcher(fullNoteName);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Badly formed note name. '" + fullNoteName + "'");
        }
        String noteName = matcher.group(1);
        String octaveNumberString = matcher.group(2);

        this.note = new Note(noteName);
        this.octave = Integer.parseInt(octaveNumberString);
        if (this.octave < 0) {
            throw new IllegalArgumentException("Bad octave: " + this.octave);
        }
    }

    public Note getNote() {
        return note;
    }

    public int getOctave() {
        return octave;
    }

    public int getNoteIndex() {
        return octave * 12 + note.getIndex();
    }

    /**
     * Returns the pitch shifted by the specified offset. e.g. A3.shiftPitch(13)=A#4
     * @param offset The offset to shift the pitch by.
     * @return The pitch, shifted by the specified offset.
     */
    public Pitch shiftPitch(int offset) {
        Note shiftedNote = getNote().shiftNote(offset);

        Note lowNote;
        if (offset >= 0) {
            lowNote = getNote();
        } else {
            lowNote = shiftedNote;
        }

        int baseFactor = Math.abs(offset / 12);
        int remainder = Math.abs(offset % 12);
        int amountBelowC = new Note("C").amountAboveClosest(lowNote);
        if (remainder >= amountBelowC && amountBelowC != 0) {
            ++baseFactor;
        }
        int newOctave = getOctave();

        if (offset >= 0) {
            newOctave += baseFactor;
        } else {
            newOctave -= baseFactor;
        }
        return new Pitch(shiftedNote, newOctave);
    }

    @Override
    public String toString() {
        return note.toString() + octave;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Pitch)) {
            return false;
        }
        Pitch other = (Pitch)obj;

        return octave == other.octave && note.equals(other.note);
    }

    @Override
    public int hashCode() {
        return octave * NOTES_IN_OCTAVE + note.getIndex();
    }
}
