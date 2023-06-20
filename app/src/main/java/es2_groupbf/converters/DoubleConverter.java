package es2_groupbf.converters;

import com.opencsv.bean.AbstractBeanField;

public class DoubleConverter<T> extends AbstractBeanField<T, T> {
    @Override
    public Object convert(String value) {
        return (value.equalsIgnoreCase("NULL")) ? 0.0 : Double.parseDouble(value.replace(',', '.'));
    }
}
