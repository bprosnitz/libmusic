package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntervalTest {
    @Test
    public void testFromDistance() throws Interval.UnknownIntervalException {
        Assert.assertEquals(Interval.Unison, Interval.fromDistance(0));
        Assert.assertEquals(Interval.MajorSecond, Interval.fromDistance(2));
        Assert.assertEquals(Interval.MajorSeventh, Interval.fromDistance(11));

        try {
            Interval.fromDistance(-1);
            Assert.fail("No interval for distance -1");
        } catch (Interval.UnknownIntervalException e) {
            // expected
        }

        try {
            Interval.fromDistance(40);
            Assert.fail("No interval for distance 40 defined yet");
        } catch (Interval.UnknownIntervalException e) {
            // expected
        }
    }

    @Test
    public void testFromIntervalNumber() throws Interval.UnknownIntervalException {
        Assert.assertEquals(Interval.MajorSecond, Interval.fromIntervalNumber(2, true));
        Assert.assertEquals(Interval.MinorThird, Interval.fromIntervalNumber(3, false));
        Assert.assertEquals(Interval.PerfectFourth, Interval.fromIntervalNumber(4, true));
        Assert.assertEquals(Interval.PerfectFifth, Interval.fromIntervalNumber(5, false));

        try {
            Interval.fromIntervalNumber(0, true);
            Assert.fail("Shouldn't hit this.");
        } catch (Interval.UnknownIntervalException e) {
            // expected
        }
    }

    @Test
    public void testComparisonAndSortingOfIntervals() {
        List<Interval> intervals = Arrays.asList(Interval.Octave, Interval.Unison, Interval.MajorSixth,
                Interval.MinorSixth);
        Collections.sort(intervals);
        Assert.assertEquals(Arrays.asList(Interval.Unison, Interval.MinorSixth, Interval.MajorSixth, Interval.Octave),
                intervals);
    }
}
