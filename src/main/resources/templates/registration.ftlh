<#import "parts/loginPart.ftlh" as l>
<#import "parts/common.ftlh" as c>
<@c.page>
<div class="mb-1" >

    ${messages}
</div>
    <@l.login "/registration" false/>
</@c.page>
