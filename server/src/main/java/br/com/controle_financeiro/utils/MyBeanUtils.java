package br.com.controle_financeiro.utils;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

import org.springframework.beans.BeanUtils;

public class MyBeanUtils {

    public static String[] getNullPropertyNames(Object source) {
        if (source == null) {
            throw new IllegalArgumentException("Source object must not be null");
        }

        return Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                .filter(pd -> {
                    try {
                        return pd.getReadMethod() != null && pd.getReadMethod().invoke(source) == null;
                    } catch (Exception e) {
                        throw new RuntimeException("Erro ao acessar propriedades: " + pd.getName(), e);
                    }
                })
                .map(PropertyDescriptor::getName)
                .toArray(String[]::new);
    }

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
}
