package com.pph.demo.service.impl;

import com.pph.demo.mapper.TopProductDataMapper;
import com.pph.demo.model.TopProductData;
import com.pph.demo.service.TopService;
import com.pph.demo.utils.Constants;
import com.pph.demo.utils.common.Params;
import com.pph.demo.utils.excel.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author: pph
 * @date 2019/12/22 19:13
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

        File file = new File("/Users/pph/Desktop/all/demo/top/PDU产品20191212en.xlsx");
        Workbook wb;

//        判断文件版本
        String fileName = file.getName();
        if (StringUtils.equals(fileName.substring(fileName.lastIndexOf(Constants.SIGN_5) + 1),
                Constants.EXCEL_SUFFIX_1)) {
            wb = new XSSFWorkbook(file);
        } else {
            wb = new HSSFWorkbook(POIFSFileSystem.create(file));
        }

        final int sheetIndex = 0, startIndex = 3;
//        将 excel 第一个 sheet 的所有值转换为集合对象
        List<TopProductData> sheetData = ExcelUtil.getSheetData(wb.getSheetAt(sheetIndex), startIndex,
                TopProductData.KEYS, TopProductData.class).stream()
//                过滤产品型号为空的数据
                .filter(data -> StringUtils.isNotBlank(data.getModel()))
                .collect(toList());

        int count = 0;
        if (!sheetData.isEmpty()) {
//            写入 DB
            count = topProductDataMapper.adds(sheetData);
        }

        return String.format("更新了 %d 条数据", count);
    }

    @Override
    public List<TopProductData> queryProductByTerms(Map<String, Object> filter) {
        Params.makePageInfo(filter);
        LOGGER.info("^^^ queryProductByTerms filter: {}", filter);
        return topProductDataMapper.queryProductByTerms(filter);
    }

    @Override
    public int queryTotalByTerms(Map<String, Object> filter) {
        LOGGER.info("^^^ queryTotalByTerms filter: {}", filter);
        return topProductDataMapper.queryTotalByTerms(filter);
    }

    @Override
    public Map<String, List<Map<String, String>>> queryFilter() {
        LOGGER.info("^^^ queryFilter");
        Map<String, List<Map<String, String>>> result = new HashMap<>(16);

        result.put("function", getFilter(topProductDataMapper.distinctFunction()));
        result.put("manufacturer", getFilter(topProductDataMapper.distinctManufacturer()));
        result.put("standardType", getFilter(topProductDataMapper.distinctStandardType()));

        return result;
    }

    private List<Map<String, String>> getFilter(List<String> list) {
        return list.stream()
                .filter(StringUtils::isNotBlank)
                .map(s -> new HashMap<String, String>() {{
                    put("text", s);
                    put("value", s);
                }})
                .collect(toList());
    }
}
