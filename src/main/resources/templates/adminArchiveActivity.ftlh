<#import "parts/common.ftlh" as c>
<#import "parts/activPart.ftlh" as a>
<#import "parts/pager.ftlh" as p>
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
    <@p.pager url page/>
        <table>
            <tr>
                <th>id</th>
                <th>text</th>
                <th>usernames</th>
                <th>tag</th>
                <th> time</th>
            </tr>
            <#list page.content as activity>
                <tr>
                    <td>                ${activity.id}            </td>
                    <td>                ${activity.text}            </td>
                    <td><#list activity.users as user > ${user.username}<#sep>, </#list></td>

                    <td>                ${activity.tag}            </td>
                    <td>                ${activity.time}            </td>
                </tr>
            <#else >
                no rec
            </#list>
        </table>
    <@p.pager url page/>
</@c.page>