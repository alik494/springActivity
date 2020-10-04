<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>
<@c.page>
   <div>
       <@l.logout/>

   </div>
    <span><a href="/user">user List</a> </span>

    <div>
        <form method="post" action="addAct">
            <input type="text" name="text" placeholder="введите текст">
            <input type="text" name="tag" placeholder="Тег">
            <input type="text" name="additionalUser" placeholder="userNames">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <form method="get" action="/main">
        <input type="text" name="filter" placeholder="введите текст">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button type="submit">найти по тегу</button>
    </form>

    <form method="post" action="filterByUser">
        <input type="text" name="userName" placeholder="введите текст">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">найти по юзеру</button>
    </form>

    <div>Список Активностей</div>
    <table>
        <tr>
            <th>id</th>
            <th>text</th>
            <th>tag</th>
            <th>usernames</th>
        </tr>
        <#list activities as activity>
            <tr>
                <td>                ${activity.id}            </td>
                <td>                ${activity.text}            </td>
                <td>                ${activity.tag}            </td>
                <td><#list activity.users as user > ${user.username}<#sep>, </#list></td>
            </tr>
        <#else >
            no rec
        </#list>
    </table>
</@c.page>