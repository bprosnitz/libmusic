package org.libmusic;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

public class ScaleVoicingTest {
    @Test
    public void testMajorScaleVoicing(){
        Scale scale = DiatonicScale.MajorScale;
        Note rootNote = new Note("E");
        ScaleVoicing scaleVoicing = new ScaleVoicing(scale, rootNote);
        Assert.assertEquals(Sets.newHashSet(new Note("E"),
                new Note("F#"), new Note("G#"), new Note("A"), new Note("B"),
                new Note("C#"), new Note("D#")), scaleVoicing.getNotes());
    }

    @Test
    public void testNaturalMinorScaleVoicing(){
        Scale scale = DiatonicScale.NaturalMinorScale;
        Note rootNote = new Note("D");
        ScaleVoicing scaleVoicing = new ScaleVoicing(scale, rootNote);
        Assert.assertEquals(Sets.newHashSet(new Note("D"), new Note("E"),
                new Note("F"), new Note("G"), new Note("A"), new Note("Bb"),
                new Note("C")), scaleVoicing.getNotes());
    }
}
