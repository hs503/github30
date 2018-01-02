/**
 * 
 */
var msg_code="물류코드를 입력해주세요.";
var msg_image="이미지를 넣어주세요.";
var msg_title = "책 제목을 입력해주세요.";
var msg_author = "책 저자를 입력해주세요.";
var msg_price = "판매가를 입력해주세요.";
var msg_amount="재고수량을 입력해주세요.";


var insertError = "책 등록에 실패했습니다. \n확인 후 다시 시도하세요.";
var deleteError = "책 삭제에 실패했습니다. \n확인 후 다시 시도하세요.";
var updateError = "책정보 수정을 실패했습니다. \n확인 후 다시 시도하세요.";

//에러메시지
function errorAlert(errorMsg) {
	alert(errorMsg);
	window.history.back(); // 이전 페이지로 이동
	return false;
}

function bookInsert(){

	if(!document.bookInsertForm.code.value) {
		alert(msg_code);
		document.bookInsertForm.code.focus();
		return false;
	}else if(!document.bookInsertForm.image.value) {
		alert(msg_image);
		document.bookInsertForm.image.focus();
		return false;		
	}else if(!document.bookInsertForm.title.value) {
		alert(msg_title);
		document.bookInsertForm.title.focus();
		return false;		
	}else if(!document.bookInsertForm.author.value) {
		alert(msg_author);
		document.bookInsertForm.author.focus();
		return false;		
	}else if(!document.bookInsertForm.price.value) {
		alert(msg_price);
		document.bookInsertForm.price.focus();
		return false;		
	}else if(!document.bookInsertForm.amount.value) {
		alert(msg_amount);
		document.bookInsertForm.amount.focus();
		return false;		
	}
}

function bookUpdate1() {
	if(!document.form1.image.value) {
		alert(msg_image);
		document.form1.image.focus();
		return false;
	}else if(!document.form1.title.value) {
		alert(msg_title);
		document.form1.title.focus();
		return false;		
	}else if(!document.form1.author.value) {
		alert(msg_author);
		document.form1.author.focus();
		return false;		
	}else if(!document.form1.price.value) {
		alert(msg_price);
		document.form1.price.focus();
		return false;		
	}else if(!document.form1.amount.value) {
		alert(msg_amount);
		document.form1.amount.focus();
		return false;		
	}
}
function bookUpdate2() {
	if(!document.form2.image.value) {
		alert(msg_image);
		document.form2.image.focus();
		return false;
	}else if(!document.form2.title.value) {
		alert(msg_title);
		document.form2.title.focus();
		return false;		
	}else if(!document.form2.author.value) {
		alert(msg_author);
		document.form2.author.focus();
		return false;		
	}else if(!document.form2.price.value) {
		alert(msg_price);
		document.form2.price.focus();
		return false;		
	}else if(!document.form2.amount.value) {
		alert(msg_amount);
		document.form2.amount.focus();
		return false;		
	}
}function bookUpdate3() {
	if(!document.form3.image.value) {
		alert(msg_image);
		document.form3.image.focus();
		return false;
	}else if(!document.form3.title.value) {
		alert(msg_title);
		document.form3.title.focus();
		return false;		
	}else if(!document.form3.author.value) {
		alert(msg_author);
		document.form3.author.focus();
		return false;		
	}else if(!document.form3.price.value) {
		alert(msg_price);
		document.form3.price.focus();
		return false;		
	}else if(!document.form3.amount.value) {
		alert(msg_amount);
		document.form3.amount.focus();
		return false;		
	}
}function bookUpdate4() {
	if(!document.form4.image.value) {
		alert(msg_image);
		document.form4.image.focus();
		return false;
	}else if(!document.form4.title.value) {
		alert(msg_title);
		document.form4.title.focus();
		return false;		
	}else if(!document.form4.author.value) {
		alert(msg_author);
		document.form4.author.focus();
		return false;		
	}else if(!document.form4.price.value) {
		alert(msg_price);
		document.form4.price.focus();
		return false;		
	}else if(!document.form4.amount.value) {
		alert(msg_amount);
		document.form4.amount.focus();
		return false;		
	}
}function bookUpdate5() {
	if(!document.form5.image.value) {
		alert(msg_image);
		document.form5.image.focus();
		return false;
	}else if(!document.form5.title.value) {
		alert(msg_title);
		document.form5.title.focus();
		return false;		
	}else if(!document.form5.author.value) {
		alert(msg_author);
		document.form5.author.focus();
		return false;		
	}else if(!document.form5.price.value) {
		alert(msg_price);
		document.form5.price.focus();
		return false;		
	}else if(!document.form5.amount.value) {
		alert(msg_amount);
		document.form5.amount.focus();
		return false;		
	}
}


