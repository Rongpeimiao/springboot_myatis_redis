package com.springboot.rong.until.jsonResult;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.tools.javac.util.List;

/**
 * <p>Description: 判断对象是否为空，进一步判断对象中的属性是否都为空 </p>
 */
@Component
public class CheckObjectIsNullUtils {
	 
	private JsonUtil jsonUtil;
	
	 
	public Result getObjCheckIsNullResult(Object object){
		boolean flag = objCheckIsNull(object);//判断对象是否为空
		Result result=new Result();
        if(flag==false){
        	result.setData(jsonUtil.object2json(object));
        }else{
        	result.setData(null);
        }
		
		return result;
		
	}
	
	 
	public  HashMap<String, Object> getObjCheckIsNullResultMap(Object object){
		boolean flag = objCheckIsNull(object);//判断对象是否为空
		HashMap<String, Object>  map = new HashMap<String, Object>();
       if(flag==false){
 	      map.put("code", "200");
 	      map.put("message", null);
 	      map.put("data", jsonUtil.object2json(object));
 	      map.put("success",true);
       }else{
    	   map.put("code", "300");
  	       map.put("message", null);
  	       map.put("data", null);
  	       map.put("success",false);
       }
		return map;
		
	}
	public static void main(String[] args) {
		 
	}
	
    /**
     * 判断对象是否为空，且对象的所有属性都为空
     * ps: boolean类型会有默认值false 判断结果不会为null 会影响判断结果
     *     序列化的默认值也会影响判断结果
     * @param object
     * @return
     */
    public  boolean objCheckIsNull(Object object){
    	if(object==null){return true;}
        Class clazz = (Class)object.getClass(); // 得到类对象
        Field fields[] = clazz.getDeclaredFields(); // 得到所有属性
        boolean flag = true; //定义返回结果，默认为true
        for(Field field : fields){
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(object); //得到属性值
                Type fieldType =field.getGenericType();//得到属性类型
                String fieldName = field.getName(); // 得到属性名
                System.out.println("属性类型："+fieldType+",属性名："+fieldName+",属性值："+fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(fieldValue != null){  //只要有一个属性值不为null 就返回false 表示对象不为null
                flag = false;
                break;
            }
        }
        return flag;
    }
}
