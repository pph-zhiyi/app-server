package com.pph.demo.java8.lambda.stream.join;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * @author: pph
 * @date: 2019/11/27 17:47
 * @description:
 */
@Data
public class StringCombiner {

    private StringBuilder builder = new StringBuilder();

    private String prefix;

    private String delimiter;

    private String suffix;

    public StringCombiner(String delimiter, String prefix, String suffix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringCombiner add(String element) {
        if (StringUtils.isBlank(builder.toString())) {
            builder.append(prefix);
        } else {
            if (!StringUtils.equals(prefix, builder.toString())) {
                builder.delete(builder.length() - suffix.length(), builder.length());
                builder.append(delimiter);
            } else {
                builder.append(delimiter);
            }
        }
        builder.append(element);
        builder.append(suffix);
        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        builder.append(other.builder);
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
