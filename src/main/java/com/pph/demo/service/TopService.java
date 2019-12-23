package com.pph.demo.service;

/**
 * @author: pph
 * @date: 2019/12/22 19:12
 * @description:
 */
public interface TopService {
    /**
     * 将最新到 Excel 数据导入 DB
     *
     * @return 结果
     */
    Object excelToDb() throws Exception;
}
