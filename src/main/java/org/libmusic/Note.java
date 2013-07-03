package org.libmusic;

import java.util.Arrays;
import java.util.List;

public class Note {
    private static List<String> NOTES_IN_OCTAVE_FLAT =
            Arrays.asList("C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B");
    private static List<String> NOTES_IN_OCTAVE_SHARP =
            Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");

    final int index;

    public static enum NoteNameType {
        Sharp,
        Flat
    }

    public Note(int noteIndex) {
        if (noteIndex < 0 || noteIndex >= 12) {
            throw new IllegalArgumentException("Invalid note index: " + noteIndex);
        }
        this.index = noteIndex;
    }

    public Note(String noteString) {
        int index = NOTES_IN_OCTAVE_FLAT.indexOf(noteString);
        if (index == -1) {
            index = NOTES_IN_OCTAVE_SHARP.indexOf(noteString);
        }
        if (index == -1) {
            throw new IllegalArgumentException("Unable to recognize note '" + noteString + "'");
        }
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName(NoteNameType type) {
        if (type == NoteNameType.Sharp) {
            return NOTES_IN_OCTAVE_SHARP.get(index);
        }
        return NOTES_IN_OCTAVE_FLAT.get(index);
    }
}
