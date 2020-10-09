<#macro userAct isShowNotAct>
    <table>
        <tr>
            <th>id</th>
            <th>text</th>
            <th>tag</th>
            <th>usernames</th>
        </tr>
        <#list activities as activity>
            <tr>
                <td>                ${activity.id}            </td>
                <td>                ${activity.text}            </td>
                <td>                ${activity.tag}            </td>
                <td><#list activity.users as user > ${user.username}<#sep>, </#list></td>
                <#if isShowNotAct>
                    <td>
                        <form method="post" action="sendTime">
                            <input type="text" name="time" placeholder="потраченое время">
                            <input type="hidden" value=" ${activity.id}  " name="activityId">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit">Завершить активность</button>
                        </form>
                    </td>
                </#if>
            </tr>
        <#else >
            no rec
        </#list>
    </table>

</#macro>


<#macro adminAct isShowPostButtom>
    <table>
        <#list activities as activity>
            <tr>
                <th>id</th>
                <th>text</th>
                <th>usernames</th>
                <th>tag</th>
                <#if !isShowPostButtom>
                    <th>               time       </th>
                </#if>
            </tr>
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
                            <input type="text" name="additionalUsername" placeholder="введите доп юзера">
                            <input type="hidden" value=" ${activity.id}  " name="activityId">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </td>
                        <td>
                            <button type="submit">активировать активность</button>
                        </td>
                    </form>
                </#if>
            </tr>

        <#else >
            no rec
        </#list>
    </table>

</#macro>