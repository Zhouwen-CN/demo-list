<h5>自定义变量指令</h5>
定义一个变量: <#assign msg='hello world'>
${msg}<br>
定义多个变量: <#assign score=60 names=['张三','李四','王五']>
${score} - ${names?join(',')}<br>
<hr>

<h5>条件判断指定</h5>
<#if score lt 60>
    你个小渣渣
<#elseif score ==60>
    刚刚及格
<#elseif score lt 80>
    那你含棒棒
<#else >
    你真是个小天才
</#if>
<hr>

<h5>列表指令</h5>
列表不为null才进行遍历: <#if users??>
    <#list users as user>
        ${user}
    </#list>
</#if><br>

<#assign users=[]>
列表为空给出指定内容: <#list users as user>
${user}
<#else >
    列表为空
</#list><br>
<hr>

<h5>自定义指令宏</h5>
<#macro address>
<#--可以使用html标签-->
    © 1999–2015 The FreeMarker Project. All rights reserved.
</#macro>
使用自定义指令: <@address></@address> <br>
<#macro getUserByName name>
<#--可以传递多个参数-->
    通过用户名查询用户 - ${name}
</#macro>
带参数的自定义指令: <@getUserByName name='张三'></@getUserByName><br>
<#macro cfb>
    <#list 1..9 as x>
        <#list 1..x as y>
            ${x} * ${y} = ${x*y} &nbsp;&nbsp;
        </#list>
        <br>
    </#list>
</#macro>
自定义指令中包含内置指令: <br>
<@cfb></@cfb> <br>
<#macro text>
    <#nested >
    这是一段文本
    <#nested >
</#macro>
占位指令: <@text>xxxxxxxx</@text>


<hr>