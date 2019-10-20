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
    public void should_return_false_when_str_is_hex_but_less_than_0() {
        //then
        assertFalse(NumberUtils.isCreatable("0X/"));
    }

    @Test
    public void should_return_false_when_str_is_hex_but_greater_than_9_and_less_than_A() {
        //then
        assertFalse(NumberUtils.isCreatable("0X@"));
    }

    @Test
    public void should_return_false_when_str_is_hex_but_greater_than_F_and_less_than_a() {
        //then
        assertFalse(NumberUtils.isCreatable("0XG"));
    }

    @Test
    public void should_return_false_when_str_is_hex_but_greater_than_f() {
        //then
        assertFalse(NumberUtils.isCreatable("0Xg"));
    }

    @Test
    public void should_return_true_when_str_is_valid_hex_ends_in_0_and_9() {
        //then
        assertTrue(NumberUtils.isCreatable("-0X1"));
    }

    @Test
    public void should_return_true_when_str_is_valid_hex_ends_in_a_and_f() {
        //then
        assertTrue(NumberUtils.isCreatable("-0Xb"));
    }

    @Test
    public void should_return_true_when_str_is_valid_hex_ends_in_A_and_F() {
        //then
        assertTrue(NumberUtils.isCreatable("-0XB"));
    }

    @Test
    public void should_return_false_when_str_begin_with_0_but_no_x() {
        //then
        assertFalse(NumberUtils.isCreatable("0*"));
    }

    @Test
    public void should_return_false_when_str_is_octal_but_greater_than_7() {
        //then
        assertFalse(NumberUtils.isCreatable("02348"));
    }

    @Test
    public void should_return_false_when_str_is_octal_but_less_than_0() {
        //then
        assertFalse(NumberUtils.isCreatable("0234/"));
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
    public void should_return_false_when_str_has_no_exponent_before_plus_sign() {
        //then
        assertFalse(NumberUtils.isCreatable("32+3"));
    }

    @Test
    public void should_return_false_when_str_has_no_exponent_before_minor_sign() {
        //then
        assertFalse(NumberUtils.isCreatable("32-3"));
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
    public void should_return_false_when_str_ends_in_exponent_e() {
        //then
        assertFalse(NumberUtils.isCreatable("-1.23e"));
    }

    @Test
    public void should_return_false_when_str_ends_in_exponent_E() {
        //then
        assertFalse(NumberUtils.isCreatable("-1.23E"));
    }

    @Test
    public void should_return_false_when_str_contains_exponent_and_ends_in_dec_point() {
        //then
        assertFalse(NumberUtils.isCreatable("1e23."));
    }

    @Test
    public void should_return_false_when_str_contains_dec_point_and_ends_in_dec_point() {
        //then
        assertFalse(NumberUtils.isCreatable("1.23."));
    }

    @Test
    public void should_return_true_when_str_has_no_exponent_and_ends_in_single_dec_point() {
        //then
        assertTrue(NumberUtils.isCreatable("123."));
    }

    @Test
    public void should_return_true_when_str_ends_in_d_which_is_after_digit() {
        //then
        assertTrue(NumberUtils.isCreatable("1.5E88d"));
    }

    @Test
    public void should_return_true_when_str_ends_in_D_which_is_after_digit() {
        //then
        assertTrue(NumberUtils.isCreatable("1.5E88D"));
    }

    @Test
    public void should_return_true_when_str_ends_in_f_which_is_after_digit() {
        //then
        assertTrue(NumberUtils.isCreatable("1.5E88f"));
    }

    @Test
    public void should_return_true_when_str_ends_in_F_which_is_after_digit() {
        //then
        assertTrue(NumberUtils.isCreatable("1.5E88F"));
    }

    @Test
    public void should_return_false_when_str_ends_in_wrong_char_and_allow_signs_false() {
        //then
        assertFalse(NumberUtils.isCreatable("154e+a"));
    }

    @Test
    public void should_return_false_when_str_ends_in_any_and_allow_signs() {
        //then
        assertFalse(NumberUtils.isCreatable("153eD"));
    }

    @Test
    public void should_return_false_when_str_ends_in_l_and_find_digit_is_false() {
        //then
        assertFalse(NumberUtils.isCreatable("123e+l"));
    }

    @Test
    public void should_return_false_when_str_ends_in_l_and_find_digit_and_has_exp() {
        //then
        assertFalse(NumberUtils.isCreatable("15E+5l"));
    }

    @Test
    public void should_return_false_when_str_ends_in_L_find_digit_and_no_exp_but_dec_point() {
        //then
        assertFalse(NumberUtils.isCreatable("1.23L"));
    }

    @Test
    public void should_return_true_when_str_ends_in_L_and_no_dec_point_and_no_exp() {
        //then
        assertTrue(NumberUtils.isCreatable("123L"));
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

    @Test
    public void should_return_true_when_str_is_single_sign() {
        //then
        assertFalse(NumberUtils.isCreatable("-"));
    }
}