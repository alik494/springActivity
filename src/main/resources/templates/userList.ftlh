<#import "parts/common.ftlh" as c>
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
    </tr>
    </thead>
    <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
<#--            <td>${user.active}</td>-->
            <td><#list user.roles as role > ${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit</a></td>
        </tr>

    </#list>


</table>

</@c.page>