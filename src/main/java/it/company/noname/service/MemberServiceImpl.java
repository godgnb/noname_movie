package it.company.noname.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.company.noname.domain.MemberVO;
import it.company.noname.mapper.memberMapper;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MemberServiceImpl implements memberService {
	
	private memberMapper mapper;
	
	@Override
	public List<MemberVO> getMemberList() {
		return mapper.getMemberList();
	}

	@Override
	public MemberVO getMember(String email) {
		return mapper.getMember(email);
	}

	@Override
	public int insertMember(MemberVO memberVO) {
		return mapper.insertMember(memberVO);
	}

	@Override
	public int deleteMember(String email) {
		return mapper.deleteMember(email);
	}

	@Override
	public int updateMember(MemberVO memberVO) {
		return mapper.updateMember(memberVO);
	}

}
