package com.cherrow.number;

public class NumberUtils {

    private NumberUtils() {
    }

    public static boolean isCreatable(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        final int start = chars[0] == '-' || chars[0] == '+' ? 1 : 0;
        //以0 +0或-0开头，不是小数，以0开头不止一位，+0或-0不止两位
        if (sz > start + 1 && chars[start] == '0' && !str.contains(".")) { // leading 0, skip if is a decimal number
            //0后面必须跟着x
            if (chars[start + 1] == 'x' || chars[start + 1] == 'X') { // leading 0x/0X
                int i = start + 2;
                //0x or -0x invalid
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    //超出十六进制范围 invalid
                    // 0 asc码为48 9为57 a为97 f为102 A为65 F为70
                    // <=0; >=9&&<=A; >=F&&<=a; >=f
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
                //0后面没有x，以0开头而且后面跟的是数字，有可能是8进制
            } else if (Character.isDigit(chars[start + 1])) {
                // leading 0, but not hex, must be octal
                int i = start + 1;
                //每一位都不能超过7
                for (; i < chars.length; i++) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || i < sz + 1 && allowSigns && !foundDigit) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    // 有两个小数点 或者指数E后面带了小数点 invalid
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    //两个指数 E
                    return false;
                }
                if (!foundDigit) {
                    //指数E前面必须是数字
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    //符号前必须是指数e
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                // 除了. e E + -之外的字符都是invalid
                return false;
            }
            i++;
        }
        //最后一个字符
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                //是数字
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                //最后一个不能是e
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    //最后一个是小数点，且前面有了小数点或者指数e
                    return false;
                }
                // single trailing decimal point after non-exponent is ok
                // 仅有一个小数点在末尾，且没有指数e ok
                return foundDigit;
            }
            if (!allowSigns
                    && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                // 最后为进制标识且前面为数字
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                // not allowing L with an exponent or decimal point
                // foundDigit=false
                // foundDigit=true hasExp=true
                // foundDigit=true hasExp=false hasDecPoint=true
                // foundDigit=true hasExp=false hasDecPoint=false
                return foundDigit && !hasExp && !hasDecPoint;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        // 只有当参数为 “+” 或者 “-” 时才进入这行
        return !allowSigns && foundDigit;
    }
}
