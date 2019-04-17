<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<@l.logout />
List of users

<table>
    <tr>
        <th>Name</th>
        <th>Roles</th>
        <th></th>
    </tr>
    <#list users as user>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}">edit</a></td>
    </tr>
</#list>
</table>
</@c.page>
