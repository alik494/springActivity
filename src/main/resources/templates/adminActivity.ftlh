<#import "parts/common.ftlh" as c>
<#import "parts/activPart.ftlh" as a>
<#import "parts/pager.ftlh" as p>
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
    <@a.adminAct false/>
<#--    <@p.pager url page/>-->
<#--    <table>-->
<#--        <tr>-->
<#--            <th>id</th>-->
<#--            <th>text</th>-->
<#--            <th>usernames</th>-->
<#--            <th>tag</th>-->
<#--        </tr>-->
<#--        <#list page.content as activity>-->

<#--            <tr>-->
<#--                <td>                ${activity.id}            </td>-->
<#--                <td>                ${activity.text}            </td>-->
<#--                <td><#list activity.users as user > ${user.username}<#sep>, </#list></td>-->


<#--                <form method="post" action="activateActivity">-->
<#--                    <td><input type="text" name="editTagAct" placeholder=${activity.tag}></td>-->
<#--                    <td>-->
<#--                        <input type="text" name="additionalUsername" placeholder="введите доп юзера">-->
<#--                        <input type="hidden" value=" ${activity.id}  " name="activityId">-->
<#--                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--                    </td>-->
<#--                    <td>-->
<#--                        <button type="submit">активировать активность</button>-->
<#--                    </td>-->
<#--                </form>-->
<#--           -->
<#--            </tr>-->

<#--        <#else >-->
<#--            no rec-->
<#--        </#list>-->
<#--    </table>-->
<#--    <@p.pager url page/>-->
</@c.page>