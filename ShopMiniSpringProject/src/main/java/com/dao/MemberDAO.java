package com.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;

	public int memberAdd(MemberDTO dto) {
		int n = session.insert("memberAdd", dto);
		return n;
	}

	public MemberDTO login(Map<String, String> map) {
		MemberDTO dto = session.selectOne("login", map);
		return dto;
	}

	public MemberDTO myPage(String userid) {
		MemberDTO member= session.selectOne("idCheck2", userid);
		return member;
	}

	public int memberUpdate(MemberDTO member) {
		int n = session.update("memberUpdate", member);
		return n;
	}
	
	
}
