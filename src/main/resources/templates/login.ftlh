<#include "parts/security.ftlh">
<#import "parts/loginPart.ftlh" as l>
<#import "parts/common.ftlh" as c>

<#import "/spring.ftl" as spring/>
<@c.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="alert alert-danger" role="alert">
         ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
    </#if>
    <@l.login "/login" false/>
</@c.page>
