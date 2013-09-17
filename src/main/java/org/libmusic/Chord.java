package org.libmusic;

import com.google.common.collect.Sets;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chord implements NoteProvider {
    private final Set<Note> notes;

    public Chord(Collection<Note> notes) {
        this.notes = new HashSet<Note>(notes);
    }

    public Chord(Note ... notes) {
        this.notes = Sets.newHashSet(notes);
    }

    public static Chord fromChordName(String chordName) throws Interval.UnknownIntervalException {
        final Pattern notePattern = Pattern.compile("^\\s*([A-G][#b]*)(.*)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = notePattern.matcher(chordName);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Badly formed chord name: " + chordName);
        }
        String rootNoteStr = matcher.group(1);
        Note rootNote = new Note(rootNoteStr);

        String remainder = matcher.group(2);

        List<Integer> addNumbers = new ArrayList<Integer>();
        Pattern addPattern = Pattern.compile("^(.*)add\\s*([0-9]+)\\s*$", Pattern.CASE_INSENSITIVE);
        while(true) {
            matcher = addPattern.matcher(remainder);

            if (!matcher.matches()) {
                break;
            }
            addNumbers.add(Integer.parseInt(matcher.group(2)));
            remainder = matcher.group(1);
        }

        String nowhitespace = remainder.replaceAll("\\s", "");

        List<Interval> intervals;
        if (nowhitespace.equals("") || nowhitespace.equals("M") ||
                nowhitespace.toLowerCase().equals("maj") || nowhitespace.toLowerCase().equals("major")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MajorThird, Interval.PerfectFifth);
        } else if (nowhitespace.equals("M7") || nowhitespace.toLowerCase().equals("maj7") ||
                nowhitespace.toLowerCase().equals("major7")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MajorThird, Interval.PerfectFifth, Interval.MajorSeventh);
        } else if (nowhitespace.equals("7") || nowhitespace.toLowerCase().equals("dominant7")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MajorThird, Interval.PerfectFifth, Interval.MinorSeventh);
        } else if (nowhitespace.equals("m") || nowhitespace.toLowerCase().equals("min") ||
                nowhitespace.toLowerCase().equals("minor")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MinorThird, Interval.PerfectFifth);
        } else if (nowhitespace.equals("m7") || nowhitespace.toLowerCase().equals("min7") ||
                nowhitespace.toLowerCase().equals("minor7")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MinorThird, Interval.PerfectFifth, Interval.MinorSeventh);
        } else if (nowhitespace.toLowerCase().equals("aug") || nowhitespace.toLowerCase().equals("augmented")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MajorThird, Interval.MinorSixth);
        } else if (nowhitespace.toLowerCase().equals("dim") || nowhitespace.toLowerCase().equals("diminished")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MinorThird, Interval.Tritone);
        } else if (nowhitespace.toLowerCase().equals("dim7") || nowhitespace.toLowerCase().equals("diminished7")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MinorThird, Interval.Tritone, Interval.MajorSixth);
        } else if (nowhitespace.toLowerCase().equals("sus2")) {
            intervals = Arrays.asList(Interval.Unison, Interval.MajorSecond, Interval.PerfectFifth);
        } else if (nowhitespace.toLowerCase().equals("sus") || nowhitespace.toLowerCase().equals("sus4")) {
            intervals = Arrays.asList(Interval.Unison, Interval.PerfectFourth, Interval.PerfectFifth);
        } else {
            throw new IllegalArgumentException("Unknown argument '" + remainder + "'");
        }

        Set<Note> notesInChord = new HashSet<Note>();
        for (Interval interval : intervals) {
            notesInChord.add(rootNote.shiftNote(interval.getDistance()));
        }

        for (int added : addNumbers) {
            Interval interval = Interval.fromIntervalNumber(added, true);
            notesInChord.add(rootNote.shiftNote(interval.getDistance()));
        }

        return new Chord(notesInChord);
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
