<#import "parts/common.ftlh" as c>
<@c.page>
    <form action="/adminCab"  method="post" >
        <input type="text" name="username" value="${user.username}">
        <#list roles as role>

            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId" />

        <div class="form-group col-md-4">
            <label for="inputState">State</label>
            <select name="inputState" id="inputState" class="form-control">
                <option selected>${user.active?then('true', 'false')}</option>
                <option>${user.active?then('false', 'true')}</option>
            </select>
        </div>


        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">изменить</button>
    </form>

</@c.page>