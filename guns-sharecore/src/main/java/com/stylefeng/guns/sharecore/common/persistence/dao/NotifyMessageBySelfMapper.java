package com.stylefeng.guns.sharecore.common.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.sharecore.common.persistence.model.NotifyMessage;

public interface NotifyMessageBySelfMapper {



    int deleteByPrimaryKey(Long id);

    int insert(NotifyMessage record);

    int insertSelective(NotifyMessage record);

    NotifyMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NotifyMessage record);

    int updateByPrimaryKey(NotifyMessage record);

	NotifyMessage queryByChuZuIdAndCustNo(@Param("chuZuId")Long chuZuId, @Param("custNo")Long custNo);

	NotifyMessage queryByZuJieIdAndCustNo(@Param("zuJieId")Long zuJieId, @Param("custNo")Long custNo);

	/**
	 * 
	 * <p>根据条件分页查询提示消息</p>
	 * @param custNo
	 * @param start
	 * @param count
	 * @return
	 */
	List<NotifyMessage> listByCustNoAndPage(@Param("custNo")Long custNo,@Param("code")Integer code, @Param("start")Integer start, @Param("count")Integer count);
	
	Long countMessageByStatus(@Param("custNo")Long custNo,@Param("messageStatus")Integer messageStatus);

	/**
	 * 
	 * <p>获取未读消息条数</p>
	 * @param custNo
	 * @return
	 */
	Long countUnreadMessage(@Param("custNo")Long custNo);

	/**
	 * 
	 * <p>删除一个月前的消息</p>
	 */
	void deleteMonthAgoMessages();
}