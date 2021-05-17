package io.github.quickmsg.common.core.utils.validate;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;


/**
 * @ClassName: ObjectFieldValidate
 * @Description:
 * @Date: 2021/3/12 10:27
 * @Author: songjg
 */
@Slf4j
public class ObjectFieldValidate {

        /**
         *
         * @Title: validateField
         * @Description: 检查指定对象的属性值是否为空
         * @param: @param object
         * @param: @param exclField
         * @param: @return
         * @return: boolean
         * @throws
         */
        public static boolean validateFieldIsEmpty(Object object){
            boolean target = false;
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                    if (f.get(object) == null) {
                        target = true;
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    target = true;
                    log.error("对象属性解析异常" + e.getMessage());
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    target = true;
                    log.error("对象属性解析异常" + e.getMessage());
                }
            }

            return target;
        }
        /**
         *
         * @Title: validateField
         * @Description: 检查指定对象的属性值是否为空，排除指定的属性值
         * @param: @param object
         * @param: @param exclField
         * @param: @return
         * @return: boolean
         * @throws
         */
        public static boolean validateFieldIsEmpty(Object object, List exclField){
            boolean target = false;
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    String name = f.getName();
                    // 判断属性名称是否在排除属性值中
                    if(!exclField.contains(name)){
                        //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                        if (f.get(object) == null) {
                            target = true;
                            break;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    target = true;
                    log.error("对象属性解析异常" + e.getMessage());
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    target = true;
                    log.error("对象属性解析异常" + e.getMessage());
                }
            }

            return target;
        }
}

