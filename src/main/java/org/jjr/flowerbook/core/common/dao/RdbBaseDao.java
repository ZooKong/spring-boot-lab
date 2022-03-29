package org.jjr.flowerbook.core.common.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RdbBaseDao {

//    @Autowired
//    public SqlSessionTemplate sqlSessionTemplate;
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    /**
//     * 상세조회
//     *
//     * @param sqlId
//     * @param param
//     * @return
//     */
//    protected Object select(String sqlId, Object param) {
//        logger.debug("select {}", sqlId);
//        return sqlSessionTemplate.selectOne(this.getClass().getName() + "." + sqlId, param);
//    }
//
//    protected Object select(String sqlId) {
//        return select(sqlId, null);
//    }
//
//    /**
//     * 목록조회
//     *
//     * @param sqlId
//     * @param param
//     * @return
//     */
//    protected List<?> list(String sqlId, Object param) {
//        return sqlSessionTemplate.selectList(this.getClass().getName() + "." + sqlId, param);
//    }
//
//    protected List<?> list(String sqlId) {
//        return list(sqlId, null);
//    }
//
//    /**
//     * 등록
//     *
//     * @param sqlId
//     * @param param
//     * @return
//     */
//    protected Object insert(String sqlId, Object param) {
//        return sqlSessionTemplate.insert(this.getClass().getName() + "." + sqlId, param);
//    }
//
//    protected Object insert(String sqlId) {
//        return insert(sqlId, null);
//    }
//
//    /**
//     * 수정
//     *
//     * @param sqlId
//     * @param param
//     * @return
//     */
//    protected int update(String sqlId, Object param) {
//        return sqlSessionTemplate.update(this.getClass().getName() + "." + sqlId, param);
//    }
//
//    protected int update(String sqlId) {
//        return update(sqlId, null);
//    }
//
//    /**
//     * 삭제
//     *
//     * @param sqlId
//     * @param param
//     * @return
//     */
//    protected int delete(String sqlId, Object param) {
//        return sqlSessionTemplate.delete(this.getClass().getName() + "." + sqlId, param);
//    }
//
//    protected int delete(String sqlId) {
//        return delete(sqlId, null);
//    }

}
