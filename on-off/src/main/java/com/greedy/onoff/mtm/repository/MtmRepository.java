//package com.greedy.onoff.mtm.repository;
//
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.EntityGraph;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.greedy.onoff.mtm.entity.Mtm;
//
//public interface MtmRepository extends JpaRepository<Mtm, Long>{
//	
//	@EntityGraph(attributePaths = {"reList"})
//	@Query("SELECT m " + 
//			"FROM Mtm m " +
//			"LEFT JOIN m.reList r on (r.reStatus = 'N') " +
//			"WHERE m.mtmDelete = 'N' " +
//			"AND m.classes.classCode = :classCode "
//			)
//	Page<Mtm> findByClassCode(Pageable pageable, @Param("classCode") Long classCode);
////	@Query(value="SELECT M.MTM_CODE, M.MTM_TITLE, C.CLASS_NAME, ME.MEMBER_NAME, M.MTM_DATE, R.RE_CODE, R.RE_TITLE, R.RE_DATE " + 
////			"FROM TBL_MTM M " +
////			"JOIN TBL_MEMBER ME ON (M.MEMBER_CODE = ME.MEMBER_CODE) " +
//// 			"JOIN TBL_CLASSES C ON(M.CLASS_CODE = C.CLASS_CODE) " + 
////			"LEFT JOIN TBL_RE R ON (M.MTM_CODE = R.MTM_CODE AND R.RE_STATUS = 'N') " +
////			"WHERE M.MTM_DELETE = 'N' " +
////			"AND C.CLASS_CODE = :classCode " +
////			"ORDER BY M.MTM_CODE DESC " +
////			"offset :offset rows fetch next :next rows only", nativeQuery=true)
////	List<Mtm> findByClassCode(@Param("classCode") Long classCode, @Param("offset") int offset, @Param("next") int next);
//	
//	/*@Query("SELECT m " + 
//			"FROM Mtm m " +
//			"WHERE m.mtmDelete = 'N' " +
//			"AND m.reList.mtmCode.reStatus = 'N' "+
//			"AND m.classes.classCode = :classCode"
//			)*/
//
//}
