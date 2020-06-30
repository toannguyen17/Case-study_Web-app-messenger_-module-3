<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: toanv
  Date: 29/06/2020
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex align-items-center h-56 head_shadow">
	<div class="d-flex align-items-center mr-auto overflow-hidden ml-3">
		<img id="avatar_header" class="rounded-circle" alt="" src="<c:out value="${requestScope['user'].getElement().getUrlAvatar()}"></c:out>" height="40" width="40">
		<div class="contact_sub text-truncate">
			<span><c:out value="${requestScope['user'].getElement().getLast_name()}"></c:out> <c:out value="${requestScope['user'].getElement().getFirst_name()}"></c:out></span>
		</div>
	</div>
	<div id="btn_setting_more" class="c_pointer d-flex align-items-center justify-content-center h-100 pl-3 pr-3" style="width: 50px">
		<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-gear-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M9.405 1.05c-.413-1.4-2.397-1.4-2.81 0l-.1.34a1.464 1.464 0 0 1-2.105.872l-.31-.17c-1.283-.698-2.686.705-1.987 1.987l.169.311c.446.82.023 1.841-.872 2.105l-.34.1c-1.4.413-1.4 2.397 0 2.81l.34.1a1.464 1.464 0 0 1 .872 2.105l-.17.31c-.698 1.283.705 2.686 1.987 1.987l.311-.169a1.464 1.464 0 0 1 2.105.872l.1.34c.413 1.4 2.397 1.4 2.81 0l.1-.34a1.464 1.464 0 0 1 2.105-.872l.31.17c1.283.698 2.686-.705 1.987-1.987l-.169-.311a1.464 1.464 0 0 1 .872-2.105l.34-.1c1.4-.413 1.4-2.397 0-2.81l-.34-.1a1.464 1.464 0 0 1-.872-2.105l.17-.31c.698-1.283-.705-2.686-1.987-1.987l-.311.169a1.464 1.464 0 0 1-2.105-.872l-.1-.34zM8 10.93a2.929 2.929 0 1 0 0-5.86 2.929 2.929 0 0 0 0 5.858z" /></svg>
	</div>
</div>

<div id="setting_more" class="dropdown-menu">
	<div id="btn_changer_avatar" class="dropdown-item">Đổi ảnh đại diện</div>
	<div class="dropdown-divider"></div>
	<div id="logout" class="dropdown-item">Đăng xuất</div>
</div>
