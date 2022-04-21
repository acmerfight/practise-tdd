/**
 * @(#)IllegalValueException.java, Apr 21, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

/**
 * @author yaogangqiang
 */
public class IllegalValueException extends RuntimeException {

    private final String optionValue;
    private final String value;

    public IllegalValueException(String optionValue, String value) {
        this.optionValue = optionValue;
        this.value = value;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public String getValue() {
        return value;
    }
}