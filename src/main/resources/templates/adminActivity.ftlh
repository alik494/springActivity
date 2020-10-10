<#import "parts/common.ftlh" as c>
<#import "parts/activPart.ftlh" as a>
<@c.page>


    <form method="get" action="/adminCab/activities">
        <input type="text" name="filterByTag" placeholder="введите текст">
        <button type="submit">найти по тегу</button>
    </form>

    <form method="get" action="/adminCab/activities">
        <input type="text" name="filterByUsername" placeholder="введите текст">
        <button type="submit">найти по юзеру</button>
    </form>

    <div>Список Активностей</div>

    <@a.adminAct true/>
</@c.page>