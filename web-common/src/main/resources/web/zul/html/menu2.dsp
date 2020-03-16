<%--
attributesearchbox.dsp

Copyright (C) 2017 Soffid IAM. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
--%><%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<div id="${self.uuid}"${self.outerAttrs}${self.innerAttrs} z.type="menu2.menu2.Menu2">
	<div id="${self.uuid}!starter" class="menu2starter"/>${self.imgTag}</div>
	<div id="${self.uuid}!modal" class="menu2modal">
		<div id="${self.uuid}!dropdown" class="menu2dropdown">
			<table id="${self.uuid}!table">
				<tbody>
					<c:forEach var="child" items="${self.children}">
						${z:redraw(child, null)}
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
