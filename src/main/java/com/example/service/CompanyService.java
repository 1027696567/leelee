package com.example.service;

import com.example.model.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CompanyService {
    /**
     * 查询所有供应商信息
     * */
    List<CompanyInfo> selectAllCompanyInfo();
    /**
     * 添加供应商
     * */
    Integer addCompanyInfo(CompanyInfo companyInfo);

    Integer editCompanyInfo(CompanyInfo companyInfo);

    Integer addVerityInfo(VerityInfo verityInfo);

    VerityInfo selectVerityInfo(Long verityId);

    Integer updateVerityInfo(VerityInfo verityInfo);

    Integer updateCompanyInfo(Long companyId);

    Integer deleteCompanyInfo(Long companyId);

    Integer addItemInfo(ItemInfo itemInfo);

    List<ItemInfo> selectAllItemInfo();

    Integer updateItemInfo(ItemInfo itemInfo);

    CompanyInfo selectUserCompanyInfo(String username);

    Integer addTenderInfo(TenderInfo tenderInfo);

    String selectTenderByUserAndItem(HttpServletResponse httpServletResponse, String username, Long itemId);

    Integer deleteItemInfo(Long itemId);

    List<TenderTable> selectTenderTable(Long itemId);

    List<NoticeInfo> selectAllNoticeInfo();

    Integer insertNoticeInfo(NoticeInfo noticeInfo);

    Integer updateNoticeInfo(NoticeInfo noticeInfo);

    NoticeForm selectNoticeForm(String username);

    NoticeForm selectNoticeForms(String publishUsername, String replyUsername);

    List<ItemInfo> selectFinish();

    ItemInfo selectByItemNumber(String itemNumber);
}
