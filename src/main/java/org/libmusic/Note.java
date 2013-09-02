package org.libmusic;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a musical note, without information about the octave.
 *
 * @author benjamin
 */
public class Note {
    private static List<String> NOTES_IN_OCTAVE_FLAT =
            Arrays.asList("C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B");
    private static List<String> NOTES_IN_OCTAVE_SHARP =
            Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");

    /**
     * The zero-based index of the musical note in the string lists above, from "C" to "B". 0 is "C", 11 is "B".
     */
    private final int index;

    /**
     * The type of the note name to use in generating strings.
     */
    public static enum NoteNameType {
        Sharp,
        Flat
    }

    /**
     * Create a note.
     * @param noteIndex The zero-based index of the note from 0 to 11, where 0 is "C", 1 is "C#" and 11 is "B".
     */
    public Note(int noteIndex) {
        if (noteIndex < 0 || noteIndex >= 12) {
            throw new IllegalArgumentException("Invalid note index: " + noteIndex);
        }
        this.index = noteIndex;
    }

    /**
     * Create a note.
     * @param noteName The name of the note. e.g. "A", "Gb" or "C#"
     */
    public Note(String noteName) {
        int index = NOTES_IN_OCTAVE_FLAT.indexOf(noteName);
        if (index == -1) {
            index = NOTES_IN_OCTAVE_SHARP.indexOf(noteName);
        }
        if (index == -1) {
            throw new IllegalArgumentException("Unable to recognize note '" + noteName + "'");
        }
        this.index = index;
    }

    /**
     * Get the zero-based index of the note.
     * @return The zero based index of the note where C is 0 and B is 11.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets the name of the note.
     * @param type Indicates if the notes should be represented in sharps or flats
     * @return A string representing the note.
     */
    public String getName(NoteNameType type) {
        if (type == NoteNameType.Sharp) {
            return NOTES_IN_OCTAVE_SHARP.get(index);
        }
        return NOTES_IN_OCTAVE_FLAT.get(index);
    }

    /**
     * Shifts a note by the specified offset. e.g. A.shiftNote(-1)=G#
     * @param offset The offset to shift the note by
     * @return A note, shifted by the specified offset.
     */
    public Note shiftNote(int offset) {
        int newIndex = (getIndex() + offset) % 12;
        return new Note(newIndex);
    }

    @Override
    public String toString() {
        return getName(NoteNameType.Sharp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Note)) {
            return false;
        }
        Note other = (Note)obj;

        return index == other.index;
    }

    @Override
    public int hashCode() {
        return index;
    }
}
