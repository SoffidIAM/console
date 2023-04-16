<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<div id="${self.uuid}" z.type="zul.fold.Fold" ${self.outerAttrs}${self.innerAttrs}>
<div id="${self.uuid}!header" class="foldheader">
<img src="/soffid/img/foldUnfold.svg" class="foldicon"><span>${self.label}</span>
</div>
<div id="${self.uuid}!body" class="foldbody">
<c:forEach var="child" items="${self.children}">
${z:redraw(child, null)}
</c:forEach></div>
</div>