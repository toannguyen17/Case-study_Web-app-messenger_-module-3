<%--
  Created by IntelliJ IDEA.
  User: toanv
  Date: 27/06/2020
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="register_box" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="title_register" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="title_register">Đăng ký</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="form_register" method="POST" action="/register" novalidate>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text fix_login L_h_1 w53">Họ</div>
							</div>
							<input id="last_name_register" type="text" class="form-control shadow-none" name="last_name" value="" minlength="2" maxlength="32" required autofocus />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text fix_login L_h_1 w53">Tên</div>
							</div>
							<input id="first_name_register" type="text" class="form-control shadow-none" name="first_name" value="" minlength="2" maxlength="32" required />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text fix_login"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-telephone" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									<path fill-rule="evenodd" d="M3.925 1.745a.636.636 0 0 0-.951-.059l-.97.97c-.453.453-.62 1.095-.421 1.658A16.47 16.47 0 0 0 5.49 10.51a16.471 16.471 0 0 0 6.196 3.907c.563.198 1.205.032 1.658-.421l.97-.97a.636.636 0 0 0-.06-.951l-2.162-1.682a.636.636 0 0 0-.544-.115l-2.052.513a1.636 1.636 0 0 1-1.554-.43L5.64 8.058a1.636 1.636 0 0 1-.43-1.554l.513-2.052a.636.636 0 0 0-.115-.544L3.925 1.745zM2.267.98a1.636 1.636 0 0 1 2.448.153l1.681 2.162c.309.396.418.913.296 1.4l-.513 2.053a.636.636 0 0 0 .167.604L8.65 9.654a.636.636 0 0 0 .604.167l2.052-.513a1.636 1.636 0 0 1 1.401.296l2.162 1.681c.777.604.849 1.753.153 2.448l-.97.97c-.693.693-1.73.998-2.697.658a17.47 17.47 0 0 1-6.571-4.144A17.47 17.47 0 0 1 .639 4.646c-.34-.967-.035-2.004.658-2.698l.97-.969z"/>
								</svg></div>
							</div>
							<input id="phone_register" type="text" class="form-control shadow-none" name="phone" value="" placeholder="Số điện thoại" pattern="^[\+]?(?:[(][0-9]{1,3}[)]|(?:84|0))[0-9]{9,10}$" required />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text fix_login"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-lock-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									<path d="M2.5 9a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9z"/>
									<path fill-rule="evenodd" d="M4.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"/>
								</svg></div>
							</div>
							<input id="password_register" type="password" class="form-control shadow-none" name="password" placeholder="Mật khẩu" minlength="6" maxlength="52" required/>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text fix_login"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-lock-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									<path d="M2.5 9a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9z"/>
									<path fill-rule="evenodd" d="M4.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"/>
								</svg></div>
							</div>
							<input id="password_confirm_register" type="password" class="form-control shadow-none" name="password-confirm" placeholder="Nhập lại mật khẩu" minlength="6" maxlength="52" required />
						</div>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-blue m_w300">
							Đăng ký
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
