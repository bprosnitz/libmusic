package org.libmusic;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Voicing {
    private final Set<Pitch> pitches;

    public Voicing(Collection<Pitch> pitches) {
        this.pitches = new HashSet<Pitch>(pitches);
    }

    public Voicing(Pitch... pitches) {
        this.pitches = Sets.newHashSet(pitches);
    }

    public Set<Pitch> getPitches() {
        return new HashSet<Pitch>(pitches);
    }

    public Chord getChord() {
        Set<Note> notes = new HashSet<Note>();
        for (Pitch pitch : pitches) {
            notes.add(pitch.getNote());
        }
        return new Chord(notes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Voicing)) {
            return false;
        }
        Voicing other = (Voicing)obj;

        return pitches.equals(other.pitches);
    }

    @Override
    public int hashCode() {
        return pitches.hashCode();
    }
}
