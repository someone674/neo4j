<ul>
<#list users as u>
    <li>
    ${u.name} --- ${u.age} -- ${u.createDate}<br/>
        <#list u.frends as friend> FRIEND ===> ${friend.name}</#list>
    </li>
</#list>
</ul>

