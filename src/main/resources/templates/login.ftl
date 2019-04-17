<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h5>Введите логин и пароль для авторизации</h5>
<@l.login "/login" false/>
</@c.page>