package com.cherrow.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberUtilsTest {
    @Test
    public void should_return_false_when_str_is_null() {
        //then
        assertFalse(NumberUtils.isCreatable(null));
    }

    @Test
    public void should_return_false_when_str_length_is_0() {
        //then
        assertFalse(NumberUtils.isCreatable(""));
    }
}