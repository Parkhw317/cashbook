package vo;

public class Member {
   private String memberId;
   private String memberPw;
   private String memberName;
   private String memberAddress;
   private String memberGender;
   private String memberPhone;
   private String memberEmail;
   private String createDate;
   private String updateDate;
   
   
   
   
@Override
public String toString() {
	return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName + ", memberAddress="
			+ memberAddress + ", memberGender=" + memberGender + ", memberPhone=" + memberPhone + ", memberEmail="
			+ memberEmail + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public String getMemberPw() {
	return memberPw;
}
public void setMemberPw(String memberPw) {
	this.memberPw = memberPw;
}
public String getMemberName() {
	return memberName;
}
public void setMemberName(String memberName) {
	this.memberName = memberName;
}
public String getMemberAddress() {
	return memberAddress;
}
public void setMemberAddress(String memberAddress) {
	this.memberAddress = memberAddress;
}
public String getMemberGender() {
	return memberGender;
}
public void setMemberGender(String memberGender) {
	this.memberGender = memberGender;
}
public String getMemberPhone() {
	return memberPhone;
}
public void setMemberPhone(String memberPhone) {
	this.memberPhone = memberPhone;
}
public String getMemberEmail() {
	return memberEmail;
}
public void setMemberEmail(String memberEmail) {
	this.memberEmail = memberEmail;
}
public String getCreateDate() {
	return createDate;
}
public void setCreateDate(String createDate) {
	this.createDate = createDate;
}
public String getUpdateDate() {
	return updateDate;
}
public void setUpdateDate(String updateDate) {
	this.updateDate = updateDate;
}

   
}