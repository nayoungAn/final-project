//package com.greedy.onoff.sms.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.greedy.onoff.classes.entity.ClassesHistory;
//
//public interface SmsRepository extends JpaRepository<ClassesHistory, String>{
//
//	/* 문자 전송 대상 조회 */
//	
////	@Query("SELECT c " +
////			 "FROM ClassesHistory c " +
////			"WHERE c.className = :className ")
//	
//	@Query("SELECT h " +
//			 "FROM ClassesHistory h " +
//			"WHERE h.OpenClasses.className LIKE className ")
//	
//	List<ClassesHistory> findByClassName(String className);
//	//List<ClassesHistory> findByMemberName(String memberName);		
//	
////	@Query("SELECT H.CLASS_HISTORY_CODE\r\n"
////			+ "    , M.MEMBER_NAME\r\n"
////			+ "    , M.MEMBER_PHONE\r\n"
////			+ "    , C.CLASS_NAME\r\n"
////			+ "    FROM TBL_CLASSES_HISTORY H\r\n"
////			+ "    JOIN TBL_CLASSES C ON (H.CLASS_CODE = C.CLASS_CODE)\r\n"
////			+ "    JOIN TBL_MEMBER M ON(H.MEMBER_CODE = H.CLASS_CODE)\r\n"
////			+ "    WHERE H.CLASS_STATUS LIKE '수강중'")
//
////	@Query("SELECT m from Member m WHERE m.memberRole='ROLE_STUDENT'")
////	Page<Member> findByMemberNameContains(Pageable pageable, String memberName);
//
//
//}
