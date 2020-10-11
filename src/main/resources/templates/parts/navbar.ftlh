<#include "security.ftlh">
<#import "/spring.ftl" as spring/>
<#import "loginPart.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Spring Activity</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" data-toggle="collapse" href="#collapseExample" role="button"
                   aria-expanded="false"><@spring.message "nav.language"/></a>
                <div class="collapse" id="collapseExample">
                    <a class="nav-link" href="?lang=uk">
                        <img src="/static/img/ua.gif" alt="ua_img">
                    </a>
                    <a class="nav-link" href="?lang=en">
                        <img src="/static/img/en.gif" alt="en_img">
                    </a>
                </div>
            </li>
            <#if !isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/main"><@spring.message "nav.myCab"/></a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/adminCab"><@spring.message "nav.listUsers"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/adminCab/activities"><@spring.message "nav.listActivities"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="/adminCab/archiveActivities"><@spring.message "nav.listArchiveActivities"/></a>
                </li>
            </#if>
        </ul>
    </div>

    <#if name !="unknown">
        <div class="navbar-text mr-3">${name}</div>
        <@l.logout/>
    </#if>

</nav>