/**
 * @(#)StringOptionParser.java, Apr 12, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

/**
 * @author yaogangqiang
 */
class StringOptionParser extends IntOptionParser {

    @Override
    protected Object parseValue(String value) {
        return String.valueOf(value);
    }
}