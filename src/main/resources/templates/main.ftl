<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="alert alert-danger" role="alert">
        ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
</#if>

<#if message??>
    <!--<div class="alert alert-${messageType}" role="alert">-->
        <div class="alert alert-success" role="alert">
        ${message}
    </div>
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