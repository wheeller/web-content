<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Введите логин и пароль для авторизации
<br>
<@l.login "/login" />
<br>
<a href="/registration">Новый пользователь</a>
</@c.page>