package com.ivx;

import cn.hutool.poi.word.TableUtil;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.exception.RenderException;
import com.deepoove.poi.policy.AbstractRenderPolicy;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.RenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.render.RenderContext;
import com.deepoove.poi.template.ElementTemplate;
import com.deepoove.poi.template.run.RunTemplate;
import com.deepoove.poi.util.StyleUtils;
import com.deepoove.poi.util.TableTools;
import com.deepoove.poi.xwpf.BodyContainer;
import com.deepoove.poi.xwpf.BodyContainerFactory;
import lombok.SneakyThrows;
import org.apache.poi.xwpf.usermodel.*;


public class MyDynamicTableRenderPolicy extends AbstractRenderPolicy<TableRenderData> {
    /**
     * 渲染表头
     */
    @SneakyThrows
    @Override
    protected void beforeRender(RenderContext<TableRenderData> context) {
        ElementTemplate eleTemplate = context.getEleTemplate();
        RunTemplate runTemplate = (RunTemplate) eleTemplate;
        XWPFRun run = runTemplate.getRun();
        run.setText("", 0);
        TableRenderData tableRenderData = context.getData();
        BodyContainer bodyContainer = BodyContainerFactory.getBodyContainer(run);
        // 创建表格
        XWPFTable table = bodyContainer.insertNewTable(run, tableRenderData.obtainRowSize(), tableRenderData.obtainColSize());
        StyleUtils.styleTable(table, tableRenderData.getTableStyle());
        // 创建表头
        XWPFTableRow xwpfTableRow = table.insertNewTableRow(0);
        xwpfTableRow.setRepeatHeader(true);
        RowRenderData rowRenderData = tableRenderData.getRows().get(0);
        System.out.println(rowRenderData);
        TableRenderPolicy.Helper.renderRow(xwpfTableRow, rowRenderData);
        //
        // RunTemplate runTemplate = (RunTemplate) context.getEleTemplate();
        // XWPFRun run = runTemplate.getRun();
        // // System.out.println(run.getText(0));
        // run.setText("", 0);
        // TableRenderData tableRenderData = (TableRenderData) context.getData();
        // BodyContainer bodyContainer = BodyContainerFactory.getBodyContainer(run);
        // XWPFTable table = bodyContainer.insertNewTable(run, tableRenderData.obtainRowSize(), tableRenderData.obtainColSize());
        // StyleUtils.styleTable(table, tableRenderData.getTableStyle());
        //
        // int size = table.getRows().size();
        // for (int i = 0; i < size; i++) {
        //     RowRenderData rowRenderData = tableRenderData.getRows().get(i);
        //     TableRenderPolicy.Helper.renderRow(table.getRows().get(i), rowRenderData, StyleUtils.retriveStyle(run));
        // }
    }


    // @Override
    // public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
    //     // 获取表格对象
    //     RunTemplate runTemplate = (RunTemplate) eleTemplate;
    //     XWPFRun run = runTemplate.getRun();
    //     XWPFTableCell cell = (XWPFTableCell) ((XWPFParagraph) run.getParent()).getBody();
    //     XWPFTable table = cell.getTableRow().getTable();
    //     String tagName = eleTemplate.getTagName();
    //
    // }

    @Override
    public void doRender(RenderContext<TableRenderData> context) throws Exception {
        XWPFTable table = this.getTable(context);
        XWPFRun run = context.getRun();
        TableRenderData tableRenderData = context.getData();
        int size = table.getRows().size();
        // 表头已经渲染好了，渲染主体
        for (int i = 1; i < size; i++) {
            RowRenderData rowRenderData = tableRenderData.getRows().get(i);
            TableRenderPolicy.Helper.renderRow(table.getRows().get(i), rowRenderData, StyleUtils.retriveStyle(run));
        }
    }

    private XWPFTable getTable(RenderContext<TableRenderData> context) {
        TableRenderData tableRenderData = context.getData();
        XWPFRun run = context.getRun();
        BodyContainer bodyContainer = BodyContainerFactory.getBodyContainer(run);
        System.out.println("1");
        return bodyContainer.insertNewTable(run, tableRenderData.obtainRowSize(), tableRenderData.obtainColSize());
    }

}
