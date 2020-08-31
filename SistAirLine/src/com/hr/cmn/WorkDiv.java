package com.hr.cmn;

import java.util.List;

import org.apache.log4j.Logger;

/***
 * 
 * @author sist
 *
 */
public abstract class WorkDiv {
   //���뺯�� 
   protected Logger LOG=Logger.getLogger(WorkDiv.class);
   
/**
 * @���� : �ܰ���ȸ
 * @param DTO
 * @return DTO
 */
   public abstract DTO doSelectOne(DTO dto);
   
   
/**
 * @����: ����
 * @param dto
 * @return:int(0-����,1-����)
 * 
 */
   public abstract int doUpdate(DTO dto);

/**
 * @����: ����
 * @param dto
 * @return: int(0-����, 1-����)
 */
   public abstract int doDelete(DTO dto);

   
   

/**
 * @����: ����
 * @param dto
 * @return: int(0-����, 1-����)
 */
   public abstract int doInsert(DTO dto);

/**
 * @����: �����ȸ
 * @param dto
 * @return
 */
   public abstract List<?> doRetrieve(DTO dto);
   
}