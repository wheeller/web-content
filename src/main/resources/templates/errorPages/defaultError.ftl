<#import "parts/common.ftl" as c>


<@c.page>
<#if message??>${message}</#if>

<h3>This is default exception page</h3>
<p>Exception: <b>${exception}</b></p>

</@c.page>