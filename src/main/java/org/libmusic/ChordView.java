package org.libmusic;

import java.util.BitSet;

public class ChordView {
    private static int OCTAVE_SIZE = 12;

    /**
     * The root note of the chord
     */
    private final Note rootNote;

    /**
     * The base note of the chord
     */
    private final Note baseNote;

    /**
     * Zero-based note offsets from the root, including the root note.
     */
    private final BitSet notePositions;

    public static enum ChordQuality {
        Major,
        Minor,
        Augmented,
        Diminished
    }

    public static class UnexpectedChordStructureException extends Exception {
        public UnexpectedChordStructureException(String message) {
            super(message);
        }
    }

    public ChordView(Note rootNote, BitSet notePositions) {
        this(rootNote, notePositions, rootNote);
    }

    public ChordView(Note rootNote, BitSet notePositions, Note baseNote) {
        this.rootNote = rootNote;
        if (!notePositions.get(0)) {
            throw new IllegalArgumentException("Bit 0 of the bit set is expected to be set.");
        }
        this.notePositions = notePositions;
        this.baseNote = baseNote;
    }

    private boolean isNotePresent(int offset) {
        if (offset < 0) {
            throw new IllegalArgumentException("Did not expect negative offset.");
        }

        return notePositions.get(offset % OCTAVE_SIZE);
    }

    public Note getRootNote() {
        return rootNote;
    }

    public Note getBaseNote() {
        return baseNote;
    }

    public ChordQuality getChordQuality() throws UnexpectedChordStructureException {
        if (isNotePresent(0) && isNotePresent(4) && isNotePresent(7)) {
            return ChordQuality.Major;
        } else if (isNotePresent(0) && isNotePresent(3) && isNotePresent(7)) {
            return ChordQuality.Minor;
        } else if (isNotePresent(0) && isNotePresent(3) && isNotePresent(8)) {
            return ChordQuality.Augmented;
        } else if (isNotePresent(0) && isNotePresent(3) && isNotePresent(6)) {
            return ChordQuality.Diminished;
        }
        throw new UnexpectedChordStructureException("Could not identify the chord quality of the provided chord");
    }


}
