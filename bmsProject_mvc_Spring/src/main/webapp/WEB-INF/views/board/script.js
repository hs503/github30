/**
 * 
 */
var msg_pwd="비밀번호를 입력해주세요!";
var msg_writer="작성자를 입력하세요!";

var pwdError="비밀번호가 일치하지 않습니다.\n 확인 후 다시 시도하세요.";
var updateError="글 수정에 실패하였습니다. \n 확인 후 다시 시도하세요.";
var deleteError="글 삭제에 실패하였습니다. \n 확인 후 다시 시도하세요.";
var insertError="글 작성에 실패하였습니다. \n 확인 후 다시 시도하세요.";
var msg_subject="제목을 입력해주세요.";

function pwdFocus(){
	document.pwdForm.pwd.focus();
}
//글수정 들어가가전 비밀번호 입력이 안됬을때
function pwdCheck(){
	if(!document.pwdForm.pwd.value){
		alert(msg_pwd);
		document.pwdForm.pwd.focus();
		return false;
	}	
}
function boardSerch(value){
	var serchValue=value;
	
	var loc="boardSerch.bd?serch="+encodeURIComponent(serchValue);
	alert(serchValue+"검색");
	
	window.location=loc;
}

function modifyFocus(){
	document.modifyform.subject.focus();
}
//글수정정보가 입력이 안됬을때
function modifyCheck(){
	if(!document.modifyform.subject.value){
		document.modifyform.subject.focus();
		alert(msg_subject);
		return false;
	}else if(!document.modifyform.pwd.value){
		document.modifyform.pwd.focus();
		alert(msg_pwd);
		return false;
	}
}


function errorAlert(msg){
	alert(msg);
	window.history.back();
}
function writeFocus(){
	document.writeform.writer.focus();
	
}
//글작성정보가 입력이 안됬을때
function writeCheck(){
	if(!document.writeform.writer.value){
		document.writeform.writer.focus();
		alert(msg_writer);
		return false;
	}else if(!document.writeform.pwd.value){
		document.writeform.pwd.focus();
		alert(msg_pwd);
		return false;
	}else if(!document.writeform.subject.value){
		document.writeform.subject.focus();
		alert(msg_subject);
		return false;
	}
}