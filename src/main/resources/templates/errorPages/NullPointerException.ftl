<#import "parts/common.ftl" as c>


<@c.page>
<#if message??>${message}</#if>

<h3>We got Null Pointer Exception</h3>
<p>Exception: <b>${exception}</b></p>

</@c.page>