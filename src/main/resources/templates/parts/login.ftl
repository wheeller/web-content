<#macro login path isRegisterForm>
<form action="${path}" method="post">

    <div class="form-group">
        <div class="col-sm-4">
            <input type="text" class="form-control" name="username" placeholder="Enter user name">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4">
            <input type="password" class="form-control" name="password" placeholder="Password">
        </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <button type="submit" class="btn btn-primary"> <#if isRegisterForm>Register<#else>Login</#if></button>
    <#if !isRegisterForm><a href="/registration">New user</a></#if>
</form>
</#macro>


<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary"> Logout</button>
</form>
</#macro>