package com.dtc.zkService;

import com.google.common.collect.Maps;
import org.springframework.beans.propertyeditors.ClassArrayEditor;
import org.springframework.cglib.core.ClassInfo;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanInfo {

    private  Class clazz;

    private Map<String, String> stringStringMap= Maps.newHashMap();

    private Map<String, Long> stringLongMap= Maps.newHashMap();

    private Map<String, Integer> stringIntegerMap= Maps.newHashMap();

    private Map<String, Short> stringShortMap= Maps.newHashMap();

    private Map<String, Boolean> stringBooleanMap= Maps.newHashMap();

    private Map<String, Byte> stringByteMap= Maps.newHashMap();

    private Map<String, Character> stringCharacterMap= Maps.newHashMap();

    private Map<String, Float> stringFloatMap= Maps.newHashMap();

    private Map<String, Double> stringDoubleMap= Maps.newHashMap();

    private Map<String, BigDecimal> stringBigDecimalMap= Maps.newHashMap();

    private Map<String, BeanInfo> stringListMap= Maps.newHashMap();

    private Map<String, Map<String, BeanInfo>> stringMapMap= Maps.newHashMap();

    private Map<String, BeanInfo> stringBeanInfoMap= Maps.newHashMap();

    public BeanInfo() {
    }

    public BeanInfo(Class clazz) {
        this.clazz = clazz;
        buildBeanIf(clazz);
        
    }



    public BeanInfo(Method method) {
        this.clazz = method.getClass();
        buildMethodInfo(method);
    }

    public Class getClazz() {
        return clazz;
    }

    public BeanInfo setClazz(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    public Map<String, String> getStringStringMap() {
        return stringStringMap;
    }

    public BeanInfo setStringStringMap(Map<String, String> stringStringMap) {
        this.stringStringMap = stringStringMap;
        return this;
    }

    public Map<String, Long> getStringLongMap() {
        return stringLongMap;
    }

    public BeanInfo setStringLongMap(Map<String, Long> stringLongMap) {
        this.stringLongMap = stringLongMap;
        return this;
    }

    public Map<String, Integer> getStringIntegerMap() {
        return stringIntegerMap;
    }

    public BeanInfo setStringIntegerMap(Map<String, Integer> stringIntegerMap) {
        this.stringIntegerMap = stringIntegerMap;
        return this;
    }

    public Map<String, Short> getStringShortMap() {
        return stringShortMap;
    }

    public BeanInfo setStringShortMap(Map<String, Short> stringShortMap) {
        this.stringShortMap = stringShortMap;
        return this;
    }

    public Map<String, Boolean> getStringBooleanMap() {
        return stringBooleanMap;
    }

    public BeanInfo setStringBooleanMap(Map<String, Boolean> stringBooleanMap) {
        this.stringBooleanMap = stringBooleanMap;
        return this;
    }

    public Map<String, Byte> getStringByteMap() {
        return stringByteMap;
    }

    public BeanInfo setStringByteMap(Map<String, Byte> stringByteMap) {
        this.stringByteMap = stringByteMap;
        return this;
    }

    public Map<String, Character> getStringCharacterMap() {
        return stringCharacterMap;
    }

    public BeanInfo setStringCharacterMap(Map<String, Character> stringCharacterMap) {
        this.stringCharacterMap = stringCharacterMap;
        return this;
    }

    public Map<String, Float> getStringFloatMap() {
        return stringFloatMap;
    }

    public BeanInfo setStringFloatMap(Map<String, Float> stringFloatMap) {
        this.stringFloatMap = stringFloatMap;
        return this;
    }

    public Map<String, Double> getStringDoubleMap() {
        return stringDoubleMap;
    }

    public BeanInfo setStringDoubleMap(Map<String, Double> stringDoubleMap) {
        this.stringDoubleMap = stringDoubleMap;
        return this;
    }

    public Map<String, BigDecimal> getStringBigDecimalMap() {
        return stringBigDecimalMap;
    }

    public BeanInfo setStringBigDecimalMap(Map<String, BigDecimal> stringBigDecimalMap) {
        this.stringBigDecimalMap = stringBigDecimalMap;
        return this;
    }

    public Map<String,BeanInfo> getStringListMap() {
        return stringListMap;
    }

    public BeanInfo setStringListMap(Map<String, BeanInfo> stringListMap) {
        this.stringListMap = stringListMap;
        return this;
    }

    public Map<String, Map<String, BeanInfo>> getStringMapMap() {
        return stringMapMap;
    }

    public BeanInfo setStringMapMap(Map<String, Map<String, BeanInfo>> stringMapMap) {
        this.stringMapMap = stringMapMap;
        return this;
    }

    public Map<String, BeanInfo> getStringBeanInfoMap() {
        return stringBeanInfoMap;
    }

    public BeanInfo setStringBeanInfoMap(Map<String, BeanInfo> stringBeanInfoMap) {
        this.stringBeanInfoMap = stringBeanInfoMap;
        return this;
    }


    public  BeanInfo buildMethodInfo(Method method) {
        BeanInfo beanInfo=new BeanInfo();

        for (Parameter parameter : method.getParameters()) {
            if ( parameter.getType().equals(String.class)) {
                beanInfo.getStringStringMap().put(parameter.getName(), "");
                continue;
            }
            if ( parameter.getType().equals(Integer.class)) {
                beanInfo.getStringIntegerMap().put(parameter.getName(), -1);
                continue;
            }
            if ( parameter.getType().equals(Long.class)) {
                beanInfo.getStringLongMap().put(parameter.getName(),-1L);
                continue;
            }
            if ( parameter.getType().equals(Short.class)) {
                beanInfo.getStringShortMap().put(parameter.getName(), (short)-1);
                continue;
            }
            if ( parameter.getType().equals(Double.class)) {
                beanInfo.getStringDoubleMap().put(parameter.getName(), -1D);
                continue;
            }
            if ( parameter.getType().equals(Float.class)) {
                beanInfo.getStringFloatMap().put(parameter.getName(), -1f);
                continue;
            }
            if ( parameter.getType().equals(Byte.class)) {
                beanInfo.getStringByteMap().put(parameter.getName(), (byte)-1);
                continue;
            }
            if ( parameter.getType().equals(Character.class)) {
                beanInfo.getStringCharacterMap().put(parameter.getName(), (char)-1);
                continue;
            }
            if ( parameter.getType().equals(Boolean.class)) {
                beanInfo.getStringBooleanMap().put(parameter.getName(), Boolean.FALSE);
            }
            if ( parameter.getType().equals(List.class)) {
                ParameterizedType parameterizedType= (ParameterizedType) parameter.getParameterizedType();

                beanInfo.getStringListMap().put(parameter.getName(),new BeanInfo(parameterizedType.getActualTypeArguments()[0].getClass()));
                continue;
            }
            if ( parameter.getType().equals(Map.class)) {
                ParameterizedType parameterizedType= (ParameterizedType) parameter.getParameterizedType();
                HashMap<String,BeanInfo> map = new HashMap<>();
                map.put("key",new BeanInfo(parameterizedType.getActualTypeArguments()[0].getClass()));
                map.put("value",new BeanInfo(parameterizedType.getActualTypeArguments()[1].getClass()));
                beanInfo.getStringMapMap().put(parameter.getName(),map);
                continue;
            }
            beanInfo.getStringBeanInfoMap().put(parameter.getName(),new BeanInfo(parameter.getParameterizedType().getClass()));

        }



        return beanInfo;
    }


    private void buildBeanIf(Class clazz) {
        BeanInfo beanInfo = new BeanInfo();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(String.class)) {
                beanInfo.getStringStringMap().put(field.getName(), "");
                continue;
            }
            if (field.getType().equals(Integer.class)) {
                beanInfo.getStringIntegerMap().put(field.getName(), -1);
                continue;
            }
            if (field.getType().equals(Long.class)) {
                beanInfo.getStringLongMap().put(field.getName(), -1L);
                continue;
            }
            if (field.getType().equals(Short.class)) {
                beanInfo.getStringShortMap().put(field.getName(), (short) -1);
                continue;
            }
            if (field.getType().equals(Double.class)) {
                beanInfo.getStringDoubleMap().put(field.getName(), -1D);
                continue;
            }
            if (field.getType().equals(Float.class)) {
                beanInfo.getStringFloatMap().put(field.getName(), -1f);
                continue;
            }
            if (field.getType().equals(Byte.class)) {
                beanInfo.getStringByteMap().put(field.getName(), (byte) -1);
                continue;
            }
            if (field.getType().equals(Character.class)) {
                beanInfo.getStringCharacterMap().put(field.getName(), (char) -1);
                continue;
            }
            if (field.getType().equals(Boolean.class)) {
                beanInfo.getStringBooleanMap().put(field.getName(), Boolean.FALSE);
            }
          /*  if ( field.getType().equals(List.class)) {
                ParameterizedType fieldizedType= (ParameterizedType) field.get;

                beanInfo.getStringListMap().put(field.getName(),new BeanInfo(fieldizedType.getActualTypeArguments()[0].getClass()));
                continue;
            }
            if ( field.getType().equals(Map.class)) {
               // ParameterizedType fieldizedType= (ParameterizedType) field.getParameterizedType();
                HashMap<String,BeanInfo> map = new HashMap<>();
                map.put("key",new BeanInfo(fieldizedType.getActualTypeArguments()[0].getClass()));
                map.put("value",new BeanInfo(fieldizedType.getActualTypeArguments()[1].getClass()));
                beanInfo.getStringMapMap().put(field.getName(),map);
                continue;
            }
            beanInfo.getStringBeanInfoMap().put(field.getName(),new BeanInfo(field.getParameterizedType().getClass()));*/
        }
    }
}
