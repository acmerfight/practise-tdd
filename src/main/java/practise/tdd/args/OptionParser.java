/**
 * @(#)OptionParser.java, Apr 12, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

import java.util.List;

/**
 * @author yaogangqiang
 */
interface OptionParser<T> {
    T parse(List<String> arguments, Option option);
}