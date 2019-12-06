package com.example.service.impl;

import com.example.mapper.CostAccountMapper;
import com.example.mapper.CostTargetMapper;
import com.example.model.CostAccountInfo;
import com.example.model.CostTargetInfo;
import com.example.model.CostTargetMerge;
import com.example.service.CostAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chen
 * @program leelee
 * @description 成本管理
 * @create 2019-12-01 20:52
 */
@Service
public class CostAccountServiceImpl implements CostAccountService {
    @Autowired
    private CostAccountMapper costAccountMapper;
    @Autowired
    private CostTargetMapper costTargetMapper;

    @Override
    public List<CostAccountInfo> selectAll() {
        return costAccountMapper.selectAll();
    }

    @Override
    public String insert(HttpServletResponse httpServletResponse, CostAccountInfo costAccountInfo) {
        System.out.println(costAccountInfo);
        if (costAccountMapper.selectByCostAccountNumber(costAccountInfo.getCostAccountNumber()).isEmpty()) {
            costAccountInfo.setHasChildren(false);
            costAccountMapper.insertSelective(costAccountInfo);
            return "添加成功";
        } else {
            httpServletResponse.setStatus(401);
            return "添加失败，科目编号已存在";
        }
    }

    @Override
    public List<CostAccountInfo> selectByItemNumber(String itemNumber) {
        return costAccountMapper.selectByItemNumber(itemNumber);
    }

    @Override
    public Integer updateCostAccount(CostAccountInfo costAccountInfo) {
        return costAccountMapper.updateByPrimaryKeySelective(costAccountInfo);
    }

    @Override
    public String updateStatus(HttpServletResponse httpServletResponse, CostAccountInfo costAccountInfo) {
        CostTargetInfo costTargetInfo = costTargetMapper.selectByCostAccountNumber(costAccountInfo.getCostAccountNumber());
        if (!costAccountInfo.isStatus()) {
            if (costTargetInfo == null) {
                if (costAccountMapper.selectChildrenByStatus(costAccountInfo.getCostAccountNumber(), true).isEmpty()) {
                    costAccountMapper.updateByPrimaryKeySelective(costAccountInfo);
                    return "更新成功";
                } else {
                    httpServletResponse.setStatus(401);
                    return "请先禁用子节点";
                }
            } else {
                httpServletResponse.setStatus(401);
                return "科目无法禁用，已设置金额";
            }
        } else {
            if (costAccountInfo.getParentNumber() == null) {
                costAccountMapper.updateByPrimaryKeySelective(costAccountInfo);
                return "更新成功";
            } else {
                if (costAccountMapper.selectByParentNumber(costAccountInfo.getParentNumber()).isStatus()) {
                    costAccountMapper.updateByPrimaryKeySelective(costAccountInfo);
                    return "更新成功";
                } else {
                    httpServletResponse.setStatus(401);
                    return "请先启用父节点";
                }
            }
        }
    }

    @Override
    public List<CostAccountInfo> selectChildren(String parentNumber) {
        return costAccountMapper.selectChildren(parentNumber);
    }

    @Override
    public Integer deleteInfo(CostAccountInfo costAccountInfo) {
        if (costAccountMapper.selectByParentNumbers(costAccountInfo.getParentNumber()).size() > 1) {
            return costAccountMapper.deleteByPrimaryKey(costAccountInfo.getCostAccountId());
        } else {
            CostAccountInfo costAccountInfo1 = costAccountMapper.selectByParentNumber(costAccountInfo.getParentNumber());
            costAccountInfo1.setHasChildren(false);
            costAccountMapper.updateByPrimaryKeySelective(costAccountInfo1);
            return costAccountMapper.deleteByPrimaryKey(costAccountInfo.getCostAccountId());
        }

    }

    @Override
    public List<CostTargetMerge> selectCostTargetMerge(String itemNumber) {
        return costTargetMapper.selectByItemNumber(itemNumber, true);
    }

    @Override
    public List<CostTargetMerge> selectCostTargetChildren(String costAccountNumber) {
        return costTargetMapper.selectChildren(costAccountNumber, true);
    }

    @Override
    public String insertCostTarget(CostTargetMerge costTargetMerge) {
        CostTargetInfo costTargetInfo = new CostTargetInfo();
        costTargetInfo.setCostAccountNumber(costTargetMerge.getCostAccountNumber());
        costTargetInfo.setMeasurementUnit(costTargetMerge.getMeasurementUnit());
        costTargetInfo.setUnitPrice(costTargetMerge.getUnitPrice());
        costTargetInfo.setWorkAmount(costTargetMerge.getWorkAmount());
        costTargetInfo.setTotalPrice(costTargetMerge.getTotalPrice());
        costTargetMapper.insertSelective(costTargetInfo);
        if (costTargetMerge.getLevel() == 3) {
            /**
             * 查询第二层科目获得父节点编号 = 第一层科目编号
             * */
            CostAccountInfo costAccountInfo = costAccountMapper.selectSelfByCostNumber(costTargetMerge.getParentNumber());
            /**
             * 查询第一层科目获得父节点编号 = 第零层科目编号
             * */
            CostAccountInfo costAccountInfo1 = costAccountMapper.selectSelfByCostNumber(costAccountInfo.getParentNumber());
            CostTargetInfo costTargetInfoV1 = costTargetMapper.selectByCostAccountNumber(costAccountInfo.getCostAccountNumber());
            if (costTargetInfoV1 == null) {
                CostTargetInfo costTargetInfo1 = new CostTargetInfo();
                costTargetInfo1.setTotalPrice(costTargetMerge.getTotalPrice());
                costTargetInfo1.setCostAccountNumber(costAccountInfo.getCostAccountNumber());
                costTargetMapper.insertSelective(costTargetInfo1);
            } else {
                costTargetInfoV1.setTotalPrice(costTargetInfoV1.getTotalPrice() + costTargetMerge.getTotalPrice());
                costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV1);
            }
            CostTargetInfo costTargetInfoV2 = costTargetMapper.selectByCostAccountNumber(costAccountInfo1.getCostAccountNumber());
            if (costTargetInfoV2 == null) {
                CostTargetInfo costTargetInfo1 = new CostTargetInfo();
                costTargetInfo1.setTotalPrice(costTargetMerge.getTotalPrice());
                costTargetInfo1.setCostAccountNumber(costAccountInfo1.getCostAccountNumber());
                costTargetMapper.insertSelective(costTargetInfo1);
            } else {
                costTargetInfoV2.setTotalPrice(costTargetInfoV2.getTotalPrice() + costTargetMerge.getTotalPrice());
                costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV2);
            }
            CostTargetInfo costTargetInfoV3 = costTargetMapper.selectByCostAccountNumber(costAccountInfo1.getParentNumber());
            if (costTargetInfoV3 == null) {
                CostTargetInfo costTargetInfo1 = new CostTargetInfo();
                costTargetInfo1.setTotalPrice(costTargetMerge.getTotalPrice());
                costTargetInfo1.setCostAccountNumber(costAccountInfo1.getParentNumber());
                costTargetMapper.insertSelective(costTargetInfo1);
            } else {
                costTargetInfoV3.setTotalPrice(costTargetInfoV3.getTotalPrice() + costTargetMerge.getTotalPrice());
                costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV3);
            }
            return "编辑成功";
        } else if (costTargetMerge.getLevel() == 2) {
            /**
             * 查询第一层科目获得父节点编号 = 第零层科目编号
             * */
            CostAccountInfo costAccountInfo = costAccountMapper.selectSelfByCostNumber(costTargetMerge.getParentNumber());
            CostTargetInfo costTargetInfoV1 = costTargetMapper.selectByCostAccountNumber(costAccountInfo.getCostAccountNumber());
            if (costTargetInfoV1 == null) {
                CostTargetInfo costTargetInfo1 = new CostTargetInfo();
                costTargetInfo1.setTotalPrice(costTargetMerge.getTotalPrice());
                costTargetInfo1.setCostAccountNumber(costAccountInfo.getCostAccountNumber());
                costTargetMapper.insertSelective(costTargetInfo1);
            } else {
                costTargetInfoV1.setTotalPrice(costTargetInfoV1.getTotalPrice() + costTargetMerge.getTotalPrice());
                costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV1);
            }
            CostTargetInfo costTargetInfoV2 = costTargetMapper.selectByCostAccountNumber(costAccountInfo.getParentNumber());
            if (costTargetInfoV2 == null) {
                CostTargetInfo costTargetInfo1 = new CostTargetInfo();
                costTargetInfo1.setTotalPrice(costTargetMerge.getTotalPrice());
                costTargetInfo1.setCostAccountNumber(costAccountInfo.getParentNumber());
                costTargetMapper.insertSelective(costTargetInfo1);
            } else {
                costTargetInfoV2.setTotalPrice(costTargetInfoV2.getTotalPrice() + costTargetMerge.getTotalPrice());
                costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV2);
            }
            return "编辑成功";
        } else if (costTargetMerge.getLevel() == 1) {
            CostTargetInfo costTargetInfoV1 = costTargetMapper.selectByCostAccountNumber(costTargetMerge.getParentNumber());
            if (costTargetInfoV1 == null) {
                CostTargetInfo costTargetInfo1 = new CostTargetInfo();
                costTargetInfo1.setTotalPrice(costTargetMerge.getTotalPrice());
                costTargetInfo1.setCostAccountNumber(costTargetMerge.getParentNumber());
                costTargetMapper.insertSelective(costTargetInfo1);
            } else {
                costTargetInfoV1.setTotalPrice(costTargetInfoV1.getTotalPrice() + costTargetMerge.getTotalPrice());
                costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV1);
            }
            return "编辑成功";
        } else {
            return "编辑成功";
        }
    }

    @Override
    public String updateCostTarget(CostTargetMerge costTargetMerge) {
        /**
         * 获取原本总价
         * */
        CostTargetInfo costTargetInfo1 = costTargetMapper.selectByCostAccountNumber(costTargetMerge.getCostAccountNumber());
        Integer price = costTargetMerge.getTotalPrice() - costTargetInfo1.getTotalPrice();
        CostTargetInfo costTargetInfo = new CostTargetInfo();
        costTargetInfo.setCostTargetId(costTargetMerge.getCostTargetId());
        costTargetInfo.setCostAccountNumber(costTargetMerge.getCostAccountNumber());
        costTargetInfo.setMeasurementUnit(costTargetMerge.getMeasurementUnit());
        costTargetInfo.setUnitPrice(costTargetMerge.getUnitPrice());
        costTargetInfo.setWorkAmount(costTargetMerge.getWorkAmount());
        costTargetInfo.setTotalPrice(costTargetMerge.getTotalPrice());
        costTargetMapper.updateByPrimaryKeySelective(costTargetInfo);

        if (costTargetMerge.getLevel() == 3) {
            /**
             * 查询第二层科目获得父节点编号 = 第一层科目编号
             * */
            CostAccountInfo costAccountInfo = costAccountMapper.selectSelfByCostNumber(costTargetMerge.getParentNumber());
            /**
             * 查询第一层科目获得父节点编号 = 第零层科目编号
             * */
            CostAccountInfo costAccountInfo1 = costAccountMapper.selectSelfByCostNumber(costAccountInfo.getParentNumber());
            CostTargetInfo costTargetInfoV1 = costTargetMapper.selectByCostAccountNumber(costAccountInfo.getCostAccountNumber());
            costTargetInfoV1.setTotalPrice(costTargetInfoV1.getTotalPrice() + price);
            costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV1);

            CostTargetInfo costTargetInfoV2 = costTargetMapper.selectByCostAccountNumber(costAccountInfo1.getCostAccountNumber());
            costTargetInfoV2.setTotalPrice(costTargetInfoV2.getTotalPrice() + price);
            costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV2);

            CostTargetInfo costTargetInfoV3 = costTargetMapper.selectByCostAccountNumber(costAccountInfo1.getParentNumber());
            costTargetInfoV3.setTotalPrice(costTargetInfoV3.getTotalPrice() + price);
            costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV3);
            return "编辑成功";
        } else if (costTargetMerge.getLevel() == 2) {
            /**
             * 查询第一层科目获得父节点编号 = 第零层科目编号
             * */
            CostAccountInfo costAccountInfo = costAccountMapper.selectSelfByCostNumber(costTargetMerge.getParentNumber());
            CostTargetInfo costTargetInfoV1 = costTargetMapper.selectByCostAccountNumber(costAccountInfo.getCostAccountNumber());
            costTargetInfoV1.setTotalPrice(costTargetInfoV1.getTotalPrice() + price);
            costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV1);

            CostTargetInfo costTargetInfoV2 = costTargetMapper.selectByCostAccountNumber(costAccountInfo.getParentNumber());
            costTargetInfoV2.setTotalPrice(costTargetInfoV2.getTotalPrice() + price);
            costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV2);
            return "编辑成功";
        } else if (costTargetMerge.getLevel() == 1) {
            CostTargetInfo costTargetInfoV1 = costTargetMapper.selectByCostAccountNumber(costTargetMerge.getParentNumber());
            costTargetInfoV1.setTotalPrice(costTargetInfoV1.getTotalPrice() + price);
            costTargetMapper.updateByPrimaryKeySelective(costTargetInfoV1);
            return "编辑成功";
        } else {
            return "编辑成功";
        }
    }
}
