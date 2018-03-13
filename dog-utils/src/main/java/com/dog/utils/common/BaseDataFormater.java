package com.dog.utils.common;

import com.dog.utils.common.formater.ValueFormater;

import java.math.BigInteger;
import java.sql.Timestamp;

public class BaseDataFormater implements ValueFormater {
    @Override
    public Object format(String key, Object value) {
        if (value instanceof Timestamp) {
            // 自动去除时间格式后缀“.0”
            String val = value.toString().substring(0, value.toString().indexOf("."));
            return val;
        }else if (value instanceof Long) {
            //解决出参long精度失真问题，转换为String
            String val = String.valueOf(value);
            return val;
        }else if (value instanceof BigInteger) {
            //解决出参BigInteger精度失真问题，转换为String
            String val = String.valueOf(value);
            return val;
        }
        else{
            return value;
        }
    }
}
