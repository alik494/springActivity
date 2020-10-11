<#import "parts/common.ftlh" as c>
<#import "parts/activPart.ftlh" as a>
<#import "parts/pager.ftlh" as p>

<#import "/spring.ftl" as spring/>
<@c.page>
    <form method="get" action="/adminCab/archiveActivities">
        <input type="text" name="filterByTag" placeholder=<@spring.message "adminForm.filterByTag"/>>
        <button type="submit"><@spring.message "adminForm.findByTag"/></button>
    </form>

    <form method="get" action="/adminCab/archiveActivities">
        <input type="text" name="filterByUsername" placeholder=<@spring.message "adminForm.filterByUsername"/>>
        <button type="submit"><@spring.message "adminForm.filterByUsername"/></button>
    </form>

    <div><@spring.message "adminForm.listOfActivities"/></div>
    <@p.pager url page/>
        <table>
            <tr>

                <th><@spring.message "activPart.id"/></th>
                <th><a href="/adminCab/archiveActivities/sortByText"><@spring.message "activPart.text"/></a></th>
                <th><@spring.message "activPart.usernames"/></th>
                <th><a href="/adminCab/archiveActivities/sortByTag"><@spring.message "activPart.tag"/></a></th>
                <th> <@spring.message "activPart.time"/></th>
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
            <@spring.message "activPart.noRec"/>
            </#list>
        </table>
    <@p.pager url page/>
</@c.page>