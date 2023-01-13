package com.ivx;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.style.*;
import com.deepoove.poi.policy.AbstractRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.render.RenderContext;
import com.deepoove.poi.template.ElementTemplate;
import com.deepoove.poi.template.run.RunTemplate;
import com.deepoove.poi.util.StyleUtils;
import com.deepoove.poi.xwpf.BodyContainer;
import com.deepoove.poi.xwpf.BodyContainerFactory;
import lombok.SneakyThrows;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblLayoutType;

import java.util.List;


public class MyDynamicTableRenderPolicy extends AbstractRenderPolicy<TableRenderData> {
    private XWPFTable table;

    /**
     * 渲染表头
     */
    @SneakyThrows
    @Override
    protected void beforeRender(RenderContext<TableRenderData> context) {
        initTable(context);
        ElementTemplate eleTemplate = context.getEleTemplate();
        RunTemplate runTemplate = (RunTemplate) eleTemplate;
        XWPFRun run = runTemplate.getRun();
        // System.out.println(run.getText(0));
        run.setText("", 0);
        TableRenderData tableRenderData = context.getData();
        setTableStyle(tableRenderData);
        // 设置表格样式
        StyleUtils.styleTable(table, tableRenderData.getTableStyle());
        // 创建表头
        XWPFTableRow xwpfTableRow = table.getRow(0);
        xwpfTableRow.setRepeatHeader(true);
        // 设置表格居中
        setCellAlign(xwpfTableRow);
        RowRenderData rowRenderData = tableRenderData.getRows().get(0);
        setRowStyle(rowRenderData, true);

        TableRenderPolicy.Helper.renderRow(xwpfTableRow, rowRenderData);

    }

    private void setCellAlign(XWPFTableRow xwpfTableRow) {
        List<XWPFTableCell> tableCells = xwpfTableRow.getTableCells();
        // 设置行高
        int size = xwpfTableRow.getTableCells().size();
        CTTrPr trPr = xwpfTableRow.getCtRow().addNewTrPr();
        CTHeight ht = trPr.addNewTrHeight();
        ht.setVal(1000L);
        for (int i = 0; i < size; i++) {
            XWPFTableCell xwpfTableCell = tableCells.get(i);
            xwpfTableCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            WidthEnum value = WidthEnum.values()[i];
            xwpfTableCell.setWidth(value.getWidth());
        }
    }

    private void setTableStyle(TableRenderData tableRenderData) {
        TableStyle tableStyle = new TableStyle();
        tableStyle.setWidth("120%");
        tableStyle.setAlign(TableRowAlign.CENTER);
        // 设置表格不自动重调尺寸适应内容固定表格宽度
        CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
        type.setType(STTblLayoutType.FIXED);
        tableRenderData.setTableStyle(tableStyle);
    }

    /**
     * @param rowRenderData 表格数据
     * @param isHeader      是否是表头
     */
    private void setRowStyle(RowRenderData rowRenderData, boolean isHeader) {
        Style style = new Style();
        if (isHeader) {
            style.setBold(true);
        }
        style.setFontFamily("宋体");
        style.setFontSize(12d);

        ParagraphStyle paragraphStyle = new ParagraphStyle();
        paragraphStyle.setDefaultTextStyle(style);
        paragraphStyle.setAlign(ParagraphAlignment.CENTER);

        CellStyle cellStyle = new CellStyle();
        cellStyle.setDefaultParagraphStyle(paragraphStyle);

        RowStyle rowStyle = new RowStyle();
        rowStyle.setDefaultCellStyle(cellStyle);
        rowRenderData.setRowStyle(rowStyle);
    }


    @Override
    public void doRender(RenderContext<TableRenderData> context) throws Exception {
        XWPFRun run = context.getRun();
        TableRenderData tableRenderData = context.getData();
        int size = tableRenderData.getRows().size();
        // 表头已经渲染好了，渲染主体
        for (int i = 1; i < size; i++) {
            RowRenderData rowRenderData = tableRenderData.getRows().get(i);
            setRowStyle(rowRenderData, false);
            // 获取表格的行对象
            XWPFTableRow xwpfTableRow = table.getRows().get(i);
            setCellAlign(xwpfTableRow);
            TableRenderPolicy.Helper.renderRow(xwpfTableRow, rowRenderData, StyleUtils.retriveStyle(run));
        }
    }

    /**
     * 创建表格对象
     */
    private void initTable(RenderContext<TableRenderData> context) {
        TableRenderData tableRenderData = context.getData();
        XWPFRun run = context.getRun();
        BodyContainer bodyContainer = BodyContainerFactory.getBodyContainer(run);
        this.table = bodyContainer.insertNewTable(run, tableRenderData.obtainRowSize(), tableRenderData.obtainColSize());
    }
}
