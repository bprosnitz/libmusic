package org.libmusic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScaleVoicing {
    private Note scaleRoot;
    private Scale scale;

    public ScaleVoicing(Scale scale, Note scaleRoot) {
        this.scale = scale;
        this.scaleRoot = scaleRoot;
    }

    public Set<Note> getNotes() {
        Set<Note> notes = new HashSet<Note>();
        List<Interval> intervals = scale.getIntervals();
        Note note = scaleRoot;
        for (Interval interval : intervals) {
            note = note.shiftNote(interval.getDistance());
            notes.add(note);
        }
        return notes;
    }
}
