package com.example.mapper;

import com.example.model.NoticeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NoticeMapper {
    int deleteByPrimaryKey(Long noticeId);

    int insert(NoticeInfo record);

    int insertSelective(NoticeInfo record);

    NoticeInfo selectByPrimaryKey(Long noticeId);

    int updateByPrimaryKeySelective(NoticeInfo record);

    int updateByPrimaryKey(NoticeInfo record);

    List<NoticeInfo> selectAllNoticeInfo();
}
