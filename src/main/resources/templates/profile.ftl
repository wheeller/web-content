<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h5>${username}</h5>

<form method="post">

    <div class="form-group">
        <div class="col-sm-4">
            <input type="email" class="form-control" name="email" placeholder="some@email.com" value="${email!''}">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4">
            <input type="password" class="form-control" name="password" placeholder="Password">
        </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    <button type="submit" class="btn btn-primary">Save</button>

</@c.page>