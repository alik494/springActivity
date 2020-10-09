<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>
<#import "parts/activPart.ftlh" as a>

<@c.page>
    <div>
        <form method="post" action="addAct">
            <input type="text" name="text" placeholder="введите текст">
            <input type="text" name="tag" placeholder="Тег">
            <input type="text" name="additionalUser" placeholder="userNames">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <form method="get" action="showNotActiveActivities">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Посмотреть не активные</button>
    </form>

    <@a.userAct false/>


</@c.page>