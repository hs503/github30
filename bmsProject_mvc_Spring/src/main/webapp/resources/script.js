/**
 * 
 */
var msg_id="아이디를 입력하세요.";
var msg_pwd="비밀번호를 입력하세요.";
var msg_repwd = "비밀번호를 한번 더 입력하세요.";
var msg_pwdChk = "비밀번호가 다릅니다.";
var msg_name = "이름을 입력하세요.";
var msg_gender="성별을 선택해주세요";
var msg_birth = "생년월일 6자리를 입력해주세요.";
var msg_birth1 = "양력 음력을 선택하해주세요.";
var msg_email = "이메일을 입력하세요.";
var msg_emailChk = "이메일 형식에 일치하지 않습니다.";
var msg_confirmId = "중복확인을 해주세요.";
var msg_hp="핸드폰 번호를 입력해주세요.";
var msg_login="로그인을 해주세요!";

var insertError = "회원가입에 실패했습니다. \n확인 후 다시 시도하세요.";
var deleteError = "회원탈퇴에 실패했습니다. \n확인 후 다시 시도하세요.";
var updateError = "회원정보 수정을 실패했습니다. \n확인 후 다시 시도하세요.";
var passwdError = "입력하신 비밀번호가 일치하지 않습니다. \n확인 후 다시 시도하세요.";
function developing(){
	alert("개발중입니다...");
	return false;
}
function amountChange(form){
	var num=form.num1.value;
	var amount=form.selectAmount.value;
	window.location="amountChange.do?amount="+amount+"&num="+num;	
}
function amountCheck(){
	var amount=document.detailForm.amount.value;
	var amount1=document.detailForm.amount1.value;
	var amount3=amount*1;
	var amount4=amount1*1;

	if(!document.detailForm.amount.value){
		alert("수량을 입력해주세요!");
		document.detailForm.amount.focus();
		
		return false;		
	}else if(amount3>amount4){
		alert("재고수량을 초과하였습니다! 확인해주세요.");
		document.detailForm.amount.value="";
		document.detailForm.amount.focus();
		return false;
	}
}

function DirectPro(){
	var amount=document.detailForm.amount.value;
	var amount1=document.detailForm.amount1.value;
	var amount3=amount*1;
	var amount4=amount1*1;

	if(!document.detailForm.amount.value){
		alert("수량을 입력해주세요!");
		document.detailForm.amount.focus();
		return false;		
	}else if(amount3>amount4){
		alert("재고수량을 초과하였습니다! 확인해주세요.");
		document.detailForm.amount.value="";
		document.detailForm.amount.focus();
		return false;
	}
	
	var title=document.detailForm.title.value;
	var amount=document.detailForm.amount.value;
	var price=document.detailForm.price.value;
	var code=document.detailForm.code.value;
	var image=document.detailForm.image.value;
	
	var loc="DirectPro.bo?amount="+amount+"&price="+price+"&title="+title+"&code="+code+"&image="+image;
	window.location=loc;
}

function button_event(){
	if(confirm("정말 탈퇴하시겠습니까??")==true){
		window.location="delete.do";
	}else{//취소
		return;
	}
}

//에러메시지
function errorAlert(errorMsg) {
	alert(errorMsg);
	window.history.back(); // 이전 페이지로 이동
	return false;
}
function passwdCheck() {
	if(!document.passwdform.pwd.value) {
		alert(msg_pwd);
		document.passwdform.pwd.focus();
		return false;
	} 
}
function inputCheck(){
	if(!document.inputForm.id.value) {
		alert(msg_id);
		document.inputForm.id.focus();
		return false;
	}else if(document.inputForm.hiddenId.value == 0) {
		alert(msg_confirmId);
		document.inputForm.dupChk.focus();
		return false;
	}else if(!document.inputForm.pwd.value) {
		alert(msg_pwd);
		document.inputForm.pwd.focus();
		return false;
		
	}else if(!document.inputForm.repwd.value) {
		alert(msg_repwd);
		document.inputForm.repwd.focus();
		return false;
		
	}else if(document.inputForm.pwd.value != document.inputForm.repwd.value) {
		alert(msg_pwdChk);
		document.inputForm.repwd.focus();
		return false;
		
	}else if(!document.inputForm.name.value) {
		alert(msg_name);
		document.inputForm.name.focus();
		return false;
		
	}else if(!document.inputForm.gender.value) {
		alert(msg_gender);
		return false;
		
	}else if(!document.inputForm.birth.value) {
		alert(msg_birth);
		document.inputForm.birth.focus();
		return false;
	}else if(!document.inputForm.birth1.value) {
		alert(msg_birth1);
		return false;
	}else if(!document.inputForm.email1.value) {
		alert(msg_email);
		document.inputForm.email1.focus();
		return false;
		
	//직접입력-email2		
	}else if(!document.inputForm.email2.value) {
		alert(msg_email);
		document.inputForm.email2.focus();
		return false;

	}else if(document.inputForm.EmailCheck.value == 0) {
		alert("이메일 인증을 해주세요!");
		document.inputForm.checkEmail.focus();
		return false;
	}else if(!document.inputForm.hp1.value){
		alert(msg_hp);
		document.inputForm.hp1.focus();
		return false;
		
	}else if(!document.inputForm.hp2.value){
		alert(msg_hp);
		document.inputForm.hp2.focus();
		return false;
		
	}else if(!document.inputForm.hp3.value){
		alert(msg_hp);
		document.inputForm.hp3.focus();
		return false;
	}else if(!document.inputForm.postNum.value||!document.inputForm.addr1.value){
		alert("주소검색을 해주세요.");
		document.inputForm.postBt.focus();
		return false;
	}else if(!document.inputForm.addr2.value){
		alert("상세주소를 입력해주세요.");
		document.inputForm.addr2.focus();
		return false;
	}
	

}
function idSearch(){
	var url="idSearch.do";
	window.open(url, "confirm", "menubar=no, width=500, height=330");	
}
function SignUp(){
	opener.window.location="inputForm.do";
	self.close();
}
function pwdSearch(){
	var url="pwdSearch.do";
	window.open(url, "confirm", "menubar=no, width=500, height=330");	
}

function confirmId() {
	if(!document.inputForm.id.value) {
		alert(msg_id);
		document.inputForm.id.focus();
		return false;
	} 
	
	/*
	 * window.open("파일명", "윈도우명", "창속성");
	 * url="주소?속성=" + 속성값;  <-- get방식
	 */	
	var url="confirmId.do?id=" + document.inputForm.id.value;
	window.open(url, "confirm", "menubar=no, width=500, height=330");	
}
function confirmEmail() {
	if(!document.inputForm.email1.value) {
		alert(msg_email);
		document.inputForm.email1.focus();
		return false;
	}else if(!document.inputForm.email2.value){
		alert(msg_email);
		document.inputForm.email2.focus();
		return false;
	}
	var email1=document.inputForm.email1.value;
	var email2=document.inputForm.email2.value;
	var email=email1+"@"+email2;
	var url="confirmEmail.do?email=" + email;
	window.open(url, "confirm", "menubar=no, width=500, height=330");	
}
function emailCheck(form){
	switch(form.email3.value){
	case "0":
		form.email2.value="";
		form.email2.focus();
		break;
	default:
		form.email2.value=form.email3.value;
		break;
	}
}

function buyCntCheck(form){
	switch(form.buyCntSelect.value){
	case "0":
		form.amount.value="";
		form.amount.focus();
		break;
	default:
		form.amount.value=form.buyCntSelect.value;
		break;
	}
}
/*
 * opener : window 객체의 open() 메소드로 열린 새창(=중복확인창)에서, 열어준 부모창(=회원가입창)에 접근할 때 사용
 * self.close(); 메시지없이 현재창을 닫을 때 사용
 * hiddenId : 중복확인 버튼 클릭 여부 체크(0: 클릭안함, 1:클릭함)를 클릭함으로 설정 
 */
function setEmail(random,CheckNum){
	if((random*1)==(CheckNum*1)){
		alert("인증에 성공하였습니다.");
		opener.document.inputForm.EmailCheck.value="1";
		self.close();
	}else{
		alert("인증번호가 다릅니다.");
		return false;
	}
}
function idSearchPro(value){
	if(!value){
		alert("검색할 메일을 입력해주세요!");
		return false;
	}
}
function pwdSearchPro(value){
	if(!value){
		alert("검색할 ID를 입력해주세요!");
		return false;
	}
}
function SearchId(id,randomNum,inputNum){
	if((randomNum*1)==(inputNum*1)){
		window.location="idView.do?id="+id;
	}else{
		alert("인증번호가 다릅니다.");
		return false;
	}
}
function SearchPwd(pwd,randomNum,inputNum){
	if((randomNum*1)==(inputNum*1)){
		window.location="pwdView.do?pwd="+pwd;
	}else{
		alert("인증번호가 다릅니다.");
		return false;
	}
}
function setId(id) {
	opener.document.inputForm.id.value=id;
	opener.document.inputForm.hiddenId.value="1";
	self.close();
}
