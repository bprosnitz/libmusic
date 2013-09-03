package org.libmusic;

import java.util.List;

public interface Scale {
    /**
     * @return The sequence of intervals that make up the scale (in an octave).
     */
    public List<Interval> getIntervals();
}
