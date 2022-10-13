<!DOCTYPE html>
<html>
<head>
    <title>数据类型</title>
</head>
<body>
<#--Boolean类型直接输出会报错-->
<h5>布尔类型</h5>
${flag?c}<br>
${flag?string('yes','no')}<br>
<hr>

<#--日期类型也不能直接输出-->
<h5>日期类型</h5>
${date?date}<br>
${date?time}<br>
${date?datetime}<br>
${date?string('yyyy-MM-dd HH:mm:ss')}<br>
<hr>

<h5>数值类型</h5>
用来测试的3个数字: <#list numbers as number>${number}&nbsp;&nbsp;</#list><br>
${numbers[0]}<br>
货币类型: ${numbers[1]?string.currency}<br>
百分比类型: ${numbers[2]?string.percent}<br>
保留小数位: ${numbers[2]?string['0.##']}<br>
<hr>

<h5>字符串类型</h5>
用来测试的两个字符串: <#list messages as message>${message}&nbsp;&nbsp;</#list><br>
字符串截取: ${messages[0]?substring(0,2)}<br>
首字母转大写: ${messages[0]?cap_first}<br>
首字母转小写: ${messages[1]?uncap_first}<br>
全部转大写: ${messages[0]?upper_case}<br>
全部转小写: ${messages[1]?lower_case}<br>
长度: ${messages[0]?length}<br>
以某某开头: ${messages[0]?starts_with('h')?c}<br>
以某某结尾: ${messages[0]?ends_with('h')?c}<br>
查找指定字符所在下标: ${messages[0]?index_of('l')}<br>
去除空格: ${messages[0]?trim}<br>
替换指定字符: ${messages[0]?replace('he','we')}<br>
<#--不存在的变量也会报错-->
空值处理,通过!来避免null值报错: ${exists!'不存在的值'}<br>
判断是否为空值: ${(exists??)?c}<br>
<hr>

<h5>序列类型</h5>
<#list stars as star>
    下标: ${star?index} - 姓名: ${star}<br>
</#list>
获取序列长度: ${stars?size}<br>
获取第一个元素: ${stars?first}<br>
获取最后一个元素: ${stars?last}<br>
<br>
list操作: <#list cities as city>
${city} -
</#list><br>
反转列表: <#list cities?reverse as city>
${city} -
</#list><br>
升序输出: <#list cities?sort as city>
${city} -
</#list><br>
倒序输出: <#list cities?sort?reverse as city>
${city} -
</#list><br>
<br>
javaBean操作: <br><#list users as user>
    编号: ${user.getId()} - 姓名: ${user.getName()} - 年龄: ${user.getAge()}<br>
</#list>
<hr>


<h5>哈希类型</h5>
获取keys: <#list map?keys as key>
${key} - ${map[key]} &nbsp;&nbsp;
</#list><br>
获取values: <#list map?values as val>
${val}&nbsp;&nbsp;
</#list><br>
<hr>
</body>
</html>