/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.processor;

import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author varmas
 */
public class Worker extends SwingWorker<Integer, Object[]>
{

  private JTable table;
  private int thread;
  private String sNroTerm;
  private int txn;
  private String title;
  private int respCode;
  private int nroTxnOk;
  private int nroTxnError;
  private int numOrder;
  private int instance;
  private String time;
  private List<Object[]> listObject;

  public JTable getTable()
  {
    return table;
  }

  public void setTable(JTable table)
  {
    this.table = table;
  }

  public int getThread()
  {
    return thread;
  }

  public void setThread(int thread)
  {
    this.thread = thread;
  }

  public String getsNroTerm()
  {
    return sNroTerm;
  }

  public void setsNroTerm(String sNroTerm)
  {
    this.sNroTerm = sNroTerm;
  }

  public int getTxn()
  {
    return txn;
  }

  public void setTxn(int txn)
  {
    this.txn = txn;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public int getRespCode()
  {
    return this.respCode;
  }

  public void setRespCode(int respCode)
  {
    this.respCode = respCode;
  }

  public int getNroTxnOk()
  {
    return this.nroTxnOk;
  }

  public void setNroTxnOk(int nroTxnOk)
  {
    this.nroTxnOk = nroTxnOk;
  }

  public int getNroTxnError()
  {
    return this.nroTxnError;
  }

  public void setNroTxnError(int nroTxnError)
  {
    this.nroTxnError = nroTxnError;
  }

  public int getNumOrder()
  {
    return numOrder;
  }

  public void setNumOrder(int numOrder)
  {
    this.numOrder = numOrder;
  }

  public int getInstance()
  {
    return instance;
  }

  public void setInstance(int instance)
  {
    this.instance = instance;
  }

  public String getTime()
  {
    return this.time;
  }

  public void setTime(String time)
  {
    this.time = time;
  }

  public List<Object[]> getListObject()
  {
    return listObject;
  }

  public void setListObject(List<Object[]> listObject)
  {
    this.listObject = listObject;
  }

  @Override
  protected Integer doInBackground() throws Exception
  {

    Object row[] =
    {
      getThread(), getsNroTerm(), getTxn(), getTitle(), getNroTxnOk(), getNroTxnError(), getRespCode(), getTime()
    };

    publish(row);

    return null;
  }

  @Override
  protected void process(final List<Object[]> rows)
  {
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    tableModel.setNumRows(getInstance());

    for (Object[] row : rows)
    {
      tableModel.setValueAt(row[0], getThread(), 0);
      tableModel.setValueAt(row[1], getThread(), 1);
      tableModel.setValueAt(row[2], getThread(), 2);
      tableModel.setValueAt(row[3], getThread(), 3);
      tableModel.setValueAt(row[4], getThread(), 4);
      tableModel.setValueAt(row[5], getThread(), 5);
      tableModel.setValueAt(row[7], getThread(), 6);
      listObject.add(row);
    }
  }

}
