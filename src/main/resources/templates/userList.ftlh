<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>

<#import "/spring.ftl" as spring/>
<@c.page>

    <div> <@spring.message "nav.listUsers"/></div>
    <table>
        <thead>
        <tr>
            <th> <@spring.message "activPart.id"/></th>
            <th><@spring.message "userList.name"/></th>
            <th><@spring.message "userList.pass"/></th>
            <th><@spring.message "userList.role"/></th>
            <th><@spring.message "userList.LinkToEdit"/></th>
            <th><@spring.message "userList.active"/></th>
        </tr>
        </thead>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td><#list user.roles as role > ${role}<#sep>, </#list></td>
                <td><a href="/adminCab/user${user.id}">
                        <@spring.message "userList.edit"/>
                    </a></td>
                <td>${user.active?then('true', 'false')}</td>
            </tr>

        </#list>


    </table>

</@c.page>