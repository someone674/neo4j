<ul>
<#list users as u>
    <li>
    ${u.value} --- ${u.age} -- ${u.createDate}<br/>
        <#list u.friends as friend> FRIEND ===> ${friend.value}</#list>
    </li>
</#list>
    ${show}
</ul>

