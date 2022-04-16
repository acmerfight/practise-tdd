/**
 * @(#)InsufficientArgumentsException.java, Apr 16, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

/**
 * @author yaogangqiang
 */
public class InsufficientArgumentsException extends RuntimeException {

    private final String option;

    public InsufficientArgumentsException(String option) {
        super(option);
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}