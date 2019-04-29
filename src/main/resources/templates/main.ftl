<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<#if message??>
    ${message}
</#if>
<div>
    <#if user??>
        <h5>Hello, ${user.username}!</h5>
    <#else>
        <h5>Hello, new commer!</h5>
        <@l.login "/" false />
    </#if>

This is learning project.

</div>
</@c.page>