package es2_groupbf.converters;

import com.opencsv.bean.AbstractBeanField;

public class PaymentMethodConverter<T> extends AbstractBeanField<T, T> {
    @Override
    public Object convert(String value) {
        if (value.equalsIgnoreCase("NULL")) return 0;
        return Integer.parseInt(value) < 1 || Integer.parseInt(value) > 4 ? 0 : Integer.parseInt(value);
    }
}
