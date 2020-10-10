<#import "parts/common.ftlh" as c>
<#import "parts/activPart.ftlh" as a>
<@c.page>


    <form method="get" action="/adminCab/activities">
        <input type="text" name="filterByTag" placeholder="введите текст">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">найти по тегу</button>
    </form>

    <form method="get" action="/main">
        <input type="text" name="userName" placeholder="введите текст">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">найти по юзеру</button>
    </form>

    <div>Список Активностей</div>

    <@a.adminAct true/>
</@c.page>