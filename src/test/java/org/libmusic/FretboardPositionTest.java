package org.libmusic;

import org.junit.Assert;
import org.junit.Test;

public class FretboardPositionTest {
    @Test
    public void testEqualsAndHashCode() {
        FretboardPosition AX1 = new FretboardPosition(4, 5);
        FretboardPosition AX2 = new FretboardPosition(4, 5);
        FretboardPosition BX1 = new FretboardPosition(3, 5);
        FretboardPosition AY1 = new FretboardPosition(4, 3);

        Assert.assertEquals(AX1, AX2);
        Assert.assertEquals(AX2, AX1);
        Assert.assertFalse(AX1.equals(BX1));
        Assert.assertFalse(BX1.equals(AX1));
        Assert.assertFalse(AX1.equals(AY1));
        Assert.assertFalse(AY1.equals(AX1));

        Assert.assertEquals(AX1.hashCode(), AX2.hashCode());
        Assert.assertFalse(AX1.hashCode() == BX1.hashCode());
        Assert.assertFalse(AX1.hashCode() == AY1.hashCode());
    }
}
