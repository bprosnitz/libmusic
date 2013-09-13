package org.libmusic;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Chord implements NoteProvider {
    private final Set<Note> notes;

    public Chord(Collection<Note> notes) {
        this.notes = new HashSet<Note>(notes);
    }

    public Chord(Note ... notes) {
        this.notes = Sets.newHashSet(notes);
    }

    public Set<Note> getNotes() {
        return new HashSet<Note>(notes);
    }

    public Collection<ChordView> getChordViews() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Chord)) {
            return false;
        }
        Chord other = (Chord)obj;

        return notes.equals(other.notes);
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }
}
