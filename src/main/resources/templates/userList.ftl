<#import "parts/common.ftl" as c>

<@c.page>
List of users

<table class="table">
    <head>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Roles</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </head>
    <tbody>
    <#list users as user>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}">Edit</a></td>
        <!--<td><a href="/user/delete/${user.id}">delete</a></td>-->
        <td>
            <!-- trigger modal -->
            <a href="/user/delete/${user.id}" data-toggle="modal" role="button" data-target="#delete${user.id}">Delete</a>
        </td>
    </tr>

    <!-- Modal -->
    <div class="modal fade" id="delete${user.id}" tabindex="-1" role="dialog" aria-labelledby="deleteModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModal">Confirm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Delete user and his messages
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <a class="btn btn-primary" href="/user/delete/${user.id}" role="button">Delete</a>
                </div>
            </div>
        </div>
    </div>

    </#list>
    </tbody>
</table>
</@c.page>
