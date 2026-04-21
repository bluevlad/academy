package com.academy.mocktest.stats.person.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academy.mapper.PersonStatsMapper;

@Service
public class PersonStatsService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final PersonStatsMapper personStatsMapper;

    @Autowired
    public PersonStatsService(PersonStatsMapper personStatsMapper) {
        this.personStatsMapper = personStatsMapper;
    }

    public List<PersonStatsVO> getPersonStatsList(PersonStatsVO vo) { return personStatsMapper.selectPersonStatsList(vo); }
    public int getPersonStatsListCount(PersonStatsVO vo) { return personStatsMapper.selectPersonStatsListCount(vo); }
    public PersonStatsVO getPersonStatsDetail(PersonStatsVO vo) { return personStatsMapper.selectPersonStatsDetail(vo); }
    public List<PersonStatsVO> getPersonSubjectStats(PersonStatsVO vo) { return personStatsMapper.selectPersonSubjectStats(vo); }
    public PersonStatsVO getPersonTotalStats(PersonStatsVO vo) { return personStatsMapper.selectPersonTotalStats(vo); }
}
