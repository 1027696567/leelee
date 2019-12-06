package com.example.service.impl;

import com.example.mapper.*;
import com.example.model.*;
import com.example.service.CompanyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 供应商信息操作
 * @create 2019-10-18 19:50
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private VerityMapper verityMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private TenderMapper tenderMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<CompanyInfo> selectAllCompanyInfo() {
        List<CompanyInfo> companyInfos = companyMapper.selectAll();
        for (int i = 0; i < companyInfos.size(); i++){
            if (companyInfos.get(i).getStatus().equals((byte)0)){
                companyInfos.get(i).setStatusName("待审核");
            } else if (companyInfos.get(i).getStatus().equals((byte)1)){
                companyInfos.get(i).setStatusName("审核通过");
            } else {
                companyInfos.get(i).setStatusName("审核未通过");
            }
        }
        return companyInfos;
    }

    @Override
    public Integer addCompanyInfo(CompanyInfo companyInfo) {
        CompanyInfo companyInfo2 = selectUserCompanyInfo(companyInfo.getCreateUserName());
        if (companyInfo2 == null) {
            CompanyInfo companyInfo1 = companyMapper.selectByNumber(companyInfo.getCompanyNumber());
            if (companyInfo1 == null) {
                companyInfo.setStatus((byte) 0);
                Date date = new Date();
                companyInfo.setCreateTime(date);
                companyMapper.insertSelective(companyInfo);
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public Integer editCompanyInfo(CompanyInfo companyInfo) {
        return companyMapper.updateByPrimaryKeySelective(companyInfo);
    }
    /**
     * 添加供应商审核信息
     * */
    @Override
    public Integer addVerityInfo(VerityInfo verityInfo) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyId(verityInfo.getCompanyId());
        companyInfo.setStatus((byte) 1);
        companyMapper.updateByPrimaryKeySelective(companyInfo);
        return verityMapper.insertSelective(verityInfo);
    }
    /**
     * 查询供应商审核信息
     */
    @Override
    public VerityInfo selectVerityInfo(Long companyId) {
        return verityMapper.selectByCompanyId(companyId);
    }

    /**
     * 更新供应商审核信息
     * */
    @Override
    public Integer updateVerityInfo(VerityInfo verityInfo) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyId(verityInfo.getCompanyId());
        companyInfo.setStatus((byte) 1);
        companyMapper.updateByPrimaryKeySelective(companyInfo);
        return verityMapper.updateByPrimaryKeySelective(verityInfo);
    }

    /**
     * 只修改供应商审核状态，不删除审核信息
     * */
    @Override
    public Integer updateCompanyInfo(Long companyId) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyId(companyId);
        companyInfo.setStatus((byte) 2);
        return companyMapper.updateByPrimaryKeySelective(companyInfo);
    }

    /**
     * 删除供应商和对应审核信息
     * */
    @Override
    public Integer deleteCompanyInfo(Long companyId) {
        verityMapper.deleteByCompanyId(companyId);
        return companyMapper.deleteByPrimaryKey(companyId);
    }

    @Override
    public Integer addItemInfo(ItemInfo itemInfo) {
        ItemInfo itemInfo1 = itemMapper.selectByItemNumber(itemInfo.getItemNumber());
        if (itemInfo1 == null) {
            itemInfo.setStatus("0");
            itemMapper.insertSelective(itemInfo);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public List<ItemInfo> selectAllItemInfo() {
        List<ItemInfo> itemInfos = itemMapper.selectAllItemInfo();
        for (int i = 0; i <itemInfos.size(); i++) {
            if (itemInfos.get(i).getStatus().equals("0")) {
                itemInfos.get(i).setStatusName("待开始");
            } else if (itemInfos.get(i).getStatus().equals("1")) {
                itemInfos.get(i).setStatusName("进行中");
            } else if (itemInfos.get(i).getStatus().equals("2")) {
                itemInfos.get(i).setStatusName("已完成");
            } else if (itemInfos.get(i).getStatus().equals("-1")) {
                itemInfos.get(i).setStatusName("流标");
            } else {
                itemInfos.get(i).setStatusName("废标");
            }
        }
        return itemInfos;
    }

    @Override
    public Integer updateItemInfo(ItemInfo itemInfo) {
        return itemMapper.updateByPrimaryKeySelective(itemInfo);
    }
    /**
     * 查寻用户所属机构
     * */
    @Override
    public CompanyInfo selectUserCompanyInfo(String username) {
        return companyMapper.selectByUsername(username);
    }
    /**
     * 添加投标书
     */
    @Override
    public Integer addTenderInfo(TenderInfo tenderInfo) {
        return tenderMapper.insertSelective(tenderInfo);
    }

    @Override
    public String selectTenderByUserAndItem(HttpServletResponse httpServletResponse, String username, Long itemId) {
        if (tenderMapper.selectByUserAndItem(username,itemId) != null) {
            httpServletResponse.setStatus(401);
            return "请勿重复报名";
        } else {
            return null;
        }
    }

    @Override
    public Integer deleteItemInfo(Long itemId) {
        return itemMapper.deleteByPrimaryKey(itemId);
    }

    @Override
    public List<TenderTable> selectTenderTable(Long itemId) {
        return tenderMapper.selectTenderTable(itemId);
    }

    @Override
    public List<NoticeInfo> selectAllNoticeInfo() {
        List<NoticeInfo> noticeInfos = noticeMapper.selectAllNoticeInfo();
        for (int i = 0; i < noticeInfos.size(); i++) {
            if (noticeInfos.get(i).getStatus().equals((byte)0)) {
                noticeInfos.get(i).setStatusName("未回复");
            } else {
                noticeInfos.get(i).setStatusName("已回复");
            }
        }
        return noticeInfos;
    }

    @Override
    public Integer insertNoticeInfo(NoticeInfo noticeInfo) {
        noticeInfo.setStatus((byte) 0);
        return noticeMapper.insertSelective(noticeInfo);
    }

    @Override
    public Integer updateNoticeInfo(NoticeInfo noticeInfo) {
        noticeInfo.setStatus((byte) 1);
        System.out.println(noticeInfo);
        return noticeMapper.updateByPrimaryKeySelective(noticeInfo);
    }

    @Override
    public NoticeForm selectNoticeForm(String username) {
        Long userId = sysUserMapper.selectIdByUserName(username);
        List<Long> rolesId = sysRoleMapper.selectRoleIdByUserId(userId);
        NoticeForm noticeForm = new NoticeForm();
        List<String> rolesName = new ArrayList<>();
        for (int i = 0; i < rolesId.size(); i++) {
            rolesName.add(sysRoleMapper.selectByRoleId(rolesId.get(i)).getRoleName());
        }
        noticeForm.setRoles(rolesName);
        noticeForm.setCompanyName(companyMapper.selectByUsername(username).getCompanyName());
        return noticeForm;
    }

    @Override
    public NoticeForm selectNoticeForms(String publishUsername, String replyUsername) {
        NoticeForm noticeForm = new NoticeForm();
        NoticeForm publishNoticeForm = selectNoticeForm(publishUsername);
        NoticeForm replyNoticeForm = selectNoticeForm(replyUsername);
        noticeForm.setRoles(publishNoticeForm.getRoles());
        noticeForm.setCompanyName(publishNoticeForm.getCompanyName());
        noticeForm.setReplyRoles(replyNoticeForm.getRoles());
        noticeForm.setReplyCompanyName(replyNoticeForm.getCompanyName());
        return noticeForm;
    }

    @Override
    public List<ItemInfo> selectFinish() {
        return itemMapper.selectFinish();
    }

    @Override
    public ItemInfo selectByItemNumber(String itemNumber) {
        return itemMapper.selectByItemNumber(itemNumber);
    }
}
