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
<li id="${self.uuid}"${self.outerAttrs}${self.innerAttrs} z.type="attributesearchbox.attributesearchbox.Attributesearchbox">
 <c:forEach var="child" items="${self.children}">${z:redraw(child, null)}</c:forEach>
 <button type="button" class="criteria-selector" id="${self.uuid}!button">
  <div class="criteria-wrap">
   <span class="fieldLabel">${c:escapeXML(self.fieldLabel)}</span>
   ${c:escapeXML(self.humanExpression)}
  </div>
 </button>
 <a href="#" class="remove-filter" title="${c:l('criterion.remove')}" id="${self.uuid}!close">
 	<img src="${self.imageUrl}" class="${self.sclass}-remove"/>
 </a>
</li>

