
<#import "/spring.ftl" as spring/>
<#macro userAct isShowNotAct>


    <table>
        <tr>
            <th><@spring.message "activPart.id"/></th>
            <th><@spring.message "activPart.text"/></th>
            <th><@spring.message "activPart.tag"/></th>
            <th><@spring.message "activPart.usernames"/></th>
        </tr>


        <#list activities as activitySendTime>
            <tr>
                <td>                ${activitySendTime.id}            </td>
                <td>                ${activitySendTime.text}            </td>
                <td>                ${activitySendTime.tag}            </td>
                <td><#list activitySendTime.users as user > ${user.username}<#sep>, </#list></td>
                <#if isShowNotAct>
                    <td>
                        <form method="post" action="sendTime">

                            <input type="datetime" class="form-control ${(timeError??)?string('is-invalid','')}"
                                   name="time" placeholder=<@spring.message "activPart.wastedTime"/>>
                            <#if timeError??>
                                <div class="invalid-feedback">
                                   ${timeError}
                                </div>
                            </#if>
                            <input type="hidden" value=" ${activitySendTime.id}  " name="activityId">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit"><@spring.message "activPart.endActivity"/></button>
                        </form>
                    </td>
                </#if>
            </tr>
        <#else >
            <@spring.message "activPart.noRec"/>
        </#list>
    </table>
</#macro>


<#macro adminAct isShowPostButtom>

    <table>
        <tr>
            <th><@spring.message "activPart.id"/></th>
            <th><@spring.message "activPart.text"/></th>
            <th><@spring.message "activPart.usernames"/></th>
            <th><@spring.message "activPart.tag"/></th>
            <#if !isShowPostButtom>
                <th><@spring.message "activPart.time"/></th>
            </#if>
        </tr>
        <#list activities as activity>

            <tr>
                <td>                ${activity.id}            </td>
                <td>                ${activity.text}            </td>
                <td><#list activity.users as user > ${user.username}<#sep>, </#list></td>
                 <#if !isShowPostButtom>
                     <td>                ${activity.tag}            </td>
                     <td>                ${activity.time}            </td>
                 </#if>
                <#if isShowPostButtom>
                    <form method="post" action="activateActivity">
                        <td><input type="text" name="editTagAct" placeholder=${activity.tag}></td>
                        <td>
                            <input type="text" name="additionalUsername" placeholder=<@spring.message "activPart.addAdditionalUser"/>>
                            <input type="hidden" value=" ${activity.id}  " name="activityId">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </td>
                        <td>
                            <button type="submit"><@spring.message "activPart.activateAct"/></button>
                        </td>
                    </form>
                </#if>
            </tr>

        <#else >
            <@spring.message "activPart.noRec"/>
        </#list>
    </table>

</#macro>

<#macro userUtil>
    <div>
        <form method="post" action="addAct">
            <input type="text" class="form-control ${(textError??)?string('is-invalid','')}"
                   value="<#if activity?? >${activity.text}</#if>" name="text" placeholder= <@spring.message "activPart.nameText"/>>
            <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
            </#if>
            <input type="text" class="form-control  ${(tagError??)?string('is-invalid','')}"
                   value="<#if activity?? >${activity.tag}</#if>" name="tag" placeholder=<@spring.message "activPart.nameTag"/>>
            <#if tagError??>
                <div class="invalid-feedback">
                    ${tagError}
                </div>
            </#if>
            <input type="text" name="additionalUser" placeholder=<@spring.message "activPart.addAdditionalUser"/>>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit"><@spring.message "activPart.addActivity"/></button>
        </form>
    </div>
    <form method="get" action="showNotActiveActivities">
        <button type="submit"><@spring.message "activPart.showNotActiveActivities"/></button>
    </form>
</#macro>