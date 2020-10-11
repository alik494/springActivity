<#import "parts/common.ftlh" as c>
<#import "parts/activPart.ftlh" as a>
<#import "parts/pager.ftlh" as p>

<#import "/spring.ftl" as spring/>
<@c.page>
    <form method="get" action="/adminCab/activities">
        <input type="text" name="filterByTag" placeholder=<@spring.message "adminForm.filterByTag"/>>
        <button type="submit"><@spring.message "adminForm.findByTag"/></button>
    </form>

    <form method="get" action="/adminCab/activities">
        <input type="text" name="filterByUsername" placeholder=<@spring.message "adminForm.filterByUsername"/>>
        <button type="submit"><@spring.message "adminForm.findByUsername"/></button>
    </form>

    <div><@spring.message "adminForm.listOfActivities"/></div>
    <@a.adminAct true/>
</@c.page>