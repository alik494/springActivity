<#import "parts/common.ftlh" as c>
<#import "parts/activPart.ftlh" as a>
<@c.page>


    <form method="get" action="/adminCab/archiveActivities">
        <input type="text" name="filterByTag" placeholder="введите tag">
        <button type="submit">найти по тегу</button>
    </form>

    <form method="get" action="/adminCab/archiveActivities">
        <input type="text" name="filterByUsername" placeholder="введите текст">
        <button type="submit">найти по юзеру</button>
    </form>

    <div>Список Активностей</div>

    <@a.adminAct false/>
</@c.page>