<span>method</span>


<table>
<#list methods as method>
    <tr>
        <td>${method.name}</td>
        <td>
            <table>
                <#list method.parameterTypes as parameterType>
                    <tr>
                        <td>${parameterType}</td>
                        <td>
                            <form>
                                
                            </form>
                        </td>
                    </tr>
                </#list>
            </table>
        </td>
    </tr>
</#list>
</table>

