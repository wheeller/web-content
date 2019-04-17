<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<@l.logout />
<div>
    <form method="post" action="add" enctype="multipart/form-data">
        <input name="text" type="text" placeholder="Введите сообщение">
        <input name="tag" type="text" placeholder="Тэг">
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">"Добавить"</button>
    </form>
</div>

<div>
    <form method="get" action="/message">
        <input name="filter" tyte="text" value="${filter?ifExists  }">
        <button type="submit">"Найти"</button>
    </form>
</div>

<div>Список сообщений</div>
<#list messages as message>
<div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
    <div>
        <#if message.filename??>
            <img src="/img/${message.filename}">
        </#if>
    </div>
</div>
<#else>
Сообщений нет
</#list>
</@c.page>