<script type="text/javascript" src="/static/zkList.js">

</script>



<p>/dubbo!</p>
<table>
<#list zkDates.childNode as child>
    <tr>
        <td>
            <span>${child.path}</span>
            <td><span>连接</span></td>
        </td>
        <td>
            <table>
                <#list child.childNode as child1>
                    <tr>
                        <td><span>${child1.path}</span> </td>
                        <td>
                            <table>
                                <#if child1.childNode ??>
                                  <#list child1.childNode as child2>
                                    <tr>
                                        <td><span> ${child2.path}</span></td>
                                    </tr>
                                  </#list>
                                </#if>

                            </table>
                        </td>
                    </tr>
                </#list>
            </table>
        </td>
    </tr>
</#list>
</table>