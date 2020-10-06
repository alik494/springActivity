<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>

<@c.page>

    <@l.logout/>


<#--    <form action="/userCab"  method="post" >-->
<#--        <input type="text" name="username" value="${user.username}">-->
<#--      -->
<#--        <input type="hidden" value="${user.id}" name="userId" />-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--        <button type="submit">изменить</button>-->
<#--    </form>-->

    <div>
        <form method="post" action="addAct">
            <input type="text" name="text" placeholder="введите текст">
            <input type="text" name="tag" placeholder="Тег">
            <input type="text" name="additionalUser" placeholder="userNames">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <form method="post" action="showNotActiveActivities">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Посмотреть не активные</button>
    </form>
    <table>
        <tr>
            <th>id</th>
            <th>text</th>
            <th>tag</th>
            <th>time</th>
            <th>usernames</th>
        </tr>
        <#list activities as activity>
            <tr>
                <td>                ${activity.id}            </td>
                <td>                ${activity.text}            </td>
                <td>                ${activity.tag}            </td>
                <td>                ${activity.time}            </td>
                <td><#list activity.users as user > ${user.username}<#sep>, </#list></td>
                <td>
                    <form method="post" action="sendTime">
                        <input type="text" name="time" placeholder="введите текст">
                        <input type="hidden" value=" ${activity.id}  " name="activityId">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit">потрченое время</button>
                    </form>
                </td>
            </tr>
        <#else >
            no rec
        </#list>
    </table>
</@c.page>