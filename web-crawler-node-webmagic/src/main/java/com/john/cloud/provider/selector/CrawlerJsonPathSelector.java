package com.john.cloud.provider.selector;


import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

import us.codecraft.webmagic.selector.Selector;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by cjl20 on 2017/5/15.
 */

public class CrawlerJsonPathSelector implements Selector {
    private String jsonPathStr;
    private JsonPath jsonPath;

    public CrawlerJsonPathSelector(String jsonPathStr) {
        this.jsonPathStr = jsonPathStr;
        this.jsonPath = JsonPath.compile(this.jsonPathStr, new Filter[0]);
    }

    public String select(String text) {
        Object object = this.jsonPath.read(text);
        if (object == null) {
            return null;
        } else {
            if (object instanceof List) {
                List list = (List) object;
                if (list != null && list.size() > 0) {
                    return list.iterator().next().toString();
                }
            }

            return object.toString();
        }
    }

    public List<String> selectList(String text) {
        List<String> list = new ArrayList();
        Object object = this.jsonPath.read(text);
        if (object == null) {
            return list;
        } else {
            if (object instanceof List) {
                List<Object> items = (List) object;
                Iterator var5 = items.iterator();

                while (var5.hasNext()) {
                    Object item = var5.next();
                    list.add(String.valueOf(item));
                }
            } else {
                list.add(String.valueOf(object));
            }

            return list;
        }
    }

    public <T> List<T> selectList(String text, Class<T> c) {
        List<T> list = new ArrayList();
        Object object = this.jsonPath.read(text);
        try {
            List<String> fs = new ArrayList<>();
            for (Field f : c.getDeclaredFields()) {
                fs.add(f.getName());
            }
            if (object != null) {
                if (object instanceof List) {
                    List<Object> items = (List) object;
                    Iterator var5 = items.iterator();
                    while (var5.hasNext()) {
                        T model = c.newInstance();
                        Map item = (Map) var5.next();
                        for (Object key : item.keySet()) {
                            if (fs.contains(key)) {
                                Field f = c.getDeclaredField(key.toString());
                                f.setAccessible(true);
                                f.set(model, item.get(key));
                            }
                        }
                        list.add(model);
                    }
                } else {
//                    list.add(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
