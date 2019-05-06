<#import "parts/common.ftl" as c>

<@c.page>

<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/message" class="form-inline">
            <input name="filter" tyte="text" value="${filter?ifExists}" placeholder="Search by tag" class="mr-3">
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new message
</a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" action="add" enctype="multipart/form-data">
            <div class="form-group">
                <input name="text" type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.text}</#if>" placeholder="Введите сообщение">
                <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
                </#if>
            </div>

            <div class="form-group">
                <input name="tag" type="text" value="<#if message??>${message.tag}</#if>" placeholder="Тэг">
                <#if tagError??>
                <div class="invalid-feedback">
                    ${tagError}
                </div>
            </#if>
            </div>

            <div class="custom-file mb-3">
                <input type="file" class="custom-file-input" name="file" id="customFile">
                <label class="custom-file-label col-sm-5" for="customFile">Choose image</label>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </div>
</div>


<div>
    <h5>Список сообщений</h5>
</div>

<div class="card-columns">
<#list messages as message>
    <div class="card my-3" style="width: 18rem;">
        <div class="card-header text-muted">
            ${message.authorName}
        </div>
        <#if message.filename??>
            <img src="/img/${message.filename}" class="card-img-top" alt="${message.filename}">
        </#if>
        <div class="card-body">
            <p class="card-text">${message.text}</p>
            <i class="card-title">${message.tag}</i>
        </div>
    </div>
</#list>
</div>
</@c.page>