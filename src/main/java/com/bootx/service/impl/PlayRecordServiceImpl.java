
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.PlayRecord;
import com.bootx.service.PlayRecordService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service - 素材目录
 * 
 * @author blackboy
 * @version 1.0
 */
@Service
public class PlayRecordServiceImpl extends BaseServiceImpl<PlayRecord, Long> implements PlayRecordService {

    @Override
    public Page<Map<String, Object>> findPageJdbc(Pageable pageable) {
        return null;
    }

    @Override
    public Map<String, Object> findJdbc(Long id) {
        return null;
    }
}