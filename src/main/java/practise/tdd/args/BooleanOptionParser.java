/**
 * @(#)BooleanOptionParser.java, Apr 12, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package practise.tdd.args;

import java.util.List;

/**
 * @author yaogangqiang
 */
class BooleanOptionParser implements OptionParser<Boolean> {

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        List<String> values = SingleValuedOptionParser.getValues(arguments, index);
        if (values.size() > 0){
            throw new TooManyArgumentsException(option.value());
        }
        return index != -1;
    }
}