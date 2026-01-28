/*회원가임 유효성 검사*/
function signupForm() {
    console.log('회원가입폼');

    let form = document.signup_form;
    if (form.id.value === '') {
        alert('새로운 id 입력');
        form.id.focus();
    } else if (form.pw.value === '') {
        alert('새로운 pw 입력!!');
        form.pw.focus();
    } else if (form.mail.value === '') {
        alert('새로운 email 입력!!');
        form.mail.focus();
    } else if (form.phone.value === '') {
        alert('새로운 전화번호 입력!!');
        form.phone.focus();
    } else {
        form.submit();
    }
}


