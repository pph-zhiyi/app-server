package com.pph.demo.service.impl;

import com.pph.demo.mapper.TopProductDataMapper;
import com.pph.demo.model.TopProductData;
import com.pph.demo.service.TopService;
import com.pph.demo.utils.common.Params;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: pph
 * @date: 2019/12/22 19:13
 * @description:
 */
@Service
public class TopServiceImpl implements TopService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TopServiceImpl.class);

    @Autowired
    public TopServiceImpl(TopProductDataMapper topProductDataMapper) {
        this.topProductDataMapper = topProductDataMapper;
    }

    private final TopProductDataMapper topProductDataMapper;

    @Override
    public Object excelToDb() throws Exception {
        LOGGER.info("^^^ excelToDb");
        List<TopProductData> lists = new ArrayList<>();

        File file = new File("/Users/pph/Desktop/all/demo/top/PDU产品20191212en.xlsx");
        Workbook wb;

//        判断文件版本
        String fileName = file.getName();
        if (StringUtils.equals(fileName.substring(fileName.lastIndexOf(".") + 1), "xlsx")) {
            wb = new XSSFWorkbook(file);
        } else {
            wb = new HSSFWorkbook(POIFSFileSystem.create(file));
        }

//        获取excel表单
        Sheet sheet = wb.getSheetAt(0);
        if (Objects.nonNull(sheet)) {
            for (int line = 3; line < sheet.getLastRowNum(); line++) {
                TopProductData data = new TopProductData();
                Class<? extends TopProductData> clazz = data.getClass();

                Row row = sheet.getRow(line);
                if (Objects.isNull(row)) {
                    continue;
                }

                List<String> keys = TopProductData.KEYS;
                for (int key = 0; key < keys.size(); key++) {
//                    通过反射获取 set 方法
                    Method set = clazz.getMethod("set".concat(Params.captureName(keys.get(key))), String.class);

                    String val = null;
                    Object cell = row.getCell(key);
                    if (key == 0 && Objects.isNull(cell)) {
//                        产品型号为空则跳出循环
                        break;
                    }
                    if (Objects.nonNull(cell)) {
                        val = String.valueOf(cell);
                    }
                    set.invoke(data, val);
                }

//                产品型号为空则过滤数据
                if (Objects.nonNull(data.getModel())) {
                    lists.add(data);
                }
            }
        }

        int inserts = 0;
        if (!lists.isEmpty()) {
            inserts = topProductDataMapper.inserts(lists);
        }

        return String.format("更新了 %d 条数据", inserts);
    }
}
