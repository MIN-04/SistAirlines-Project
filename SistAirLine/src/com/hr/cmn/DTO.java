/**
 *<pre>
 * com.hr.cmn
 * Class Name : DTO.java
 * Description : ���� VO
 * Modification Information
 * 
 *   ������      ������              ��������
 *  ---------   ---------   -------------------------------
 *  2020-02-03           ���ʻ���
 *
 * @author ���������ӿ�ũ ����ȯ�� ������
 * @since 2020-02-03 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
 
package com.hr.cmn;

/**
 * @author sist
 *
 */
public class DTO {
   private int total;
   private int num;
   private int pageSize;
   private int pageNum;
   
   public DTO() {
      
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public int getNum() {
      return num;
   }

   public void setNum(int num) {
      this.num = num;
   }

   public int getPageSize() {
      return pageSize;
   }

   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }

   public int getPageNum() {
      return pageNum;
   }

   public void setPageNum(int pageNum) {
      this.pageNum = pageNum;
   }

   
   @Override
   public String toString() {
      return "DTO [total=" + total + ", num=" + num + ", pageSize=" + pageSize + ", pageNum=" + pageNum + "]";
   }
   
   


}