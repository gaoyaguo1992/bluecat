package com.stylefeng.guns.sharecore.common.persistence.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * 生成seq Model mapper
 * @author Alan.huang
 *
 */
public interface SeqModelMapper {
	/**
	 * 
	 * @param seqName
	 * @return
	 */
	String selectSeqByName( @Param("seqName")String seqName);
	/**
	 * 
	 * @param seqName
	 * @return
	 */
	String selectSeqByNameAndNumber( @Param("seqName")String seqName,@Param("number")Long number);
	/**
	 * 设置uft8mb4
	 */
	void setNamesUtf8mb4();
}