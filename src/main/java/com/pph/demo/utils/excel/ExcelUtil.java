package com.pph.demo.utils.excel;

import com.pph.demo.utils.common.Params;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author pph
 * @date 2019/12/23 08:59
 * @description
 */
public final class ExcelUtil {
    private ExcelUtil() {
    }

    /**
     * @param sheet sheet 对象
     * @param start 从第几行开始取 sheet 值，默认从 0 开始
     * @param keys  生成对象的所有属性
     * @param type  对象类型（注意顺序必须与 sheet 的列一一对应，否则会出现值错位）
     * @param <T>   类型
     * @return 结果结合
     * @throws Exception 异常
     */
    public static <T> List<T> getSheetData(Sheet sheet, int start, List<String> keys, Class<T> type) throws Exception {
        List<T> result = new ArrayList<>();

        if (Objects.nonNull(sheet)) {
            for (int line = start; line < sheet.getLastRowNum(); line++) {
                T t = type.newInstance();
                Class<?> clazz = t.getClass();

                Row row = sheet.getRow(line);
                if (Objects.isNull(row)) {
                    continue;
                }

                for (int key = 0; key < keys.size(); key++) {
                    String name = keys.get(key);
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);

                    // TODO: 2019/12/23 属性类型通过反射获取
                    Object cell = row.getCell(key);
//                    通过反射调用 set 方法
                    clazz.getMethod("set".concat(Params.captureName(name)), field.getType())
                            .invoke(t, Objects.isNull(cell) ? null : String.valueOf(cell));
                }

                result.add(t);
            }
        }

        return result;
    }
}
