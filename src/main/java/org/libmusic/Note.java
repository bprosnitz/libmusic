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
     * The zero-based index of the musical note in the string lists above where "C" is 0, "D" is 2, etc.
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
     * Get the zero-based index of the note.
     * @return The zero based index of the note where C is 0 and B is 11.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Create a note.
     * @param noteName The name of the note. e.g. "A", "Gb" or "C#"
     */
    public Note(String noteName) {
        if (noteName.isEmpty()) {
            throw new IllegalArgumentException("Illegal empty note name");
        }

        noteName = noteName.toLowerCase();

        int noteIndex;
        switch (noteName.charAt(0)) {
            case 'c':
                noteIndex = 0;
                break;
            case 'd':
                noteIndex = 2;
                break;
            case 'e':
                noteIndex = 4;
                break;
            case 'f':
                noteIndex = 5;
                break;
            case 'g':
                noteIndex = 7;
                break;
            case 'a':
                noteIndex = 9;
                break;
            case 'b':
                noteIndex = 11;
                break;
            default:
                throw new IllegalArgumentException("Illegal initial note name '" + noteName.charAt(0) + "'");
        }

        for (int index = 1; index < noteName.length(); ++index) {
            Character ch = noteName.charAt(index);
            switch (ch) {
                case 'b':
                    --noteIndex;
                    break;
                case '#':
                    ++noteIndex;
                    break;
                default:
                    throw new IllegalArgumentException("Unallowed character: '" + ch + "'");
            }
        }

        noteIndex %= 12;
        if (noteIndex < 0) {
            noteIndex += 12;
        }
        this.index = noteIndex;
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
        int newIndex = (index + offset) % 12;
        if (newIndex < 0) {
            newIndex += 12;
        }
        return new Note(newIndex);
    }

    /**
     * The amount above (distance to) the closest note of the specified type.
     * @param other The type of the note to compute the distance to
     * @return The distance of this note about the specified note.
     */
    public int amountAboveClosest(Note other) {
        int diff = this.index - other.index;
        if (diff >= 0) {
            return diff;
        }
        return 12 + diff;
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
