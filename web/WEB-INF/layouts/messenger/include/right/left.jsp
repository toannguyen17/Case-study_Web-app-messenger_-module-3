<%--
  Created by IntelliJ IDEA.
  User: toanv
  Date: 29/06/2020
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="_4_j4">
	<div class="uiScrollableArea" style="max-height: calc(calc(100vh) - calc(102px));">
		<div class="h-100 overflow-auto">
			<div></div>
			<div id="messangers" style="padding: 10px;">
			</div>
			<div></div>
		</div>
	</div>

	<div class="d-flex _4rv3 _7og6 w-100" style="height: 46px;">
		<div class="d-flex align-items-center ml-3 w-100 h-100">
			<div class="w-100">
				<div class="d-flex align-items-center w-100 h-100" style="background: #f1f1f1; border-radius: 20px; padding: 0 16px;">
					<form id="form_messenger" class="w-100" method="post">
						<input id="input_messenger" style="height: 38px;" autocomplete="off" class="border-0 w-100 bg-transparent" placeholder="Nhập tin nhắn" spellcheck="false" type="text" value="">
					</form>
				</div>
			</div>
		</div>
		<div id="btn_send" class="c_pointer d-flex align-items-center justify-content-center h-100 pl-3 pr-3" style="width: 50px">
			<svg style="color: #FF5722;" width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-cursor-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
				<path fill-rule="evenodd" d="M14.082 2.182a.5.5 0 0 1 .103.557L8.528 15.467a.5.5 0 0 1-.917-.007L5.57 10.694.803 8.652a.5.5 0 0 1-.006-.916l12.728-5.657a.5.5 0 0 1 .556.103z"/>
			</svg>
		</div>
	</div>
</div>
