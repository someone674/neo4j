<ul>
<#list users as u>
    <li>
    ${u.name} --- ${u.age} -- ${u.createDate}<br/>
        <#list u.friends as friend> FRIEND ===> ${friend.name}</#list>
        <#list u.findingType as finding> finding : ${finding}</#list>


    </li>
</#list>
    ${show}
</ul>

