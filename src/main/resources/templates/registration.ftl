<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="mb-1">
    Registration new user
</div>

<b>${message?ifExists}</b>
<@l.login "/registration" true />
</@c.page>