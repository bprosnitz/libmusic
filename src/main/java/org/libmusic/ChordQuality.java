package org.libmusic;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Set;

public enum ChordQuality {
    Major(Sets.newHashSet(0, 4, 7), ""),
    Minor(Sets.newHashSet(0, 3, 7), "m"),
    Diminished(Sets.newHashSet(0, 3, 6), "dim"),
    Augmented(Sets.newHashSet(0, 4, 8), "aug");

    public static class UnknownChordQualityException extends Exception {
        public UnknownChordQualityException(String message) {
            super(message);
        }
    }

    private String shortName;
    private Set<Integer> requiredOffsets;

    ChordQuality(Set<Integer> requiredOffsets, String shortName) {
        this.shortName = shortName;
        this.requiredOffsets = requiredOffsets;
    }

    /**
     * Determine the chord quality based on the chord's offsets to a root note.
     * @param noteOffsets The set of offsets to the root note.
     * @return The matching chord quality, if found
     * @throws UnknownChordQualityException If there is not a unique chord quality match.
     */
    public static ChordQuality fromNoteOffsets(Set<Integer> noteOffsets) throws UnknownChordQualityException {
        ChordQuality foundQuality = null;
        for (ChordQuality chordQuality : ChordQuality.values()) {
            if (noteOffsets.containsAll(chordQuality.getRequiredOffsets())) {
                if (foundQuality != null) {
                    throw new UnknownChordQualityException("Multiple matching chord qualities found for offsets " +
                        noteOffsets);
                }
                foundQuality = chordQuality;
            }
        }
        if (foundQuality != null) {
            return foundQuality;
        }

        throw new UnknownChordQualityException("No chord quality found for note offsets" +
            noteOffsets);
    }

    public String getShortName() {
        return shortName;
    }

    public Collection<Integer> getRequiredOffsets() {
        return requiredOffsets;
    }
}
