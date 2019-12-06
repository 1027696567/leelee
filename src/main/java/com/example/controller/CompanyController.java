package com.example.controller;

import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.example.model.*;
import com.example.service.CompanyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @program leelee
 * @description 招投标管理
 * @create 2019-10-18 19:58
 */
@CrossOrigin
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/company/list")
    @RequiresAuthentication
    public List<CompanyInfo> selectAllCompanyInfo(){
       return companyService.selectAllCompanyInfo();
    }

    @PostMapping("/company/insert")
    @RequiresAuthentication
    public Integer addInfo(@RequestBody CompanyInfo companyInfo){
        return companyService.addCompanyInfo(companyInfo);
    }

    @PutMapping("/company/edit")
    @RequiresAuthentication
    public Integer editInfo(@RequestBody CompanyInfo companyInfo){
        return companyService.editCompanyInfo(companyInfo);
    }
    /**
     * 供应商审核按钮权限判断
     */
    @GetMapping("/authority")
    @RequiresPermissions("sys:user:shiro")
    public String getPermission() {
        return "有权访问";
    }

    @PostMapping("/company/verity")
    @RequiresAuthentication
    public Integer addVerityInfo(@RequestBody VerityInfo verityInfo) {
        return companyService.addVerityInfo(verityInfo);
    }

    @GetMapping("/company/getVerityInfo")
    @RequiresAuthentication
    public VerityInfo selectVerityInfo(@RequestParam(value = "companyId") Long companyId) {
        return companyService.selectVerityInfo(companyId);
    }

    @PutMapping("/company/updateVerityInfo")
    @RequiresAuthentication
    public Integer updateVerityInfo(@RequestBody VerityInfo verityInfo) {
        return companyService.updateVerityInfo(verityInfo);
    }

    @PutMapping("/company/updateCompanyInfo")
    @RequiresAuthentication
    public Integer updateCompanyInfo(@RequestParam("companyId") Long companyId) {
        return companyService.updateCompanyInfo(companyId);
    }

    @DeleteMapping("/company/deleteCompanyInfo")
    @RequiresPermissions("sys:user:shiro")
    public Integer deleteCompanyInfo(@RequestParam("companyId") Long companyId) {
        return companyService.deleteCompanyInfo(companyId);
    }

    @PostMapping("/company/addItemInfo")
    @RequiresPermissions("sys:user:shiro")
    public Integer addItemInfo(@RequestBody ItemInfo itemInfo) {
        return companyService.addItemInfo(itemInfo);
    }

    @GetMapping("/company/selectAllItemInfo")
    @RequiresAuthentication
    public List<ItemInfo> selectAllItemInfo() {
        return companyService.selectAllItemInfo();
    }

    @GetMapping("/company/selectFinish")
    @RequiresAuthentication
    public List<ItemInfo> selecatFinish() {
        return companyService.selectFinish();
    }

    @PutMapping("/company/updateItemInfo")
    @RequiresPermissions("sys:user:shiro")
    public Integer updateItemInfo(@RequestBody ItemInfo itemInfo) {
        return companyService.updateItemInfo(itemInfo);
    }

    @GetMapping("/company/selectUserCompanyInfo")
    @RequiresAuthentication
    public CompanyInfo selectUserCompanyInfo(@RequestParam("username")String username) {
        return companyService.selectUserCompanyInfo(username);
    }

    @PostMapping("/company/addTender")
    @RequiresPermissions("sys:user:shiro")
    public Integer addTender(@RequestBody TenderInfo tenderInfo) {
        return companyService.addTenderInfo(tenderInfo);
    }

    @GetMapping("/company/verityTender")
    @RequiresPermissions("sys:user:shiro")
    public String selectTenderByUserAndItem(@RequestParam("username")String username,
                                            @RequestParam("itemId")Long itemId,HttpServletResponse httpServletResponse) {
        return companyService.selectTenderByUserAndItem(httpServletResponse,username,itemId);
    }

    @DeleteMapping("/company/deleteItemInfo")
    @RequiresPermissions("sys:user:shiro")
    public Integer deleteItemInfo(@RequestParam("itemId")Long itemId) {
        return companyService.deleteItemInfo(itemId);
    }

    @GetMapping("/company/selectTenderTable")
    @RequiresAuthentication
    public List<TenderTable> selectTenderTable(@RequestParam("itemId")Long itemId) {
        return companyService.selectTenderTable(itemId);
    }

    @GetMapping("/company/selectNoticeInfo")
    @RequiresAuthentication
    public List<NoticeInfo> selectAllNoticeInfo() {
        return companyService.selectAllNoticeInfo();
    }

    @PostMapping("/company/insertNoticeInfo")
    @RequiresAuthentication
    public Integer insertNoticeInfo(@RequestBody NoticeInfo noticeInfo) {
        return companyService.insertNoticeInfo(noticeInfo);
    }

    @PutMapping("/company/updateNoticeInfo")
    @RequiresPermissions("sys:user:shiro")
    public Integer updateNoticeInfo(@RequestBody NoticeInfo noticeInfo) {
        return companyService.updateNoticeInfo(noticeInfo);
    }

    @GetMapping("/company/selectNoticeForm")
    @RequiresAuthentication
    public NoticeForm selectNoticeForm(@RequestParam("username")String username) {
        return companyService.selectNoticeForm(username);
    }

    @GetMapping("/company/selectNoticeForms")
    @RequiresAuthentication
    public NoticeForm selectNoticeForms(@RequestParam("publishUsername")String publishUsername,
                                        @RequestParam("replyUsername")String replyUsername) {
        return companyService.selectNoticeForms(publishUsername,replyUsername);
    }

    @GetMapping("/company/selectItemInfo")
    @RequiresAuthentication
    public ItemInfo selectItemInfo(@RequestParam("itemNumber")String itemNumber) {
        return companyService.selectByItemNumber(itemNumber);
    }

    @RequestMapping("/upload")
    @RequiresAuthentication
    public String addImg(@RequestBody MultipartFile file, HttpServletRequest request, HttpServletResponse response)throws Exception{
        System.out.println(file.getOriginalFilename());
        if(file != null){
            // 文件路径
            String path =null;
            //图片类型
            String docType=null;
            // 原文件名称
            String  fileName = file.getOriginalFilename();
            // 判断图片类型
            docType=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if(docType!=null){
                if("DOC".equals(docType.toUpperCase()) || "DOCX".equals(docType.toUpperCase()) || "PDF".equals(docType.toUpperCase())){
                    // 项目在容器中实际发布运行的根路径
                    /* String realPath = request.getSession().getServletContext().getRealPath("/");*/
                    String realPath = "C:\\Users\\15p\\Desktop\\report\\doc\\";
                    // 自定义的文件名称
                    String trueFileName=String.valueOf(System.currentTimeMillis())+"--"+fileName;
                    // 设置图片存放的路径
                    path=realPath+trueFileName;
                    System.out.println("图片的存放路径为"+path);
                    // 转存文件到指定路径
                    // 转存而不是写出
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
                }else{
                    System.out.println("请上传DOC、DOCX或者PDF格式的文件");
                }
            }else{
                System.out.println("文件类型为空");
            }
            return path;
        }else{
            System.out.println("没有找到相对应的文件");
        }
        System.out.println("文件上传的原名称为:"+file.getOriginalFilename());

        return "";
    }

}
