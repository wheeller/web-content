<#macro login path isRegisterForm>
<form action="${path}" method="post">

    <div class="form-group">
        <div class="col-sm-4">
            <input type="text" class="form-control  ${(usernameError??)?string('is-invalid', '')}"
                   name="username" placeholder="Enter user name" value="<#if user??>${user.username}</#if>">
            <#if usernameError??>
            <div class="invalid-feedback">
                ${usernameError}
            </div>
            </#if>
        </div>
    </div>


    <div class="form-group">
        <div class="col-sm-4">
            <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   name="password" placeholder="Password">
            <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
        </#if>
        </div>
    </div>

    <#if isRegisterForm>
    <div class="form-group">
        <div class="col-sm-4">
            <input type="password" class="form-control ${(password2Error??)?string('is-invalid', '')}"
                   name="password2" placeholder="Retype password">
            <#if password2Error??>
            <div class="invalid-feedback">
                ${password2Error}
            </div>
            </#if>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-4">
            <input type="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                   name="email" placeholder="some@email.com"  value="<#if user??>${user.email}</#if>">
            <#if emailError??>
            <div class="invalid-feedback">
                ${emailError}
            </div>
            </#if>
        </div>
    </div>
    </#if>

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