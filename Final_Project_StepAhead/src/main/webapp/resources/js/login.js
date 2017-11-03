/**
 * 
 */

function checkInput() {
	var uname = document.getElementById("uname").value.trim();
	var pwd = document.getElementById("pwd").value.trim();

	if ((uname == null || uname == "") && (pwd == null || pwd == "")) {
		if (uname == "" || uname == null) {
			document.getElementById("errorUname").style.display = "block";
			return false;
		} else if (pwd == "" || pwd == null) {
			document.getElementById("errorPwd").style.display = "block";
			return false;
		} else {
			document.getElementById("errorUname").style.display = "block";
			document.getElementById("errorPwd").style.display = "block";
			return false;
		}
	}
}