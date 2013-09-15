package org.libmusic;

public class FretboardPosition {
    private int string;
    private int positionOnString;

    public FretboardPosition(int string, int positionOnString) {
        this.string = string;
        this.positionOnString = positionOnString;
    }

    public int getString() {
        return string;
    }

    public int getPositionOnString() {
        return positionOnString;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof FretboardPosition)) {
            return false;
        }
        FretboardPosition fp = (FretboardPosition)other;
        return string == fp.string && positionOnString == fp.positionOnString;
    }

    @Override
    public int hashCode() {
        return (string << 8) + positionOnString;
    }

    @Override
    public String toString() {
        return "(" + string + "," + positionOnString + ")";
    }
}
