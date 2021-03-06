package com.cerner.ccl.testing.maven.ccl.reports.common;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * Unit tests for {@link CoveredStatus}.
 *
 * @author Joshua Hyde
 *
 */

public class CoveredStatusTest {
    /**
     * All statuses should be resolvable by their own character representation.
     */
    @Test
    public void testForCharacterRepresentation() {
        for (CoveredStatus status : CoveredStatus.values()) {
            assertThat(status).isEqualTo(CoveredStatus.forCharacterRepresentation(status.getCharacterRepresentation()));
        }
    }

    /**
     * Resolution of covered status by character representation should be case-sensitive to ensure that the proper
     * format of the XML is being followed.
     */
    @Test
    public void testForCharacterRepresentationCaseSensitive() {
        final String toSearch = StringUtils.swapCase(CoveredStatus.COVERED.getCharacterRepresentation());
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            CoveredStatus.forCharacterRepresentation(toSearch);
        });
        assertThat(e.getMessage()).isEqualTo("Unknown character representation: " + toSearch);
    }

    /**
     * If the given character representation is unknown, then the lookup should fail.
     */
    @Test
    public void testForCharacterRepresentationUnknown() {
        final String toSearch = getClass().getCanonicalName();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            CoveredStatus.forCharacterRepresentation(toSearch);
        });
        assertThat(e.getMessage()).isEqualTo("Unknown character representation: " + toSearch);
    }
}
