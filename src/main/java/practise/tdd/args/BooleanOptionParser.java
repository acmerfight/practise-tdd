/**
 * @(#)BooleanOptionParser.java, Apr 12, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

import java.util.List;

import static practise.tdd.args.SingleValuedOptionParser.values;

/**
 * @author yaogangqiang
 */
class BooleanOptionParser implements OptionParser<Boolean> {

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        return values(arguments, option, 0).isPresent();
    }
}