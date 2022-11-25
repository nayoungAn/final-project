package com.greedy.onoff.member.dto;

import java.sql.Date;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class MemberDto implements UserDetails{
	
	private Long memberCode;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberPhone;
	private String memberBirthday;
	private String memberGender;
	private String memberAddress;
	private String memberStatus;
	private String memberEmail;
	private String memberRole;
	private Date memberRegisterDate;
	private String memberImageUrl;
	private MultipartFile memberImage;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return memberPassword;
	}
	
	@Override
	public String getUsername() {
		return memberId;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
