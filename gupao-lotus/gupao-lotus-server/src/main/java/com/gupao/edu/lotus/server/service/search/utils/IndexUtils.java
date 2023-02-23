package com.gupao.edu.lotus.server.service.search.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gupao.edu.lotus.server.service.search.annotation.DocId;
import com.gupao.edu.lotus.server.service.search.annotation.IndexSettings;
import com.gupao.edu.lotus.server.service.search.annotation.Mapping;
import com.gupao.edu.lotus.server.service.search.enums.DataType;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 索引工具类
 **/
@Slf4j
public class IndexUtils {

    private static Map<Class,String> docIdMap = new ConcurrentHashMap<>();

    /**
     * 获取索引名、主分片、备份分片数的配置
     * @param indexClass
     * @return
     */
    public static SettingData getSettings(Class<?> indexClass){
        IndexSettings annotation = indexClass.getAnnotation(IndexSettings.class);
        SettingData settingData = new SettingData();
        if(annotation != null){
            settingData.setIndexName(annotation.name());
            settingData.setNumberOfReplicas(annotation.number_of_replicas());
            settingData.setNumberOfShards(annotation.number_of_shards());
        }
        return settingData;
    }

    /**
     * 获取ID
     * @param obj
     * @return
     */
    public static String getDocId(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for(Field f : fields){
                f.setAccessible(true);
                DocId docId = f.getAnnotation(DocId.class);
                if(docId != null){
                    Object value = f.get(obj);
                    if(value == null){
                        return null;
                    }else{
                        return value.toString();
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置ID
     * @return
     */
    public static <T> void setDocId(Class<T> indexClass,T t,String _id){
        try{
            if(StringUtils.isEmpty(_id)){
                return;
            }
            Field field = null;
            if(docIdMap.containsKey(indexClass)){
                field = indexClass.getDeclaredField(docIdMap.get(indexClass));
            }
            if (field==null){
                for (int i = 0; i < indexClass.getDeclaredFields().length; i++) {
                    field = indexClass.getDeclaredFields()[i];
                    if(field.getAnnotation(DocId.class) != null){
                        docIdMap.put(indexClass,field.getName());
                        break;
                    }
                }
            }
            if (field==null){
                return;
            }
            field.setAccessible(true);
            Class<?> type = field.getType();
            if (type == String.class){
                field.set(t, _id);
            } else if (type == Integer.class){
                field.set(t, Integer.valueOf(_id));
            } else if (type == Long.class){
                field.set(t, Long.valueOf(_id));
            }
        }catch (Exception e){
            log.error("setDocId error!",e);
        }
    }

    /**
     * 获取配置于Field上的mapping信息，如果未配置注解，则给出默认信息
     * @param field
     * @return
     */
    public static MappingData getMappingData(Field field){
        if(field == null){
            return null;
        }
        field.setAccessible(true);
        MappingData mappingData = new MappingData();
        String fieldName = StringUtils.camelToUnderline(field.getName());
        mappingData.setFieldName(fieldName);
        Mapping mapping = field.getAnnotation(Mapping.class);
        if(mapping != null){
            String name = mapping.name();
            if (StringUtils.isNotEmpty(name)){
                mappingData.setFieldName(name);
            }
            DataType type = mapping.type();
            mappingData.setType(type);
            mappingData.setAnalyzer(mapping.analyzer());
            mappingData.setSearchAnalyzer(mapping.search_analyzer());
            mappingData.setFormat(mapping.format());
        }
        return mappingData;
    }

    public static List<MappingData> getMappingData(Class<?> indexClass){
        Field[] fields = indexClass.getDeclaredFields();
        List<MappingData> mappingDataList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            DocId annotation = field.getAnnotation(DocId.class);
            if (annotation!=null){
                continue;
            }
            if(field.getName().equals("serialVersionUID")){
                continue;
            }
            mappingDataList.add(getMappingData(field));;
        }
        return mappingDataList;
    }
}
