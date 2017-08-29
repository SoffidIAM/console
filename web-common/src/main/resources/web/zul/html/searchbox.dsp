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
<span id="${self.uuid}"${self.outerAttrs}${self.innerAttrs} z.type="attributesearchbox.attributesearchbox.Searchbox">
<div style="float:right; padding-top:4px; padding-right:8px;">
	<a href="#" id="${self.uuid}!mode" class="change-mode-label">${self.changeModeLabel}</a>
</div>
<c:if test="${!self.advancedMode}">
	<ul>
		<c:forEach var="child" items="${self.attributeSearchBoxesAndPopups}">
			<li>
			${z:redraw(child, null)}
			</li>
		</c:forEach>
		${z:redraw(self.addAttributeButton, null)}
		<img id="${self.uuid}!search" class="search-icon" src="${self.searchIconUrl}"/>
	</ul>	
</c:if>
<c:if test="${self.advancedMode}">
	${z:redraw(self.advancedSearch, null)}
	<a href="#" class="search-link" id="${self.uuid}!search">
		<img class="search-icon" src="${self.searchIconUrl}"/>
	</a>
</c:if>
<c:forEach var="child" items="${self.otherChildren}">
	${z:redraw(child, null)}
</c:forEach>
</span>

