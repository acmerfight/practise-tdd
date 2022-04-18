/**
 * @(#)IllegalOptionException.java, Apr 18, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

/**
 * @author yaogangqiang
 */
public class IllegalOptionException extends RuntimeException{
    private final String parameter;

    public IllegalOptionException(String option) {
        super(option);
        this.parameter = option;
    }

    public String getParameter() {
        return parameter;
    }
}