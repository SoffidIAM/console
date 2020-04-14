<%--
grid.dsp

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue Oct 25 16:56:36     2005, Created by tomyeh
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
--%><%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<table id="${self.uuid}" ${self.outerAttrs}${self.innerAttrs}>
<c:if test="${!empty self.columns}">
	<thead>
		<c:forEach var="head" items="${self.heads}">
			${z:redraw(head, null)}
		</c:forEach>
	</thead>
</c:if>
	${z:redraw(self.rows, null)}
</table>
