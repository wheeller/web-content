<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
    <#if user??>
        <h5>Hello, ${user.username}!</h5>
    <#else>
        <h5>Hello, new commer!</h5>
        <@l.login "/login" false />
    </#if>

This is learning project.

</div>
</@c.page>