package es2_groupbf.converters;

import com.opencsv.bean.AbstractBeanField;
import es2_groupbf.utils.Utils;

import java.text.ParseException;

public class DateConverter<T> extends AbstractBeanField<T, T> {
    @Override
    public Object convert(String value) {
        try {
            return (value.equalsIgnoreCase("NULL")) ? Utils.formatDate("01/01/1970") : Utils.formatDate(value);
        } catch (ParseException exception) {
            throw new RuntimeException(exception);
        }
    }
}
