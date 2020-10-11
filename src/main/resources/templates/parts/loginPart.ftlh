
<#import "/spring.ftl" as spring/>
<#macro login path isRegisterForm>

    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><@spring.message "loginPart.login"/></label>
            <div class="col-sm-5">
                <input type="text" name="username"
                       value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid','')}"
                       placeholder=<@spring.message "loginPart.yourLogin"/>/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>

            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <@spring.message "loginPart.pass"/></label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"

                       placeholder=<@spring.message "loginPart.yourPass"/>/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>


        </div>

        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><@spring.message "loginPart.passConf"/></label>
                <div class="col-sm-5">
                    <input type="password" name="password2" class="form-control ${(password2Error??)?string('is-invalid','')}"
                           placeholder=<@spring.message "loginPart.yourPassConf"/>/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary"><#if isRegisterForm><@spring.message "loginPart.createUser"/><#else><@spring.message "loginPart.signInUser"/></#if></button>
        <#if !isRegisterForm><a href="/registration"><@spring.message "loginPart.linkToRegisterForm"/></a></#if>

    </form>
</#macro>

<#macro logout >

    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary"><@spring.message "loginPart.signOut"/></button>
    </form>
</#macro>