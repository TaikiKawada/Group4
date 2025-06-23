<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<c:if test="${not empty toastMessage}">
	<div class="toast-container position-fixed bottom-0 end-0 p-3">
		<div
			class="toast text-bg-${toastType == 'error' ? 'danger' : 'primary'} show"
			role="alert" aria-ilve="assertive" aria-atomic="true">
			<div class="d-flex">
				<div class="toast-body">${toastMessage}</div>
				<button type="button" class="btn-close btn-close-white me-2 m-auto"
					data-bs-dismiss="toast" aria-label="Close"></button>
			</div>
		</div>
	</div>
</c:if>