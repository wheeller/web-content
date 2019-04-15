<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Registration new user

<#if message??>
<br>
<b>${message}</b>
</#if>
<@l.login "/registration" />
</@c.page>