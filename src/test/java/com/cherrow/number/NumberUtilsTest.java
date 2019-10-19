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

    @Test
    public void should_return_false_when_str_contain_x_but_not_hex() {
        //then
        assertFalse(NumberUtils.isCreatable("0x"));
    }

    @Test
    public void should_return_false_when_str_is_hex_but_out_scope() {
        //then
        assertFalse(NumberUtils.isCreatable("0XG"));
    }

    @Test
    public void should_return_true_when_str_is_valid_hex() {
        //then
        assertTrue(NumberUtils.isCreatable("-0Xb"));
    }

    @Test
    public void should_return_false_when_str_is_octal_but_out_scope() {
        //then
        assertFalse(NumberUtils.isCreatable("02348"));
    }

    @Test
    public void should_return_true_when_str_is_valid_octal() {
        //then
        assertTrue(NumberUtils.isCreatable("+074"));
    }

    @Test
    public void should_return_false_when_str_has_more_than_one_dec_point() {
        //then
        assertFalse(NumberUtils.isCreatable("0.2.3"));
    }

    @Test
    public void should_return_false_when_str_has_dec_point_after_exponent() {
        //then
        assertFalse(NumberUtils.isCreatable("2E.3"));
    }

    @Test
    public void should_return_false_when_str_has_more_than_one_exponent() {
        //then
        assertFalse(NumberUtils.isCreatable("2E2e3"));
    }

    @Test
    public void should_return_false_when_str_has_no_digit_before_exponent() {
        //then
        assertFalse(NumberUtils.isCreatable(".e3"));
    }

    @Test
    public void should_return_false_when_str_has_no_exponent_before_sign() {
        //then
        assertFalse(NumberUtils.isCreatable("32+3"));
    }

    @Test
    public void should_return_false_when_str_has_invalid_letter_inside() {
        //then
        assertFalse(NumberUtils.isCreatable("3f3"));
    }

    @Test
    public void should_return_true_when_str_is_valid_and_ends_in_digit() {
        //then
        assertTrue(NumberUtils.isCreatable("-1.23"));
    }

    @Test
    public void should_return_false_when_str_ends_in_exponent() {
        //then
        assertFalse(NumberUtils.isCreatable("-1.23e"));
    }

    @Test
    public void should_return_false_when_str_contains_exponent_and_ends_in_dec_point() {
        //then
        assertFalse(NumberUtils.isCreatable("1e23."));
    }

    @Test
    public void should_return_true_when_str_has_no_exponent_and_ends_in_single_dec_point() {
        //then
        assertTrue(NumberUtils.isCreatable("123."));
    }


    @Test
    public void should_return_true_when_str_ends_in_F_which_is_after_digit() {
        //then
        assertTrue(NumberUtils.isCreatable("1.5E88F"));
    }

    @Test
    public void should_return_false_when_str_ends_in_D_with_no_digit_before() {
        //then
        assertFalse(NumberUtils.isCreatable("15E+D"));
    }

    @Test
    public void should_return_false_when_str_contains_exponent_and_ends_in_l() {
        //then
        assertFalse(NumberUtils.isCreatable("15E+5l"));
    }

    @Test
    public void should_return_false_when_str_contains_dec_point_and_ends_in_L() {
        //then
        assertFalse(NumberUtils.isCreatable("1.23L"));
    }

    @Test
    public void should_return_false_when_last_char_is_illegal() {
        //then
        assertFalse(NumberUtils.isCreatable("1.23a"));
    }

    @Test
    public void should_return_false_when_str_is_single_sign() {
        //then
        assertFalse(NumberUtils.isCreatable("+"));
    }
}