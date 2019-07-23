package util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Administrator
 * @date 2019/7/17 23:37:45
 * @description
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object jsonObject) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
}
