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

    private StringOptionParser() {
        super(String::valueOf);
    }

    public static StringOptionParser createStringOptionParser() {
        return new StringOptionParser();
    }
}