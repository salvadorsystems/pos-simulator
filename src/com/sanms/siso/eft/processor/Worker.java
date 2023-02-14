/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.formatter.Field;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author salvador
 */
public class Worker extends SwingWorker<Integer, Object[]> {

    private JTable table;
    private JTable tableResponse;
    private int thread;
    private String sNroTerm;
    private int txn;
    private String title;
    private int respCode;
    private int nroTxnOk;
    private int nroTxnError;
    private int numOrder;
    private String instance;
    private String time;
    private List<Object[]> listObject;
    private List<Field> listField;
    private List<Field> listFieldResponse;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTable getTableResponse() {
        return tableResponse;
    }

    public void setTableResponse(JTable tableResponse) {
        this.tableResponse = tableResponse;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public String getsNroTerm() {
        return sNroTerm;
    }

    public void setsNroTerm(String sNroTerm) {
        this.sNroTerm = sNroTerm;
    }

    public int getTxn() {
        return txn;
    }

    public void setTxn(int txn) {
        this.txn = txn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRespCode() {
        return this.respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public int getNroTxnOk() {
        return this.nroTxnOk;
    }

    public void setNroTxnOk(int nroTxnOk) {
        this.nroTxnOk = nroTxnOk;
    }

    public int getNroTxnError() {
        return this.nroTxnError;
    }

    public void setNroTxnError(int nroTxnError) {
        this.nroTxnError = nroTxnError;
    }

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Object[]> getListObject() {
        return listObject;
    }

    public void setListObject(List<Object[]> listObject) {
        this.listObject = listObject;
    }

    public List<Field> getListField() {
        return listField;
    }

    public void setListField(List<Field> listField) {
        this.listField = listField;
    }

    public List<Field> getListFieldResponse() {
        return listFieldResponse;
    }

    public void setListFieldResponse(List<Field> listFieldResponse) {
        this.listFieldResponse = listFieldResponse;
    }
    
    

    @Override
    protected Integer doInBackground() throws Exception {

        
        publish((Object[]) null);

        return null;
    }

    @Override
    protected void process(final List<Object[]> rows) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        TableColumnModel columnModel = table.getColumnModel();

        tableModel.setNumRows(listField.size());
        tableModel.setColumnCount(5);
        columnModel.getColumn(0).setPreferredWidth(120);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(4).setPreferredWidth(200);
        int i = 0;
        for (Field field : listField) {
            if (!field.getValue().isEmpty()) {
                tableModel.setValueAt(field.getAlias(), i, 0);
                tableModel.setValueAt(field.getIsoBit(), i, 1);
                tableModel.setValueAt(field.getFormat(), i, 2);
                tableModel.setValueAt(field.getValueSize(), i, 3);
                tableModel.setValueAt(field.getValue(), i, 4);
                i++;
            }
        }

        /**
         * 
         */
        DefaultTableModel tableModelResponse =  (DefaultTableModel) tableResponse.getModel();
        TableColumnModel columnModelResponse = tableResponse.getColumnModel();

        tableModelResponse.setNumRows(listFieldResponse.size());
        tableModelResponse.setColumnCount(5);
        columnModelResponse.getColumn(0).setPreferredWidth(120);
        columnModelResponse.getColumn(1).setPreferredWidth(50);
        columnModelResponse.getColumn(2).setPreferredWidth(50);
        columnModelResponse.getColumn(3).setPreferredWidth(60);
        columnModelResponse.getColumn(4).setPreferredWidth(200);
        int j = 0;
        for (Field field : listFieldResponse) {
            if (!field.getValue().isEmpty()) {
                tableModelResponse.setValueAt(field.getAlias(), j, 0);
                tableModelResponse.setValueAt(field.getIsoBit(), j, 1);
                tableModelResponse.setValueAt(field.getFormat(), j, 2);
                tableModelResponse.setValueAt(field.getValueSize(), j, 3);
                tableModelResponse.setValueAt(field.getValue(), j, 4);
                j++;
            }
        }
    }

}
