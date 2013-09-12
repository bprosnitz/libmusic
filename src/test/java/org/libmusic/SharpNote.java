package org.libmusic;

public enum SharpNote {
    A("A"), A_SHARP("A#"), B("B"), B_SHARP("B#"), C("C"), C_SHARP("C#"), D("D"), D_SHARP("D#"), E("E"), F("F"),
    F_SHARP("F#"), G("G"), G_SHARP("G#");

    private Note note;

    SharpNote(String noteName) {
        note = new Note(noteName);
    }

    public Note getNote() {
        return note;
    }
}
