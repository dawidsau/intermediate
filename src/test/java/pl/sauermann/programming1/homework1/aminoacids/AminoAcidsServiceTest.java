package pl.sauermann.programming1.homework1.aminoacids;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AminoAcidsServiceTest {

    @Test
    void shouldCompareTwoAminoAcids() {
        Boolean result = AminoAcidsService.changePossible("ABCD", "DCBA");
        Boolean resultOfDifferentAcids = AminoAcidsService.changePossible("ABCD", "DCBK");

        assertEquals(true, result);
        assertEquals(false, resultOfDifferentAcids);
    }
}