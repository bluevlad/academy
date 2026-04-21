package com.academy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.academy.mocktest.mouigosa.apply.service.MouiApplyVO;

@Mapper
public interface MouiApplyMapper {
    List<MouiApplyVO> selectMouiApplyList(MouiApplyVO vo);
    int selectMouiApplyListCount(MouiApplyVO vo);
    MouiApplyVO selectMouiApplyDetail(MouiApplyVO vo);
    int insertMouiApply(MouiApplyVO vo);
    int updateMouiApply(MouiApplyVO vo);
    int deleteMouiApply(MouiApplyVO vo);
    int updateApplyStatus(MouiApplyVO vo);
    int updateExamResult(MouiApplyVO vo);
}
