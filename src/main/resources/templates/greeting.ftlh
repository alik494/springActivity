<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
    <h2><@spring.message "main.greeting"/></h2>
    <div>Hello,User</div>
    <a href="/main">Main paige</a>
    <a href="/login">login</a>
    <a href="/registration">registration</a>
</@c.page>
