package com.ruoyi.corework.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.corework.constant.AssetOperStatus;
import com.ruoyi.corework.domain.Asset;
import com.ruoyi.corework.domain.AssetOper;
import com.ruoyi.corework.domain.dto.AssetOperQueryDto;
import com.ruoyi.corework.domain.dto.AssetOperStatDto;
import com.ruoyi.corework.domain.vo.AssetOperStatDayVo;
import com.ruoyi.corework.mapper.AssetMapper;
import com.ruoyi.corework.mapper.AssetOperMapper;
import com.ruoyi.corework.service.IAssetOperService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ClassName:AssetOperServiceImpl
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/6/25-16:31
 * @Version 1.0
 */

@Service
public class AssetOperServiceImpl implements IAssetOperService {

    @Autowired
    private AssetOperMapper assetOperMapper;

    @Override
    public List<AssetOper> selectAssetOperList(AssetOperQueryDto assetOperQueryDto) {
        return assetOperMapper.selectAssetOperList(assetOperQueryDto);
    }

    @Override
    public AssetOperStatDayVo selectAssetOperByDay(AssetOperStatDto assetOperStatDto) {

        if (assetOperStatDto.getStartDate() == null && assetOperStatDto.getEndDate() == null) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0); // 零点
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            assetOperStatDto.setEndDate(cal.getTime());

            cal.add(Calendar.DAY_OF_MONTH, -6);
            assetOperStatDto.setStartDate(cal.getTime());
        }

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(assetOperStatDto.getEndDate());
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        assetOperStatDto.setEndDate(endCal.getTime());

        List<String> dateList = new ArrayList<>();
        List<Long> inList = new ArrayList<>();
        List<Long> outList = new ArrayList<>();

        List<Map<String, Object>> mapList = assetOperMapper.selectAssetOperByDay(assetOperStatDto);
        if (!mapList.isEmpty()) {
            for (Map<String, Object> map : assetOperMapper.selectAssetOperByDay(assetOperStatDto)) {
                dateList.add(map.get("day").toString());
                inList.add(Long.parseLong(map.get("total_in").toString()));
                outList.add(Long.parseLong(map.get("total_out").toString()));
            }
        }
        AssetOperStatDayVo assetOperStatDayVo = new AssetOperStatDayVo();
        assetOperStatDayVo.setDateList(dateList);
        assetOperStatDayVo.setInList(inList);
        assetOperStatDayVo.setOutList(outList);
        return assetOperStatDayVo;
    }


}
