<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>
<@c.page>

    <div>Список Users</div>
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>pass</th>
            <th>role</th>
            <th>link to edit</th>
            <th>active</th>
        </tr>
        </thead>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td><#list user.roles as role > ${role}<#sep>, </#list></td>
                <td><a href="/adminCab/user${user.id}">edit</a></td>
                <td>${user.active?then('true', 'false')}</td>
            </tr>

        </#list>


    </table>

</@c.page>