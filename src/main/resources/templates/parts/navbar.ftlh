<#include "security.ftlh">

<#import "loginPart.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Spring Activity</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Мой кабинет <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout<span class="sr-only">(current)</span></a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/adminCab">лист юзеров</a>
            </li>
            </#if>
        </ul>
    </div>
    <div class="navbar-text mr-3">${name}</div>
    <@l.logout />
</nav>