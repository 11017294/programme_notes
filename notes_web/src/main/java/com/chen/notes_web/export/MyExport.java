package com.chen.notes_web.export;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-27 08-48
 */
@Slf4j
public class MyExport<T> {

    private static final Integer TEN_THOUSAND = 10000;

    // 变量值由参数传入
    private ExportBaseService<T> exportBaseService; // 策略接口
    private HttpServletResponse response;
    private String sheetName;   // sheet名
    private String fileName;    // 导出文件名
    private Class<T> clazz;

    // 变量值通过定义得到
    private Integer writeDataRows;  // 每次写入行数
    private Integer sheetDataRows;  // sheet存放数据量

    // 变量值通过计算得到
    private Integer totalCount;     // 导出总数
    private Integer sheetNum;       // Sheet个数
    private Integer oneSheetWriteCount;  // 每一个Sheet需要写入的次数
    private Integer lastSheetWriteCount; // 最后一个sheet需要写入的次数

    private Page<T> page;
    private ExcelWriter excelWriter;

    {
        // 每一个Sheet存放100w条数据
        this.sheetDataRows = 100 * this.TEN_THOUSAND;
        // 每次写入的数据量20w
        this.writeDataRows = 20 * this.TEN_THOUSAND;
    }

    /**
     *
     * @param exportBaseService 策略接口
     * @param response
     * @param fileName excle文件名
     * @param clazz 导出的实体类Class
     * @throws IOException
     */
    public static void dataExport(ExportBaseService exportBaseService, HttpServletResponse response, String fileName, Class clazz) throws IOException {
        new MyExport(exportBaseService, response, fileName, clazz, "sheet");
    }

    /**
     *
     * @param exportBaseService 策略接口
     * @param response
     * @param sheetName sheet名
     * @param fileName excle文件名
     * @param clazz 导出的实体类Class
     * @throws IOException
     */
    public static void dataExport(ExportBaseService exportBaseService, HttpServletResponse response, String fileName, Class clazz, String sheetName) throws IOException {
        new MyExport(exportBaseService, response, fileName, clazz, sheetName);
    }

    public MyExport (){

    }

    public MyExport (ExportBaseService<T> exportBaseService, HttpServletResponse response, String fileName, Class<T> clazz, String sheetName) throws IOException {
        this.exportBaseService = exportBaseService;
        this.response = response;
        this.fileName = fileName;
        this.sheetName = sheetName;
        this.clazz = clazz;

        this.initProperty();
        this.computeProperty();
        this.setExportConfigure();
        this.export();
    }

    /**
     * 初始化属性(得到导出总数)
     */
    private void initProperty() {
        this.page = new Page<>();
        this.totalCount = this.exportBaseService.totalExport();
    }

    /**
     * 设置导出配置
     * @throws IOException
     */
    private void setExportConfigure() throws IOException {
        this.response.setContentType("application/vnd.ms-excel");
        this.response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        this.fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        this.response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        this.excelWriter = EasyExcel.write(response.getOutputStream()).build();
    }

    /**
     * 计算属性值
     * (Sheet个数、每个Sheet写入次数、最后一个sheet写入次数)
     */
    private void computeProperty() {
        // 最后一个Sheet的行数
        Integer lastSheetNum = this.totalCount % this.sheetDataRows;

        // 每个Sheet需要写入的次数
        this.oneSheetWriteCount = this.sheetDataRows / this.writeDataRows;
        // 最后一个Sheet写入的次数
        this.lastSheetWriteCount = (lastSheetNum + this.writeDataRows - 1) / this.writeDataRows;

        // 计算sheet的个数
        this.sheetNum = (this.totalCount + this.sheetDataRows - 1) / this.sheetDataRows;
    }

    /**
     * 导出excel
     */
    private void export(){
        long startTime = System.currentTimeMillis();
        log.info("{}\t导出开始时间:{}", clazz.getName(), startTime);
        for (int i = 0; i < this.sheetNum; i++) {
            log.info("{}\t第{}个Sheet", clazz.getName(), i + 1);
            //创建Sheet
            String sheetName = this.sheetName + (i + 1);
            //循环写入次数: j的自增条件是当不是最后一个Sheet的时候写入次数为正常的每个Sheet写入的次数,如果是最后一个就需要使用计算的次数lastSheetWriteCount
            for (int j = 0; j < (i != this.sheetNum - 1 ? this.oneSheetWriteCount : this.lastSheetWriteCount); j++) {
                //分页查询一次20w
                Integer current = this.oneSheetWriteCount * i + j + 1;
                this.page.setCurrent(current);
                this.page.setSize(this.writeDataRows);

                List<T> list = this.exportBaseService.exportQuery(page);
                WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName)
                        .head(this.clazz).build();

                this.excelWriter.write(list, writeSheet);
            }
        }
        this.excelWriter.finish();
        log.info("{}\t导出耗时：{}", clazz.getName(), System.currentTimeMillis() - startTime);
    }

}
