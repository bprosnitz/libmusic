package org.libmusic;

public enum Interval {
    Unison(0, 1, IntervalQuality.Perfect),
    MinorSecond(1, 2, IntervalQuality.Minor),
    MajorSecond(2, 2, IntervalQuality.Major),
    MinorThird(3, 3, IntervalQuality.Minor),
    MajorThird(4, 3, IntervalQuality.Major),
    PerfectFourth(5, 4, IntervalQuality.Perfect),
    Tritone(6, null, null),
    PerfectFifth(7, 5, IntervalQuality.Perfect),
    MinorSixth(8, 6, IntervalQuality.Minor),
    MajorSixth(9, 6, IntervalQuality.Major),
    MinorSeventh(10, 7, IntervalQuality.Minor),
    MajorSeventh(11, 7, IntervalQuality.Minor),
    Octave(12, 8, IntervalQuality.Perfect);

    public static class UnknownIntervalException extends Exception {
        public UnknownIntervalException(String message) {
            super(message);
        }
    }

    int distance;
    Integer intervalNumber;
    IntervalQuality intervalQuality;

    Interval(int distance, Integer intervalNumber, IntervalQuality intervalQuality) {
        this.distance = distance;
        this.intervalNumber = intervalNumber;
        this.intervalQuality = intervalQuality;
    }

    /**
     * Returns the interval for the specified semitone distance.
     * @param distance The size of the interval, in semitones.
     * @return An interval matching the requested distance.
     * @throws UnknownIntervalException If the distance is negative or large.
     */
    public static Interval fromDistance(int distance) throws UnknownIntervalException {
        for (Interval interval : Interval.values()) {
            if (interval.distance == distance) {
                return interval;
            }
        }
        throw new UnknownIntervalException("Unknown interval with distance " +
            distance);
    }
}
