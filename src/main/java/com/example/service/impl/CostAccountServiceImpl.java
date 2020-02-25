package com.example.service.impl;

import com.example.mapper.CostAccountMapper;
import com.example.mapper.CostStatementMapper;
import com.example.mapper.CostTargetMapper;
import com.example.model.*;
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
    @Autowired
    private CostStatementMapper costStatementMapper;

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

    @Override
    public List<CostStatementMerge> selectCostStatementInfo(String itemNumber) {
        return costStatementMapper.selectByItemNumber(itemNumber,true);
    }

    @Override
    public List<CostStatementMerge> selectCostStatementChildren(String costAccountNumber) {
        return costStatementMapper.selectChildren(costAccountNumber,true);
    }

    @Override
    public String insertCostStatement(CostStatementMerge costStatementMerge) {
        // 本次工作量
        Integer workAmount = costStatementMerge.getWorkAmount();
        // 本次单价
        Integer unitPrice = costStatementMerge.getUnitPrice();
        // 剩余工作量
        Integer restWorkAmount = costStatementMerge.getRestWorkAmount() - workAmount;
        // 已发生成本
        Integer costIncurred = workAmount * unitPrice;
        // 待发生成本
        Integer costWaitIncurred = restWorkAmount * unitPrice;
        // 动态成本
        Integer costDynamics = costIncurred + costWaitIncurred;
        // 差价
        Integer differPrice = costDynamics - costStatementMerge.getTotalPrice();
        CostStatementInfo costStatementInfo = new CostStatementInfo();
        costStatementInfo.setCostAccountNumber(costStatementMerge.getCostAccountNumber());
        costStatementInfo.setCostDynamics(costDynamics);
        costStatementInfo.setCostIncurred(costIncurred);
        costStatementInfo.setUnitPrice(unitPrice);
        costStatementInfo.setCostWaitIncurred(costWaitIncurred);
        costStatementInfo.setDifferPrice(differPrice);
        costStatementInfo.setRestWorkAmount(restWorkAmount);
        costStatementInfo.setWorkAmount(workAmount);
        costStatementMapper.insertSelective(costStatementInfo);
        if (costStatementMerge.getLevel() == 3) {
            /**
             * 查询第二层科目获得父节点编号 = 第一层科目编号
             * */
            CostAccountInfo costAccountInfo = costAccountMapper.selectSelfByCostNumber(costStatementMerge.getParentNumber());
            /**
             * 查询第一层科目获得父节点编号 = 第零层科目编号
             * */
            CostAccountInfo costAccountInfo1 = costAccountMapper.selectSelfByCostNumber(costAccountInfo.getParentNumber());
            CostStatementInfo costStatementInfoV1 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo.getCostAccountNumber());
            if (costStatementInfoV1 == null) {
                CostStatementInfo costStatementInfo1 = new CostStatementInfo();
                costStatementInfo1.setCostIncurred(costIncurred);
                costStatementInfo1.setCostWaitIncurred(costWaitIncurred);
                costStatementInfo1.setCostDynamics(costDynamics);
                costStatementInfo1.setDifferPrice(differPrice);
                costStatementInfo1.setCostAccountNumber(costAccountInfo.getCostAccountNumber());
                costStatementMapper.insertSelective(costStatementInfo1);
            } else {
                costStatementInfoV1.setCostIncurred(costStatementInfoV1.getCostIncurred() + costIncurred);
                costStatementInfoV1.setCostWaitIncurred(costStatementInfoV1.getCostWaitIncurred() + costWaitIncurred);
                costStatementInfoV1.setCostDynamics(costStatementInfoV1.getCostDynamics() + costDynamics);
                costStatementInfoV1.setDifferPrice(costStatementInfoV1.getDifferPrice() + differPrice);
                costStatementMapper.updateByPrimaryKeySelective(costStatementInfoV1);
            }
            CostStatementInfo costStatementInfoV2 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo1.getCostAccountNumber());
            if (costStatementInfoV2 == null) {
                CostStatementInfo costStatementInfo1 = new CostStatementInfo();
                costStatementInfo1.setCostIncurred(costIncurred);
                costStatementInfo1.setCostWaitIncurred(costWaitIncurred);
                costStatementInfo1.setCostDynamics(costDynamics);
                costStatementInfo1.setDifferPrice(differPrice);
                costStatementInfo1.setCostAccountNumber(costAccountInfo1.getCostAccountNumber());
                costStatementMapper.insertSelective(costStatementInfo1);
            } else {
                costStatementInfoV2.setCostIncurred(costStatementInfoV2.getCostIncurred() + costIncurred);
                costStatementInfoV2.setCostWaitIncurred(costStatementInfoV2.getCostWaitIncurred() + costWaitIncurred);
                costStatementInfoV2.setCostDynamics(costStatementInfoV2.getCostDynamics() + costDynamics);
                costStatementInfoV2.setDifferPrice(costStatementInfoV2.getDifferPrice() + differPrice);
                costStatementMapper.updateByPrimaryKeySelective(costStatementInfoV2);
            }
            CostStatementInfo costStatementInfoV3 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo1.getParentNumber());
            if (costStatementInfoV3 == null) {
                CostStatementInfo costStatementInfo1 = new CostStatementInfo();
                costStatementInfo1.setCostIncurred(costIncurred);
                costStatementInfo1.setCostWaitIncurred(costWaitIncurred);
                costStatementInfo1.setCostDynamics(costDynamics);
                costStatementInfo1.setDifferPrice(differPrice);
                costStatementInfo1.setCostAccountNumber(costAccountInfo1.getParentNumber());
                costStatementMapper.insertSelective(costStatementInfo1);
            } else {
                costStatementInfoV3.setCostIncurred(costStatementInfoV3.getCostIncurred() + costIncurred);
                costStatementInfoV3.setCostWaitIncurred(costStatementInfoV3.getCostWaitIncurred() + costWaitIncurred);
                costStatementInfoV3.setCostDynamics(costStatementInfoV3.getCostDynamics() + costDynamics);
                costStatementInfoV3.setDifferPrice(costStatementInfoV3.getDifferPrice() + differPrice);
                costStatementMapper.updateByPrimaryKeySelective(costStatementInfoV3);
            }
            return "编辑成功";
        } else if (costStatementMerge.getLevel() == 2) {
            /**
             * 查询第一层科目获得父节点编号 = 第零层科目编号
             * */
            CostAccountInfo costAccountInfo = costAccountMapper.selectSelfByCostNumber(costStatementMerge.getParentNumber());
            CostStatementInfo costStatementInfoV1 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo.getCostAccountNumber());
            if (costStatementInfoV1 == null) {
                CostStatementInfo costStatementInfo1 = new CostStatementInfo();
                costStatementInfo1.setCostIncurred(costIncurred);
                costStatementInfo1.setCostWaitIncurred(costWaitIncurred);
                costStatementInfo1.setCostDynamics(costDynamics);
                costStatementInfo1.setDifferPrice(differPrice);
                costStatementInfo1.setCostAccountNumber(costAccountInfo.getCostAccountNumber());
                costStatementMapper.insertSelective(costStatementInfo1);
            } else {
                costStatementInfoV1.setCostIncurred(costStatementInfoV1.getCostIncurred() + costIncurred);
                costStatementInfoV1.setCostWaitIncurred(costStatementInfoV1.getCostWaitIncurred() + costWaitIncurred);
                costStatementInfoV1.setCostDynamics(costStatementInfoV1.getCostDynamics() + costDynamics);
                costStatementInfoV1.setDifferPrice(costStatementInfoV1.getDifferPrice() + differPrice);
                costStatementMapper.updateByPrimaryKeySelective(costStatementInfoV1);
            }
            CostStatementInfo costStatementInfoV2 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo.getParentNumber());
            if (costStatementInfoV2 == null) {
                CostStatementInfo costStatementInfo1 = new CostStatementInfo();
                costStatementInfo1.setCostIncurred(costIncurred);
                costStatementInfo1.setCostWaitIncurred(costWaitIncurred);
                costStatementInfo1.setCostDynamics(costDynamics);
                costStatementInfo1.setDifferPrice(differPrice);
                costStatementInfo1.setCostAccountNumber(costAccountInfo.getParentNumber());
                costStatementMapper.insertSelective(costStatementInfo1);
            } else {
                costStatementInfoV2.setCostIncurred(costStatementInfoV2.getCostIncurred() + costIncurred);
                costStatementInfoV2.setCostWaitIncurred(costStatementInfoV2.getCostWaitIncurred() + costWaitIncurred);
                costStatementInfoV2.setCostDynamics(costStatementInfoV2.getCostDynamics() + costDynamics);
                costStatementInfoV2.setDifferPrice(costStatementInfoV2.getDifferPrice() + differPrice);
                costStatementMapper.updateByPrimaryKeySelective(costStatementInfoV2);
            }
            return "编辑成功";
        } else if (costStatementMerge.getLevel() == 1) {
            CostStatementInfo costStatementInfoV1 = costStatementMapper.selectSelfByCostAccountNumber(costStatementMerge.getParentNumber());
            if (costStatementInfoV1 == null) {
                CostStatementInfo costStatementInfo1 = new CostStatementInfo();
                costStatementInfo1.setCostIncurred(costIncurred);
                costStatementInfo1.setCostWaitIncurred(costWaitIncurred);
                costStatementInfo1.setCostDynamics(costDynamics);
                costStatementInfo1.setDifferPrice(differPrice);
                costStatementInfo1.setCostAccountNumber(costStatementMerge.getParentNumber());
                costStatementMapper.insertSelective(costStatementInfo1);
            } else {
                costStatementInfoV1.setCostIncurred(costStatementInfoV1.getCostIncurred() + costIncurred);
                costStatementInfoV1.setCostWaitIncurred(costStatementInfoV1.getCostWaitIncurred() + costWaitIncurred);
                costStatementInfoV1.setCostDynamics(costStatementInfoV1.getCostDynamics() + costDynamics);
                costStatementInfoV1.setDifferPrice(costStatementInfoV1.getDifferPrice() + differPrice);
                costStatementMapper.updateByPrimaryKeySelective(costStatementInfoV1);
            }
            return "编辑成功";
        } else {
            return "编辑成功";
        }
    }

    @Override
    public String updateCostStatementInfo(CostStatementMerge costStatementMerge) {
        /**
         * 获取原数值
         * */
        CostStatementInfo costStatementInfo = costStatementMapper.selectSelfByCostAccountNumber(costStatementMerge.getCostAccountNumber());
        // 本次工作量
        Integer workAmount = costStatementMerge.getWorkAmount();
        // 本次单价
        Integer unitPrice = costStatementMerge.getUnitPrice();
        // 剩余工作量
        Integer restWorkAmount = costStatementMerge.getRestWorkAmount() - workAmount;
        // 已发生成本
        Integer costIncurred = costStatementInfo.getCostIncurred() + workAmount * unitPrice;
        // 待发生成本
        Integer costWaitIncurred = restWorkAmount * unitPrice;
        // 动态成本
        Integer costDynamics = costIncurred + costWaitIncurred;
        // 差价
        Integer differPrice = costDynamics - costStatementMerge.getTotalPrice();

        //已发生成本差
        Integer differCostIncurred = workAmount * unitPrice;
        // 待发生成本差
        Integer differCostWaitIncurred = costWaitIncurred - costStatementInfo.getCostWaitIncurred();
        // 动态成本差
        Integer differCostDynamics = costDynamics - costStatementInfo.getCostDynamics();
        // 差价差
        Integer differDifferPrice = differPrice - costStatementInfo.getDifferPrice();
        costStatementInfo.setCostDynamics(costDynamics);
        costStatementInfo.setCostIncurred(costIncurred);
        costStatementInfo.setUnitPrice(unitPrice);
        costStatementInfo.setCostWaitIncurred(costWaitIncurred);
        costStatementInfo.setDifferPrice(differPrice);
        costStatementInfo.setRestWorkAmount(restWorkAmount);
        costStatementInfo.setWorkAmount(workAmount);
        costStatementMapper.updateByPrimaryKeySelective(costStatementInfo);

        if (costStatementMerge.getLevel() == 3) {
            /**
             * 查询第二层科目获得父节点编号 = 第一层科目编号
             * */
            CostAccountInfo costAccountInfo = costAccountMapper.selectSelfByCostNumber(costStatementMerge.getParentNumber());
            /**
             * 查询第一层科目获得父节点编号 = 第零层科目编号
             * */
            CostAccountInfo costAccountInfo1 = costAccountMapper.selectSelfByCostNumber(costAccountInfo.getParentNumber());
            CostStatementInfo costStatementInfo1 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo.getCostAccountNumber());
            costStatementInfo1.setCostIncurred(costStatementInfo1.getCostIncurred() + differCostIncurred);
            costStatementInfo1.setCostWaitIncurred(costStatementInfo1.getCostWaitIncurred() + differCostWaitIncurred);
            costStatementInfo1.setCostDynamics(costStatementInfo1.getCostDynamics() + differCostDynamics);
            costStatementInfo1.setDifferPrice(costStatementInfo1.getDifferPrice() + differDifferPrice);
            costStatementMapper.updateByPrimaryKeySelective(costStatementInfo1);

            CostStatementInfo costStatementInfo2 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo1.getCostAccountNumber());
            costStatementInfo2.setCostIncurred(costStatementInfo2.getCostIncurred() + differCostIncurred);
            costStatementInfo2.setCostWaitIncurred(costStatementInfo2.getCostWaitIncurred() + differCostWaitIncurred);
            costStatementInfo2.setCostDynamics(costStatementInfo2.getCostDynamics() + differCostDynamics);
            costStatementInfo2.setDifferPrice(costStatementInfo2.getDifferPrice() + differDifferPrice);
            costStatementMapper.updateByPrimaryKeySelective(costStatementInfo2);

            CostStatementInfo costStatementInfo3 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo1.getParentNumber());
            costStatementInfo3.setCostIncurred(costStatementInfo3.getCostIncurred() + differCostIncurred);
            costStatementInfo3.setCostWaitIncurred(costStatementInfo3.getCostWaitIncurred() + differCostWaitIncurred);
            costStatementInfo3.setCostDynamics(costStatementInfo3.getCostDynamics() + differCostDynamics);
            costStatementInfo3.setDifferPrice(costStatementInfo3.getDifferPrice() + differDifferPrice);
            costStatementMapper.updateByPrimaryKeySelective(costStatementInfo3);
            return "编辑成功";
        } else if (costStatementMerge.getLevel() == 2) {
            /**
             * 查询第一层科目获得父节点编号 = 第零层科目编号
             * */
            CostAccountInfo costAccountInfo = costAccountMapper.selectSelfByCostNumber(costStatementMerge.getParentNumber());
            CostStatementInfo costStatementInfo1 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo.getCostAccountNumber());
            costStatementInfo1.setCostIncurred(costStatementInfo1.getCostIncurred() + differCostIncurred);
            costStatementInfo1.setCostWaitIncurred(costStatementInfo1.getCostWaitIncurred() + differCostWaitIncurred);
            costStatementInfo1.setCostDynamics(costStatementInfo1.getCostDynamics() + differCostDynamics);
            costStatementInfo1.setDifferPrice(costStatementInfo1.getDifferPrice() + differDifferPrice);
            costStatementMapper.updateByPrimaryKeySelective(costStatementInfo1);

            CostStatementInfo costStatementInfo2 = costStatementMapper.selectSelfByCostAccountNumber(costAccountInfo.getParentNumber());
            costStatementInfo2.setCostIncurred(costStatementInfo2.getCostIncurred() + differCostIncurred);
            costStatementInfo2.setCostWaitIncurred(costStatementInfo2.getCostWaitIncurred() + differCostWaitIncurred);
            costStatementInfo2.setCostDynamics(costStatementInfo2.getCostDynamics() + differCostDynamics);
            costStatementInfo2.setDifferPrice(costStatementInfo2.getDifferPrice() + differDifferPrice);
            costStatementMapper.updateByPrimaryKeySelective(costStatementInfo2);
            return "编辑成功";
        } else if (costStatementMerge.getLevel() == 1) {
            CostStatementInfo costStatementInfo2 = costStatementMapper.selectSelfByCostAccountNumber(costStatementMerge.getParentNumber());
            costStatementInfo2.setCostIncurred(costStatementInfo2.getCostIncurred() + differCostIncurred);
            costStatementInfo2.setCostWaitIncurred(costStatementInfo2.getCostWaitIncurred() + differCostWaitIncurred);
            costStatementInfo2.setCostDynamics(costStatementInfo2.getCostDynamics() + differCostDynamics);
            costStatementInfo2.setDifferPrice(costStatementInfo2.getDifferPrice() + differDifferPrice);
            costStatementMapper.updateByPrimaryKeySelective(costStatementInfo2);
            return "编辑成功";
        } else {
            return "编辑成功";
        }
    }

}
