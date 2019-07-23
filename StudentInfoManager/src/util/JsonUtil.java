package util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

/**
 * @author Administrator
 * @date 2019/7/17 22:43:31
 * @description
 */
public class JsonUtil {
    public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
        ResultSetMetaData md = rs.getMetaData();
        int num = md.getColumnCount() ;
        JSONArray array = new JSONArray();
        while(rs.next()){
            JSONObject mapOfColValues = new JSONObject();
            for (int i = 1; i <=num; i++) {
                Object o = rs.getObject(i);
                if(o instanceof Date){
                    mapOfColValues.put(md.getColumnName(i),DateUtil.formatDate((Date)o,"yyyy-MM-dd"));

                }else{
                    mapOfColValues.put(md.getColumnName(i),o);
                }

            }
            array.add(mapOfColValues);
        }
        return array;
    }
}
