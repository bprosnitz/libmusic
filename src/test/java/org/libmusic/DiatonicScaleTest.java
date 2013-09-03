package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DiatonicScaleTest {
    @Test
    public void testShiftMode() {
        Assert.assertEquals(DiatonicScale.Aeolian, DiatonicScale.Ionian.shiftMode(-2));
        Assert.assertEquals(DiatonicScale.Mixolydian, DiatonicScale.Phrygian.shiftMode(2));
        Assert.assertEquals(DiatonicScale.Ionian, DiatonicScale.Locrian.shiftMode(1));
    }

    @Test
    public void testGetIntervals() {
        Interval T = Interval.MajorSecond;
        Interval s = Interval.MinorSecond;
        Assert.assertEquals(Arrays.asList(s, T, T, T, s, T, T), DiatonicScale.Phrygian.getIntervals());
        Assert.assertEquals(Arrays.asList(T, s, T, T, s, T, T), DiatonicScale.Aeolian.getIntervals());
    }

    @Test
    public void testIntervalsSumToOctave() {
        List<Interval> intervals = DiatonicScale.Dorian.getIntervals();
        int sum = 0;
        for (Interval interval : intervals) {
            sum += interval.getDistance();
        }
        Assert.assertEquals(12, sum);
    }
}
