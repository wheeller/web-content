<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<b>${message?ifExists}</b>
<div class="mb-1">
    Registration new user
</div>
<@l.login "/registration" true />
</@c.page>